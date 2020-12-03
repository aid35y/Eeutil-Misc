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


package es.mpt.dsic.eeutil.service.sia.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para anonymous complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="ERROR" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DESCERROR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ERROR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ACTUACIONES" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ACTUACION" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="CODIGOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="TIPOTRAMITE" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}TIPOTRAMITE"/>
 *                             &lt;element name="INTERNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="COMUN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DENOMINACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TITULOCIUDADANO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DESCRIPCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ORGANISMORESPONSABLE" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}ORGANISMORESPONSABLE" minOccurs="0"/>
 *                             &lt;element name="DESTINATARIOS" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}DESTINATARIOS" minOccurs="0"/>
 *                             &lt;element name="SUJETOATASAS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DESCPERIODICIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CODPERIODICIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="REQUISITOSINICIACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ADAPTABILIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CODNIVELADMINISTRACIONELECTRONICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DESNIVELADMINISTRACIONELECTRONICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CODREQUISITOSIDENTPJ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DESREQUISITOSIDENTPJ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CODREQUISITOSIDENTPF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DESREQUISITOSIDENTPF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NOTIFICACIONES" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}NOTIFICACIONES" minOccurs="0"/>
 *                             &lt;element name="CANALESACCESO" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}CANALESACCESO" minOccurs="0"/>
 *                             &lt;element name="SISTEMASIDENTIFICACION" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}SISTEMASIDENTIFICACION" minOccurs="0"/>
 *                             &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CODPORTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DESPORTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="IDINTEGRADOCLAVE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DESINTEGRADOCLAVE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OBSERVACIONINTEGRADOCLAVE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="REDUCCIONES" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}REDUCCIONES" minOccurs="0"/>
 *                             &lt;element name="TIEMPOMEDIORESOLUCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VOLTRAMITACIONESACTUAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VOLUMENESTRAMITACIONES" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}VOLUMENESTRAMITACIONES" minOccurs="0"/>
 *                             &lt;element name="VOLNOTIFICACIONESACTUAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VOLUMENNOTIFICACIONES" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}VOLUMENNOTIFICACIONES" minOccurs="0"/>
 *                             &lt;element name="MATERIAS" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}MATERIAS" minOccurs="0"/>
 *                             &lt;element name="CODCLASETRAMITE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DESTIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="HECHOSVITALES" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}HECHOSVITALES" minOccurs="0"/>
 *                             &lt;element name="TRAMITESRELACIONADOS" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}TRAMITESRELACIONADOS" minOccurs="0"/>
 *                             &lt;element name="PRESENTACIONPRESENCIAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="LUGARPRESENTACION" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}LUGARPRESENTACION" minOccurs="0"/>
 *                             &lt;element name="FORMULARIOS" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}FORMULARIOS" minOccurs="0"/>
 *                             &lt;element name="INICIOS" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}INICIOS" minOccurs="0"/>
 *                             &lt;element name="PLAZORESOLUCION" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}PLAZORESOLUCION" minOccurs="0"/>
 *                             &lt;element name="FINVIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NORMATIVAS" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}NORMATIVAS" minOccurs="0"/>
 *                             &lt;element name="NOREQUIEREDOCUMENTACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DOCUMENTACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DOCUMENTOSCATALOGO" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}DOCUMENTOSCATALOGO" minOccurs="0"/>
 *                             &lt;element name="CODESTADOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DESESTADOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="FECHAULTIMAACTUALIZACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"error", "actuaciones"})
@XmlRootElement(name = "enviaSIA")
public class EnviaSIA2 {

  @XmlElement(name = "ERROR")
  protected EnviaSIA2.ERROR error;
  @XmlElement(name = "ACTUACIONES")
  protected EnviaSIA2.ACTUACIONES actuaciones;

  /**
   * Obtiene el valor de la propiedad error.
   * 
   * @return possible object is {@link EnviaSIA2 .ERROR }
   * 
   */
  public EnviaSIA2.ERROR getERROR() {
    return error;
  }

  /**
   * Define el valor de la propiedad error.
   * 
   * @param value allowed object is {@link EnviaSIA2 .ERROR }
   * 
   */
  public void setERROR(EnviaSIA2.ERROR value) {
    this.error = value;
  }

  /**
   * Obtiene el valor de la propiedad actuaciones.
   * 
   * @return possible object is {@link EnviaSIA2 .ACTUACIONES }
   * 
   */
  public EnviaSIA2.ACTUACIONES getACTUACIONES() {
    return actuaciones;
  }

  /**
   * Define el valor de la propiedad actuaciones.
   * 
   * @param value allowed object is {@link EnviaSIA2 .ACTUACIONES }
   * 
   */
  public void setACTUACIONES(EnviaSIA2.ACTUACIONES value) {
    this.actuaciones = value;
  }


