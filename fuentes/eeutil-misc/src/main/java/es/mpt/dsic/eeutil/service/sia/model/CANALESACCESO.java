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
 * Clase Java para CANALESACCESO complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CANALESACCESO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CANALACCESO" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CODCANALACCESO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DESCANALACCESO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "CANALESACCESO", propOrder = {"canalacceso"})
public class CANALESACCESO {

  @XmlElement(name = "CANALACCESO")
  protected List<CANALESACCESO.CANALACCESO> canalacceso;

  /**
   * Gets the value of the canalacceso property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the canalacceso property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getCANALACCESO().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link CANALESACCESO.CANALACCESO }
   * 
   * 
   */
  public List<CANALESACCESO.CANALACCESO> getCANALACCESO() {
    if (canalacceso == null) {
      canalacceso = new ArrayList<CANALESACCESO.CANALACCESO>();
    }
    return this.canalacceso;
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
   *         &lt;element name="CODCANALACCESO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="DESCANALACCESO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"codcanalacceso", "descanalacceso"})
  public static class CANALACCESO {

    @XmlElement(name = "CODCANALACCESO")
    protected String codcanalacceso;
    @XmlElement(name = "DESCANALACCESO")
    protected String descanalacceso;

    /**
     * Obtiene el valor de la propiedad codcanalacceso.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCODCANALACCESO() {
      return codcanalacceso;
    }

    /**
     * Define el valor de la propiedad codcanalacceso.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCODCANALACCESO(String value) {
      this.codcanalacceso = value;
    }

    /**
     * Obtiene el valor de la propiedad descanalacceso.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDESCANALACCESO() {
      return descanalacceso;
    }

    /**
     * Define el valor de la propiedad descanalacceso.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDESCANALACCESO(String value) {
      this.descanalacceso = value;
    }

  }

}
