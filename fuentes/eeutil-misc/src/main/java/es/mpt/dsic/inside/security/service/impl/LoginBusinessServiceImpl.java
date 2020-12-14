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

package es.mpt.dsic.inside.security.service.impl;


import java.util.Properties;
import org.springframework.stereotype.Service;
import es.mpt.dsic.inside.security.service.LoginBusinessService;
import es.mpt.dsic.inside.ws.service.exception.InSideException;
import es.mpt.dsic.inside.ws.service.model.EstadoInfo;
import eu.stork.peps.auth.commons.IPersonalAttributeList;
import eu.stork.peps.auth.commons.PersonalAttribute;
import eu.stork.peps.auth.commons.PersonalAttributeList;
import eu.stork.peps.auth.commons.STORKAuthnRequest;
import eu.stork.peps.auth.engine.STORKSAMLEngine;
import eu.stork.peps.exceptions.STORKSAMLEngineException;

@Service("loginBusinessService")
public class LoginBusinessServiceImpl implements LoginBusinessService {

  @Override
  public byte[] generaTokenClave(String url, Properties properties) throws InSideException {

    IPersonalAttributeList pAttList = new PersonalAttributeList();

    /* eIdentifier */
    PersonalAttribute att = new PersonalAttribute();
    att.setName(properties.getProperty("attribute1.name"));
    att.setIsRequired(true);
    pAttList.add(att);

    /* givenName */
    att = new PersonalAttribute();
    att.setName(properties.getProperty("attribute2.name"));
    att.setIsRequired(true);
    pAttList.add(att);

    /* surname */
    att = new PersonalAttribute();
    att.setName(properties.getProperty("attribute3.name"));
    att.setIsRequired(true);
    pAttList.add(att);

    STORKAuthnRequest authnRequest = new STORKAuthnRequest();

    authnRequest.setDestination(url);
    authnRequest.setProviderName(properties.getProperty("provider.name"));
    authnRequest.setQaa(Integer.parseInt(properties.getProperty("sp.qaalevel")));
    authnRequest.setPersonalAttributeList(pAttList);
    authnRequest.setAssertionConsumerServiceURL(properties.getProperty("sp.return"));

    // new parameters
    authnRequest.setSpSector(properties.getProperty("sp.sector"));
    authnRequest.setSpApplication(properties.getProperty("sp.aplication"));

    // V-IDP parameters
    authnRequest.setSPID(properties.getProperty("provider.name"));

    try {
      STORKSAMLEngine engine = STORKSAMLEngine.getInstance("SP");
      authnRequest = engine.generateSTORKAuthnRequest(authnRequest);

    } catch (STORKSAMLEngineException e) {
      EstadoInfo estadoInfo = new EstadoInfo();
      estadoInfo.setDescripcion("Could not generate token for Saml Request");
      throw new InSideException("Could not generate token for Saml Request", estadoInfo, e);
    }

    return authnRequest.getTokenSaml();
  }

}
