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

package es.mpt.dsic.inside.ws.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.lowagie.text.pdf.PdfReader;
import com.pdftools.NativeLibrary;
import com.pdftools.pdfvalidator.PdfValidatorAPI;
import es.mpt.dsic.inside.config.EeutilApplicationDataConfig;
import es.mpt.dsic.inside.pdf.PdfUtils;
import es.mpt.dsic.inside.utils.file.FileUtil;
import es.mpt.dsic.inside.ws.service.exception.InSideException;
import es.mpt.dsic.inside.ws.service.model.EstadoInfo;

public class UtilPdfA {

  protected final static Log logger = LogFactory.getLog(UtilPdfA.class);//

  // Converter properties
  private String converterLibrary;
  private String converterKey;
  private boolean converterReportDetails;
  private boolean converterReportSummary;
  private boolean converterSubsetFonts;
  private boolean converterPostAnalyze;

  // Validator properties
  private String validatorLibrary;
  private String validatorKey;
  private int validatorReportingLevel;

  public void configure() {
    try {
      // converter
      logger.warn("Configurando PDF/A Converter, cargando libreria:"
          + EeutilApplicationDataConfig.CONFIG_PATH + File.separator + converterLibrary);
      System.load(EeutilApplicationDataConfig.CONFIG_PATH + File.separator + converterLibrary);

      // validator
      logger.warn("Configurando PDF/A Validator, cargando libreria:"
          + EeutilApplicationDataConfig.CONFIG_PATH + File.separator + validatorLibrary);
      System.load(EeutilApplicationDataConfig.CONFIG_PATH + File.separator + validatorLibrary);
    } catch (UnsatisfiedLinkError e) {
      logger.error("Error cargando libreria.\n" + e);
    }
  }

  public String getConverterLibrary() {
    return converterLibrary;
  }

  public void setConverterLibrary(String converterLibrary) {
    this.converterLibrary = converterLibrary;
  }

  public String getConverterKey() {
    return converterKey;
  }

  public void setConverterKey(String converterKey) {
    this.converterKey = converterKey;
  }

  public boolean isConverterReportDetails() {
    return converterReportDetails;
  }

  public void setConverterReportDetails(boolean converterReportDetails) {
    this.converterReportDetails = converterReportDetails;
  }

  public boolean isConverterReportSummary() {
    return converterReportSummary;
  }

  public void setConverterReportSummary(boolean converterReportSummary) {
    this.converterReportSummary = converterReportSummary;
  }

  public boolean isConverterSubsetFonts() {
    return converterSubsetFonts;
  }

  public void setConverterSubsetFonts(boolean converterSubsetFonts) {
    this.converterSubsetFonts = converterSubsetFonts;
  }

  public boolean isConverterPostAnalyze() {
    return converterPostAnalyze;
  }

  public void setConverterPostAnalyze(boolean converterPostAnalyze) {
    this.converterPostAnalyze = converterPostAnalyze;
  }

  public String getValidatorLibrary() {
    return validatorLibrary;
  }

  public void setValidatorLibrary(String validatorLibrary) {
    this.validatorLibrary = validatorLibrary;
  }

  public String getValidatorKey() {
    return validatorKey;
  }

  public void setValidatorKey(String validatorKey) {
    this.validatorKey = validatorKey;
  }

  public int getValidatorReportingLevel() {
    return validatorReportingLevel;
  }

  public void setValidatorReportingLevel(int validatorReportingLevel) {
    this.validatorReportingLevel = validatorReportingLevel;
  }

  public Boolean isPDFA(byte[] data, String password, Integer level) throws InSideException {
    PdfValidatorAPI.setLicenseKey(getValidatorKey());

    PdfValidatorAPI pdfValidator = new PdfValidatorAPI();
    pdfValidator.setStopOnError(true);
    pdfValidator.setReportingLevel(getValidatorReportingLevel());

    if (password == null) {
      password = "";
    }

    boolean validate = false;

    if (level == null) {
      validate = pdfValidator.open(data, password, NativeLibrary.COMPLIANCE.ePDFA2b);
    } else {
      validate = pdfValidator.open(data, password, level);
    }

    if (!validate) {
      if (pdfValidator.getErrorCode() == NativeLibrary.ERRORCODE.PDF_E_PASSWORD) {
        logger.error("Error al comprobar a PDF/A");
        EstadoInfo estadoInfo = new EstadoInfo();
        throw new InSideException("El documento esta cifrado. Password requirida.", estadoInfo);
      } else {
        logger.error("Error al comprobar a PDF/A");
        EstadoInfo estadoInfo = new EstadoInfo();
        throw new InSideException(
            String.format("No es posible abrir el documento: 0x%08X", pdfValidator.getErrorCode()),
            estadoInfo);
      }
    }

    if (!pdfValidator.validate()) {
      return false;
    } else {
      return (pdfValidator.getErrorCode() != NativeLibrary.ERRORCODE.PDF_E_CONFORMANCE);
    }
  }

  public int getPdfNumbersPages(File f) throws InSideException, IOException {
    FileInputStream fis = null;
    PdfReader r = null;
    int retorno;
    try {
      fis = new FileInputStream(f);
      r = new PdfReader(fis);

      retorno = r.getNumberOfPages();
    } catch (FileNotFoundException e) {
      logger.error(e.getMessage());
      EstadoInfo estadoInfo = new EstadoInfo();
      throw new InSideException("Error al leer el numero de paginas a convertir.", estadoInfo);
    } catch (IOException e) {
      logger.error(e.getMessage());
      EstadoInfo estadoInfo = new EstadoInfo();
      throw new InSideException("Error al leer el numero de paginas a convertir.", estadoInfo);
    } finally {
      FileUtil.close(fis);
      PdfUtils.close(r);
    }
    return retorno;
  }

  public boolean validateSimple(byte[] data) {
    System.out.println("Licencia:" + getValidatorKey());
    PdfValidatorAPI.setLicenseKey(getValidatorKey());

    // Check license
    if (!PdfValidatorAPI.getLicenseIsValid()) {
      System.out.println("No valid license found:");
    }

    // Create object
    PdfValidatorAPI doc = new PdfValidatorAPI();
    doc.setStopOnError(true);
    doc.setReportingLevel(0);

    try {
      // Open document, set PDF/A-2b compliance
      if (!doc.open(data, "", NativeLibrary.COMPLIANCE.ePDFA2b)) {
        if (doc.getErrorCode() == NativeLibrary.ERRORCODE.PDF_E_PASSWORD)
          throw new Exception("Document is encrypted. Password required.");
        else
          throw new Exception("Unable to open document");
      }

      // Validate document
      if (!doc.validate())
        throw new Exception(
            String.format("Unable to validate document (error 0x%08X).", doc.getErrorCode()));

      boolean isCompliant = (doc.getErrorCode() != NativeLibrary.ERRORCODE.PDF_E_CONFORMANCE);

      // Print result
      System.out.printf("Document is %scompliant with the PDF/A-2b standard.\n",
          isCompliant ? "" : "not ");
    } catch (Throwable e) {
      System.out.println(e.getMessage());
      return false;
    } finally {
      // Clean up
      doc.close();
      doc.destroyObject();
    }

    return true;
  }

}
