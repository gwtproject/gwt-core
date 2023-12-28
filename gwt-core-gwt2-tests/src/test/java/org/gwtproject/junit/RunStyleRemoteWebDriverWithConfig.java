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

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.junit.JUnitShell;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class RunStyleRemoteWebDriverWithConfig extends RunStyleAbstractRemoteWebDriver {
  public RunStyleRemoteWebDriverWithConfig(JUnitShell shell) {
    super(shell);
  }

  @Override
  protected RemoteWebDriverConfiguration readConfiguration(String args)
      throws ConfigurationException {
    File jsonConfigFile = new File(args);

    try {
      RemoteWebDriverConfiguration config =
          new Gson().fromJson(new FileReader(jsonConfigFile), RemoteWebDriverConfiguration.class);
      return config;
    } catch (FileNotFoundException e) {
      getLogger().log(TreeLogger.Type.ERROR, "Configuration file not found: " + args, e);
      throw new ConfigurationException();
    } catch (JsonIOException e) {
      getLogger().log(TreeLogger.Type.ERROR, "Error reading config file: " + args, e);
      throw new ConfigurationException();
    } catch (JsonSyntaxException e) {
      getLogger().log(TreeLogger.Type.ERROR, "Error parsing config file: " + args, e);
      throw new ConfigurationException();
    }
  }
}
