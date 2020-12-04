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

package es.mpt.dsic.inside.ws.service.codigosRespuestaValidacion;

// C�digo_Respuesta - Detalle respuesta
public class ExpedienteENICodigosRespuestaValidacion {

  // CODIGOS

  // Expediente Estructura Se comprobar� que se responde al XSD
  public static final String E_E_000_CODIGO = "[E.E.000]";
  public static final String E_E_999_CODIGO = "[E.E.999]";

  // Se comprobar� que la firma es v�lida. Si este servicio estuviera saturado o no contestara, se
  // contestar� en este sentido, si no,
  // se devolver� el resultado de la validaci�n. Adem�s, si no tiene TimeStamp, podr�a recomendarse
  // su estampado (aunque la validaci�n sea correcta).
  // Adem�s, si no es longeva, podr�a recomendarse su actualizaci�n si el expediente est� dirigido a
  // archivo (aunque la validaci�n sea correcta).
  public static final String E_F_000_CODIGO = "[E.F.000]";
  public static final String E_F_001_CODIGO = "[E.F.001]";
  public static final String E_F_002_CODIGO = "[E.F.002]";
  public static final String E_F_003_CODIGO = "[E.F.003]";
  public static final String E_F_998_CODIGO = "[E.F.998]";
  public static final String E_F_999_CODIGO = "[E.F.999]";


  // Se comprobar� la existencia de c�digo DIR3 en una descarga que se
  // realizar� diariamente. Si no existiera, se expondr� este extremo.
  public static final String E_D_000_CODIGO = "[E.D.000]";
  public static final String E_D_999_CODIGO = "[E.D.999]";


  // Se comprobar� la existencia de c�digo SIA en una descarga que se realizar� diariamente.
  // Adem�s, se escribir� el nombre del procedimiento. Si no existiera, se expondr� este extremo.
  public static final String E_S_000_CODIGO = "[E.S.000]";
  public static final String E_S_999_CODIGO = "[E.S.999]";


  // Documentos_General. Se comprobar� que figuran todos los que deben estar.
  public static final String E_N_000_CODIGO = "[E.N.000]";
  public static final String E_N_999_CODIGO = "[E.N.999]";


  // Integridad documentos. Se realizar� la comprobaci�n de que el hash de cada documento
  // (seg�n algoritmo indicado en el �ndice) corresponde con los documentos aportados.
  public static final String E_I_000_CODIGO = "[E.I.000]";
  public static final String E_I_999_CODIGO = "[E.I.999]";



  // DETALLES

  // Expediente Estructura Se comprobar� que se responde al XSD
  public static final String E_E_000_DETALLE = "Estructura Correcta";
  public static final String E_E_999_DETALLE = "Estructura Incorrecta";

  // Se comprobar� que la firma es v�lida. Si este servicio estuviera saturado o no contestara, se
  // contestar� en este sentido, si no,
  // se devolver� el resultado de la validaci�n. Adem�s, si no tiene TimeStamp, podr�a recomendarse
  // su estampado (aunque la validaci�n sea correcta).
  // Adem�s, si no es longeva, podr�a recomendarse su actualizaci�n si el expediente est� dirigido a
  // archivo (aunque la validaci�n sea correcta).
  public static final String E_F_000_DETALLE = "Firma V�lida";
  public static final String E_F_001_DETALLE = "Firma V�lida pero Caducada";
  public static final String E_F_002_DETALLE = "Firma V�lida sin Timestamp";
  public static final String E_F_003_DETALLE = "Firma V�lida no Longeva";
  public static final String E_F_998_DETALLE = "Servicio indisponible";
  public static final String E_F_999_DETALLE = "Firma Incorrecta";


  // Se comprobar� la existencia de c�digo DIR3 en una descarga que se
  // realizar� diariamente. Si no existiera, se expondr� este extremo.
  public static final String E_D_000_DETALLE = "C�digo DIR3 Existente";
  public static final String E_D_999_DETALLE = "C�digo DIR3 NO existente";


  // Se comprobar� la existencia de c�digo SIA en una descarga que se realizar� diariamente.
  // Adem�s, se escribir� el nombre del procedimiento. Si no existiera, se expondr� este extremo.
  public static final String E_S_000_DETALLE = "C�digo SIA Existente";
  public static final String E_S_999_DETALLE = "C�digo SIA NO existente";


  // Documentos_General. Se comprobar� que figuran todos los que deben estar.
  public static final String E_N_000_DETALLE = "Correspondencia documentos";
  public static final String E_N_999_DETALLE = "No correspondencia documentos";


  // Integridad documentos. Se realizar� la comprobaci�n de que el hash de cada documento
  // (seg�n algoritmo indicado en el �ndice) corresponde con los documentos aportados.
  public static final String E_I_000_DETALLE = "Integridad documentos";
  public static final String E_I_999_DETALLE = "No integridad documentos";



}