  /**
   * <p>
   * Clase Java para anonymous complex type.
   * 
   * <p>
   * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
   * 
   * <pre>
   * &lt;complexType>
   *   &lt;complexContent>
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *       &lt;sequence>
   *         &lt;element name="ACTUACION" maxOccurs="unbounded" minOccurs="0">
   *           &lt;complexType>
   *             &lt;complexContent>
   *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                 &lt;sequence>
   *                   &lt;element name="CODIGOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *                   &lt;element name="TIPOTRAMITE" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}TIPOTRAMITE"/>
   *                   &lt;element name="INTERNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="COMUN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="DENOMINACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="TITULOCIUDADANO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="DESCRIPCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="ORGANISMORESPONSABLE" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}ORGANISMORESPONSABLE" minOccurs="0"/>
   *                   &lt;element name="DESTINATARIOS" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}DESTINATARIOS" minOccurs="0"/>
   *                   &lt;element name="SUJETOATASAS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="DESCPERIODICIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="CODPERIODICIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="REQUISITOSINICIACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="ADAPTABILIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="CODNIVELADMINISTRACIONELECTRONICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="DESNIVELADMINISTRACIONELECTRONICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="CODREQUISITOSIDENTPJ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="DESREQUISITOSIDENTPJ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="CODREQUISITOSIDENTPF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="DESREQUISITOSIDENTPF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="NOTIFICACIONES" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}NOTIFICACIONES" minOccurs="0"/>
   *                   &lt;element name="CANALESACCESO" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}CANALESACCESO" minOccurs="0"/>
   *                   &lt;element name="SISTEMASIDENTIFICACION" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}SISTEMASIDENTIFICACION" minOccurs="0"/>
   *                   &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="CODPORTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="DESPORTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="IDINTEGRADOCLAVE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="DESINTEGRADOCLAVE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="OBSERVACIONINTEGRADOCLAVE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="REDUCCIONES" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}REDUCCIONES" minOccurs="0"/>
   *                   &lt;element name="TIEMPOMEDIORESOLUCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="VOLTRAMITACIONESACTUAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="VOLUMENESTRAMITACIONES" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}VOLUMENESTRAMITACIONES" minOccurs="0"/>
   *                   &lt;element name="VOLNOTIFICACIONESACTUAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="VOLUMENNOTIFICACIONES" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}VOLUMENNOTIFICACIONES" minOccurs="0"/>
   *                   &lt;element name="MATERIAS" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}MATERIAS" minOccurs="0"/>
   *                   &lt;element name="CODCLASETRAMITE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="DESTIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="HECHOSVITALES" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}HECHOSVITALES" minOccurs="0"/>
   *                   &lt;element name="TRAMITESRELACIONADOS" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}TRAMITESRELACIONADOS" minOccurs="0"/>
   *                   &lt;element name="PRESENTACIONPRESENCIAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="LUGARPRESENTACION" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}LUGARPRESENTACION" minOccurs="0"/>
   *                   &lt;element name="FORMULARIOS" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}FORMULARIOS" minOccurs="0"/>
   *                   &lt;element name="INICIOS" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}INICIOS" minOccurs="0"/>
   *                   &lt;element name="PLAZORESOLUCION" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}PLAZORESOLUCION" minOccurs="0"/>
   *                   &lt;element name="FINVIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="NORMATIVAS" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}NORMATIVAS" minOccurs="0"/>
   *                   &lt;element name="NOREQUIEREDOCUMENTACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="DOCUMENTACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="DOCUMENTOSCATALOGO" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}DOCUMENTOSCATALOGO" minOccurs="0"/>
   *                   &lt;element name="CODESTADOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="DESESTADOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                   &lt;element name="FECHAULTIMAACTUALIZACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *                 &lt;/sequence>
   *               &lt;/restriction>
   *             &lt;/complexContent>
   *           &lt;/complexType>
   *         &lt;/element>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"actuacion"})
  public static class ACTUACIONES {

    @XmlElement(name = "ACTUACION")
    protected List<EnviaSIA2.ACTUACIONES.ACTUACION> actuacion;

    /**
     * Gets the value of the actuacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any
     * modification you make to the returned list will be present inside the JAXB object. This is
     * why there is not a <CODE>set</CODE> method for the actuacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getACTUACION().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnviaSIA2 .ACTUACIONES.ACTUACION }
     * 
     * 
     */
    public List<EnviaSIA2.ACTUACIONES.ACTUACION> getACTUACION() {
      if (actuacion == null) {
        actuacion = new ArrayList<EnviaSIA2.ACTUACIONES.ACTUACION>();
      }
      return this.actuacion;
    }


