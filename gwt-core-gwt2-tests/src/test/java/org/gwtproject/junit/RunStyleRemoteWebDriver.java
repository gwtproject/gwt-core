/*
 * Copyright © 2019 The GWT Project Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gwtproject.junit;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.junit.JUnitShell;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.openqa.selenium.remote.DesiredCapabilities;

public class RunStyleRemoteWebDriver extends RunStyleAbstractRemoteWebDriver {

  public RunStyleRemoteWebDriver(JUnitShell shell) {
    super(shell);
  }

  @Override
  protected RemoteWebDriverConfiguration readConfiguration(String args)
      throws ConfigurationException {
    RemoteWebDriverConfiguration config = new RemoteWebDriverConfiguration();
    if (args == null || args.length() == 0) {
      getLogger()
          .log(
              TreeLogger.ERROR,
              "RemoteWebDriver runstyle requires a parameter of the form protocol://hostname:port?browser1[,browser2]");
      throw new ConfigurationException();
    }

    String[] parts = args.split("\\?");
    String url = parts[0];
    URL remoteAddress = null;
    try {
      remoteAddress = new URL(url);
      if (remoteAddress.getPath().equals("")
          || (remoteAddress.getPath().equals("/") && !url.endsWith("/"))) {
        getLogger()
            .log(
                TreeLogger.INFO,
                "No path specified in webdriver remote url, using default of /wd/hub");
        config.setRemoteWebDriverUrl(url + "/wd/hub");
      } else {
        config.setRemoteWebDriverUrl(url);
      }
    } catch (MalformedURLException e) {
      getLogger().log(TreeLogger.ERROR, e.getMessage(), e);
      throw new ConfigurationException();
    }

    // build each driver based on parts[1].split(",")
    String[] browserNames = parts[1].split(",");
    config.setBrowserCapabilities(new ArrayList<>());
    for (String browserName : browserNames) {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setBrowserName(browserName);
      config.getBrowserCapabilities().add(capabilities.asMap());
    }

    return config;
  }
}
