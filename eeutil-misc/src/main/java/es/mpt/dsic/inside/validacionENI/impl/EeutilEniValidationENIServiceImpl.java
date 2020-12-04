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

package es.mpt.dsic.inside.validacionENI.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.xml.transform.Source;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import es.mpt.dsic.eeutil.SIA.consumer.impl.ConsumidorSIA;
import es.mpt.dsic.eeutil.operFirma.consumer.impl.ConsumerEeutilOperFirmaImpl;
import es.mpt.dsic.eeutil.operFirma.consumer.model.DatosFirmados;
import es.mpt.dsic.eeutil.operFirma.consumer.model.ResultadoValidacionInfo;
import es.mpt.dsic.inside.dao.EeutilDao;
// import es.mpt.dsic.inside.model.UnidadOrganica;
import es.mpt.dsic.inside.utils.xml.XMLUtil;
import es.mpt.dsic.inside.validacionENI.EeutilEniValidationENIService;
import es.mpt.dsic.inside.ws.service.codigosRespuestaValidacion.DocumentoENICodigosRespuestaValidacion;
import es.mpt.dsic.inside.ws.service.codigosRespuestaValidacion.ExpedienteENICodigosRespuestaValidacion;
import es.mpt.dsic.inside.ws.service.codigosRespuestaValidacion.GlobalValidacionENICodigosRespuestaValidacion;
import es.mpt.dsic.inside.ws.service.model.Detalle;
import es.mpt.dsic.loadTables.model.UnidadOrganica;



public class EeutilEniValidationENIServiceImpl implements EeutilEniValidationENIService {


  protected static final Log logger = LogFactory.getLog(EeutilEniValidationENIServiceImpl.class);

  @Autowired
  @Qualifier(value = "consumerEeutilOperFirma")
  private ConsumerEeutilOperFirmaImpl consumerEeutilOperFirmaImpl;

  @Autowired
  private ConsumidorSIA consumidorSIA;

  @Autowired
  private EeutilDao eeutilDao;

  private String schemasDir;
  private String versionENI;

  private Source[] schemasSourcesDefault;

  // nombre del identificador del documentoEni o del expedienteEni
  private String identificadorObjeto;



  // XPATH documentoEni
  private final String XPATH_RUTA_IDENTIFICADOR_DOC = "documento/metadatos/Identificador";
  private final String XPATH_RUTA_ORGANOS_DOC = "documento/metadatos/Organo";
  private final String XPATH_RUTA_TIPO_FIRMA_DOC = "documento/firmas/firma/TipoFirma";

  // Tag para coger datos de firma Documentos
  private final String XPATH_RUTA_TF03_XADES_ENVELOPED = "documento/contenido/DatosXML";
  private final String XPATH_RUTA_TF02_XADES_DETACHED = "documento/contenido/DatosXML";
  private final String XPATH_RUTA_TF05_CADES_ATTACHED =
      "documento/firmas/firma/ContenidoFirma/FirmaConCertificado/FirmaBase64";
  private final String XPATH_RUTA_TF05_CADES_ATTACHED_REF_VALORBINARIO =
      "documento/contenido/ValorBinario";
  private final String XPATH_RUTA_TF04_CADES_DETACHED =
      "documento/firmas/firma/ContenidoFirma/FirmaConCertificado/FirmaBase64";
  private final String XPATH_RUTA_TF04_CADES_DETACHED_DOC = "documento/contenido/ValorBinario";
  private final String XPATH_RUTA_TFO6_PADES = "documento/contenido/ValorBinario";
  private final String XPATH_RUTA_TF07_XADES_MANIFEST =
      "documento/firmas/firma/ContenidoFirma/FirmaConCertificado/FirmaBase64";



  // XPATH expedienteEni
  private final String XPATH_RUTA_IDENTIFICADOR_EXP = "expediente/metadatosExp/Identificador";
  private final String XPATH_RUTA_ORGANOS_EXP = "expediente/metadatosExp/Organo";
  private final String XPATH_RUTA_CLASIFICACION_EXP = "expediente/metadatosExp/Clasificacion";
  private final String XPATH_RUTA_FECHA_APERTURA_EXP =
      "expediente/metadatosExp/FechaAperturaExpediente";
  private final String XPATH_RUTA_FIRMA_SIGNATURE_EXP =
      "expediente/indice/firmas/firma/ContenidoFirma/FirmaConCertificado/Signature";
  private final String XPATH_RUTA_FIRMA_BASE64_EXP =
      "expediente/indice/firmas/firma/ContenidoFirma/FirmaConCertificado/FirmaBase64";
  private final String XPATH_RUTA_TIPO_FIRMA_EXP = "expediente/indice/firmas/firma/TipoFirma";


