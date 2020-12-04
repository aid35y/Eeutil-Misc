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
 * Clase Java para FORMULARIOS complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="FORMULARIOS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FORMULARIO" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TITULOFORM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="URLFORM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "FORMULARIOS", propOrder = {"formulario"})
public class FORMULARIOS {

  @XmlElement(name = "FORMULARIO")
  protected List<FORMULARIOS.FORMULARIO> formulario;

  /**
   * Gets the value of the formulario property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the formulario property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getFORMULARIO().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link FORMULARIOS.FORMULARIO }
   * 
   * 
   */
  public List<FORMULARIOS.FORMULARIO> getFORMULARIO() {
    if (formulario == null) {
      formulario = new ArrayList<FORMULARIOS.FORMULARIO>();
    }
    return this.formulario;
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
   *         &lt;element name="TITULOFORM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="URLFORM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"tituloform", "urlform"})
  public static class FORMULARIO {

    @XmlElement(name = "TITULOFORM")
    protected String tituloform;
    @XmlElement(name = "URLFORM")
    protected String urlform;

    /**
     * Obtiene el valor de la propiedad tituloform.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTITULOFORM() {
      return tituloform;
    }

    /**
     * Define el valor de la propiedad tituloform.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setTITULOFORM(String value) {
      this.tituloform = value;
    }

    /**
     * Obtiene el valor de la propiedad urlform.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getURLFORM() {
      return urlform;
    }

    /**
     * Define el valor de la propiedad urlform.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setURLFORM(String value) {
      this.urlform = value;
    }

  }

}
