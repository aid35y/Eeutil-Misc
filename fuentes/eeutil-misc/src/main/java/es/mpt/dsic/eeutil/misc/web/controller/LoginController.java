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

package es.mpt.dsic.eeutil.misc.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import es.mpt.dsic.eeutil.misc.web.util.WebConstants;
import es.mpt.dsic.inside.config.EeutilApplicationDataConfig;
import es.mpt.dsic.inside.security.service.LoginBusinessService;
import es.mpt.dsic.inside.security.util.ConstantsClave;
import es.mpt.dsic.inside.utils.xml.XMLUtil;
import es.mpt.dsic.inside.ws.service.exception.InSideException;
import eu.stork.peps.auth.commons.PEPSUtil;

@Controller
@PropertySource("file:${eeutil-misc.config.path}/clave.properties")
public class LoginController {

  @Autowired
  private Environment env;

  @Autowired
  private LoginBusinessService loginBusinessService;

  protected static final Log logger = LogFactory.getLog(LoginController.class);

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView init(HttpSession session) {
    ModelAndView retorno = new ModelAndView("login");
    if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
      retorno = new ModelAndView("principal");
    }
    return retorno;
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView loginGet(@RequestParam(value = "error", required = false) String error,
      HttpServletRequest request) {

    ModelAndView retorno = new ModelAndView("login");
    if (error != null) {
      ResourceBundle bundle = ResourceBundle.getBundle("messages", request.getLocale());
      retorno.addObject("error", bundle.getString(WebConstants.KEY_ERROR_LOGIN));
    }
    return retorno;
  }

  @RequestMapping(value = "/loginRedirectClave", method = RequestMethod.POST)
  public String loginClave(Model model, HttpSession session, HttpServletRequest request,
      RedirectAttributes redirectAttributes, Locale locale) {
    logger.debug("loginRedirectClave");
    FileInputStream fin = null;
    try {

      String claveServiceUrl = env.getProperty(ConstantsClave.PROPERTY_URL_CLAVE);
      String excludedIdPList = env.getProperty(ConstantsClave.PROPERTY_EXCLUDED_IDPLIST);
      String forcedIdP = env.getProperty(ConstantsClave.PROPERTY_FORCED_IDP);

      byte[] token = null;

      Properties props = new Properties();
      fin = new FileInputStream(
          EeutilApplicationDataConfig.CONFIG_PATH + File.separator + ConstantsClave.SP_PROPERTIES);
      props.load(fin);
      token = loginBusinessService.generaTokenClave(claveServiceUrl, props);

      String samlRequest = PEPSUtil.encodeSAMLToken(token);
      String samlRequestXML = new String(token, XMLUtil.UTF8_CHARSET);
      if (logger.isInfoEnabled()) {
        logger.debug(samlRequestXML);
      }

      model.addAttribute("claveServiceUrl", claveServiceUrl);
      model.addAttribute(ConstantsClave.ATRIBUTO_EXCLUDED_IDPLIST, excludedIdPList);
      model.addAttribute(ConstantsClave.ATRIBUTO_FORCED_IDP, forcedIdP);
      model.addAttribute(ConstantsClave.ATRIBUTO_SAML_REQUEST, samlRequest);

      return "login-redirect";
    } catch (InSideException e) {
      logger.error("Error: " + e.toString());
      return "login?error=Error acceso a cl@ve";

    } catch (Exception e) {
      logger.error("Error: " + e.toString());
      return "login?error=Error acceso a cl@ve";
    } finally {
      try {
        if (fin != null) {
          fin.close();
        }
      } catch (IOException e) {
        logger.error("Error: " + e.toString());
        return "login?error=Error acceso a cl@ve";
      }
    }
  }


}
