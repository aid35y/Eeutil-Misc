/* Copyright (C) 2012-13 MINHAP, Gobierno de España
   This program is licensed and may be used, modified and redistributed under the terms
   of the European Public License (EUPL), either version 1.1 or (at your
   option) any later version as soon as they are approved by the European Commission.
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
   or implied. See the License for the specific language governing permissions and
   more details.
   You should have received a copy of the EUPL1.1 license
   along with this program; if not, you may find it at
   http://joinup.ec.europa.eu/software/page/eupl/licence-eupl */

package es.mpt.dsic.eeutil.operFirma.consumer.model;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.0.3
 * 2016-01-28T11:15:49.395+01:00
 * Generated source version: 3.0.3
 * 
 */
@WebService(targetNamespace = "http://service.ws.inside.dsic.mpt.es/", name = "EeUtilService")
@XmlSeeAlso({ObjectFactory.class})
public interface EeUtilService {

    @WebResult(name = "ResultadoValidacionInfo", targetNamespace = "")
    @RequestWrapper(localName = "validacionFirma", targetNamespace = "http://service.ws.inside.dsic.mpt.es/", className = "es.mpt.dsic.eeutil.operFirma.consumer.model.ValidacionFirma")
    @WebMethod(action = "urn:validacionFirma")
    @ResponseWrapper(localName = "validacionFirmaResponse", targetNamespace = "http://service.ws.inside.dsic.mpt.es/", className = "es.mpt.dsic.eeutil.operFirma.consumer.model.ValidacionFirmaResponse")
    public es.mpt.dsic.eeutil.operFirma.consumer.model.ResultadoValidacionInfo validacionFirma(
        @WebParam(name = "aplicacionInfo", targetNamespace = "")
        es.mpt.dsic.eeutil.operFirma.consumer.model.ApplicationLogin aplicacionInfo,
        @WebParam(name = "Firma", targetNamespace = "")
        byte[] firma,
        @WebParam(name = "TipoFirma", targetNamespace = "")
        java.lang.String tipoFirma,
        @WebParam(name = "DatosFirmados", targetNamespace = "")
        es.mpt.dsic.eeutil.operFirma.consumer.model.DatosFirmados datosFirmados
    ) throws InSideException;

    @WebResult(name = "FirmaProcesada", targetNamespace = "")
    @RequestWrapper(localName = "postProcesarFirma", targetNamespace = "http://service.ws.inside.dsic.mpt.es/", className = "es.mpt.dsic.eeutil.operFirma.consumer.model.PostProcesarFirma")
    @WebMethod(action = "urn:postProcesarFirma")
    @ResponseWrapper(localName = "postProcesarFirmaResponse", targetNamespace = "http://service.ws.inside.dsic.mpt.es/", className = "es.mpt.dsic.eeutil.operFirma.consumer.model.PostProcesarFirmaResponse")
    public byte[] postProcesarFirma(
        @WebParam(name = "aplicacionInfo", targetNamespace = "")
        es.mpt.dsic.eeutil.operFirma.consumer.model.ApplicationLogin aplicacionInfo,
        @WebParam(name = "Firma", targetNamespace = "")
        byte[] firma
    ) throws InSideException;

    @WebResult(name = "resultadoObtenerInformacionFirma", targetNamespace = "")
    @RequestWrapper(localName = "obtenerInformacionFirma", targetNamespace = "http://service.ws.inside.dsic.mpt.es/", className = "es.mpt.dsic.eeutil.operFirma.consumer.model.ObtenerInformacionFirma")
    @WebMethod(action = "urn:obtenerInformacionFirma")
    @ResponseWrapper(localName = "obtenerInformacionFirmaResponse", targetNamespace = "http://service.ws.inside.dsic.mpt.es/", className = "es.mpt.dsic.eeutil.operFirma.consumer.model.ObtenerInformacionFirmaResponse")
    public es.mpt.dsic.eeutil.operFirma.consumer.model.InformacionFirma obtenerInformacionFirma(
        @WebParam(name = "aplicacionInfo", targetNamespace = "")
        es.mpt.dsic.eeutil.operFirma.consumer.model.ApplicationLogin aplicacionInfo,
        @WebParam(name = "Firma", targetNamespace = "")
        byte[] firma,
        @WebParam(name = "opcionesObtenerInformacionFirma", targetNamespace = "")
        es.mpt.dsic.eeutil.operFirma.consumer.model.OpcionesObtenerInformacionFirma opcionesObtenerInformacionFirma,
        @WebParam(name = "Contenido", targetNamespace = "")
        byte[] contenido
    ) throws InSideException;