  // Tipos de firma
  private final String TIPOFIRMA_TF_01 = "TF01";
  private final String TIPOFIRMA_TF_02 = "TF02";
  private final String TIPOFIRMA_TF_03 = "TF03";
  private final String TIPOFIRMA_TF_04 = "TF04";
  private final String TIPOFIRMA_TF_05 = "TF05";
  private final String TIPOFIRMA_TF_06 = "TF06";


  public ConsumidorSIA getConsumidorSIA() {
    return consumidorSIA;
  }

  public void setConsumidorSIA(ConsumidorSIA consumidorSIA) {
    this.consumidorSIA = consumidorSIA;
  }


  public String getVersionENI() {
    return versionENI;
  }

  public void setVersionENI(String versionENI) {
    this.versionENI = versionENI;
  }

  public String getSchemasDir() {
    return schemasDir;
  }


  @Required
  public void setSchemasDir(String schemasDir) {
    this.schemasDir = schemasDir;
  }


  @PostConstruct
  public void configureService() {
    logger.info(
        "Leyendo esquemas del directorio: " + schemasDir + " concatenando la version del ENI");

    schemasSourcesDefault = getSchemasByVesion(versionENI);

    if (schemasSourcesDefault.length == 0) {
      logger.error("No se han podido cargar los schemas del directorio: " + schemasDir);
    }
  }

  private Source[] getSchemasByVesion(String version) {
    return XMLUtil.getSchemasSources(schemasDir + File.separator + version);
  }


  @Override
  public void setIdentificadorExpedienteXML(String xml) throws Exception {
    identificadorObjeto = XMLUtil.getvalorNodoDatosXML(xml, this.XPATH_RUTA_IDENTIFICADOR_DOC);
  }


  /************************************************************************************************************************************
   * ************************************ VALIDACIONES DOCUMENTOENI
   * ******************************************************************
   * **********************************************************************************************************************************
   */


