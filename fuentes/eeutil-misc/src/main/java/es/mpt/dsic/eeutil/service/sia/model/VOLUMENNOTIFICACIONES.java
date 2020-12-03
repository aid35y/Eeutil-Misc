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
 * Clase Java para VOLUMENNOTIFICACIONES complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="VOLUMENNOTIFICACIONES">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VOLUMENNOTIFICACION" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ANIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="PERIODO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="NUMTOTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="NUMPAPEL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="NUMCOMPARECENCIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="NUMDEH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "VOLUMENNOTIFICACIONES", propOrder = {"volumennotificacion"})
public class VOLUMENNOTIFICACIONES {

  @XmlElement(name = "VOLUMENNOTIFICACION")
  protected List<VOLUMENNOTIFICACIONES.VOLUMENNOTIFICACION> volumennotificacion;

  /**
   * Gets the value of the volumennotificacion property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the volumennotificacion property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getVOLUMENNOTIFICACION().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link VOLUMENNOTIFICACIONES.VOLUMENNOTIFICACION }
   * 
   * 
   */
  public List<VOLUMENNOTIFICACIONES.VOLUMENNOTIFICACION> getVOLUMENNOTIFICACION() {
    if (volumennotificacion == null) {
      volumennotificacion = new ArrayList<VOLUMENNOTIFICACIONES.VOLUMENNOTIFICACION>();
    }
    return this.volumennotificacion;
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
   *         &lt;element name="ANIO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="PERIODO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="NUMTOTAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="NUMPAPEL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="NUMCOMPARECENCIA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="NUMDEH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
      propOrder = {"anio", "periodo", "numtotal", "numpapel", "numcomparecencia", "numdeh"})
  public static class VOLUMENNOTIFICACION {

    @XmlElement(name = "ANIO")
    protected String anio;
    @XmlElement(name = "PERIODO")
    protected String periodo;
    @XmlElement(name = "NUMTOTAL")
    protected String numtotal;
    @XmlElement(name = "NUMPAPEL")
    protected String numpapel;
    @XmlElement(name = "NUMCOMPARECENCIA")
    protected String numcomparecencia;
    @XmlElement(name = "NUMDEH")
    protected String numdeh;

    /**
     * Obtiene el valor de la propiedad anio.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getANIO() {
      return anio;
    }

    /**
     * Define el valor de la propiedad anio.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setANIO(String value) {
      this.anio = value;
    }

    /**
     * Obtiene el valor de la propiedad periodo.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getPERIODO() {
      return periodo;
    }

    /**
     * Define el valor de la propiedad periodo.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setPERIODO(String value) {
      this.periodo = value;
    }

    /**
     * Obtiene el valor de la propiedad numtotal.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNUMTOTAL() {
      return numtotal;
    }

    /**
     * Define el valor de la propiedad numtotal.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setNUMTOTAL(String value) {
      this.numtotal = value;
    }

    /**
     * Obtiene el valor de la propiedad numpapel.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNUMPAPEL() {
      return numpapel;
    }

    /**
     * Define el valor de la propiedad numpapel.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setNUMPAPEL(String value) {
      this.numpapel = value;
    }

    /**
     * Obtiene el valor de la propiedad numcomparecencia.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNUMCOMPARECENCIA() {
      return numcomparecencia;
    }

    /**
     * Define el valor de la propiedad numcomparecencia.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setNUMCOMPARECENCIA(String value) {
      this.numcomparecencia = value;
    }

    /**
     * Obtiene el valor de la propiedad numdeh.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNUMDEH() {
      return numdeh;
    }

    /**
     * Define el valor de la propiedad numdeh.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setNUMDEH(String value) {
      this.numdeh = value;
    }

  }

}