    /**
     * <p>
     * Clase Java para anonymous complex type.
     * 
     * <p>
     * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta
     * clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="CODIGOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TIPOTRAMITE" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}TIPOTRAMITE"/>
     *         &lt;element name="INTERNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="COMUN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DENOMINACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="TITULOCIUDADANO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DESCRIPCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ORGANISMORESPONSABLE" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}ORGANISMORESPONSABLE" minOccurs="0"/>
     *         &lt;element name="DESTINATARIOS" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}DESTINATARIOS" minOccurs="0"/>
     *         &lt;element name="SUJETOATASAS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DESCPERIODICIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="CODPERIODICIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="REQUISITOSINICIACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ADAPTABILIDAD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="CODNIVELADMINISTRACIONELECTRONICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DESNIVELADMINISTRACIONELECTRONICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="CODREQUISITOSIDENTPJ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DESREQUISITOSIDENTPJ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="CODREQUISITOSIDENTPF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DESREQUISITOSIDENTPF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="NOTIFICACIONES" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}NOTIFICACIONES" minOccurs="0"/>
     *         &lt;element name="CANALESACCESO" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}CANALESACCESO" minOccurs="0"/>
     *         &lt;element name="SISTEMASIDENTIFICACION" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}SISTEMASIDENTIFICACION" minOccurs="0"/>
     *         &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="CODPORTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DESPORTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="IDINTEGRADOCLAVE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DESINTEGRADOCLAVE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="OBSERVACIONINTEGRADOCLAVE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="REDUCCIONES" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}REDUCCIONES" minOccurs="0"/>
     *         &lt;element name="TIEMPOMEDIORESOLUCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="VOLTRAMITACIONESACTUAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="VOLUMENESTRAMITACIONES" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}VOLUMENESTRAMITACIONES" minOccurs="0"/>
     *         &lt;element name="VOLNOTIFICACIONESACTUAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="VOLUMENNOTIFICACIONES" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}VOLUMENNOTIFICACIONES" minOccurs="0"/>
     *         &lt;element name="MATERIAS" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}MATERIAS" minOccurs="0"/>
     *         &lt;element name="CODCLASETRAMITE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DESTIPOLOGIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="HECHOSVITALES" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}HECHOSVITALES" minOccurs="0"/>
     *         &lt;element name="TRAMITESRELACIONADOS" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}TRAMITESRELACIONADOS" minOccurs="0"/>
     *         &lt;element name="PRESENTACIONPRESENCIAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="LUGARPRESENTACION" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}LUGARPRESENTACION" minOccurs="0"/>
     *         &lt;element name="FORMULARIOS" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}FORMULARIOS" minOccurs="0"/>
     *         &lt;element name="INICIOS" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}INICIOS" minOccurs="0"/>
     *         &lt;element name="PLAZORESOLUCION" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}PLAZORESOLUCION" minOccurs="0"/>
     *         &lt;element name="FINVIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="NORMATIVAS" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}NORMATIVAS" minOccurs="0"/>
     *         &lt;element name="NOREQUIEREDOCUMENTACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DOCUMENTACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DOCUMENTOSCATALOGO" type="{http://www.map.es/sgca/consultar/messages/v3_0/EnviaSIA}DOCUMENTOSCATALOGO" minOccurs="0"/>
     *         &lt;element name="CODESTADOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DESESTADOACTUACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="FECHAULTIMAACTUALIZACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "",
        propOrder = {"codigoactuacion", "tipotramite", "interno", "comun", "denominacion",
            "titulociudadano", "descripcion", "organismoresponsable", "destinatarios",
            "sujetoatasas", "descperiodicidad", "codperiodicidad", "requisitosiniciacion",
            "adaptabilidad", "codniveladministracionelectronica",
            "desniveladministracionelectronica", "codrequisitosidentpj", "desrequisitosidentpj",
            "codrequisitosidentpf", "desrequisitosidentpf", "notificaciones", "canalesacceso",
            "sistemasidentificacion", "url", "codportal", "desportal", "idintegradoclave",
            "desintegradoclave", "observacionintegradoclave", "reducciones",
            "tiempomedioresolucion", "voltramitacionesactual", "volumenestramitaciones",
            "volnotificacionesactual", "volumennotificaciones", "materias", "codclasetramite",
            "destipologia", "hechosvitales", "tramitesrelacionados", "presentacionpresencial",
            "lugarpresentacion", "formularios", "inicios", "plazoresolucion", "finvia",
            "normativas", "norequieredocumentacion", "documentacion", "documentoscatalogo",
            "codestadoactuacion", "desestadoactuacion", "fechaultimaactualizacion"})
    public static class ACTUACION {

      @XmlElement(name = "CODIGOACTUACION", required = true)
      protected String codigoactuacion;
      @XmlElement(name = "TIPOTRAMITE", required = true)
      protected TIPOTRAMITE2 tipotramite;
      @XmlElement(name = "INTERNO")
      protected String interno;
      @XmlElement(name = "COMUN")
      protected String comun;
      @XmlElement(name = "DENOMINACION")
      protected String denominacion;
      @XmlElement(name = "TITULOCIUDADANO")
      protected String titulociudadano;
      @XmlElement(name = "DESCRIPCION")
      protected String descripcion;
      @XmlElement(name = "ORGANISMORESPONSABLE")
      protected ORGANISMORESPONSABLE2 organismoresponsable;
      @XmlElement(name = "DESTINATARIOS")
      protected DESTINATARIOS2 destinatarios;
      @XmlElement(name = "SUJETOATASAS")
      protected String sujetoatasas;
      @XmlElement(name = "DESCPERIODICIDAD")
      protected String descperiodicidad;
      @XmlElement(name = "CODPERIODICIDAD")
      protected String codperiodicidad;
      @XmlElement(name = "REQUISITOSINICIACION")
      protected String requisitosiniciacion;
      @XmlElement(name = "ADAPTABILIDAD")
      protected String adaptabilidad;
      @XmlElement(name = "CODNIVELADMINISTRACIONELECTRONICA")
      protected String codniveladministracionelectronica;
      @XmlElement(name = "DESNIVELADMINISTRACIONELECTRONICA")
      protected String desniveladministracionelectronica;
      @XmlElement(name = "CODREQUISITOSIDENTPJ")
      protected String codrequisitosidentpj;
      @XmlElement(name = "DESREQUISITOSIDENTPJ")
      protected String desrequisitosidentpj;
      @XmlElement(name = "CODREQUISITOSIDENTPF")
      protected String codrequisitosidentpf;
      @XmlElement(name = "DESREQUISITOSIDENTPF")
      protected String desrequisitosidentpf;
      @XmlElement(name = "NOTIFICACIONES")
      protected NOTIFICACIONES notificaciones;
      @XmlElement(name = "CANALESACCESO")
      protected CANALESACCESO canalesacceso;
      @XmlElement(name = "SISTEMASIDENTIFICACION")
      protected SISTEMASIDENTIFICACION2 sistemasidentificacion;
      @XmlElement(name = "URL")
      protected String url;
      @XmlElement(name = "CODPORTAL")
      protected String codportal;
      @XmlElement(name = "DESPORTAL")
      protected String desportal;
      @XmlElement(name = "IDINTEGRADOCLAVE")
      protected String idintegradoclave;
      @XmlElement(name = "DESINTEGRADOCLAVE")
      protected String desintegradoclave;
      @XmlElement(name = "OBSERVACIONINTEGRADOCLAVE")
      protected String observacionintegradoclave;
      @XmlElement(name = "REDUCCIONES")
      protected REDUCCIONES2 reducciones;
      @XmlElement(name = "TIEMPOMEDIORESOLUCION")
      protected String tiempomedioresolucion;
      @XmlElement(name = "VOLTRAMITACIONESACTUAL")
      protected String voltramitacionesactual;
      @XmlElement(name = "VOLUMENESTRAMITACIONES")
      protected VOLUMENESTRAMITACIONES2 volumenestramitaciones;
      @XmlElement(name = "VOLNOTIFICACIONESACTUAL")
      protected String volnotificacionesactual;
      @XmlElement(name = "VOLUMENNOTIFICACIONES")
      protected VOLUMENNOTIFICACIONES volumennotificaciones;
      @XmlElement(name = "MATERIAS")
      protected MATERIAS materias;
      @XmlElement(name = "CODCLASETRAMITE")
      protected String codclasetramite;
      @XmlElement(name = "DESTIPOLOGIA")
      protected String destipologia;
      @XmlElement(name = "HECHOSVITALES")
      protected HECHOSVITALES hechosvitales;
      @XmlElement(name = "TRAMITESRELACIONADOS")
      protected TRAMITESRELACIONADOS2 tramitesrelacionados;
      @XmlElement(name = "PRESENTACIONPRESENCIAL")
      protected String presentacionpresencial;
      @XmlElement(name = "LUGARPRESENTACION")
      protected LUGARPRESENTACION2 lugarpresentacion;
      @XmlElement(name = "FORMULARIOS")
      protected FORMULARIOS formularios;
      @XmlElement(name = "INICIOS")
      protected INICIOS2 inicios;
      @XmlElement(name = "PLAZORESOLUCION")
      protected PLAZORESOLUCION2 plazoresolucion;
      @XmlElement(name = "FINVIA")
      protected String finvia;
      @XmlElement(name = "NORMATIVAS")
      protected NORMATIVAS2 normativas;
      @XmlElement(name = "NOREQUIEREDOCUMENTACION")
      protected String norequieredocumentacion;
      @XmlElement(name = "DOCUMENTACION")
      protected String documentacion;
      @XmlElement(name = "DOCUMENTOSCATALOGO")
      protected DOCUMENTOSCATALOGO2 documentoscatalogo;
      @XmlElement(name = "CODESTADOACTUACION")
      protected String codestadoactuacion;
      @XmlElement(name = "DESESTADOACTUACION")
      protected String desestadoactuacion;
      @XmlElement(name = "FECHAULTIMAACTUALIZACION")
      protected String fechaultimaactualizacion;

      /**
       * Obtiene el valor de la propiedad codigoactuacion.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getCODIGOACTUACION() {
        return codigoactuacion;
      }

      /**
       * Define el valor de la propiedad codigoactuacion.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setCODIGOACTUACION(String value) {
        this.codigoactuacion = value;
      }

      /**
       * Obtiene el valor de la propiedad tipotramite.
       * 
       * @return possible object is {@link TIPOTRAMITE2 }
       * 
       */
      public TIPOTRAMITE2 getTIPOTRAMITE() {
        return tipotramite;
      }

