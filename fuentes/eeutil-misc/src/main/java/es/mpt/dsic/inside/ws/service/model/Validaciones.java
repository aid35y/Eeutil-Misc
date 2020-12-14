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

package es.mpt.dsic.inside.ws.service.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Validaciones",
    propOrder = {"validaSchema", "validaDir3", "validaSIA", "validaFirma"})
public class Validaciones {

  @XmlElement(required = false, name = "validaSchema", type = Boolean.class)
  private boolean validaSchema;
  @XmlElement(required = false, name = "validaDir3", type = Boolean.class)
  private boolean validaDir3;
  @XmlElement(required = false, name = "validaSIA", type = Boolean.class)
  private boolean validaSIA;
  @XmlElement(required = false, name = "validaFirma", type = Boolean.class)
  private boolean validaFirma;


  public boolean isValidaSchema() {
    return validaSchema;
  }

  public void setValidaSchema(boolean validaSchema) {
    this.validaSchema = validaSchema;
  }

  public boolean isValidaDir3() {
    return validaDir3;
  }

  public void setValidaDir3(boolean validaDir3) {
    this.validaDir3 = validaDir3;
  }

  public boolean isValidaSIA() {
    return validaSIA;
  }

  public void setValidaSIA(boolean validaSIA) {
    this.validaSIA = validaSIA;
  }

  public boolean isValidaFirma() {
    return validaFirma;
  }

  public void setValidaFirma(boolean validaFirma) {
    this.validaFirma = validaFirma;
  }



}
