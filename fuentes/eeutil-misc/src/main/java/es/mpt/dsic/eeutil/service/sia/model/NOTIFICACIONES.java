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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para NOTIFICACIONES complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="NOTIFICACIONES">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NOTIFICACION" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CODNOTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DESNOTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "NOTIFICACIONES", propOrder = {"notificacion"})
public class NOTIFICACIONES {

  @XmlElement(name = "NOTIFICACION")
  protected List<NOTIFICACIONES.NOTIFICACION> notificacion;

  /**
   * Gets the value of the notificacion property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the notificacion property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getNOTIFICACION().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link NOTIFICACIONES.NOTIFICACION }
   * 
   * 
   */
  public List<NOTIFICACIONES.NOTIFICACION> getNOTIFICACION() {
    if (notificacion == null) {
      notificacion = new ArrayList<NOTIFICACIONES.NOTIFICACION>();
    }
    return this.notificacion;
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
   *         &lt;element name="CODNOTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="DESNOTIFICACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"codnotificacion", "desnotificacion"})
  public static class NOTIFICACION {

    @XmlElement(name = "CODNOTIFICACION")
    protected String codnotificacion;
    @XmlElement(name = "DESNOTIFICACION")
    protected String desnotificacion;

    /**
     * Obtiene el valor de la propiedad codnotificacion.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCODNOTIFICACION() {
      return codnotificacion;
    }

    /**
     * Define el valor de la propiedad codnotificacion.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCODNOTIFICACION(String value) {
      this.codnotificacion = value;
    }

    /**
     * Obtiene el valor de la propiedad desnotificacion.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDESNOTIFICACION() {
      return desnotificacion;
    }

    /**
     * Define el valor de la propiedad desnotificacion.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDESNOTIFICACION(String value) {
      this.desnotificacion = value;
    }

  }

}