      /**
       * Define el valor de la propiedad tipotramite.
       * 
       * @param value allowed object is {@link TIPOTRAMITE2 }
       * 
       */
      public void setTIPOTRAMITE(TIPOTRAMITE2 value) {
        this.tipotramite = value;
      }

      /**
       * Obtiene el valor de la propiedad interno.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getINTERNO() {
        return interno;
      }

      /**
       * Define el valor de la propiedad interno.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setINTERNO(String value) {
        this.interno = value;
      }

      /**
       * Obtiene el valor de la propiedad comun.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getCOMUN() {
        return comun;
      }

      /**
       * Define el valor de la propiedad comun.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setCOMUN(String value) {
        this.comun = value;
      }

      /**
       * Obtiene el valor de la propiedad denominacion.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getDENOMINACION() {
        return denominacion;
      }

      /**
       * Define el valor de la propiedad denominacion.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setDENOMINACION(String value) {
        this.denominacion = value;
      }

      /**
       * Obtiene el valor de la propiedad titulociudadano.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getTITULOCIUDADANO() {
        return titulociudadano;
      }

      /**
       * Define el valor de la propiedad titulociudadano.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setTITULOCIUDADANO(String value) {
        this.titulociudadano = value;
      }

      /**
       * Obtiene el valor de la propiedad descripcion.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getDESCRIPCION() {
        return descripcion;
      }

      /**
       * Define el valor de la propiedad descripcion.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setDESCRIPCION(String value) {
        this.descripcion = value;
      }

      /**
       * Obtiene el valor de la propiedad organismoresponsable.
       * 
       * @return possible object is {@link ORGANISMORESPONSABLE2 }
       * 
       */
      public ORGANISMORESPONSABLE2 getORGANISMORESPONSABLE() {
        return organismoresponsable;
      }

