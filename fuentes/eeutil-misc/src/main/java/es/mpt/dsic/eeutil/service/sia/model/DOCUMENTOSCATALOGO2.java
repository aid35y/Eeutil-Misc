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
 * Clase Java para DOCUMENTOSCATALOGO complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DOCUMENTOSCATALOGO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DOCUMENTOCATALOGO" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CODDOCUMENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DESDOCUMENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="CATALOGOCOMUN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="CODENTIDADEMISORA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="CODDEPARTAMENTORESP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="CODCENTRODIRECTIVORESP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="CODDEPARTAMENTOEMISOR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="CODCENTRODIRECTIVOEMISOR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="OBLIGADOAPORTARLOINTERESADO" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "DOCUMENTOSCATALOGO", propOrder = {"documentocatalogo"})
public class DOCUMENTOSCATALOGO2 {

  @XmlElement(name = "DOCUMENTOCATALOGO")
  protected List<DOCUMENTOSCATALOGO2.DOCUMENTOCATALOGO> documentocatalogo;

  /**
   * Gets the value of the documentocatalogo property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the documentocatalogo property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getDOCUMENTOCATALOGO().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link DOCUMENTOSCATALOGO2 .DOCUMENTOCATALOGO }
   * 
   * 
   */
  public List<DOCUMENTOSCATALOGO2.DOCUMENTOCATALOGO> getDOCUMENTOCATALOGO() {
    if (documentocatalogo == null) {
      documentocatalogo = new ArrayList<DOCUMENTOSCATALOGO2.DOCUMENTOCATALOGO>();
    }
    return this.documentocatalogo;
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
   *         &lt;element name="CODDOCUMENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="DESDOCUMENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="CATALOGOCOMUN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="CODENTIDADEMISORA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="CODDEPARTAMENTORESP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="CODCENTRODIRECTIVORESP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="CODDEPARTAMENTOEMISOR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="CODCENTRODIRECTIVOEMISOR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
   *         &lt;element name="OBLIGADOAPORTARLOINTERESADO" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
      propOrder = {"coddocumento", "desdocumento", "catalogocomun", "codentidademisora",
          "coddepartamentoresp", "codcentrodirectivoresp", "coddepartamentoemisor",
          "codcentrodirectivoemisor", "obligadoaportarlointeresado"})
  public static class DOCUMENTOCATALOGO {

    @XmlElement(name = "CODDOCUMENTO")
    protected String coddocumento;
    @XmlElement(name = "DESDOCUMENTO")
    protected String desdocumento;
    @XmlElement(name = "CATALOGOCOMUN")
    protected String catalogocomun;
    @XmlElement(name = "CODENTIDADEMISORA")
    protected String codentidademisora;
    @XmlElement(name = "CODDEPARTAMENTORESP")
    protected String coddepartamentoresp;
    @XmlElement(name = "CODCENTRODIRECTIVORESP")
    protected String codcentrodirectivoresp;
    @XmlElement(name = "CODDEPARTAMENTOEMISOR")
    protected String coddepartamentoemisor;
    @XmlElement(name = "CODCENTRODIRECTIVOEMISOR")
    protected String codcentrodirectivoemisor;
    @XmlElement(name = "OBLIGADOAPORTARLOINTERESADO", required = true)
    protected String obligadoaportarlointeresado;

    /**
     * Obtiene el valor de la propiedad coddocumento.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCODDOCUMENTO() {
      return coddocumento;
    }

    /**
     * Define el valor de la propiedad coddocumento.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCODDOCUMENTO(String value) {
      this.coddocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad desdocumento.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDESDOCUMENTO() {
      return desdocumento;
    }

    /**
     * Define el valor de la propiedad desdocumento.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDESDOCUMENTO(String value) {
      this.desdocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad catalogocomun.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCATALOGOCOMUN() {
      return catalogocomun;
    }

    /**
     * Define el valor de la propiedad catalogocomun.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCATALOGOCOMUN(String value) {
      this.catalogocomun = value;
    }

    /**
     * Obtiene el valor de la propiedad codentidademisora.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCODENTIDADEMISORA() {
      return codentidademisora;
    }

    /**
     * Define el valor de la propiedad codentidademisora.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCODENTIDADEMISORA(String value) {
      this.codentidademisora = value;
    }

    /**
     * Obtiene el valor de la propiedad coddepartamentoresp.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCODDEPARTAMENTORESP() {
      return coddepartamentoresp;
    }

    /**
     * Define el valor de la propiedad coddepartamentoresp.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCODDEPARTAMENTORESP(String value) {
      this.coddepartamentoresp = value;
    }

    /**
     * Obtiene el valor de la propiedad codcentrodirectivoresp.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCODCENTRODIRECTIVORESP() {
      return codcentrodirectivoresp;
    }

    /**
     * Define el valor de la propiedad codcentrodirectivoresp.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCODCENTRODIRECTIVORESP(String value) {
      this.codcentrodirectivoresp = value;
    }

    /**
     * Obtiene el valor de la propiedad coddepartamentoemisor.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCODDEPARTAMENTOEMISOR() {
      return coddepartamentoemisor;
    }

    /**
     * Define el valor de la propiedad coddepartamentoemisor.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCODDEPARTAMENTOEMISOR(String value) {
      this.coddepartamentoemisor = value;
    }

    /**
     * Obtiene el valor de la propiedad codcentrodirectivoemisor.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCODCENTRODIRECTIVOEMISOR() {
      return codcentrodirectivoemisor;
    }

    /**
     * Define el valor de la propiedad codcentrodirectivoemisor.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCODCENTRODIRECTIVOEMISOR(String value) {
      this.codcentrodirectivoemisor = value;
    }

    /**
     * Obtiene el valor de la propiedad obligadoaportarlointeresado.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getOBLIGADOAPORTARLOINTERESADO() {
      return obligadoaportarlointeresado;
    }

    /**
     * Define el valor de la propiedad obligadoaportarlointeresado.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setOBLIGADOAPORTARLOINTERESADO(String value) {
      this.obligadoaportarlointeresado = value;
    }

  }

}
