/*
 * Copyright © 2019 The GWT Authors
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
package org.gwtproject.core;

import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import org.gwtproject.core.client.*;
import org.gwtproject.core.client.impl.SchedulerImplTest;

public class CoreSuite {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite("All core tests");

    suite.addTestSuite(GWTTest.class);
    suite.addTestSuite(JavaScriptObjectTest.class);
    suite.addTestSuite(JsonUtilsTest.class);
    suite.addTestSuite(JsArrayTest.class);
    suite.addTestSuite(JsArrayMixedTest.class);
    suite.addTestSuite(SchedulerTest.class);
    suite.addTestSuite(SchedulerImplTest.class);
    suite.addTestSuite(ScriptInjectorTest.class);

    return suite;
  }
}
