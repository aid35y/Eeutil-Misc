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

package es.mpt.dsic.inside.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import es.mpt.dsic.inside.security.model.ApplicationLogin;
import es.mpt.dsic.inside.ws.service.model.RespuestaValidacionENI;
import es.mpt.dsic.inside.ws.service.model.Validaciones;



@WebService
public interface EeUtilValidacionENIService {

  @WebMethod(operationName = "validarDocumentoENI", action = "urn:validarDocumentoENI")
  @WebResult(name = "Resultado", partName = "Resultado")
  public RespuestaValidacionENI validarDocumentoENI(
      @WebParam(name = "aplicacionInfo") @XmlElement(required = true,
          name = "aplicacionInfo") ApplicationLogin info,
      @WebParam(name = "documentoENI") @XmlElement(required = true,
          name = "documentoENI") byte[] documentoENI,
      @WebParam(name = "versionENI") @XmlElement(required = false,
          name = "versionENI") String versionENI,
      @WebParam(name = "validaciones") @XmlElement(required = true,
          name = "validaciones") Validaciones validaciones);

  @WebMethod(operationName = "validarExpedienteENI", action = "urn:validarExpedienteENI")
  @WebResult(name = "Resultado", partName = "Resultado")
  public RespuestaValidacionENI validarExpedienteENI(
      @WebParam(name = "aplicacionInfo") @XmlElement(required = true,
          name = "aplicacionInfo") ApplicationLogin info,
      @WebParam(name = "expedienteENI") @XmlElement(required = true,
          name = "expedienteENI") byte[] expedienteENI,
      @WebParam(name = "versionENI") @XmlElement(required = false,
          name = "versionENI") String versionENI,
      @WebParam(name = "validaciones") @XmlElement(required = true,
          name = "validaciones") Validaciones validaciones);


  @WebMethod(operationName = "validarFirmaExpedienteENI", action = "urn:validarFirmaExpedienteENI")
  @WebResult(name = "Resultado", partName = "Resultado")
  public RespuestaValidacionENI validarFirmaExpedienteENI(
      @WebParam(name = "aplicacionInfo") @XmlElement(required = true,
          name = "aplicacionInfo") ApplicationLogin info,
      @WebParam(name = "expedienteENI") @XmlElement(required = true,
          name = "expedienteENI") byte[] expedienteENI);


  @WebMethod(operationName = "validarFirmaDocumentoENI", action = "urn:validarFirmaDocumentoENI")
  @WebResult(name = "Resultado", partName = "Resultado")
  public RespuestaValidacionENI validarFirmaDocumentoENI(
      @WebParam(name = "aplicacionInfo") @XmlElement(required = true,
          name = "aplicacionInfo") ApplicationLogin info,
      @WebParam(name = "documentoENI") @XmlElement(required = true,
          name = "documentoENI") byte[] documentoENI);


  @WebMethod(operationName = "validarExpedienteDocumentosENIZIP",
      action = "urn:validarExpedienteDocumentosENIZIP")
  @WebResult(name = "Resultado", partName = "Resultado")
  public RespuestaValidacionENI validarExpedienteDocumentosENIZIP(
      @WebParam(name = "aplicacionInfo") @XmlElement(required = true,
          name = "aplicacionInfo") ApplicationLogin info,
      @WebParam(name = "expedienteENIConDocumentosENI") @XmlElement(required = true,
          name = "expedienteENIConDocumentosENI") byte[] expedienteENIConDocumentosENI,
      @WebParam(name = "versionENI") @XmlElement(required = false,
          name = "versionENI") String versionENI,
      @WebParam(name = "validacionesExpediente") @XmlElement(required = true,
          name = "validacionesExpediente") Validaciones validacionesExpediente,
      @WebParam(name = "validacionesDocumentos") @XmlElement(required = true,
          name = "validacionesDocumentos") Validaciones validacionesDocumentos);


}