      /**
       * Define el valor de la propiedad organismoresponsable.
       * 
       * @param value allowed object is {@link ORGANISMORESPONSABLE2 }
       * 
       */
      public void setORGANISMORESPONSABLE(ORGANISMORESPONSABLE2 value) {
        this.organismoresponsable = value;
      }

      /**
       * Obtiene el valor de la propiedad destinatarios.
       * 
       * @return possible object is {@link DESTINATARIOS2 }
       * 
       */
      public DESTINATARIOS2 getDESTINATARIOS() {
        return destinatarios;
      }

      /**
       * Define el valor de la propiedad destinatarios.
       * 
       * @param value allowed object is {@link DESTINATARIOS2 }
       * 
       */
      public void setDESTINATARIOS(DESTINATARIOS2 value) {
        this.destinatarios = value;
      }

      /**
       * Obtiene el valor de la propiedad sujetoatasas.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getSUJETOATASAS() {
        return sujetoatasas;
      }

      /**
       * Define el valor de la propiedad sujetoatasas.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setSUJETOATASAS(String value) {
        this.sujetoatasas = value;
      }

      /**
       * Obtiene el valor de la propiedad descperiodicidad.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getDESCPERIODICIDAD() {
        return descperiodicidad;
      }

      /**
       * Define el valor de la propiedad descperiodicidad.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setDESCPERIODICIDAD(String value) {
        this.descperiodicidad = value;
      }

      /**
       * Obtiene el valor de la propiedad codperiodicidad.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getCODPERIODICIDAD() {
        return codperiodicidad;
      }

      /**
       * Define el valor de la propiedad codperiodicidad.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setCODPERIODICIDAD(String value) {
        this.codperiodicidad = value;
      }

      /**
       * Obtiene el valor de la propiedad requisitosiniciacion.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getREQUISITOSINICIACION() {
        return requisitosiniciacion;
      }

      /**
       * Define el valor de la propiedad requisitosiniciacion.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setREQUISITOSINICIACION(String value) {
        this.requisitosiniciacion = value;
      }

      /**
       * Obtiene el valor de la propiedad adaptabilidad.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getADAPTABILIDAD() {
        return adaptabilidad;
      }

      /**
       * Define el valor de la propiedad adaptabilidad.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setADAPTABILIDAD(String value) {
        this.adaptabilidad = value;
      }

      /**
       * Obtiene el valor de la propiedad codniveladministracionelectronica.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getCODNIVELADMINISTRACIONELECTRONICA() {
        return codniveladministracionelectronica;
      }

      /**
       * Define el valor de la propiedad codniveladministracionelectronica.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setCODNIVELADMINISTRACIONELECTRONICA(String value) {
        this.codniveladministracionelectronica = value;
      }

      /**
       * Obtiene el valor de la propiedad desniveladministracionelectronica.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getDESNIVELADMINISTRACIONELECTRONICA() {
        return desniveladministracionelectronica;
      }

      /**
       * Define el valor de la propiedad desniveladministracionelectronica.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setDESNIVELADMINISTRACIONELECTRONICA(String value) {
        this.desniveladministracionelectronica = value;
      }

      /**
       * Obtiene el valor de la propiedad codrequisitosidentpj.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getCODREQUISITOSIDENTPJ() {
        return codrequisitosidentpj;
      }

      /**
       * Define el valor de la propiedad codrequisitosidentpj.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setCODREQUISITOSIDENTPJ(String value) {
        this.codrequisitosidentpj = value;
      }

      /**
       * Obtiene el valor de la propiedad desrequisitosidentpj.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getDESREQUISITOSIDENTPJ() {
        return desrequisitosidentpj;
      }

      /**
       * Define el valor de la propiedad desrequisitosidentpj.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setDESREQUISITOSIDENTPJ(String value) {
        this.desrequisitosidentpj = value;
      }

      /**
       * Obtiene el valor de la propiedad codrequisitosidentpf.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getCODREQUISITOSIDENTPF() {
        return codrequisitosidentpf;
      }

      /**
       * Define el valor de la propiedad codrequisitosidentpf.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setCODREQUISITOSIDENTPF(String value) {
        this.codrequisitosidentpf = value;
      }

      /**
       * Obtiene el valor de la propiedad desrequisitosidentpf.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getDESREQUISITOSIDENTPF() {
        return desrequisitosidentpf;
      }

      /**
       * Define el valor de la propiedad desrequisitosidentpf.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setDESREQUISITOSIDENTPF(String value) {
        this.desrequisitosidentpf = value;
      }

      /**
       * Obtiene el valor de la propiedad notificaciones.
       * 
       * @return possible object is {@link NOTIFICACIONES }
       * 
       */
      public NOTIFICACIONES getNOTIFICACIONES() {
        return notificaciones;
      }

