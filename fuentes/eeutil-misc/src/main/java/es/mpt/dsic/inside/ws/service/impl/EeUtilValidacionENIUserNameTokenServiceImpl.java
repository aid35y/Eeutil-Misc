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

package es.mpt.dsic.inside.ws.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.ws.WebServiceContext;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import com.sun.istack.ByteArrayDataSource;
import es.mpt.dsic.inside.security.wss4j.CredentialUtil;
import es.mpt.dsic.inside.utils.xml.XMLUtil;
import es.mpt.dsic.inside.validacionENI.EeutilEniValidationENIService;
import es.mpt.dsic.inside.ws.service.EeUtilValidacionENIUserNameTokenService;
import es.mpt.dsic.inside.ws.service.codigosRespuestaValidacion.ExpedienteENICodigosRespuestaValidacion;
import es.mpt.dsic.inside.ws.service.codigosRespuestaValidacion.GlobalValidacionENICodigosRespuestaValidacion;
import es.mpt.dsic.inside.ws.service.model.Detalle;
import es.mpt.dsic.inside.ws.service.model.RespuestaValidacionENI;
import es.mpt.dsic.inside.ws.service.model.Validaciones;
import es.mpt.dsic.inside.ws.service.model.pdf.DocumentoEntradaMtom;

@Service("eeUtilValidacionENIUserNameTokenService")
@WebService(
    endpointInterface = "es.mpt.dsic.inside.ws.service.EeUtilValidacionENIUserNameTokenService")
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.BARE, use = Use.LITERAL)
public class EeUtilValidacionENIUserNameTokenServiceImpl
    implements EeUtilValidacionENIUserNameTokenService {

  protected final static Log logger =
      LogFactory.getLog(EeUtilValidacionENIUserNameTokenServiceImpl.class);

  @Autowired
  private EeutilEniValidationENIService eeutilEniValidationENIService;

  @Resource
  private WebServiceContext wsContext;

  @Autowired
  CredentialUtil credentialUtil;

  @Override
  public RespuestaValidacionENI validarFirmaDocumentoENI(byte[] documentoENI) {
    // Lista resultado de las validaciones
    List<Detalle> listaDetalles = new ArrayList<Detalle>();

    // Formatea y generaliza los datos a string xml
    String dataDocumentoXml;
    try {
      dataDocumentoXml = XMLUtil.obtenerDocumentoENIXML(XMLUtil.decodeUTF8(documentoENI));

    } catch (Exception e) {
      RespuestaValidacionENI respuesta = new RespuestaValidacionENI();
      respuesta.setGlobal(GlobalValidacionENICodigosRespuestaValidacion.G_XXX_CODIGO + " - "
          + GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
      respuesta.setDetalle(listaDetalles);
      return respuesta;
    }

    // VALIDACION 3 VALIDACIONFIRMA();
    Detalle detalleFirma = eeutilEniValidationENIService.validarFirmaDocumentoEniFile(
        credentialUtil.getCredentialEeutilUserToken(wsContext), dataDocumentoXml);
    listaDetalles.add(detalleFirma);

    // Construir respuesta global.
    return getRespuestaGlobal(listaDetalles);
  }

  @Override
  public RespuestaValidacionENI validarDocumentoENI(byte[] documentoENI, String version,
      Validaciones validaciones) {

    // Lista resultado de las validaciones
    List<Detalle> listaDetalles = new ArrayList<Detalle>();

    // Formatea y generaliza los datos a string xml
    String dataDocumentoXml;
    try {
      dataDocumentoXml = XMLUtil.obtenerDocumentoENIXML(XMLUtil.decodeUTF8(documentoENI));

    } catch (Exception e) {
      RespuestaValidacionENI respuesta = new RespuestaValidacionENI();
      respuesta.setGlobal(GlobalValidacionENICodigosRespuestaValidacion.G_XXX_CODIGO + " - "
          + GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
      respuesta.setDetalle(listaDetalles);
      return respuesta;
    }

    if (validaciones.isValidaSchema()) {
      // VALIDACION 1 VALIDACIONSHEMA();
      Detalle detalleShemas =
          eeutilEniValidationENIService.validarShemaDocumentoEniFile(dataDocumentoXml, version);
      listaDetalles.add(detalleShemas);
    }

    if (validaciones.isValidaDir3()) {
      // VALIDACION 2 VALIDACIONCODIGOUNIDADDIR3();
      Detalle detalleDir3 =
          eeutilEniValidationENIService.validarUnidadOrganicaDocumentoEniFile(dataDocumentoXml);
      listaDetalles.add(detalleDir3);
    }

    if (validaciones.isValidaFirma()) {
      // VALIDACION 3 VALIDACIONFIRMA();
      Detalle detalleFirma = eeutilEniValidationENIService.validarFirmaDocumentoEniFile(
          credentialUtil.getCredentialEeutilUserToken(wsContext), dataDocumentoXml);
      listaDetalles.add(detalleFirma);
    }

    // Construir respuesta global.
    return getRespuestaGlobal(listaDetalles);
  }

  @Override
  public RespuestaValidacionENI validarExpedienteENI(byte[] expedienteENI, String version,
      Validaciones validaciones) {

    // Lista resultado de las validaciones
    List<Detalle> listaDetalles = new ArrayList<Detalle>();

    String dataExpedienteENIXml;
    String dataExpedienteENIXmlNoProcedenteInside;
    try {
      dataExpedienteENIXml = XMLUtil.obtenerExpedienteENIXML(XMLUtil.decodeUTF8(expedienteENI));
      dataExpedienteENIXmlNoProcedenteInside =
          XMLUtil.obtenerExpedienteENIXMLNoProcedenteInside(XMLUtil.decodeUTF8(expedienteENI));

    } catch (RuntimeException | ParserConfigurationException | SAXException | IOException
        | TransformerFactoryConfigurationError | TransformerException e) {
      RespuestaValidacionENI respuesta = new RespuestaValidacionENI();
      respuesta.setGlobal(GlobalValidacionENICodigosRespuestaValidacion.G_XXX_CODIGO + " - "
          + GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
      respuesta.setDetalle(listaDetalles);
      return respuesta;
    }

    if (validaciones.isValidaSchema()) {
      // VALIDACION 1 VALIDACIONSHEMA
      Detalle detalleShemas = eeutilEniValidationENIService
          .validarShemaExpedienteEniFile(dataExpedienteENIXml, version);
      listaDetalles.add(detalleShemas);
    }

    if (validaciones.isValidaDir3()) {
      // VALIDACION 3 VALIDACIONCODIGOUNIDADDIR3
      Detalle detalleDir3 = eeutilEniValidationENIService
          .validarUnidadOrganicaExpedienteEniFile(dataExpedienteENIXml);
      listaDetalles.add(detalleDir3);
    }

    if (validaciones.isValidaSIA()) {
      // VALIDACION 4 VALIDACIONCODIGOSIA
      Detalle detalleSIA =
          eeutilEniValidationENIService.validarCodigoSIAExpedienteEniFile(dataExpedienteENIXml);
      listaDetalles.add(detalleSIA);
    }

    if (validaciones.isValidaFirma()) {
      // VALIDACION 2 VALIDACIONFIRMAS
      Detalle detalleFirma = eeutilEniValidationENIService.validarFirmaExpedienteEniFile(
          credentialUtil.getCredentialEeutilUserToken(wsContext), dataExpedienteENIXml,
          dataExpedienteENIXmlNoProcedenteInside, expedienteENI);
      listaDetalles.add(detalleFirma);
    }

    // Construir respuesta global.
    return getRespuestaGlobal(listaDetalles);
  }

  @Override
  public RespuestaValidacionENI validarFirmaExpedienteENI(byte[] expedienteENI) {

    // Lista resultado de las validaciones
    List<Detalle> listaDetalles = new ArrayList<Detalle>();

    String dataExpedienteENIXml;
    String dataExpedienteENIXmlNoProcedenteInside;
    try {
      dataExpedienteENIXml = XMLUtil.obtenerExpedienteENIXML(XMLUtil.decodeUTF8(expedienteENI));
      dataExpedienteENIXmlNoProcedenteInside =
          XMLUtil.obtenerExpedienteENIXMLNoProcedenteInside(XMLUtil.decodeUTF8(expedienteENI));
      eeutilEniValidationENIService.setIdentificadorExpedienteXML(dataExpedienteENIXml);

    } catch (Exception e) {
      RespuestaValidacionENI respuesta = new RespuestaValidacionENI();
      respuesta.setGlobal(GlobalValidacionENICodigosRespuestaValidacion.G_XXX_CODIGO + " - "
          + GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
      respuesta.setDetalle(listaDetalles);
      return respuesta;
    }

    // VALIDACION 2 VALIDACIONFIRMAS
    Detalle detalleFirma = eeutilEniValidationENIService.validarFirmaExpedienteEniFile(
        credentialUtil.getCredentialEeutilUserToken(wsContext), dataExpedienteENIXml,
        dataExpedienteENIXmlNoProcedenteInside, expedienteENI);
    listaDetalles.add(detalleFirma);

    // Construir respuesta global.
    return getRespuestaGlobal(listaDetalles);
  }

  /*
   * Si hay algun codigo de error devuelve codigo de error en el global
   */
  private RespuestaValidacionENI getRespuestaGlobal(List<Detalle> listaDetalles) {

    RespuestaValidacionENI respuesta = new RespuestaValidacionENI();
    respuesta.setGlobal(GlobalValidacionENICodigosRespuestaValidacion.G_000_CODIGO + " - "
        + GlobalValidacionENICodigosRespuestaValidacion.G_000_DETALLE);
    respuesta.setDetalle(listaDetalles);

    if (listaDetalles.isEmpty()) {
      Detalle detalleVaciasValidaciones = new Detalle();
      detalleVaciasValidaciones
          .setCodigoRespuesta(GlobalValidacionENICodigosRespuestaValidacion.G_999_CODIGO);
      detalleVaciasValidaciones
          .setDetalleRespuesta(GlobalValidacionENICodigosRespuestaValidacion.G_999_DETALLE);
      detalleVaciasValidaciones.setIdObjeto(
          GlobalValidacionENICodigosRespuestaValidacion.G_ZZZ_ID_OBJETO_ERROR_VALIDACIONES);
      // Se suma un detalle vacio para indicar que no se ha solicitado
      // hacer ninguna validacion
      respuesta.getDetalle().add(detalleVaciasValidaciones);
      respuesta.setGlobal(GlobalValidacionENICodigosRespuestaValidacion.G_999_CODIGO + " - "
          + GlobalValidacionENICodigosRespuestaValidacion.G_999_DETALLE);

    } else {
      for (Detalle detalle : listaDetalles) {

        if (detalle.getCodigoRespuesta().endsWith("999]")) {
          respuesta.setGlobal(GlobalValidacionENICodigosRespuestaValidacion.G_999_CODIGO + " - "
              + GlobalValidacionENICodigosRespuestaValidacion.G_999_DETALLE);
          break;
        }
      }
    }

    return respuesta;
  }

  @Override
  public RespuestaValidacionENI validarExpedienteDocumentosENIZIP(byte[] docZipEntrada,
      String version, Validaciones validacionesExpediente, Validaciones validacionesDocumentos) {
    logger.debug("Inicio validarExpedienteDocumentosENIZIP");
    RespuestaValidacionENI respuestaExpediente = null;
    List<RespuestaValidacionENI> listaRespuestaDocumentos = new ArrayList<RespuestaValidacionENI>();

    try {
      DocumentoEntradaMtom docZipEntradaObj = new DocumentoEntradaMtom();
      ByteArrayDataSource dataSourceExp = new ByteArrayDataSource(docZipEntrada, null);
      docZipEntradaObj.setContenido(new DataHandler(dataSourceExp));
      HashMap<String, List<byte[]>> ficherosExpAndDocs = descomprimirZIP(docZipEntradaObj);
      List<byte[]> listaExpediente = ficherosExpAndDocs.get(XMLUtil.KEY_MAPA_LISTAEXPEDIENTE);

      if (listaExpediente.size() > 1) {
        RespuestaValidacionENI respuesta = new RespuestaValidacionENI();
        // ponemos global a error
        respuesta.setGlobal(GlobalValidacionENICodigosRespuestaValidacion.G_XXX_CODIGO + " - "
            + GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
        return respuesta;
      }

      List<byte[]> listaDocumentos = ficherosExpAndDocs.get(XMLUtil.KEY_MAPA_LISTADOCUMENTO);

      // validamos expediente
      logger.debug("Inicio validarExpedienteENIZIP");
      respuestaExpediente =
          validarExpedienteENIZIP(listaExpediente, version, validacionesExpediente);
      logger.debug("Fin validarExpedienteENIZIP");

      // validamos documento
      logger.debug("Inicio validarDocumentosENIZIP");
      listaRespuestaDocumentos =
          validarDocumentosENIZIP(listaDocumentos, version, validacionesDocumentos);
      logger.debug("Fin validarDocumentosENIZIP");

      // correspondencia docs
      logger.debug("Inicio validarCorrespondenciaExpedienteDocumentosENIZIP");
      RespuestaValidacionENI resultadoCorrespondencia =
          validarCorrespondenciaExpedienteDocumentosENIZIP(listaExpediente, listaDocumentos);
      logger.debug("Fin validarCorrespondenciaExpedienteDocumentosENIZIP");

      logger.debug("Fin validarExpedienteDocumentosENIZIP");
      return getRespuestaGlobalZIP(respuestaExpediente, listaRespuestaDocumentos,
          resultadoCorrespondencia);

    } catch (Exception e) {
      // ponemos global a error
      RespuestaValidacionENI respuesta = new RespuestaValidacionENI();
      respuesta.setGlobal(GlobalValidacionENICodigosRespuestaValidacion.G_XXX_CODIGO + " - "
          + GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
      return respuesta;
    }

  }

  private HashMap<String, List<byte[]>> descomprimirZIP(DocumentoEntradaMtom docZipEntrada)
      throws Exception {
    byte[] zipExpAndDocs;
    zipExpAndDocs = IOUtils.toByteArray(docZipEntrada.getContenido().getInputStream());
    return XMLUtil.unZipFileExpYDocs(zipExpAndDocs);
  }

  private RespuestaValidacionENI getRespuestaGlobalZIP(RespuestaValidacionENI respuestaExpediente,
      List<RespuestaValidacionENI> listaRespuestaDocumentos,
      RespuestaValidacionENI respuestaCorrespondencia) {
    logger.debug("Inicio getRespuestaGlobalZIP");
    RespuestaValidacionENI respuesta = new RespuestaValidacionENI();
    respuesta.setGlobal(GlobalValidacionENICodigosRespuestaValidacion.G_000_CODIGO + " - "
        + GlobalValidacionENICodigosRespuestaValidacion.G_000_DETALLE);

    // lista global acumulando todos los detalles
    List<Detalle> listaGlobalDetalles = new ArrayList<Detalle>();
    listaGlobalDetalles.addAll(respuestaExpediente.getDetalle());

    for (int iDoc = 0; iDoc < listaRespuestaDocumentos.size(); iDoc++) {
      listaGlobalDetalles.addAll(listaRespuestaDocumentos.get(iDoc).getDetalle());
    }

    listaGlobalDetalles.addAll(respuestaCorrespondencia.getDetalle());

    respuesta.setDetalle(listaGlobalDetalles);

    // si en algun detalle hay error, el global debe ser error.
    for (Detalle detalleGlobal : listaGlobalDetalles) {
      if (detalleGlobal.getCodigoRespuesta().endsWith("999]")) {
        respuesta.setGlobal(GlobalValidacionENICodigosRespuestaValidacion.G_999_CODIGO + " - "
            + GlobalValidacionENICodigosRespuestaValidacion.G_999_DETALLE);
        break;
      }
    }

    logger.debug("Fin getRespuestaGlobalZIP");
    return respuesta;
  }

  private RespuestaValidacionENI validarCorrespondenciaExpedienteDocumentosENIZIP(
      List<byte[]> listaExpediente, List<byte[]> listaDocumentos) {

    List<String> listaIdentificadorDocumentoIndizadoEnIndice =
        getListaIdentificadorDocumentoIndizado(listaExpediente);

    List<String> listaIdentificadorDocumento = getListaIdentificadorDocumentos(listaDocumentos);

    RespuestaValidacionENI resultado = correspondenciaIdentificadoresDocIndiceConDocumentos(
        listaIdentificadorDocumentoIndizadoEnIndice, listaIdentificadorDocumento);

    return resultado;
  }

  private RespuestaValidacionENI validarExpedienteENIZIP(List<byte[]> listaExpediente,
      String version, Validaciones validacionesExpediente) {
    // DocumentoEntradaMtom expcEntrada = new DocumentoEntradaMtom();
    // ByteArrayDataSource dataSourceExp = new
    // ByteArrayDataSource(listaExpediente.get(0),null);
    // expcEntrada.setContenido(new DataHandler(dataSourceExp));
    byte[] expcEntrada = listaExpediente.get(0);
    RespuestaValidacionENI respuestaExpediente =
        validarExpedienteENI(expcEntrada, version, validacionesExpediente);

    return respuestaExpediente;
  }

  private List<RespuestaValidacionENI> validarDocumentosENIZIP(List<byte[]> listaDocumentos,
      String version, Validaciones validacionesDocumentos) {
    List<RespuestaValidacionENI> listaRespuestaDocumentos = new ArrayList<RespuestaValidacionENI>();

    for (int indiceDoc = 0; indiceDoc < listaDocumentos.size(); indiceDoc++) {
      // DocumentoEntradaMtom docEntrada = new DocumentoEntradaMtom();
      // ByteArrayDataSource dataSourceDoc = new
      // ByteArrayDataSource(listaDocumentos.get(indiceDoc),null);
      // docEntrada.setContenido(new DataHandler(dataSourceDoc));
      byte[] docEntrada = listaDocumentos.get(indiceDoc);
      RespuestaValidacionENI respuestaDocumento =
          validarDocumentoENI(docEntrada, version, validacionesDocumentos);
      listaRespuestaDocumentos.add(respuestaDocumento);
    }

    return listaRespuestaDocumentos;
  }

  private List<String> getListaIdentificadorDocumentoIndizado(List<byte[]> listaExpediente) {
    byte[] expedienteENI = listaExpediente.get(0);
    String tagNodo = "IdentificadorDocumento";
    return XMLUtil.listaValoresNodo(expedienteENI, tagNodo);

  }

  private List<String> getListaIdentificadorDocumentos(List<byte[]> listaDocumentos) {
    String tagNodoDocumentoIdentificador = "Identificador";
    List<String> listaIdentificadorDocumento = new ArrayList<String>();
    for (int i = 0; i < listaDocumentos.size(); i++) {
      try {
        List<String> listaIdentDocAux =
            XMLUtil.listaValoresNodo(listaDocumentos.get(i), tagNodoDocumentoIdentificador);
        listaIdentificadorDocumento.add(listaIdentDocAux.get(0));
      } catch (RuntimeException e) {
        logger.error(
            "ERROR en getListaIdentificadorDocumentos al obtener el identificadorDocuemtno en este documento : "
                + new String(listaDocumentos.get(i), XMLUtil.UTF8_CHARSET));
      }
    }

    return listaIdentificadorDocumento;

  }

  private RespuestaValidacionENI correspondenciaIdentificadoresDocIndiceConDocumentos(
      List<String> indice, List<String> documentos) {
    // Lista resultado de las validaciones
    List<Detalle> listaDetalles = new ArrayList<Detalle>();

    StringBuilder documentoNoEnIndice = new StringBuilder("");

    for (int i = 0; i < documentos.size(); i++) {
      Detalle detalleValidacion = new Detalle();
      detalleValidacion.setIdObjeto(documentos.get(i));
      detalleValidacion.setCodigoRespuesta(ExpedienteENICodigosRespuestaValidacion.E_N_000_CODIGO);
      detalleValidacion
          .setDetalleRespuesta(ExpedienteENICodigosRespuestaValidacion.E_N_000_DETALLE);

      if (!indice.contains(documentos.get(i))) {
        // error el indice tiene un identDeDocumentos que no tienen
        // ningun documentoeni
        detalleValidacion
            .setCodigoRespuesta(ExpedienteENICodigosRespuestaValidacion.E_N_999_CODIGO);
        detalleValidacion
            .setDetalleRespuesta(ExpedienteENICodigosRespuestaValidacion.E_N_999_DETALLE);
        documentoNoEnIndice.append(documentos.get(i));
        documentoNoEnIndice.append(". ");
      }

      listaDetalles.add(detalleValidacion);
    }

    if (indice.size() != documentos.size()) {
      // error hay distinto numero de de documentos en el indice y los que
      // trae el zip
      Detalle detalleValidacion = new Detalle();
      detalleValidacion.setIdObjeto("N�MERO FICHEROS");
      detalleValidacion.setCodigoRespuesta(ExpedienteENICodigosRespuestaValidacion.E_N_999_CODIGO);
      detalleValidacion.setDetalleRespuesta(ExpedienteENICodigosRespuestaValidacion.E_N_999_DETALLE
          + "-- Documentos no en Indice :" + documentoNoEnIndice);
      listaDetalles.add(detalleValidacion);
    }

    return getRespuestaGlobal(listaDetalles);

  }

}