  /*
   * Validar shema de documentoeni (non-Javadoc)
   * 
   * @see
   * es.mpt.dsic.inside.validacionENI.EeutilEniValidationENIService#validarShemaDocumentoEniFile(
   * byte[])
   */
  @Override
  public Detalle validarShemaDocumentoEniFile(String dataXml, String version) {

    try {
      identificadorObjeto =
          XMLUtil.getvalorNodoDatosXML(dataXml, this.XPATH_RUTA_IDENTIFICADOR_DOC);
      validarSchema(dataXml.getBytes(XMLUtil.UTF8_CHARSET), version); // por acentos

    } catch (SAXException e) {

      Detalle detalleValidacion =
          getDetalleValidacion(DocumentoENICodigosRespuestaValidacion.D_E_999_CODIGO,
              DocumentoENICodigosRespuestaValidacion.D_E_999_DETALLE);
      return detalleValidacion;

    } catch (Exception e) {

      Detalle detalleValidacion =
          getDetalleValidacion(GlobalValidacionENICodigosRespuestaValidacion.G_EXX_CODIGO,
              GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
      return detalleValidacion;

    }

    return getDetalleValidacion(DocumentoENICodigosRespuestaValidacion.D_E_000_CODIGO,
        DocumentoENICodigosRespuestaValidacion.D_E_000_DETALLE);

  }



  @Override
  public Detalle validarUnidadOrganicaDocumentoEniFile(String dataXml) {
    try {
      identificadorObjeto =
          XMLUtil.getvalorNodoDatosXML(dataXml, this.XPATH_RUTA_IDENTIFICADOR_DOC);

      // String dataXml = XMLUtils.getDataExpedienteXML(xmlBytes);
      List<String> listaOrganos = XMLUtil.getContentNodeList(dataXml.getBytes(XMLUtil.UTF8_CHARSET),
          this.XPATH_RUTA_ORGANOS_DOC);
      String dir3Erroneo = validateOrganos(listaOrganos);
      if (!dir3Erroneo.equals("")) {
        return getDetalleValidacion(DocumentoENICodigosRespuestaValidacion.D_D_999_CODIGO,
            DocumentoENICodigosRespuestaValidacion.D_D_999_DETALLE + " -- (" + dir3Erroneo + ")");
      } else {
        return getDetalleValidacion(DocumentoENICodigosRespuestaValidacion.D_D_000_CODIGO,
            DocumentoENICodigosRespuestaValidacion.D_D_000_DETALLE);
      }



    } catch (Exception e) {
      Detalle detalleValidacion =
          getDetalleValidacion(GlobalValidacionENICodigosRespuestaValidacion.G_DXX_CODIGO,
              GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
      return detalleValidacion;
    }

  }



  /***********************************************************************************************************************************
   * **********************************************************************************************************************************
   * ************************************ VALIDACIONES EXPEDIENTEENI
   * ******************************************************************
   * **********************************************************************************************************************************
   * **********************************************************************************************************************************
   */



  /*
   * Validar shema de ExpedienteEni (non-Javadoc)
   * 
   * @see
   * es.mpt.dsic.inside.validacionENI.EeutilEniValidationENIService#validarShemaExpedienteEniFile(
   * byte[])
   */
  @Override
  public Detalle validarShemaExpedienteEniFile(String dataXml, String version) {

    try {
      identificadorObjeto =
          XMLUtil.getvalorNodoDatosXML(dataXml, this.XPATH_RUTA_IDENTIFICADOR_EXP);
      validarSchema(dataXml.getBytes(XMLUtil.UTF8_CHARSET), version);

    } catch (SAXException e) {

      Detalle detalleValidacion =
          getDetalleValidacion(ExpedienteENICodigosRespuestaValidacion.E_E_999_CODIGO,
              ExpedienteENICodigosRespuestaValidacion.E_E_999_DETALLE);
      return detalleValidacion;

    } catch (Exception e) {

      Detalle detalleValidacion =
          getDetalleValidacion(GlobalValidacionENICodigosRespuestaValidacion.G_EXX_CODIGO,
              GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
      return detalleValidacion;

    }

    return getDetalleValidacion(ExpedienteENICodigosRespuestaValidacion.E_E_000_CODIGO,
        ExpedienteENICodigosRespuestaValidacion.E_E_000_DETALLE);

  }



  @Override
  public Detalle validarUnidadOrganicaExpedienteEniFile(String dataXml) {
    try {
      identificadorObjeto =
          XMLUtil.getvalorNodoDatosXML(dataXml, this.XPATH_RUTA_IDENTIFICADOR_EXP);
      List<String> listaOrganos = XMLUtil.getContentNodeList(dataXml.getBytes(XMLUtil.UTF8_CHARSET),
          this.XPATH_RUTA_ORGANOS_EXP);
      String dir3Erroneo = validateOrganos(listaOrganos);
      if (!dir3Erroneo.equals("")) {
        return getDetalleValidacion(ExpedienteENICodigosRespuestaValidacion.E_D_999_CODIGO,
            ExpedienteENICodigosRespuestaValidacion.E_D_999_DETALLE + " -- (" + dir3Erroneo + ")");
      } else {
        return getDetalleValidacion(ExpedienteENICodigosRespuestaValidacion.E_D_000_CODIGO,
            ExpedienteENICodigosRespuestaValidacion.E_D_000_DETALLE);
      }



    } catch (Exception e) {
      Detalle detalleValidacion =
          getDetalleValidacion(GlobalValidacionENICodigosRespuestaValidacion.G_DXX_CODIGO,
              GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
      return detalleValidacion;
    }

  }


  @Override
  public Detalle validarCodigoSIAExpedienteEniFile(String dataXml) {
    try {
      identificadorObjeto =
          XMLUtil.getvalorNodoDatosXML(dataXml, this.XPATH_RUTA_IDENTIFICADOR_EXP);

      String clasificacion_SIA = XMLUtil.getContentNode(dataXml.getBytes(XMLUtil.UTF8_CHARSET),
          XPATH_RUTA_CLASIFICACION_EXP);
      String fechaAperturaExpediente = XMLUtil
          .getContentNode(dataXml.getBytes(XMLUtil.UTF8_CHARSET), XPATH_RUTA_FECHA_APERTURA_EXP);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Date feApExFormateada = sdf.parse(fechaAperturaExpediente);
      Calendar calendario = GregorianCalendar.getInstance();
      calendario.setTime(feApExFormateada);

      boolean correcto = validateClasificacionSIA(clasificacion_SIA, calendario);
      if (!correcto) {
        return getDetalleValidacion(ExpedienteENICodigosRespuestaValidacion.E_S_999_CODIGO,
            ExpedienteENICodigosRespuestaValidacion.E_S_999_DETALLE + " -- (" + clasificacion_SIA
                + ")");
      } else {
        return getDetalleValidacion(ExpedienteENICodigosRespuestaValidacion.E_S_000_CODIGO,
            ExpedienteENICodigosRespuestaValidacion.E_S_000_DETALLE);
      }

    } catch (Exception e) {
      Detalle detalleValidacion =
          getDetalleValidacion(GlobalValidacionENICodigosRespuestaValidacion.G_SXX_CODIGO,
              GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
      return detalleValidacion;
    }

  }



  @Override
  public Detalle validarFirmaExpedienteEniFile(
      es.mpt.dsic.inside.security.model.ApplicationLogin info, String xml,
      String xmlNoProcedenteInside, byte[] expedienteRecibidoOriginalSinTocar) {

    ResultadoValidacionInfo resultado;
    try {
      identificadorObjeto = XMLUtil.getvalorNodoDatosXML(xml, this.XPATH_RUTA_IDENTIFICADOR_EXP);

      byte[] firmaAValidar = getFirmaExpediente(xml);

      resultado = consumerEeutilOperFirmaImpl.validacionFirma(info, firmaAValidar, null, null);

    } catch (Exception e) {
      Detalle detalleValidacion =
          getDetalleValidacion(GlobalValidacionENICodigosRespuestaValidacion.G_FXX_CODIGO,
              GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
      return detalleValidacion;
    }



    // Puede ser valida pero da error de referencia por meter unos namespaces en
    // addNameSpacesToExpedienteENIXML validos para
    // cuando el eni se hizo en inside, pero pueden mandar un eni no hecho desde inside y hay que
    // quitar esos namespaces aniadidos.
    // se a�ade la condicion resultado.getDetalle().contains("COD_103") porque en des-afirma y
    // afirma pro devolvian distinto error
    // y paara ambos conviene volver a realizar la llamada.
    if (resultado.getDetalle().contains("(VALIDATION) La referencia 2 contenida en la Firma")
        || resultado.getDetalle().contains("COD_103")) {
      try {
        // Elimina los namespaces que son 3 innecesarios para validar porque no se hizo en inside el
        // eni

        // String xmlNuevo = XMLUtil.obtenerExpedienteENIXMLDELETENAMESPACES(xml);
        byte[] firmaAValidarNOProvieneInside = getFirmaExpediente(xmlNoProcedenteInside);

        resultado = consumerEeutilOperFirmaImpl.validacionFirma(info, firmaAValidarNOProvieneInside,
            null, null);

      } catch (Exception e) {
        Detalle detalleValidacion =
            getDetalleValidacion(GlobalValidacionENICodigosRespuestaValidacion.G_FXX_CODIGO,
                GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
        return detalleValidacion;
      }
    }



    // CASO PARA ARTURO COMUNIDAD DE MADRID. FIRMAN TODO EL XML POR LO TANTO HAY QUE PASAR A VALIDAR
    // EL EXPEDIENTE TAL CUAL NOS LO DAN SIN TOCARLO
    if (resultado.getDetalle().contains("(VALIDATION) La referencia 0 contenida en la Firma")) {
      try {

        resultado = consumerEeutilOperFirmaImpl.validacionFirma(info,
            expedienteRecibidoOriginalSinTocar, null, null);

      } catch (Exception e) {
        Detalle detalleValidacion =
            getDetalleValidacion(GlobalValidacionENICodigosRespuestaValidacion.G_FXX_CODIGO,
                GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
        return detalleValidacion;
      }
    }



    // expediente de sip que iria nos da que no valida... mete bien los namespace pero no hay que
    // meter los tres que se introduces insidews, ns8 y ns9
    if (!resultado.isEstado()) {
      try {
        // Elimina los namespaces que son 3 innecesarios para validar porque no se hizo en inside el
        // eni
        byte[] firmaAValidarNOProvieneInside =
            getFirmaExpediente(XMLUtil.deleteNameSpacesToExpedienteENIXML(
                XMLUtil.getNode(xml.getBytes(XMLUtil.UTF8_CHARSET), "*"), null));

        resultado = consumerEeutilOperFirmaImpl.validacionFirma(info, firmaAValidarNOProvieneInside,
            null, null);

      } catch (Exception e) {
        Detalle detalleValidacion =
            getDetalleValidacion(GlobalValidacionENICodigosRespuestaValidacion.G_FXX_CODIGO,
                GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
        return detalleValidacion;
      }
    }



    // si el estado es true -> firma valida
    if (resultado.isEstado())
      return getDetalleValidacion(ExpedienteENICodigosRespuestaValidacion.E_F_000_CODIGO,
          ExpedienteENICodigosRespuestaValidacion.E_F_000_DETALLE + " -- Estado: "
              + resultado.isEstado() + " -- detalle: " + resultado.getDetalle());
    else
      return getDetalleValidacion(ExpedienteENICodigosRespuestaValidacion.E_F_999_CODIGO,
          ExpedienteENICodigosRespuestaValidacion.E_F_999_DETALLE + " -- Estado: "
              + resultado.isEstado() + " -- detalle: " + resultado.getDetalle());
  }


  /*
   * valida un xml frente a unos shemas
   * 
   */
  private void validarSchema(byte[] xmlBytes, String version) throws SAXException, Exception {
    ByteArrayInputStream bin = null;
    try {
      bin = new ByteArrayInputStream(xmlBytes);

      XMLReader parser = null;

      if (StringUtils.isNotBlank(version)) {
        parser = XMLUtil.createParserForValidation(getSchemasByVesion(version));
      } else {
        parser = XMLUtil.createParserForValidation(schemasSourcesDefault);
      }
      InputSource xmlSource = new InputSource(bin);
      parser.parse(xmlSource);
    } catch (Exception e) {
      throw e;
    } finally {
      if (bin != null) {
        bin.close();
      }
    }
  }


  /*
   * Construye detalle validacion
   * 
   */
  private Detalle getDetalleValidacion(String codigo, String Detalle) {
    Detalle detalleValidacion = new Detalle();
    detalleValidacion.setIdObjeto(identificadorObjeto);
    detalleValidacion.setCodigoRespuesta(codigo);
    detalleValidacion.setDetalleRespuesta(Detalle);
    return detalleValidacion;

  }


  /*
   * Devuelve los dir3 erroneos
   * 
   */
  private String validateOrganos(List<String> organos) {
    StringBuffer tmpBuff = new StringBuffer("");
    for (String organo : organos) {
      List<Criterion> criterias = new ArrayList<Criterion>();
      criterias.add(Restrictions.eq("codigoUnidadOrganica", organo));
      UnidadOrganica unidadOrganica =
          (UnidadOrganica) eeutilDao.findObjeto(UnidadOrganica.class, criterias);
      if (unidadOrganica == null) {
        if (!tmpBuff.toString().equals("")) {
          tmpBuff.append(",");
        }
        tmpBuff.append(organo);
      }
    }

    return tmpBuff.toString();
  }


  private boolean validateClasificacionSIA(String clasificacionSIA, Calendar fecha) {
    String clasificacionPattern = ".*_PRO_.*";
    boolean retorno = true;
    Integer anio = fecha.get(Calendar.YEAR);
    boolean exists = true;

    try {
      exists = consumidorSIA.existClasificacion(clasificacionSIA, anio.toString());
    } catch (Exception e) {
      retorno = false;
      logger.error("Error en la llamada al servicio de SIA para validar");
    }


    try {
      if (!exists) {
        logger.debug("Valor de clasificaci�n no corresponde en SIA");
        Pattern pattern = Pattern.compile(clasificacionPattern);
        Matcher matcher = pattern.matcher(clasificacionSIA);
        if (!matcher.matches()) {
          retorno = false;
        } else {

          List<Criterion> criterias = new ArrayList<Criterion>();

          StringTokenizer tmpToken = new StringTokenizer(clasificacionSIA, "_PRO_");
          String data = (String) tmpToken.nextElement();
          criterias.add(Restrictions.eq("codigoUnidadOrganica", data));

          UnidadOrganica unidadOrganica =
              (UnidadOrganica) eeutilDao.findObjeto(UnidadOrganica.class, criterias);

          if (unidadOrganica == null) {
            retorno = false;
          }
        }
      }
    } catch (Exception e) {
      retorno = false;
      logger.error("Error en la busqueda de SIA en tabla para validarlo");
    }

    return retorno;

  }



  private byte[] getFirmaExpediente(String xml) throws Exception {

    String firma = null;

    try {
      String tipoFirma = XMLUtil.getvalorNodoDatosXML(xml, this.XPATH_RUTA_TIPO_FIRMA_EXP);

      // CADES
      if (tipoFirma != null && tipoFirma.equalsIgnoreCase(this.TIPOFIRMA_TF_05)) {
        firma =
            XMLUtil.getContentNode(xml.getBytes(XMLUtil.UTF8_CHARSET), XPATH_RUTA_FIRMA_BASE64_EXP);
      }

      // XADES INTERNAL DETACHED XADES ENVELOPED SIGNATURE
      if (tipoFirma != null && (tipoFirma.equalsIgnoreCase(this.TIPOFIRMA_TF_02)
          || tipoFirma.equalsIgnoreCase(this.TIPOFIRMA_TF_03))) {
        // ver si trae firma en nodo firmabase64 codificada en base64. si es asi devolvemos eso.
        firma =
            XMLUtil.getContentNode(xml.getBytes(XMLUtil.UTF8_CHARSET), XPATH_RUTA_FIRMA_BASE64_EXP);

        // Si firmanodobase64 es nulo
        if (firma == null || "".equals(firma.trim())) {
          firma = xml;
        }

      }

      if (StringUtils.isEmpty(firma)) {
        // lanzamos error no hemos detectado la firma del expediente
        Exception e = new Exception("No se ha detectado la firma del expediente");
        throw new Exception(e);
      }

    } catch (RuntimeException e) {
      throw e;
    }

    if (Base64.isBase64(firma))
      return Base64.decodeBase64(firma);
    else
      return firma.getBytes(XMLUtil.UTF8_CHARSET);

  }



  @Override
  public Detalle validarFirmaDocumentoEniFile(
      es.mpt.dsic.inside.security.model.ApplicationLogin info, String xml) {

    ResultadoValidacionInfo resultado;
    try {
      identificadorObjeto = XMLUtil.getvalorNodoDatosXML(xml, this.XPATH_RUTA_IDENTIFICADOR_DOC);

      // byte[] firmaAValidar = getFirmaDocumento(xml);

      HashMap auxMap = (HashMap) getFirmaDocumentoMap(xml);
      byte[] firmaAValidar = (byte[]) auxMap.get("CONTENIDOFIRMA");
      DatosFirmados df = (DatosFirmados) auxMap.get("DATOSFIRMADOS");

      // df.setDocumento(Base64.decodeBase64("PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/Pgo8bnM3OmV4cGVkaWVudGUgeG1sbnM6bnMyPSJodHRwOi8vYWRtaW5pc3RyYWNpb25lbGVjdHJvbmljYS5nb2IuZXMvRU5JL1hTRC92MS4wL2V4cGVkaWVudGUtZS9pbmRpY2UtZSIgeG1sbnM9Imh0dHA6Ly9hZG1pbmlzdHJhY2lvbmVsZWN0cm9uaWNhLmdvYi5lcy9FTkkvWFNEL3YxLjAvZXhwZWRpZW50ZS1lL2luZGljZS1lL2NvbnRlbmlkbyIgeG1sbnM6bnM0PSJodHRwOi8vd3d3LnczLm9yZy8yMDAwLzA5L3htbGRzaWcjIiB4bWxuczpuczM9Imh0dHA6Ly9hZG1pbmlzdHJhY2lvbmVsZWN0cm9uaWNhLmdvYi5lcy9FTkkvWFNEL3YxLjAvZmlybWEiIHhtbG5zOm5zNT0iaHR0cDovL2FkbWluaXN0cmFjaW9uZWxlY3Ryb25pY2EuZ29iLmVzL0VOSS9YU0QvdjEuMC9leHBlZGllbnRlLWUvbWV0YWRhdG9zIiB4bWxuczpuczY9Imh0dHA6Ly9hZG1pbmlzdHJhY2lvbmVsZWN0cm9uaWNhLmdvYi5lcy9FTkkvWFNEL3YxLjAvZG9jdW1lbnRvLWUvY29udGVuaWRvIiB4bWxuczpuczc9Imh0dHA6Ly9hZG1pbmlzdHJhY2lvbmVsZWN0cm9uaWNhLmdvYi5lcy9FTkkvWFNEL3YxLjAvZXhwZWRpZW50ZS1lIj4KICAgIDxuczI6aW5kaWNlPgogICAgICAgIDxuczI6SW5kaWNlQ29udGVuaWRvIElkPSJFWFBfSU5ESUNFX0NPTlRFTklET0VTX0UwNDkxMjEwMV8yMDE2X0VYUF9tYXVyaV8wMDAwMDAwMDkiPgogICAgICAgICAgICA8RmVjaGFJbmRpY2VFbGVjdHJvbmljbz4yMDE2LTEwLTA3VDEwOjAyOjQyLjAwMCswMjowMDwvRmVjaGFJbmRpY2VFbGVjdHJvbmljbz4KICAgICAgICAgICAgPERvY3VtZW50b0luZGl6YWRvPgogICAgICAgICAgICAgICAgPElkZW50aWZpY2Fkb3JEb2N1bWVudG8+RVNfRTA0OTEyMTAxXzIwMTZfRE9DX1BBREVTXzAwMDAwMDAzPC9JZGVudGlmaWNhZG9yRG9jdW1lbnRvPgogICAgICAgICAgICAgICAgPFZhbG9ySHVlbGxhPjE4NzUyNzllOGE4YmQ2YjU3NDFkN2EwYjNlNzZkZTZlPC9WYWxvckh1ZWxsYT4KICAgICAgICAgICAgICAgIDxGdW5jaW9uUmVzdW1lbj5tZDU8L0Z1bmNpb25SZXN1bWVuPgogICAgICAgICAgICAgICAgPEZlY2hhSW5jb3Jwb3JhY2lvbkV4cGVkaWVudGU+MjAxNi0xMC0wN1QwMDowMDowMC4wMDArMDI6MDA8L0ZlY2hhSW5jb3Jwb3JhY2lvbkV4cGVkaWVudGU+CiAgICAgICAgICAgICAgICA8T3JkZW5Eb2N1bWVudG9FeHBlZGllbnRlPjE8L09yZGVuRG9jdW1lbnRvRXhwZWRpZW50ZT4KICAgICAgICAgICAgPC9Eb2N1bWVudG9JbmRpemFkbz4KICAgICAgICA8L25zMjpJbmRpY2VDb250ZW5pZG8+CiAgICA8L25zMjppbmRpY2U+CjwvbnM3OmV4cGVkaWVudGU+Cg==".getBytes("UTF-8")));
      // byte[]
      // firmaAValidar=Base64.decodeBase64("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".getBytes(XMLUtil.UTF8_CHARSET));


      resultado = consumerEeutilOperFirmaImpl.validacionFirma(info, firmaAValidar, null, df);

    } catch (Exception e) {
      Detalle detalleValidacion =
          getDetalleValidacion(GlobalValidacionENICodigosRespuestaValidacion.G_FXX_CODIGO,
              GlobalValidacionENICodigosRespuestaValidacion.G_XXX_DETALLE);
      return detalleValidacion;
    }

    // si el estado es true -> firma valida
    if (resultado.isEstado())
      return getDetalleValidacion(DocumentoENICodigosRespuestaValidacion.D_F_000_CODIGO,
          DocumentoENICodigosRespuestaValidacion.D_F_000_DETALLE + " -- Estado: "
              + resultado.isEstado() + " -- detalle: " + resultado.getDetalle());
    else
      return getDetalleValidacion(DocumentoENICodigosRespuestaValidacion.D_F_999_CODIGO,
          DocumentoENICodigosRespuestaValidacion.D_F_999_DETALLE + " -- Estado: "
              + resultado.isEstado() + " -- detalle: " + resultado.getDetalle());
  }



  private byte[] getFirmaDocumento(String xml) throws Exception {

    String contenidoFirma = "";

    try {
      String tipoFirma = XMLUtil.getvalorNodoDatosXML(xml, this.XPATH_RUTA_TIPO_FIRMA_DOC);

      switch (Integer.parseInt("" + tipoFirma.charAt(3))) {
        case 2:
          contenidoFirma = XMLUtil.getContentNode(xml.getBytes(XMLUtil.UTF8_CHARSET),
              XPATH_RUTA_TF02_XADES_DETACHED);
          break;
        case 3:
          contenidoFirma = XMLUtil.getContentNode(xml.getBytes(XMLUtil.UTF8_CHARSET),
              XPATH_RUTA_TF03_XADES_ENVELOPED);
          break;
        case 4:
          contenidoFirma = XMLUtil.getContentNode(xml.getBytes(XMLUtil.UTF8_CHARSET),
              XPATH_RUTA_TF04_CADES_DETACHED);
          break;
        case 5:

          contenidoFirma = XMLUtil.getContentNode(xml.getBytes(XMLUtil.UTF8_CHARSET),
              XPATH_RUTA_TF05_CADES_ATTACHED);
          if ("".equals(contenidoFirma))
            contenidoFirma = XMLUtil.getContentNode(xml.getBytes(XMLUtil.UTF8_CHARSET),
                XPATH_RUTA_TF05_CADES_ATTACHED_REF_VALORBINARIO);

          break;
        case 6:
          contenidoFirma =
              XMLUtil.getContentNode(xml.getBytes(XMLUtil.UTF8_CHARSET), XPATH_RUTA_TFO6_PADES);
          break;
        case 7:

          contenidoFirma = XMLUtil.getContentNode(xml.getBytes(XMLUtil.UTF8_CHARSET),
              XPATH_RUTA_TF07_XADES_MANIFEST);
          break;
      }


      if (Base64.isBase64(contenidoFirma.getBytes(XMLUtil.UTF8_CHARSET)))
        return Base64.decodeBase64(contenidoFirma.getBytes("UTF-8"));
      else
        return contenidoFirma.getBytes("UTF-8");

    } catch (RuntimeException e) {
      throw e;
    }

  }



  private Map<String, Object> getFirmaDocumentoMap(String xml) throws Exception {

    String contenidoFirma = "";

    // para el caso de tf04
    HashMap<String, Object> datosFirma = new HashMap<String, Object>();
    datosFirma.put("DATOSFIRMADOS", null);


    try {
      String tipoFirma = XMLUtil.getvalorNodoDatosXML(xml, this.XPATH_RUTA_TIPO_FIRMA_DOC);

      switch (Integer.parseInt("" + tipoFirma.charAt(3))) {
        case 2:
          contenidoFirma = XMLUtil.getContentNode(xml.getBytes(XMLUtil.UTF8_CHARSET),
              XPATH_RUTA_TF02_XADES_DETACHED);
          break;
        case 3:
          contenidoFirma = XMLUtil.getContentNode(xml.getBytes(XMLUtil.UTF8_CHARSET),
              XPATH_RUTA_TF03_XADES_ENVELOPED);
          break;
        case 4:
          contenidoFirma = XMLUtil.getContentNode(xml.getBytes(XMLUtil.UTF8_CHARSET),
              XPATH_RUTA_TF04_CADES_DETACHED);
          String datosFirmados = XMLUtil.getContentNode(xml.getBytes(XMLUtil.UTF8_CHARSET),
              XPATH_RUTA_TF04_CADES_DETACHED_DOC);
          DatosFirmados df = new DatosFirmados();
          df.setDocumento(Base64.decodeBase64(datosFirmados.getBytes(XMLUtil.UTF8_CHARSET)));
          datosFirma.put("DATOSFIRMADOS", df);
          break;
        case 5:
          contenidoFirma = XMLUtil.getContentNode(xml.getBytes(XMLUtil.UTF8_CHARSET),
              XPATH_RUTA_TF05_CADES_ATTACHED);
          if ("".equals(contenidoFirma))
            contenidoFirma = XMLUtil.getContentNode(xml.getBytes(XMLUtil.UTF8_CHARSET),
                XPATH_RUTA_TF05_CADES_ATTACHED_REF_VALORBINARIO);


          break;
        case 6:
          contenidoFirma =
              XMLUtil.getContentNode(xml.getBytes(XMLUtil.UTF8_CHARSET), XPATH_RUTA_TFO6_PADES);
          break;
        case 7:

          contenidoFirma = XMLUtil.getContentNode(xml.getBytes(XMLUtil.UTF8_CHARSET),
              XPATH_RUTA_TF07_XADES_MANIFEST);
          break;
      }


      if (Base64.isBase64(contenidoFirma.getBytes(XMLUtil.UTF8_CHARSET))) {
        datosFirma.put("CONTENIDOFIRMA", Base64.decodeBase64(contenidoFirma.getBytes("UTF-8")));
        return datosFirma;
      }

      else {
        datosFirma.put("CONTENIDOFIRMA", contenidoFirma.getBytes("UTF-8"));
        return datosFirma;

      }


    } catch (Exception e) {
      throw e;
    }

  }



}

