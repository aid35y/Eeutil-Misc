/*
 * Copyright (C) 2012-13 MINHAP, Gobierno de España This program is licensed and may be used,
 * modified and redistributed under the terms of the European Public License (EUPL), either version
 * 1.1 or (at your option) any later version as soon as they are approved by the European
 * Commission. Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * more details. You should have received a copy of the EUPL1.1 license along with this program; if
 * not, you may find it at http://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 */

package es.mpt.dsic.eeutil.misc.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import es.mpt.dsic.eeutil.inside.admin.model.InsideWSException;
import es.mpt.dsic.eeutil.misc.web.util.ComboItem;
import es.mpt.dsic.inside.model.pagination.FilterPageRequest;
import es.mpt.dsic.inside.model.pagination.ResultadoBusqueda;
import es.mpt.dsic.inside.model.procedimiento.ProcedimientoObject;
import es.mpt.dsic.inside.model.unidad.UnidadObject;
import es.mpt.dsic.inside.model.usuario.UsuarioObject;
import es.mpt.dsic.inside.service.usuario.EeutilUsuarioService;
import es.mpt.dsic.inside.ws.service.model.UnidadResponse;
import es.mpt.dsic.loadTables.hibernate.service.impl.UnidadOrganicaServiceImpl;
import es.mpt.dsic.loadTables.model.UnidadOrganica;

@Controller
public class UsuariosController {

  protected static final Log logger = LogFactory.getLog(UsuariosController.class);

  @Autowired
  private EeutilUsuarioService eeutilUsuarioService;

  @Autowired
  private UnidadOrganicaServiceImpl unidadOrganicaService;

  @Autowired
  private MessageSource messageSource;

  private static final int DEFAULT_PAGE = 1;
  private static final int DEFAULT_SIZE = 25;

  @RequestMapping(value = "/usuariosLista", method = RequestMethod.GET)
  public ModelAndView usuariosLista(@RequestParam(value = "app", required = true) String app,
      HttpServletRequest request, Locale locale) {
    ModelAndView retorno = new ModelAndView("usuarios/lista");

    retorno.addObject("app", app);

    String[] option = new String[1];
    option[0] = app;
    retorno.addObject("titulo", messageSource.getMessage("listaUsuarios.title", option, locale));
    return retorno;
  }

  @RequestMapping(value = "/usuariosListaJson", method = RequestMethod.GET)
  public @ResponseBody ResultadoBusqueda<UsuarioObject> usuariosListaJson(
      @RequestParam(value = "app", required = true) String app, HttpServletRequest request,
      Locale locale) {
    ResultadoBusqueda<UsuarioObject> usuarios = new ResultadoBusqueda<UsuarioObject>();
    try {
      String filterString = StringUtils.isNotEmpty(request.getParameter("search[value]"))
          ? request.getParameter("search[value]")
          : "";
      int defaultSize = StringUtils.isNotEmpty(request.getParameter("length"))
          ? Integer.parseInt(request.getParameter("length"))
          : DEFAULT_SIZE;
      FilterPageRequest filter =
          new FilterPageRequest(filterString, DEFAULT_PAGE, defaultSize, null);
      usuarios = eeutilUsuarioService.getUsuarios(app, filter);
    } catch (InsideWSException e) {
      logger.error("Error al obtener usuarios");
    }
    return usuarios;
  }


  @RequestMapping(value = "/usuariosListaFromNif", method = RequestMethod.GET)
  public @ResponseBody UnidadResponse usuariosListaFromNif(@RequestParam String user,
      HttpServletRequest request, Locale locale) {

    UnidadResponse unidadResponse = new UnidadResponse();
    List<UnidadObject> listaUnidades = null;
    try {
      listaUnidades = eeutilUsuarioService.getUsuariosFromNif(user);

    } catch (InsideWSException e) {
      logger.error("Error al obtener la lista de dir3 por usuarios");
    }
    unidadResponse.setData(listaUnidades);
    return unidadResponse;
  }

  /**
   * Recupera las unidades DIR3 vigentes para el autocompletado. Son los de estado en V o T
   * 
   * @param codigo String con el c�digo a completar o parte de �l
   * @return List<ComboItem> lista a mostrar en el autocomplete
   */
  @RequestMapping(value = "/autocomplete/dir3Vigentes", method = RequestMethod.GET)
  public @ResponseBody List<ComboItem> autocompleteCodigoDIRVigentes(
      @RequestParam(value = "term") final String codigo) {

    logger.info("[INI] Entramos en autocompleteCodigoDIRVigentes. ");

    // Se obtienen las unidades que cumplen con la b�squeda
    logger.info("Buscamos las unidades...");
    List<Criterion> criterios = new ArrayList<Criterion>();
    criterios.add(Restrictions.or(Restrictions.like("nombreUnidadOrganica", "%" + codigo + "%"),
        Restrictions.like("codigoUnidadOrganica", "%" + codigo + "%")));
    criterios.add(Restrictions.or(Restrictions.eq("estado", "T"), Restrictions.eq("estado", "V")));

    List<UnidadOrganica> unidades =
        unidadOrganicaService.findByCriterias(0, 10, UnidadOrganica.class, criterios);

    // Se carga la lista a devolver convirtiendo los resultados
    logger.info("Transformamos en tipo SelectIntemVO para mostrar lista de autocompletado...");
    List<ComboItem> listaReturn = new ArrayList<ComboItem>();
    for (UnidadOrganica unidad : unidades) {
      listaReturn.add(
          new ComboItem(unidad.getCodigoUnidadOrganica(), unidad.getNombreUnidadOrganica(), null));
    }

    logger.info(
        "[FIN] Salimos de autocompleteCodigoDIRVigentes. Total a mostrar: " + listaReturn.size());

    return listaReturn;
  }

