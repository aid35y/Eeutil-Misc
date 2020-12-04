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

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import es.mpt.dsic.eeutil.misc.web.util.MessageObject;
import es.mpt.dsic.eeutil.misc.web.util.WebConstants;
import es.mpt.dsic.inside.convert.ConvertDataEeutil;
import es.mpt.dsic.inside.dao.EeutilDao;
import es.mpt.dsic.inside.model.EeutilAplicacionOperacion;
import es.mpt.dsic.inside.model.aplicacionPeticion.AplicacionOperacionObject;
import es.mpt.dsic.inside.model.peticion.PeticionObject;
import es.mpt.dsic.inside.security.service.AdministrationService;
import es.mpt.dsic.inside.ws.service.model.PeticionesResponse;
import es.mpt.dsic.loadTables.controller.UnidadOrganicaController;

@Controller
@RequestMapping("administracion")
public class AdminController {

  protected static final Log logger = LogFactory.getLog(AdminController.class);

  @Autowired
  private EeutilDao eeutilDao;

  @Autowired
  AdministrationService administrationService;

  @Autowired
  private MessageSource messageSource;

  @Autowired
  UnidadOrganicaController unidadOrganicaController;

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "contadorPeticiones", method = RequestMethod.GET)
  public ModelAndView contadorPeticiones() {
    ModelAndView retorno = new ModelAndView("administracion/contadorPeticiones");

    List<EeutilAplicacionOperacion> data = eeutilDao.getAllObjetos(EeutilAplicacionOperacion.class);

    retorno.addObject("datos", ConvertDataEeutil.converDataEeutilAplicacionPeticion(data));
    return retorno;
  }

  @SuppressWarnings("unchecked")
  @RequestMapping(value = "capturarPeticiones", method = RequestMethod.GET)
  public ModelAndView capturarPeticiones() {
    ModelAndView retorno = new ModelAndView("administracion/capturarPeticiones");

    List<EeutilAplicacionOperacion> data = eeutilDao.getAllObjetos(EeutilAplicacionOperacion.class);

    retorno.addObject("datos", ConvertDataEeutil.converDataEeutilAplicacionPeticion(data));
    return retorno;
  }

  @RequestMapping(value = "/modificarCaptura", method = RequestMethod.POST)
  public ModelAndView modificarCaptura(HttpServletRequest request) {
    String aplicacion = request.getParameter("aplicacion");
    String operacion = request.getParameter("operacion");
    boolean capturar = Boolean.valueOf(request.getParameter("capturar"));

    AplicacionOperacionObject appOperacion =
        new AplicacionOperacionObject(aplicacion, operacion, null, capturar);

    // Modificamos el registro de aplicacionOperacion
    administrationService.modificarAplicacionOperacion(appOperacion);

    return capturarPeticiones();
  }

  @RequestMapping(value = "/eliminarTodasPeticiones", method = RequestMethod.POST)
  public ModelAndView eliminarTodasPeticiones() {

    // eliminamos todas las peticiones recibidas
    administrationService.eliminarTodasPeticiones();

    return capturarPeticiones();
  }

  @RequestMapping(value = "/eliminarPeticion", method = RequestMethod.POST)
  public ModelAndView eliminarPeticion(HttpServletRequest request) {
    String aplicacion = request.getParameter("aplicacion");
    String operacion = request.getParameter("operacion");

    PeticionObject peticion = new PeticionObject(aplicacion, operacion, null, null);

    // eliminamos los registros de las peticiones de esa aplicacion-operacion
    administrationService.eliminarPeticion(peticion);

    return capturarPeticiones();
  }

  @RequestMapping(value = "/consultarPeticiones", method = RequestMethod.GET)
  @ResponseBody
  public PeticionesResponse consultarPeticiones(@RequestParam String aplicacion,
      @RequestParam String operacion) {
    PeticionesResponse response = new PeticionesResponse();
    PeticionObject peticion = new PeticionObject(aplicacion, operacion, null, null);
    List<PeticionObject> peticiones = administrationService.consultarPeticiones(peticion);
    response.setData(peticiones);
    return response;
  }

  @RequestMapping(value = "/descargarPeticion", method = RequestMethod.POST)
  public ModelAndView descargarDocumentoEni(HttpServletRequest request,
      HttpServletResponse response) {
    OutputStream pr = null;
    try {
      String aplicacion = request.getParameter("aplicacion");
      String operacion = request.getParameter("operacion");
      Date fecha = new Date(Long.valueOf(request.getParameter("fecha")));

      pr = response.getOutputStream();
      response.setContentType("application/text");
      SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH:mm:ss");
      String fechaString = sdf.format(fecha);
      response.addHeader("Content-Disposition",
          "attachment; filename=\"" + aplicacion + "_" + operacion + "_" + fechaString + ".txt\"");

      PeticionObject peticion = new PeticionObject(aplicacion, operacion, fecha, null);

      byte[] file = administrationService.descargarPeticion(peticion);

      pr.write(file);
      pr.close();

      return null;
    } catch (IOException | RuntimeException e) {
      try {
        logger.error("Error al descargar la peticion:", e);
        if (pr != null) {
          pr.close();
        }
        return null;
      } catch (IOException e1) {
        logger.error("Error al descargar la peticion:", e1);
        return null;
      }
    }

  }

  @RequestMapping(value = "/actualizarDIR3", method = RequestMethod.GET)
  public ModelAndView actualizarDIR3(HttpServletRequest request, HttpServletResponse response,
      Locale locale) {
    ModelAndView retorno = new ModelAndView("principal");

    unidadOrganicaController.loadUnidadOrganica();

    MessageObject mensaje = new MessageObject(WebConstants.MESSAGE_LEVEL_SUCCESS,
        messageSource.getMessage("administracion.actualizarDIR3", null, locale));
    retorno.addObject("mensajeUsuario", mensaje);

    return retorno;
  }
}
