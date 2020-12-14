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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import es.mpt.dsic.inside.utils.file.FileUtil;

@Controller
public class CleanTempController {

  protected static final Log logger = LogFactory.getLog(CleanTempController.class);


  @RequestMapping(value = "/cleanTemp", method = RequestMethod.GET)
  public ModelAndView clean() {
    logger.debug("Iniciado CleanTempController.clean");
    ModelAndView modelAndView = new ModelAndView("principal");
    FileUtil.deleteDirRecursively(new File(FileUtil.getTmpDir()));
    return modelAndView;
  }

}