  /**
   * Recupera la lista de numeros de procedimiento
   * 
   * @param codigo String con el c�digo a completar o parte de �l
   * @return List<ComboItem> lista a mostrar en el autocomplete
   */
  @RequestMapping(value = "/autocomplete/numeroProcedimiento", method = RequestMethod.GET)
  public @ResponseBody List<ComboItem> autocompleteNumeroProcedimiento(
      @RequestParam(value = "term") final String codigo) {

    logger.info("[INI] Entramos en autocompleteNumeroProcedimiento. ");

    // Se obtienen los n�mero de procedimiento
    logger.info("Buscamos los numeros de procedimiento...");

    List<ComboItem> listaReturn = new ArrayList<ComboItem>();
    try {
      List<ProcedimientoObject> numeroProcedimientoList =
          eeutilUsuarioService.getListaProcedimientos(codigo);

      // Se carga la lista a devolver convirtiendo los resultados
      logger.info("Transformamos en tipo SelectIntemVO para mostrar lista de autocompletado...");
      if (numeroProcedimientoList != null) {
        for (ProcedimientoObject procedimiento : numeroProcedimientoList) {
          if (procedimiento.getNumeroProcedimiento().contains(codigo)) {
            listaReturn.add(new ComboItem(procedimiento.getNumeroProcedimiento(),
                procedimiento.getNumeroProcedimiento(), null));
          }
        }
      }
    } catch (InsideWSException e) {
      logger.error("Error al recuperar la lista de procedimientos");
    }
    logger.info(
        "[FIN] Salimos de autocompleteNumeroProcedimiento. Total a mostrar: " + listaReturn.size());

    return listaReturn;
  }

  @RequestMapping(value = "/borrarUsuario", method = RequestMethod.POST)
  public ModelAndView borrarUsuario(HttpServletRequest request, Locale locale) {
    String app = request.getParameter("app");
    try {
      // damos de baja el usuario
      UsuarioObject usuario = new UsuarioObject();
      usuario.setIdentificador(request.getParameter("identificador"));
      usuario = eeutilUsuarioService.bajaUsuario(app, usuario);
    } catch (InsideWSException e) {
      logger.error("Error al eliminar usuario");
    }
    return usuariosLista(app, request, locale);
  }

  @RequestMapping(value = "/altaUsuario", method = RequestMethod.POST)
  public ModelAndView altaUsuario(HttpServletRequest request, Locale locale) {
    String app = request.getParameter("app");
    try {
      // damos de baja el usuario
      UsuarioObject usuario = new UsuarioObject();
      usuario.setIdentificador(request.getParameter("identificador"));
      usuario.setCodigoUnidadOrganica(request.getParameter("codigoUnidadOrganica"));
      if (StringUtils.isNotEmpty(request.getParameter("numeroProcedimiento"))) {
        usuario.setNumeroProcedimiento(request.getParameter("numeroProcedimiento"));
      }

      // Por defecto mandamos role 0 que es role_user en tabla insiderole del esquema inside
      String role = "0";
      if (StringUtils.isNotEmpty(request.getParameter("altaUsuarioRole"))) {
        role = request.getParameter("altaUsuarioRole");
      }

      usuario = eeutilUsuarioService.altaUsuario(app, usuario, role);
    } catch (InsideWSException e) {
      logger.error("Error al crear usuario");
    }
    return usuariosLista(app, request, locale);
  }

  @RequestMapping(value = "/modificacionActivo", method = RequestMethod.POST)
  public ModelAndView modificacionActivo(HttpServletRequest request, Locale locale) {
    String app = request.getParameter("app");
    try {
      // damos de baja el usuario
      UsuarioObject usuario = new UsuarioObject();
      usuario.setIdentificador(request.getParameter("identificador"));
      usuario.setCodigoUnidadOrganica(request.getParameter("codigoUnidadOrganica"));
      usuario.setActivo(Boolean.parseBoolean(request.getParameter("activo")));
      if (StringUtils.isNotEmpty(request.getParameter("numeroProcedimiento"))) {
        usuario.setNumeroProcedimiento(request.getParameter("numeroProcedimiento"));
      }
      usuario = eeutilUsuarioService.altaUsuario(app, usuario);
    } catch (InsideWSException e) {
      logger.error("Error al crear usuario");
    }
    return usuariosLista(app, request, locale);
  }

  @RequestMapping(value = "/borrarUnidadUsuario", method = RequestMethod.POST)
  public ModelAndView borrarUnidadUsuario(HttpServletRequest request, Locale locale) {
    logger.info("[INI] Entramos en borrarUnidadUsuario. ");
    String app = request.getParameter("app");
    try {
      String codigo = request.getParameter("codigoUnidadOrganica");
      String numeroProcedimiento = request.getParameter("numeroProcedimiento");
      String nif = request.getParameter("identificador");

      eeutilUsuarioService.borrarUnidadUsuario(nif, codigo, numeroProcedimiento);

      logger.info("[FIN] Salimos de borrarUnidadUsuario: ");
    } catch (InsideWSException e) {
      logger.error("Error al recuperar la lista de procedimientos");
    }
    return usuariosLista(app, request, locale);
  }

}