      /**
       * Define el valor de la propiedad notificaciones.
       * 
       * @param value allowed object is {@link NOTIFICACIONES }
       * 
       */
      public void setNOTIFICACIONES(NOTIFICACIONES value) {
        this.notificaciones = value;
      }

      /**
       * Obtiene el valor de la propiedad canalesacceso.
       * 
       * @return possible object is {@link CANALESACCESO }
       * 
       */
      public CANALESACCESO getCANALESACCESO() {
        return canalesacceso;
      }

      /**
       * Define el valor de la propiedad canalesacceso.
       * 
       * @param value allowed object is {@link CANALESACCESO }
       * 
       */
      public void setCANALESACCESO(CANALESACCESO value) {
        this.canalesacceso = value;
      }

      /**
       * Obtiene el valor de la propiedad sistemasidentificacion.
       * 
       * @return possible object is {@link SISTEMASIDENTIFICACION2 }
       * 
       */
      public SISTEMASIDENTIFICACION2 getSISTEMASIDENTIFICACION() {
        return sistemasidentificacion;
      }

      /**
       * Define el valor de la propiedad sistemasidentificacion.
       * 
       * @param value allowed object is {@link SISTEMASIDENTIFICACION2 }
       * 
       */
      public void setSISTEMASIDENTIFICACION(SISTEMASIDENTIFICACION2 value) {
        this.sistemasidentificacion = value;
      }

      /**
       * Obtiene el valor de la propiedad url.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getURL() {
        return url;
      }

      /**
       * Define el valor de la propiedad url.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setURL(String value) {
        this.url = value;
      }

      /**
       * Obtiene el valor de la propiedad codportal.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getCODPORTAL() {
        return codportal;
      }

      /**
       * Define el valor de la propiedad codportal.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setCODPORTAL(String value) {
        this.codportal = value;
      }

      /**
       * Obtiene el valor de la propiedad desportal.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getDESPORTAL() {
        return desportal;
      }

      /**
       * Define el valor de la propiedad desportal.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setDESPORTAL(String value) {
        this.desportal = value;
      }

      /**
       * Obtiene el valor de la propiedad idintegradoclave.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getIDINTEGRADOCLAVE() {
        return idintegradoclave;
      }

      /**
       * Define el valor de la propiedad idintegradoclave.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setIDINTEGRADOCLAVE(String value) {
        this.idintegradoclave = value;
      }

      /**
       * Obtiene el valor de la propiedad desintegradoclave.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getDESINTEGRADOCLAVE() {
        return desintegradoclave;
      }

      /**
       * Define el valor de la propiedad desintegradoclave.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setDESINTEGRADOCLAVE(String value) {
        this.desintegradoclave = value;
      }

      /**
       * Obtiene el valor de la propiedad observacionintegradoclave.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getOBSERVACIONINTEGRADOCLAVE() {
        return observacionintegradoclave;
      }

      /**
       * Define el valor de la propiedad observacionintegradoclave.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setOBSERVACIONINTEGRADOCLAVE(String value) {
        this.observacionintegradoclave = value;
      }

      /**
       * Obtiene el valor de la propiedad reducciones.
       * 
       * @return possible object is {@link REDUCCIONES2 }
       * 
       */
      public REDUCCIONES2 getREDUCCIONES() {
        return reducciones;
      }

      /**
       * Define el valor de la propiedad reducciones.
       * 
       * @param value allowed object is {@link REDUCCIONES2 }
       * 
       */
      public void setREDUCCIONES(REDUCCIONES2 value) {
        this.reducciones = value;
      }

      /**
       * Obtiene el valor de la propiedad tiempomedioresolucion.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getTIEMPOMEDIORESOLUCION() {
        return tiempomedioresolucion;
      }

      /**
       * Define el valor de la propiedad tiempomedioresolucion.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setTIEMPOMEDIORESOLUCION(String value) {
        this.tiempomedioresolucion = value;
      }

      /**
       * Obtiene el valor de la propiedad voltramitacionesactual.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getVOLTRAMITACIONESACTUAL() {
        return voltramitacionesactual;
      }

      /**
       * Define el valor de la propiedad voltramitacionesactual.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setVOLTRAMITACIONESACTUAL(String value) {
        this.voltramitacionesactual = value;
      }

      /**
       * Obtiene el valor de la propiedad volumenestramitaciones.
       * 
       * @return possible object is {@link VOLUMENESTRAMITACIONES2 }
       * 
       */
      public VOLUMENESTRAMITACIONES2 getVOLUMENESTRAMITACIONES() {
        return volumenestramitaciones;
      }

