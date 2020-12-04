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

import java.util.Locale;

public class WebConstants {

  public static final String VERSION_NTI =
      "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e";

  public static final String KEY_ERROR_LOGIN_APP = "error.label.loginApp";

  public static final String KEY_ERROR_LOGIN = "error.login";

  public static final String KEY_ERROR_ACCESS_DENIED = "error.access.denied";

  public static final String KEY_TIPO_RELACION_RANGO = "combo.opcion.rango";

  public static final String KEY_TIPO_RELACION_IGUAL = "combo.opcion.igual";

  public static final String KEY_TIPO_RELACION_EXPRESION = "combo.opcion.expresion";

  public static final String KEY_TIPO_RELACION_MAYOR = "combo.opcion.mayor";

  public static final String KEY_TIPO_RELACION_MENOR = "combo.opcion.menor";

  public static final String KEY_ERROR_DATOS_NO_VALIDOS = "error.msg.datosNoValidos";

  public static final String KEY_ERROR_DATOS_NO_VALIDOS_PARAMETERS = "error.msg.datosNoValidos2";

  public static final String MSG_CONTENT_DOC_NO_VALID = "error.msg.contenidoDocError";

  // locale por defecto
  public static final Locale LOCALE_DEFAULT = new Locale("es", "ES");
  public static final String LANGUAGE_TAG_DEFAULT = "es-ES";
  public static final String ENCODING_DEFAULT = "UTF-8";

  public static final String USUARIO_SESSION = "usuarioSesion";

  // FORMATO FECHA
  public static String FORMATO_FECHA_DEFECTO = "dd-MM-yyyy";
  public static String FORMATO_FECHA_DEFECTO2 = "yyyy-MM-dd";

  /** maximo de registros a devolver en autocomplete */
  public static int MAX_RESULTS_AUTOCOMPLETE = 10;

  /** niveles para mostrar mensajes por pantalla a los usuarios */
  public static final int MESSAGE_LEVEL_SUCCESS = 1;
  public static final int MESSAGE_LEVEL_INFO = 2;
  public static final int MESSAGE_LEVEL_WARNING = 3;
  public static final int MESSAGE_LEVEL_ERROR = 4;

  /** Nivel de mensaje para errores en dialog. */
  public static final int MESSAGE_LEVEL_ERROR_DIALOG = 5;

  // Keys de los mensajes
  public static final String MSG_KEY_EXPED_ERROR_FECHA_INICIO = "expediente.error.fecha.inicio";
  public static final String MSG_KEY_ERROR_GENERIC = "error.generic";
  public static final String MSG_KEY_DOC_VISUALIZAR_NOMBRE_DOCENI =
      "visualizarDocumento.nombre.doceni";

  public static final String MSG_KEY_DOC_DOCENI_NO_VALIDO = "expediente.error.documento.novalido";

  public static final String MSG_KEY_DOC_DOCENI_MAX_SIZE = "expediente.error.documento.limit";

  // Mensajes validacion expediente
  public static final String MSG_ERROR_VALIDACION_EXPE_SIN_FIRMA =
      "Validación incorrecta : cvc-complex-type.2.4.b: The content of element 'eniconexpind:CarpetaIndizada' is not complete";
  public static final String MSG_ERROR_VALIDACION_EXPE_SIN_FIRMA_ADITIONAL =
      "Validación incorrecta : cvc-complex-type.2.4.b: The content of element 'ns2:indice' is not complete";
  public static final String MSG_VALIDACION_EXP_CORRECTA = "Validacion correcta";
  public static final String MSG_VALIDACION_EXP_FORMATO_ERROR = "validarExpedient.formatoError";


  // importar expediente
  public static final String MSG_IMPORTAR_EXP_FALTA_EXP = "importarExpediente.error.faltaExp";
  public static final String MSG_IMPORTAR_EXP_EXP_NO_VALIDO =
      "importarExpediente.error.expNoValido";
  public static final String MSG_IMPORTAR_EXP_CORRESP_NO_VALIDA =
      "importarExpediente.error.correspNoValida";
  public static final String MSG_IMPORTAR_EXP_OK = "importarExpediente.ok";

  // importar expediente
  public static final String MSG_IMPORTAR_DOC_EXP_NO_ENCONTRADO =
      "importarDocumento.error.expNoEncontrado";
  public static final String MSG_IMPORTAR_DOC_NO_VALIDO = "importarDocumento.error.docNoValido";
  public static final String MSG_IMPORTAR_DOC_USUARIO_NO_ASOCIADO_EXP =
      "importarDocumento.error.docUsuarioNoAsociadoExp";
  public static final String MSG_IMPORTAR_DOC_USUARIO_NO_ASOCIADO_DOC =
      "importarDocumento.error.docUsuarioNoAsociadoDoc";
  public static final String MSG_IMPORTAR_DOC_OK = "importarDocumento.ok";
  public static final String MSG_IMPORTAR_DOC_ERROR = "importarDocumento.error";
  public static final String MSG_BAJA_DOC_VINCULADO =
      "documentosAlmacenados.bajdoc.vinculado.error";
  public static final String MSG_BAJA_DOC_OK = "documentosAlmacenados.bajdoc.ok";
  public static final String MSG_IMPORTAR_DOC_EXP_INDEX_ERR = "importarDocumento.error.indexExp";

  public static final String MSG_EXP_ALMACENADOS_GENERATE_TOKEN =
      "expedientesAlmacenados.generarToken.error";
  public static final String MSG_BAJA_EXP_OK = "expedientesAlmacenados.bajexp.ok";

  public static final String MSG_EXP_PUESTADISPOSICION_NO_VALIDO =
      "puestaDisposicion.token.novalido";

}
