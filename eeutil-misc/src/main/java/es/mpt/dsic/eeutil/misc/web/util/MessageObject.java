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

package es.mpt.dsic.eeutil.misc.web.util;


/**
 * Clase para mostrar mensajes al usuario en pantalla.
 * 
 */
public class MessageObject {

  /** Nivel del mensaje: INFO, ERROR, WARNING, etc. Ver clase de Constantes. */
  private int level;

  /** Cadena con el mensaje a mostrar. */
  private String message;

  /**
   * Constructor por defecto.
   */
  public MessageObject() {
    super();
  }

  /**
   * Constructor con par�metros level y message.
   * 
   * @param level
   * @param message
   */
  public MessageObject(int level, String message) {
    super();
    this.level = level;
    this.message = message;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "MessageObject [level=" + level + ", message=" + message + "]";
  }

}
