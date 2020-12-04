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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.pdftools.NativeLibrary;
import com.pdftools.pdf2pdf.Pdf2PdfAPI;
import es.mpt.dsic.eeutil.operFirma.consumer.impl.ConsumerEeutilOperFirmaImpl;
import es.mpt.dsic.inside.config.EeutilApplicationDataConfig;
import es.mpt.dsic.inside.pdf.converter.HtmlConverter;
import es.mpt.dsic.inside.pdf.converter.PdfConverter;
import es.mpt.dsic.inside.pdf.converter.TcnToPdfConverter;
import es.mpt.dsic.inside.pdf.exception.PdfConversionException;
import es.mpt.dsic.inside.security.context.AplicacionContext;
import es.mpt.dsic.inside.security.model.AppInfo;
import es.mpt.dsic.inside.security.model.ApplicationLogin;
import es.mpt.dsic.inside.security.service.impl.EeutilAplicacionConversionService;
import es.mpt.dsic.inside.utils.file.FileUtil;
import es.mpt.dsic.inside.utils.xml.XMLUtil;
import es.mpt.dsic.inside.ws.service.EeUtilService;
import es.mpt.dsic.inside.ws.service.exception.InSideException;
import es.mpt.dsic.inside.ws.service.model.ContenidoInfo;
import es.mpt.dsic.inside.ws.service.model.DocumentoContenido;
import es.mpt.dsic.inside.ws.service.model.EstadoInfo;
import es.mpt.dsic.inside.ws.service.model.SalidaVisualizacion;
import es.mpt.dsic.inside.ws.service.model.TCNInfo;
import es.mpt.dsic.inside.ws.service.model.pdf.DocumentoEntrada;
import es.mpt.dsic.inside.ws.service.model.pdf.PdfSalida;
import es.mpt.dsic.inside.ws.service.util.UtilFacturae;
import es.mpt.dsic.inside.ws.service.util.UtilPdfA;

@Service("eeUtilService")
@WebService(endpointInterface = "es.mpt.dsic.inside.ws.service.EeUtilService")
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.BARE, use = Use.LITERAL)
public class EeUtilServiceImpl implements EeUtilService {

  protected final static Log logger = LogFactory.getLog(EeUtilServiceImpl.class);

  @Autowired
  private UtilFacturae utilFacturae;

  @Autowired
  private AplicacionContext aplicacionContext;

  @Autowired
  private UtilPdfA utilPdfA;

  @Autowired
  private EeutilAplicacionConversionService aplicacionConversionService;

  @Autowired
  @Qualifier(value = "consumerEeutilOperFirma")
  private ConsumerEeutilOperFirmaImpl consumerEeutilOperFirmaImpl;

  @Autowired
  TcnToPdfConverter tcnToPdfConverter;

  @Autowired
  PdfConverter pdfConverter;

  @Autowired
  HtmlConverter htmlConverter;

  @Override
  public byte[] postProcesarFirma(ApplicationLogin info, byte[] firma) throws InSideException {
    try {
      return consumerEeutilOperFirmaImpl.postProcesarFirma(info, firma);
    } catch (es.mpt.dsic.eeutil.operFirma.consumer.model.InSideException e) {
      throw new InSideException(e.getMessage(), new EstadoInfo(), e);
    }
  }

  @Override
  public ContenidoInfo convertirTCNAPdf(ApplicationLogin application, TCNInfo tcnInfo)
      throws InSideException {
    ContenidoInfo contenidoInfo = new ContenidoInfo();

    try {
      byte[] pdf = tcnToPdfConverter.convertTCNToPdfBytes(tcnInfo.getContenido().getContenido());
      contenidoInfo.setContenido(pdf);
      contenidoInfo.setTipoMIME("application/pdf");
    } catch (PdfConversionException e) {
      logger.error("Error en la conversi�n a PDF del TCN", e);
      EstadoInfo estadoInfo = new EstadoInfo();
      throw new InSideException("Error convirtiendo TCN a PDF: ", estadoInfo);
    } catch (Throwable t) {
      logger.error("Error inesperado convirtiendo TCNaPDF", t);
      EstadoInfo estadoInfo = new EstadoInfo();
      throw new InSideException("Error convirtiendo TCN a PDF: ", estadoInfo);
    }

    return contenidoInfo;
  }

