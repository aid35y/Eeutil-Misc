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

package es.mpt.dsic.eeutil.operFirma.consumer.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import es.mpt.dsic.eeutil.operFirma.consumer.model.DatosFirmados;
import es.mpt.dsic.eeutil.operFirma.consumer.model.EeUtilService;
import es.mpt.dsic.eeutil.operFirma.consumer.model.EeUtilServiceImplService;
import es.mpt.dsic.eeutil.operFirma.consumer.model.InSideException;
import es.mpt.dsic.eeutil.operFirma.consumer.model.ResultadoValidacionInfo;
import es.mpt.dsic.inside.security.model.ApplicationLogin;

@Service
public class ConsumerEeutilOperFirmaImpl {

  protected static final Log logger = LogFactory.getLog(ConsumerEeutilOperFirmaImpl.class);

  private EeUtilService operFirmaWs;

  private Properties properties;

  private String truststore;
  private String passTruststore;

  public Properties getProperties() {
    return properties;
  }

  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  public String getTruststore() {
    return truststore;
  }

  public void setTruststore(String truststore) {
    this.truststore = truststore;
  }

  public String getPassTruststore() {
    return passTruststore;
  }

  public void setPassTruststore(String passTruststore) {
    this.passTruststore = passTruststore;
  }

  public void configure() throws InSideException {
    if (operFirmaWs == null) {
      URL urlOperFirma = null;
      String urlOper = null;
      try {
        urlOper = properties.getProperty("eeutil.operfirma.ws.url");
        logger.debug(String.format("El WS se encuentra en %s", urlOper));
        urlOperFirma = new URL(urlOper);

        System.setProperty("javax.net.ssl.trustStore", this.truststore);
        System.setProperty("javax.net.ssl.trustStorePassword", this.passTruststore);

        EeUtilServiceImplService ssOperFirma = new EeUtilServiceImplService(urlOperFirma);
        operFirmaWs = ssOperFirma.getEeUtilServiceImplPort();
      } catch (MalformedURLException e) {
        logger.error("No se puede crear la URL del servicio Eeutil OperFirma " + urlOper, e);
        throw new InSideException("No se puede crear la URL del servicio Eeutil OperFirma ", e);
      }
    }
  }

  public byte[] postProcesarFirma(ApplicationLogin info, byte[] firma) throws InSideException {
    configure();
    es.mpt.dsic.eeutil.operFirma.consumer.model.ApplicationLogin credential =
        new es.mpt.dsic.eeutil.operFirma.consumer.model.ApplicationLogin();
    credential.setIdaplicacion(info.getIdApplicacion());
    credential.setPassword(info.getPassword());
    return operFirmaWs.postProcesarFirma(credential, firma);
  }


  public ResultadoValidacionInfo validacionFirma(ApplicationLogin info, byte[] firma,
      String tipoFirma, DatosFirmados datosFirmados) throws InSideException {
    configure();
    es.mpt.dsic.eeutil.operFirma.consumer.model.ApplicationLogin credential =
        new es.mpt.dsic.eeutil.operFirma.consumer.model.ApplicationLogin();
    credential.setIdaplicacion(info.getIdApplicacion());
    credential.setPassword(info.getPassword());
    ResultadoValidacionInfo result =
        operFirmaWs.validacionFirma(credential, firma, tipoFirma, datosFirmados);
    return result;
  }

}
