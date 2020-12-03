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
 * Clase Java para TEMATICAS complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TEMATICAS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TEMATICA" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CODTEMATICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DESMATERIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "TEMATICAS", namespace = "http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA",
    propOrder = {"tematica"})
public class TEMATICAS {

  @XmlElement(name = "TEMATICA")
  protected List<TEMATICAS.TEMATICA> tematica;

  /**
   * Gets the value of the tematica property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the tematica property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getTEMATICA().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link TEMATICAS.TEMATICA }
   * 
   * 
   */
  public List<TEMATICAS.TEMATICA> getTEMATICA() {
    if (tematica == null) {
      tematica = new ArrayList<TEMATICAS.TEMATICA>();
    }
    return this.tematica;
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
   *         &lt;element name="CODTEMATICA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="DESMATERIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"codtematica", "desmateria"})
  public static class TEMATICA {

    @XmlElement(name = "CODTEMATICA",
        namespace = "http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA")
    protected String codtematica;
    @XmlElement(name = "DESMATERIA",
        namespace = "http://www.map.es/sgca/consultar/messages/v2_4/EnviaSIA")
    protected String desmateria;

    /**
     * Obtiene el valor de la propiedad codtematica.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCODTEMATICA() {
      return codtematica;
    }

    /**
     * Define el valor de la propiedad codtematica.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCODTEMATICA(String value) {
      this.codtematica = value;
    }

    /**
     * Obtiene el valor de la propiedad desmateria.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDESMATERIA() {
      return desmateria;
    }

    /**
     * Define el valor de la propiedad desmateria.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDESMATERIA(String value) {
      this.desmateria = value;
    }

  }

}