      /**
       * Define el valor de la propiedad volumenestramitaciones.
       * 
       * @param value allowed object is {@link VOLUMENESTRAMITACIONES2 }
       * 
       */
      public void setVOLUMENESTRAMITACIONES(VOLUMENESTRAMITACIONES2 value) {
        this.volumenestramitaciones = value;
      }

      /**
       * Obtiene el valor de la propiedad volnotificacionesactual.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getVOLNOTIFICACIONESACTUAL() {
        return volnotificacionesactual;
      }

      /**
       * Define el valor de la propiedad volnotificacionesactual.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setVOLNOTIFICACIONESACTUAL(String value) {
        this.volnotificacionesactual = value;
      }

      /**
       * Obtiene el valor de la propiedad volumennotificaciones.
       * 
       * @return possible object is {@link VOLUMENNOTIFICACIONES }
       * 
       */
      public VOLUMENNOTIFICACIONES getVOLUMENNOTIFICACIONES() {
        return volumennotificaciones;
      }

      /**
       * Define el valor de la propiedad volumennotificaciones.
       * 
       * @param value allowed object is {@link VOLUMENNOTIFICACIONES }
       * 
       */
      public void setVOLUMENNOTIFICACIONES(VOLUMENNOTIFICACIONES value) {
        this.volumennotificaciones = value;
      }

      /**
       * Obtiene el valor de la propiedad materias.
       * 
       * @return possible object is {@link MATERIAS }
       * 
       */
      public MATERIAS getMATERIAS() {
        return materias;
      }

      /**
       * Define el valor de la propiedad materias.
       * 
       * @param value allowed object is {@link MATERIAS }
       * 
       */
      public void setMATERIAS(MATERIAS value) {
        this.materias = value;
      }

      /**
       * Obtiene el valor de la propiedad codclasetramite.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getCODCLASETRAMITE() {
        return codclasetramite;
      }

      /**
       * Define el valor de la propiedad codclasetramite.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setCODCLASETRAMITE(String value) {
        this.codclasetramite = value;
      }

      /**
       * Obtiene el valor de la propiedad destipologia.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getDESTIPOLOGIA() {
        return destipologia;
      }

      /**
       * Define el valor de la propiedad destipologia.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setDESTIPOLOGIA(String value) {
        this.destipologia = value;
      }

      /**
       * Obtiene el valor de la propiedad hechosvitales.
       * 
       * @return possible object is {@link HECHOSVITALES }
       * 
       */
      public HECHOSVITALES getHECHOSVITALES() {
        return hechosvitales;
      }

      /**
       * Define el valor de la propiedad hechosvitales.
       * 
       * @param value allowed object is {@link HECHOSVITALES }
       * 
       */
      public void setHECHOSVITALES(HECHOSVITALES value) {
        this.hechosvitales = value;
      }

      /**
       * Obtiene el valor de la propiedad tramitesrelacionados.
       * 
       * @return possible object is {@link TRAMITESRELACIONADOS2 }
       * 
       */
      public TRAMITESRELACIONADOS2 getTRAMITESRELACIONADOS() {
        return tramitesrelacionados;
      }

      /**
       * Define el valor de la propiedad tramitesrelacionados.
       * 
       * @param value allowed object is {@link TRAMITESRELACIONADOS2 }
       * 
       */
      public void setTRAMITESRELACIONADOS(TRAMITESRELACIONADOS2 value) {
        this.tramitesrelacionados = value;
      }

      /**
       * Obtiene el valor de la propiedad presentacionpresencial.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getPRESENTACIONPRESENCIAL() {
        return presentacionpresencial;
      }

      /**
       * Define el valor de la propiedad presentacionpresencial.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setPRESENTACIONPRESENCIAL(String value) {
        this.presentacionpresencial = value;
      }

      /**
       * Obtiene el valor de la propiedad lugarpresentacion.
       * 
       * @return possible object is {@link LUGARPRESENTACION2 }
       * 
       */
      public LUGARPRESENTACION2 getLUGARPRESENTACION() {
        return lugarpresentacion;
      }

      /**
       * Define el valor de la propiedad lugarpresentacion.
       * 
       * @param value allowed object is {@link LUGARPRESENTACION2 }
       * 
       */
      public void setLUGARPRESENTACION(LUGARPRESENTACION2 value) {
        this.lugarpresentacion = value;
      }

      /**
       * Obtiene el valor de la propiedad formularios.
       * 
       * @return possible object is {@link FORMULARIOS }
       * 
       */
      public FORMULARIOS getFORMULARIOS() {
        return formularios;
      }

      /**
       * Define el valor de la propiedad formularios.
       * 
       * @param value allowed object is {@link FORMULARIOS }
       * 
       */
      public void setFORMULARIOS(FORMULARIOS value) {
        this.formularios = value;
      }

