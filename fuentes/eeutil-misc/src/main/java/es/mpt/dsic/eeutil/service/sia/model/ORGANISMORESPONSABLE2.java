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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Clase Java para ORGANISMORESPONSABLE complex type.
 * 
 * <p>
 * El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ORGANISMORESPONSABLE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CODADMINISTRACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DESADMINISTRACION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODCOMUNIDADAUTONOMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DESCOMUNIDADAUTONOMA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODAMBITO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DESAMBITO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODDEPARTAMENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DESDEPARTAMENTO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CODCENTRODIRECTIVO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DESCENTRODIRECTIVO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UNIDADGESTORATRAMITE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ORGANISMORESPONSABLE",
    propOrder = {"codadministracion", "desadministracion", "codcomunidadautonoma",
        "descomunidadautonoma", "codambito", "desambito", "coddepartamento", "desdepartamento",
        "codcentrodirectivo", "descentrodirectivo", "unidadgestoratramite"})
public class ORGANISMORESPONSABLE2 {

  @XmlElement(name = "CODADMINISTRACION")
  protected String codadministracion;
  @XmlElement(name = "DESADMINISTRACION")
  protected String desadministracion;
  @XmlElement(name = "CODCOMUNIDADAUTONOMA")
  protected String codcomunidadautonoma;
  @XmlElement(name = "DESCOMUNIDADAUTONOMA")
  protected String descomunidadautonoma;
  @XmlElement(name = "CODAMBITO")
  protected String codambito;
  @XmlElement(name = "DESAMBITO")
  protected String desambito;
  @XmlElement(name = "CODDEPARTAMENTO")
  protected String coddepartamento;
  @XmlElement(name = "DESDEPARTAMENTO")
  protected String desdepartamento;
  @XmlElement(name = "CODCENTRODIRECTIVO")
  protected String codcentrodirectivo;
  @XmlElement(name = "DESCENTRODIRECTIVO")
  protected String descentrodirectivo;
  @XmlElement(name = "UNIDADGESTORATRAMITE")
  protected String unidadgestoratramite;

  /**
   * Obtiene el valor de la propiedad codadministracion.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCODADMINISTRACION() {
    return codadministracion;
  }

  /**
   * Define el valor de la propiedad codadministracion.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCODADMINISTRACION(String value) {
    this.codadministracion = value;
  }

  /**
   * Obtiene el valor de la propiedad desadministracion.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDESADMINISTRACION() {
    return desadministracion;
  }

  /**
   * Define el valor de la propiedad desadministracion.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDESADMINISTRACION(String value) {
    this.desadministracion = value;
  }

  /**
   * Obtiene el valor de la propiedad codcomunidadautonoma.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCODCOMUNIDADAUTONOMA() {
    return codcomunidadautonoma;
  }

  /**
   * Define el valor de la propiedad codcomunidadautonoma.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCODCOMUNIDADAUTONOMA(String value) {
    this.codcomunidadautonoma = value;
  }

  /**
   * Obtiene el valor de la propiedad descomunidadautonoma.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDESCOMUNIDADAUTONOMA() {
    return descomunidadautonoma;
  }

  /**
   * Define el valor de la propiedad descomunidadautonoma.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDESCOMUNIDADAUTONOMA(String value) {
    this.descomunidadautonoma = value;
  }

  /**
   * Obtiene el valor de la propiedad codambito.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCODAMBITO() {
    return codambito;
  }

  /**
   * Define el valor de la propiedad codambito.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCODAMBITO(String value) {
    this.codambito = value;
  }

  /**
   * Obtiene el valor de la propiedad desambito.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDESAMBITO() {
    return desambito;
  }

  /**
   * Define el valor de la propiedad desambito.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDESAMBITO(String value) {
    this.desambito = value;
  }

  /**
   * Obtiene el valor de la propiedad coddepartamento.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCODDEPARTAMENTO() {
    return coddepartamento;
  }

  /**
   * Define el valor de la propiedad coddepartamento.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCODDEPARTAMENTO(String value) {
    this.coddepartamento = value;
  }

  /**
   * Obtiene el valor de la propiedad desdepartamento.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDESDEPARTAMENTO() {
    return desdepartamento;
  }

  /**
   * Define el valor de la propiedad desdepartamento.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDESDEPARTAMENTO(String value) {
    this.desdepartamento = value;
  }

  /**
   * Obtiene el valor de la propiedad codcentrodirectivo.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getCODCENTRODIRECTIVO() {
    return codcentrodirectivo;
  }

  /**
   * Define el valor de la propiedad codcentrodirectivo.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setCODCENTRODIRECTIVO(String value) {
    this.codcentrodirectivo = value;
  }

  /**
   * Obtiene el valor de la propiedad descentrodirectivo.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDESCENTRODIRECTIVO() {
    return descentrodirectivo;
  }

  /**
   * Define el valor de la propiedad descentrodirectivo.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDESCENTRODIRECTIVO(String value) {
    this.descentrodirectivo = value;
  }

  /**
   * Obtiene el valor de la propiedad unidadgestoratramite.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getUNIDADGESTORATRAMITE() {
    return unidadgestoratramite;
  }

  /**
   * Define el valor de la propiedad unidadgestoratramite.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setUNIDADGESTORATRAMITE(String value) {
    this.unidadgestoratramite = value;
  }

}
