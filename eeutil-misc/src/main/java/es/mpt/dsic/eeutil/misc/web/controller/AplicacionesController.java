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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
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
import org.springframework.web.servlet.ModelAndView;
import es.mpt.dsic.csvstorage.admin.model.CSVStorageException;
import es.mpt.dsic.csvstorage.admin.model.CSVStorageException_Exception;
import es.mpt.dsic.eeutil.csvbroker.admin.model.CSVBrokerException;
import es.mpt.dsic.eeutil.inside.admin.model.InsideWSException;
import es.mpt.dsic.eeutil.misc.web.util.MessageObject;
import es.mpt.dsic.eeutil.misc.web.util.WebConstants;
import es.mpt.dsic.inside.dao.EeutilDao;
import es.mpt.dsic.inside.model.EeutilAplicacion;
import es.mpt.dsic.inside.model.aplicacion.AplicacionObject;
import es.mpt.dsic.inside.service.aplicacion.EeutilAplicacionService;
import es.mpt.dsic.loadTables.model.UnidadOrganica;

@Controller
public class AplicacionesController {

  protected static final Log logger = LogFactory.getLog(AplicacionesController.class);

  @Autowired
  private EeutilAplicacionService eeutilAplicacionService;

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private EeutilDao eeutilDao;

  @RequestMapping(value = "/aplicacionesLista", method = RequestMethod.GET)
  public ModelAndView aplicacionesLista(@RequestParam(value = "app", required = true) String app,
      HttpServletRequest request, Locale locale) {
    ModelAndView retorno = null;

    if (app.equalsIgnoreCase("Inside")) {
      retorno = new ModelAndView("aplicaciones/listaInside");
    }

    if (app.equalsIgnoreCase("Eeutil")) {
      retorno = new ModelAndView("aplicaciones/lista");
    }

    try {
      retorno.addObject("data", eeutilAplicacionService.getAplicaciones(app));
      retorno.addObject("app", app);

      // para ordenar por key
      Map<String, String> mapa = eeutilAplicacionService.getInfAdicional(app);
      Map<String, String> treeMap = new TreeMap<String, String>(mapa);

      retorno.addObject("aditionalData", treeMap);

      String[] option = new String[1];
      option[0] = app;
      retorno.addObject("titulo",
          messageSource.getMessage("listaAplicaciones.title", option, locale));

    } catch (InsideWSException e) {
      logger.error("Error al obtener aplicaciones");
    } catch (CSVStorageException e) {
      logger.error("Error al obtener aplicaciones");
    } catch (CSVStorageException_Exception e) {
      logger.error("Error al obtener aplicaciones");
    } catch (CSVBrokerException e) {
      logger.error("Error al obtener aplicaciones");
    }
    return retorno;
  }

  @RequestMapping(value = "/bajaAplicacion", method = RequestMethod.POST)
  public ModelAndView bajaAplicacion(HttpServletRequest request, Locale locale) {
    ModelAndView retorno = null;
    try {
      String app = request.getParameter("app");

      // damos de baja la aplicacion
      AplicacionObject aplicacion = new AplicacionObject();
      aplicacion.setIdentificador(request.getParameter("identificador"));

      EeutilAplicacion apli = (EeutilAplicacion) eeutilDao.getObjeto(EeutilAplicacion.class,
          aplicacion.getIdentificador());
      if (apli.isActiva()) {
        aplicacion = eeutilAplicacionService.desactivar(app, aplicacion);
      } else {
        aplicacion = eeutilAplicacionService.activar(app, aplicacion);
      }

      retorno = aplicacionesLista(app, request, locale);

      String[] option = new String[1];
      option[0] = app;
      retorno.addObject("titulo",
          messageSource.getMessage("listaAplicaciones.title", option, locale));

    } catch (InsideWSException e) {
      logger.error("Error al eliminar aplicacion");
    } catch (CSVStorageException e) {
      logger.error("Error al eliminar aplicacion");
    } catch (CSVBrokerException e) {
      logger.error("Error al eliminar aplicacion");
    }
    return retorno;
  }

