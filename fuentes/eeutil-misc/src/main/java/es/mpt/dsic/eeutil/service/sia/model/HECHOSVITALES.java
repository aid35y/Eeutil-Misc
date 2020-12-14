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
 * Clase Java para HECHOSVITALES complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="HECHOSVITALES">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HECHOVITAL" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CODHECHOVITAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DESHECHOVITAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "HECHOSVITALES", propOrder = {"hechovital"})
public class HECHOSVITALES {

  @XmlElement(name = "HECHOVITAL")
  protected List<HECHOSVITALES.HECHOVITAL> hechovital;

  /**
   * Gets the value of the hechovital property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the hechovital property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getHECHOVITAL().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link HECHOSVITALES.HECHOVITAL }
   * 
   * 
   */
  public List<HECHOSVITALES.HECHOVITAL> getHECHOVITAL() {
    if (hechovital == null) {
      hechovital = new ArrayList<HECHOSVITALES.HECHOVITAL>();
    }
    return this.hechovital;
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
   *         &lt;element name="CODHECHOVITAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="DESHECHOVITAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"codhechovital", "deshechovital"})
  public static class HECHOVITAL {

    @XmlElement(name = "CODHECHOVITAL")
    protected String codhechovital;
    @XmlElement(name = "DESHECHOVITAL")
    protected String deshechovital;

    /**
     * Obtiene el valor de la propiedad codhechovital.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCODHECHOVITAL() {
      return codhechovital;
    }

    /**
     * Define el valor de la propiedad codhechovital.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCODHECHOVITAL(String value) {
      this.codhechovital = value;
    }

    /**
     * Obtiene el valor de la propiedad deshechovital.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDESHECHOVITAL() {
      return deshechovital;
    }

    /**
     * Define el valor de la propiedad deshechovital.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDESHECHOVITAL(String value) {
      this.deshechovital = value;
    }

  }

}