  @Override
  public SalidaVisualizacion visualizarFacturae(ApplicationLogin info, byte[] factura,
      String version) throws InSideException {
    logger.debug("Inicio visualizarFacturae");

    SalidaVisualizacion facturaPost = new SalidaVisualizacion();
    DocumentoContenido contenido = new DocumentoContenido();
    File cpr = null;
    try {
      if (StringUtils.isEmpty(version)) {
        cpr = new File(EeutilApplicationDataConfig.CONFIG_PATH + File.separator
            + utilFacturae.getTemplateDefault());
      } else {
        cpr = new File(
            EeutilApplicationDataConfig.CONFIG_PATH + File.separator + utilFacturae.getDirectorio()
                + File.separator + version + File.separator + utilFacturae.getTemplate());

      }

      TransformerFactory tFactory = TransformerFactory.newInstance();
      Transformer transformer = tFactory.newTransformer(new StreamSource(cpr));

      ByteArrayInputStream bin = new ByteArrayInputStream(factura);
      ByteArrayOutputStream bos = new ByteArrayOutputStream();

      StreamSource sourceFactura = new StreamSource(bin);
      StreamResult result = new StreamResult(bos);
      transformer.transform(sourceFactura, result);

      contenido.setBytesDocumento(bos.toByteArray());
      contenido.setMimeDocumento("text/html");

      facturaPost.setDocumentoContenido(contenido);
    } catch (TransformerConfigurationException e) {
      logger.error("Error inesperado visualizando Facturae", e);
      EstadoInfo estadoInfo = new EstadoInfo();
      throw new InSideException("Error visualizando Facturae: ", estadoInfo);
    } catch (TransformerFactoryConfigurationError e) {
      logger.error("Error inesperado visualizando Facturae", e);
      EstadoInfo estadoInfo = new EstadoInfo();
      throw new InSideException("Error visualizando Facturae: ", estadoInfo);
    } catch (TransformerException e) {
      logger.error("Error inesperado visualizando Facturae", e);
      EstadoInfo estadoInfo = new EstadoInfo();
      throw new InSideException("Error visualizando Facturae: ", estadoInfo);
    }

    return facturaPost;
  }