  @RequestMapping(value = "/eliminarAplicacion", method = RequestMethod.POST)
  public ModelAndView eliminarAplicacion(HttpServletRequest request, Locale locale) {
    ModelAndView retorno = null;
    String app = request.getParameter("app");
    try {

      // damos de baja la aplicacion
      AplicacionObject aplicacion = new AplicacionObject();
      aplicacion.setIdentificador(request.getParameter("identificador"));
      eeutilAplicacionService.eliminarAplicacion(app, aplicacion, locale);

      retorno = aplicacionesLista(app, request, locale);

      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_SUCCESS,
          messageSource.getMessage("listaAplicaciones.eliminar.ok", null, locale));
      retorno.addObject("mensajeUsuario", mensaje);

    } catch (InsideWSException e) {
      logger.error("Error al eliminar aplicacion");
      retorno = aplicacionesLista(app, request, locale);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.addObject("mensajeUsuario", mensaje);
    } catch (CSVStorageException e) {
      logger.error("Error al eliminar aplicacion");
      retorno = aplicacionesLista(app, request, locale);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.addObject("mensajeUsuario", mensaje);
    } catch (CSVBrokerException e) {
      logger.error("Error al eliminar aplicacion");
      retorno = aplicacionesLista(app, request, locale);
      MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, e.getMessage());
      retorno.addObject("mensajeUsuario", mensaje);
    }

    return retorno;
  }

  @RequestMapping(value = "/altaAplicacion", method = RequestMethod.POST)
  public ModelAndView altaAplicacion(HttpServletRequest request, Locale locale) {
    ModelAndView retorno = null;
    try {
      String app = request.getParameter("app");

      // damos de baja la aplicacion
      AplicacionObject aplicacion = convertToObject(request);

      MessageObject mensaje = null;
      if (aplicacion != null) {
        aplicacion = eeutilAplicacionService.altaAplicacion(app, aplicacion);
        mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_SUCCESS,
            messageSource.getMessage("listaAplicaciones.alta.guardar.ok", null, locale));
      } else {
        mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_WARNING,
            messageSource.getMessage("error.msg.datosNoValidos", null, locale));
      }
      retorno = aplicacionesLista(app, request, locale);
      retorno.addObject("mensajeUsuario", mensaje);

    } catch (InsideWSException e) {
      logger.error("Error crear aplicacion");
    } catch (CSVStorageException e) {
      logger.error("Error crear aplicacion");
    } catch (CSVBrokerException e) {
      logger.error("Error crear aplicacion");
    }
    return retorno;
  }

  private AplicacionObject convertToObject(HttpServletRequest request) {
    if (StringUtils.isEmpty(request.getParameter("identificador"))
        || StringUtils.isEmpty(request.getParameter("email"))
        || StringUtils.isEmpty(request.getParameter("responsable"))
        || StringUtils.isEmpty(request.getParameter("telefono"))
        || StringUtils.isEmpty(request.getParameter("codigoUnidadOrganica"))) {
      return null;
    } else {
      AplicacionObject retorno = new AplicacionObject();
      retorno.setIdentificador(request.getParameter("identificador"));
      retorno.setPassword(request.getParameter("password"));
      retorno.setEmail(request.getParameter("email"));
      retorno.setResponsable(request.getParameter("responsable"));
      retorno.setTelefono(request.getParameter("telefono"));
      if (StringUtils.isNotEmpty(request.getParameter("serialNumberCertificado"))) {
        retorno.setSerialNumberCertificado(request.getParameter("serialNumberCertificado"));
      }
      if (StringUtils.isNotEmpty(request.getParameter("numeroProcedimiento"))) {
        retorno.setNumeroProcedimiento(request.getParameter("numeroProcedimiento"));
      }

      if (StringUtils.isNotEmpty(request.getParameter("aditional"))) {
        StringTokenizer tmpToken = new StringTokenizer(request.getParameter("aditional"), ";;");
        Map<String, String> aditionalData = new HashMap<String, String>();
        while (tmpToken.hasMoreTokens()) {
          String[] datos = tmpToken.nextToken().split("=");
          if (datos.length > 1)
            aditionalData.put(datos[0], datos[1]);
          else
            aditionalData.put(datos[0], "");
        }
        retorno.setAditionalData(aditionalData);
      }
      if (StringUtils.isNotEmpty(request.getParameter("codigoUnidadOrganica"))
          && validateOrgano(request.getParameter("codigoUnidadOrganica"))) {
        retorno.setCodigoUnidadOrganica(request.getParameter("codigoUnidadOrganica"));
      }

      return retorno;
    }
  }

  private boolean validateOrgano(String organo) {
    boolean idValid = false;
    List<Criterion> criterias = new ArrayList<Criterion>();
    criterias.add(Restrictions.eq("codigoUnidadOrganica", organo));
    UnidadOrganica unidadOrganica =
        (UnidadOrganica) eeutilDao.findObjeto(UnidadOrganica.class, criterias);
    if (unidadOrganica != null) {
      idValid = true;
    }
    return idValid;
  }

}