      /**
       * Obtiene el valor de la propiedad inicios.
       * 
       * @return possible object is {@link INICIOS2 }
       * 
       */
      public INICIOS2 getINICIOS() {
        return inicios;
      }

      /**
       * Define el valor de la propiedad inicios.
       * 
       * @param value allowed object is {@link INICIOS2 }
       * 
       */
      public void setINICIOS(INICIOS2 value) {
        this.inicios = value;
      }

      /**
       * Obtiene el valor de la propiedad plazoresolucion.
       * 
       * @return possible object is {@link PLAZORESOLUCION2 }
       * 
       */
      public PLAZORESOLUCION2 getPLAZORESOLUCION() {
        return plazoresolucion;
      }

      /**
       * Define el valor de la propiedad plazoresolucion.
       * 
       * @param value allowed object is {@link PLAZORESOLUCION2 }
       * 
       */
      public void setPLAZORESOLUCION(PLAZORESOLUCION2 value) {
        this.plazoresolucion = value;
      }

      /**
       * Obtiene el valor de la propiedad finvia.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getFINVIA() {
        return finvia;
      }

      /**
       * Define el valor de la propiedad finvia.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setFINVIA(String value) {
        this.finvia = value;
      }

      /**
       * Obtiene el valor de la propiedad normativas.
       * 
       * @return possible object is {@link NORMATIVAS2 }
       * 
       */
      public NORMATIVAS2 getNORMATIVAS() {
        return normativas;
      }

      /**
       * Define el valor de la propiedad normativas.
       * 
       * @param value allowed object is {@link NORMATIVAS2 }
       * 
       */
      public void setNORMATIVAS(NORMATIVAS2 value) {
        this.normativas = value;
      }

      /**
       * Obtiene el valor de la propiedad norequieredocumentacion.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getNOREQUIEREDOCUMENTACION() {
        return norequieredocumentacion;
      }

      /**
       * Define el valor de la propiedad norequieredocumentacion.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setNOREQUIEREDOCUMENTACION(String value) {
        this.norequieredocumentacion = value;
      }

      /**
       * Obtiene el valor de la propiedad documentacion.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getDOCUMENTACION() {
        return documentacion;
      }

      /**
       * Define el valor de la propiedad documentacion.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setDOCUMENTACION(String value) {
        this.documentacion = value;
      }

      /**
       * Obtiene el valor de la propiedad documentoscatalogo.
       * 
       * @return possible object is {@link DOCUMENTOSCATALOGO2 }
       * 
       */
      public DOCUMENTOSCATALOGO2 getDOCUMENTOSCATALOGO() {
        return documentoscatalogo;
      }

      /**
       * Define el valor de la propiedad documentoscatalogo.
       * 
       * @param value allowed object is {@link DOCUMENTOSCATALOGO2 }
       * 
       */
      public void setDOCUMENTOSCATALOGO(DOCUMENTOSCATALOGO2 value) {
        this.documentoscatalogo = value;
      }

      /**
       * Obtiene el valor de la propiedad codestadoactuacion.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getCODESTADOACTUACION() {
        return codestadoactuacion;
      }

      /**
       * Define el valor de la propiedad codestadoactuacion.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setCODESTADOACTUACION(String value) {
        this.codestadoactuacion = value;
      }

      /**
       * Obtiene el valor de la propiedad desestadoactuacion.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getDESESTADOACTUACION() {
        return desestadoactuacion;
      }

      /**
       * Define el valor de la propiedad desestadoactuacion.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setDESESTADOACTUACION(String value) {
        this.desestadoactuacion = value;
      }

      /**
       * Obtiene el valor de la propiedad fechaultimaactualizacion.
       * 
       * @return possible object is {@link String }
       * 
       */
      public String getFECHAULTIMAACTUALIZACION() {
        return fechaultimaactualizacion;
      }

      /**
       * Define el valor de la propiedad fechaultimaactualizacion.
       * 
       * @param value allowed object is {@link String }
       * 
       */
      public void setFECHAULTIMAACTUALIZACION(String value) {
        this.fechaultimaactualizacion = value;
      }

    }

  }


  /**
   * <p>
   * Clase Java para anonymous complex type.
   * 
   * <p>
   * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
   * 
   * <pre>
   * &lt;complexType>
   *   &lt;complexContent>
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *       &lt;sequence>
   *         &lt;element name="DESCERROR" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *         &lt;element name="ERROR" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"descerror", "error"})
  public static class ERROR {

    @XmlElement(name = "DESCERROR", required = true)
    protected String descerror;
    @XmlElement(name = "ERROR", required = true)
    protected String error;

    /**
     * Obtiene el valor de la propiedad descerror.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDESCERROR() {
      return descerror;
    }

    /**
     * Define el valor de la propiedad descerror.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDESCERROR(String value) {
      this.descerror = value;
    }

    /**
     * Obtiene el valor de la propiedad error.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getERROR() {
      return error;
    }

    /**
     * Define el valor de la propiedad error.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setERROR(String value) {
      this.error = value;
    }

  }

}