  @Override
  public PdfSalida convertirPDFA(ApplicationLogin info, DocumentoEntrada docEntrada,
      Integer nivelCompilacion) throws InSideException {
    File pdfOriginal = null;
    try {
      logger.debug("convertirPDFA");
      logger.debug("mime:" + docEntrada.getMime());
      PdfSalida retorno = new PdfSalida();

      AppInfo appInfo = aplicacionContext.getAplicacionInfo();
      String ipOpenOffice = appInfo.getPropiedades().get("ip.openoffice");
      String portOpenOffice = appInfo.getPropiedades().get("port.openoffice");

      String filePathIn = FileUtil.createFilePath("PDFA_", docEntrada.getContenido());

      pdfOriginal = pdfConverter.convertir(ipOpenOffice, portOpenOffice, new File(filePathIn),
          docEntrada.getMime());

      Boolean isPDFA = utilPdfA.isPDFA(IOUtils.toByteArray(new FileInputStream(pdfOriginal)),
          docEntrada.getPassword(), nivelCompilacion);

      // Validate document
      if (!isPDFA) {
        Pdf2PdfAPI.setLicenseKey(utilPdfA.getConverterKey());

        Pdf2PdfAPI api = new Pdf2PdfAPI();
        if (nivelCompilacion == null) {
          api.setCompliance(NativeLibrary.COMPLIANCE.ePDFA2b);
        } else {
          api.setCompliance(nivelCompilacion);
        }
        api.setReportDetails(utilPdfA.isConverterReportDetails());
        api.setReportSummary(utilPdfA.isConverterReportSummary());
        api.setSubsetFonts(utilPdfA.isConverterSubsetFonts());
        api.setPostAnalyze(utilPdfA.isConverterPostAnalyze());

        int numberPages = utilPdfA.getPdfNumbersPages(pdfOriginal);
        // insercion en bbdd
        aplicacionConversionService.saveAplicacionConversionInfo(info.getIdApplicacion(),
            numberPages);

        boolean convert = api.convertMem2(IOUtils.toByteArray(new FileInputStream(pdfOriginal)),
            docEntrada.getPassword());

        if (convert) {
          retorno.setMime("application/pdf");
          retorno.setContenido(api.getPDF());
        } else {
          EstadoInfo estadoInfo = new EstadoInfo();
          estadoInfo.setDescripcion(new String(api.getLog(), XMLUtil.UTF8_CHARSET));
          throw new InSideException("Error al convertir a PDF/A: ", estadoInfo);
        }
      } else {
        retorno.setMime("application/pdf");
        retorno.setContenido(IOUtils.toByteArray(new FileInputStream(pdfOriginal)));
      }

      return retorno;
    } catch (NumberFormatException e) {
      logger.error("Error al convertir a PDF/A:" + e.getMessage());
      EstadoInfo estadoInfo = new EstadoInfo();
      throw new InSideException("Error al convertir a PDF/A: ", estadoInfo);
    } catch (FileNotFoundException e) {
      logger.error("Error al convertir a PDF/A:" + e.getMessage());
      EstadoInfo estadoInfo = new EstadoInfo();
      throw new InSideException("Error al convertir a PDF/A: ", estadoInfo);
    } catch (IOException e) {
      logger.error("Error al convertir a PDF/A:" + e.getMessage());
      EstadoInfo estadoInfo = new EstadoInfo();
      throw new InSideException("Error al convertir a PDF/A: ", estadoInfo);
    } catch (PdfConversionException e) {
      logger.error("Error al convertir a PDF/A:" + e.getMessage());
      EstadoInfo estadoInfo = new EstadoInfo();
      throw new InSideException("Error al convertir a PDF/A: ", estadoInfo);
    } finally {
      try {
        FileUtils.forceDelete(pdfOriginal);
      } catch (IOException e) {
        logger.warn("Error al eliminar fichero temporal:" + pdfOriginal.getAbsolutePath());
      }
    }
  }

  @Override
  public Boolean comprobarPDFA(ApplicationLogin info, DocumentoEntrada docEntrada,
      Integer nivelCompilacion) throws InSideException {
    return utilPdfA.isPDFA(docEntrada.getContenido(), docEntrada.getPassword(), nivelCompilacion);
  }

  @Override
  public SalidaVisualizacion visualizarFacturaePDF(ApplicationLogin info, byte[] factura,
      String version) throws InSideException {

    logger.debug("Inicio visualizarFacturaePDF");

    SalidaVisualizacion facturaPDFPost = new SalidaVisualizacion();

    DocumentoContenido contenido = new DocumentoContenido();

    try {
      // llamada al visualizarFacturae para obtener el html
      SalidaVisualizacion facturaHTML = visualizarFacturae(info, factura, version);

      // metodo que pasa de html a xhtml y desde xhtml a pdf
      byte[] facturaEPDF =
          htmlConverter.convertHTMLtoPDF(facturaHTML.getDocumentoContenido().getBytesDocumento());

      contenido.setBytesDocumento(facturaEPDF);
      contenido.setMimeDocumento("application/pdf");
      facturaPDFPost.setDocumentoContenido(contenido);


    } catch (TransformerFactoryConfigurationError e) {
      logger.error("Error inesperado visualizando Facturae", e);
      EstadoInfo estadoInfo = new EstadoInfo();
      throw new InSideException("Error visualizando Facturae: ", estadoInfo);
    }

    return facturaPDFPost;
  }
}
