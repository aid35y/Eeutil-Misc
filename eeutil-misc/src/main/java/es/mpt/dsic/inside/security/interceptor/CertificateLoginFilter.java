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

package es.mpt.dsic.inside.security.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.stereotype.Component;
import es.mpt.dsic.inside.security.authentication.UserAuthentication;
import es.mpt.dsic.inside.security.model.UsuarioCredencialInfo;
import es.mpt.dsic.inside.security.service.CredentialnfoService;
import es.mpt.dsic.inside.security.util.ConstantsClave;
import eu.stork.peps.auth.commons.IPersonalAttributeList;
import eu.stork.peps.auth.commons.PEPSUtil;
import eu.stork.peps.auth.commons.PersonalAttribute;
import eu.stork.peps.auth.commons.STORKAuthnResponse;
import eu.stork.peps.auth.engine.STORKSAMLEngine;
import eu.stork.peps.exceptions.STORKSAMLEngineException;

@Component
public class CertificateLoginFilter extends AbstractAuthenticationProcessingFilter {

  protected static final Log logger = LogFactory.getLog(CertificateLoginFilter.class);

  public CertificateLoginFilter() {
    super("/accesoCertificado");
  }

  public CertificateLoginFilter(String defaultFilterProcessesUrl) {
    super(defaultFilterProcessesUrl);
  }

  @Autowired
  private Environment env;

  @Autowired
  private CredentialnfoService credentialnfoService;

  @Qualifier("autenticationManager")
  private AuthenticationManager autenticationManager;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

    try {
      logger.debug("login by clave");

      STORKAuthnResponse authnResponse = null;
      IPersonalAttributeList personalAttributeList = null;

      /* Recuperamos la respuesta de la aplicaci�n */
      String samlResponse = request.getParameter(ConstantsClave.ATRIBUTO_SAML_RESPONSE);
      logger.debug("Datos de respuesta de cl@ve:" + samlResponse);
      if (!StringUtils.isBlank(samlResponse)) {
        /* Decodificamos la respuesta SAML */
        byte[] decSamlToken = PEPSUtil.decodeSAMLToken(samlResponse);

        /* Obtenemos la instancia de SAMLEngine */
        STORKSAMLEngine engine = STORKSAMLEngine.getInstance(ConstantsClave.SP_CONF);

        /* Validamos el token SAML */

        authnResponse =
            engine.validateSTORKAuthnResponse(decSamlToken, (String) request.getRemoteHost());

        if (authnResponse.isFail()) {
          logger.error("Acceso no autorizado");
          throw new PreAuthenticatedCredentialsNotFoundException("Acceso no autorizado");
        } else {
          /* Recuperamos los atributos */
          personalAttributeList = authnResponse.getPersonalAttributeList();

          PersonalAttribute identificadorAttribute =
              personalAttributeList.get(env.getProperty(ConstantsClave.PROPERTY_IDENTIFICADOR));
          List<String> identificadores = identificadorAttribute.getValue();
          String id = identificadores.get(0).split("/")[2];

          UsuarioCredencialInfo usuario = credentialnfoService.getCredentialInfo(id);
          if (usuario != null) {
            List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
            roles.add(new GrantedAuthorityImpl("USER_ROLE"));
            return new UserAuthentication(usuario.getNif(), null, roles, "1", usuario.getNif());
          }

        }

      }
      throw new PreAuthenticatedCredentialsNotFoundException("Acceso no autorizado");
    } catch (DataAccessException e) {
      logger.error("DataAccessException: " + e.getMessage());
      throw new PreAuthenticatedCredentialsNotFoundException("Acceso no autorizado");
    } catch (STORKSAMLEngineException e) {
      logger.error("STORKSAMLEngineException: " + e.getMessage());
      throw new PreAuthenticatedCredentialsNotFoundException("Acceso no autorizado");
    }
  }

}
