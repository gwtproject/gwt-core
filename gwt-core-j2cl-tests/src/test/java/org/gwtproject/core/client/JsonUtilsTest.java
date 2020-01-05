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
package org.gwtproject.core.client;

import static org.junit.Assert.assertEquals;

import com.google.j2cl.junit.apt.J2clTestInput;
import elemental2.core.Global;
import jsinterop.base.Js;
import org.junit.Test;

@J2clTestInput(JsonUtilsTest.class)
public class JsonUtilsTest {

  @Test
  public void testStringify() {
    assertEquals("{\"a\":2}", JsonUtils.stringify(createJson()));
    assertEquals("{\n\t\"a\": 2\n}", JsonUtils.stringify(createJson(), "\t"));
    assertEquals("{\nXYZ\"a\": 2\n}", JsonUtils.stringify(createJson(), "XYZ"));
  }

  private JavaScriptObject createJson() {
    return Js.cast(Global.JSON.parse("{ \"a\": 2 }"));
  }
}