    @WebResult(name = "resultadoValidarCertificado", targetNamespace = "")
    @RequestWrapper(localName = "validarCertificado", targetNamespace = "http://service.ws.inside.dsic.mpt.es/", className = "es.mpt.dsic.eeutil.operFirma.consumer.model.ValidarCertificado")
    @WebMethod(action = "urn:validarCertificado")
    @ResponseWrapper(localName = "validarCertificadoResponse", targetNamespace = "http://service.ws.inside.dsic.mpt.es/", className = "es.mpt.dsic.eeutil.operFirma.consumer.model.ValidarCertificadoResponse")
    public es.mpt.dsic.eeutil.operFirma.consumer.model.ResultadoValidarCertificado validarCertificado(
        @WebParam(name = "aplicacionInfo", targetNamespace = "")
        es.mpt.dsic.eeutil.operFirma.consumer.model.ApplicationLogin aplicacionInfo,
        @WebParam(name = "certificate", targetNamespace = "")
        java.lang.String certificate
    ) throws InSideException;

    @WebResult(name = "resultadoAmpliarFirma", targetNamespace = "")
    @RequestWrapper(localName = "ampliarFirma", targetNamespace = "http://service.ws.inside.dsic.mpt.es/", className = "es.mpt.dsic.eeutil.operFirma.consumer.model.AmpliarFirma")
    @WebMethod(action = "urn:ampliarFirma")
    @ResponseWrapper(localName = "ampliarFirmaResponse", targetNamespace = "http://service.ws.inside.dsic.mpt.es/", className = "es.mpt.dsic.eeutil.operFirma.consumer.model.AmpliarFirmaResponse")
    public es.mpt.dsic.eeutil.operFirma.consumer.model.ResultadoAmpliarFirma ampliarFirma(
        @WebParam(name = "aplicacionInfo", targetNamespace = "")
        es.mpt.dsic.eeutil.operFirma.consumer.model.ApplicationLogin aplicacionInfo,
        @WebParam(name = "Firma", targetNamespace = "")
        byte[] firma,
        @WebParam(name = "configuracionAmpliarFirma", targetNamespace = "")
        es.mpt.dsic.eeutil.operFirma.consumer.model.ConfiguracionAmpliarFirma configuracionAmpliarFirma
    ) throws InSideException;

    @WebResult(name = "ListaFirmantes", targetNamespace = "")
    @RequestWrapper(localName = "obtenerFirmantes", targetNamespace = "http://service.ws.inside.dsic.mpt.es/", className = "es.mpt.dsic.eeutil.operFirma.consumer.model.ObtenerFirmantes")
    @WebMethod(action = "urn:obtenerFirmantes")
    @ResponseWrapper(localName = "obtenerFirmantesResponse", targetNamespace = "http://service.ws.inside.dsic.mpt.es/", className = "es.mpt.dsic.eeutil.operFirma.consumer.model.ObtenerFirmantesResponse")
    public es.mpt.dsic.eeutil.operFirma.consumer.model.ListaFirmaInfo obtenerFirmantes(
        @WebParam(name = "aplicacionInfo", targetNamespace = "")
        es.mpt.dsic.eeutil.operFirma.consumer.model.ApplicationLogin aplicacionInfo,
        @WebParam(name = "Firma", targetNamespace = "")
        byte[] firma,
        @WebParam(name = "Datos", targetNamespace = "")
        byte[] datos,
        @WebParam(name = "TipoFirma", targetNamespace = "")
        java.lang.String tipoFirma
    ) throws InSideException;
}