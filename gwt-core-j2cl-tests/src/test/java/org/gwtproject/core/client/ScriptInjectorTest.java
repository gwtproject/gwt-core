/*
 * Copyright © 2020 The GWT Authors
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.google.j2cl.junit.apt.J2clTestInput;
import elemental2.dom.Document;
import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.NodeList;
import elemental2.dom.Window;
import elemental2.promise.Promise;
import jsinterop.base.Any;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;
import org.gwtproject.core.client.Scheduler.RepeatingCommand;
import org.gwtproject.core.client.ScriptInjector.FromString;
import org.junit.Test;

@J2clTestInput(ScriptInjectorTest.class)
public class ScriptInjectorTest {

  private static final int CHECK_DELAY = 100;

  private static final int TEST_DELAY = 10000;

  /** Install a script in the same window as GWT. */
  @Test
  public void testInjectDirectThisWindow() {

    String scriptBody = "__ti1_var__ = 1;";
    assertFalse(test1Worked());
    new FromString(scriptBody).inject();
    boolean worked = test1Worked();
    assertTrue(worked);
    JavaScriptObject scriptElement = findScriptTextInThisWindow(scriptBody);
    cleanupThisWindow("__ti1_var__", scriptElement);
    assertFalse("cleanup failed", test1Worked());
    assertTrue("__ti1_var not set in this window", worked);
    assertNull("script element 1 not removed by injection", scriptElement);
  }

  /** Install a script in the top window. */
  @Test
  public void testInjectDirectTopWindow() {
    String scriptBody = "__ti2_var__ = 2;";
    assertFalse(test2Worked());
    ScriptInjector.fromString(scriptBody).setWindow(ScriptInjector.TOP_WINDOW).inject();
    boolean worked = test2Worked();
    assertTrue(worked);
    JavaScriptObject scriptElement = findScriptTextInTopWindow(scriptBody);
    cleanupTopWindow("__ti2_var__", scriptElement);
    assertTrue("__ti2_var not set in top window", worked);
    assertNull("script element 2 not removed by injection", scriptElement);
  }

  /** Install a script in the same window as GWT, turn off the tag removal. */
  @Test
  public void testInjectDirectWithoutRemoveTag() {
    assertFalse(test3Worked());
    String scriptBody = "__ti3_var__ = 3;";
    new FromString(scriptBody).setRemoveTag(false).inject();
    boolean worked = test3Worked();
    JavaScriptObject scriptElement = findScriptTextInThisWindow(scriptBody);
    cleanupThisWindow("__ti3_var__", scriptElement);
    assertFalse("cleanup failed", test3Worked());
    assertTrue(worked);
    assertNotNull("script element 3 should have been left in DOM", scriptElement);
  }

  /** Inject an absolute URL on this window. */
  @Test(timeout = TEST_DELAY)
  public Promise<Void> testInjectUrlAbsolute() {

    return new Promise<Void>(
        (resolve, reject) -> {
          assertFalse(injectUrlAbsoluteWorked());

          ScriptInjector.fromUrl(getTestBasePath() + "script_injector_test_absolute.js")
              .setCallback(
                  new Callback<Void, Exception>() {

                    @Override
                    public void onFailure(Exception reason) {
                      assertNotNull(reason);
                      reject.onInvoke("Injection failed: " + reason.toString());
                    }

                    @Override
                    public void onSuccess(Void result) {
                      assertTrue(injectUrlAbsoluteWorked());
                      resolve.onInvoke((Void) null);
                    }
                  })
              .inject();
        });
  }

  /** Inject an absolute URL on the top level window. */
  @Test(timeout = TEST_DELAY)
  public Promise<Void> testInjectUrlAbsoluteTop() {

    return new Promise<Void>(
        (resolve, reject) -> {
          assertFalse(absoluteTopUrlIsLoaded());

          ScriptInjector.fromUrl(getTestBasePath() + "script_injector_test_absolute_top.js")
              .setWindow(ScriptInjector.TOP_WINDOW)
              .setCallback(
                  new Callback<Void, Exception>() {

                    @Override
                    public void onFailure(Exception reason) {
                      assertNotNull(reason);
                      reject.onInvoke("Injection failed: " + reason.toString());
                    }

                    @Override
                    public void onSuccess(Void result) {
                      assertTrue(absoluteTopUrlIsLoaded());
                      resolve.onInvoke((Void) null);
                    }
                  })
              .inject();
        });
  }

  /**
   * This script injection should fail and fire the onFailure callback.
   *
   * <p>Note, the onerror mechanism used to trigger the failure event is a modern browser feature.
   *
   * <p>On IE, the script.onerror tag has been documented, but busted for <a href=
   * "http://stackoverflow.com/questions/2027849/how-to-trigger-script-onerror-in-internet-explorer/2032014#2032014"
   * >aeons</a>.
   */
  @Test(timeout = TEST_DELAY)
  public Promise<Void> testInjectUrlFail() {

    return new Promise<Void>(
        (resolve, reject) -> {
          JavaScriptObject injectedElement =
              ScriptInjector.fromUrl(getTestBasePath() + "uNkNoWn_sCrIpT_404.js")
                  .setCallback(
                      new Callback<Void, Exception>() {

                        @Override
                        public void onFailure(Exception reason) {
                          assertNotNull(reason);
                          resolve.onInvoke((Void) null);
                        }

                        @Override
                        public void onSuccess(Void result) {
                          reject.onInvoke("Injection unexpectedly succeeded.");
                        }
                      })
                  .inject();

          assertNotNull(injectedElement);
        });
  }

  /** Install a script in the same window as GWT by URL */
  @Test(timeout = TEST_DELAY)
  public Promise<Void> testInjectUrlThisWindow() {

    return new Promise<Void>(
        (resolve, reject) -> {
          final String scriptUrl = "script_injector_test4.js";
          assertFalse(test4Worked());
          final JavaScriptObject injectedElement =
              ScriptInjector.fromUrl(scriptUrl).setRemoveTag(false).inject();

          // We'll check using a callback in another test. This test will poll to see
          // that the script had an effect.
          Scheduler.get()
              .scheduleFixedDelay(
                  new RepeatingCommand() {
                    int numLoops = 0;

                    @Override
                    public boolean execute() {
                      numLoops++;
                      boolean worked = test4Worked();
                      if (!worked && (numLoops * CHECK_DELAY < TEST_DELAY)) {
                        return true;
                      }
                      JavaScriptObject scriptElement = findScriptUrlInThisWindow(scriptUrl);
                      cleanupThisWindow("__ti4_var__", scriptElement);
                      assertFalse("cleanup failed", test4Worked());
                      assertTrue("__ti4_var not set in this window", worked);
                      assertNotNull("script element 4 not found", scriptElement);
                      assertEquals(injectedElement, scriptElement);

                      resolve.onInvoke((Void) null);

                      // never reached
                      return false;
                    }
                  },
                  CHECK_DELAY);

          assertNotNull(injectedElement);
        });
  }

  /** Install a script in the same window as GWT by URL */
  @Test(timeout = TEST_DELAY)
  public Promise<Void> testInjectUrlThisWindowCallback() {

    return new Promise<Void>(
        (resolve, reject) -> {
          assertFalse(test5Worked());

          String scriptUrl = getTestBasePath() + "script_injector_test5.js";

          JavaScriptObject injectedElement =
              ScriptInjector.fromUrl(scriptUrl)
                  .setRemoveTag(false)
                  .setCallback(
                      new Callback<Void, Exception>() {

                        @Override
                        public void onFailure(Exception reason) {
                          assertNotNull(reason);
                          reject.onInvoke("Injection failed: " + reason.toString());
                        }

                        @Override
                        public void onSuccess(Void result) {
                          boolean worked = test5Worked();
                          JavaScriptObject scriptElement = findScriptUrlInThisWindow(scriptUrl);
                          cleanupThisWindow("__ti5_var__", scriptElement);
                          assertFalse("cleanup failed", test5Worked());
                          assertTrue("__ti5_var not set in this window", worked);
                          assertNotNull("script element 5 not found", scriptElement);
                          resolve.onInvoke((Void) null);
                        }
                      })
                  .inject();

          assertNotNull(injectedElement);
        });
  }

  /** Install a script in the top window by URL */
  @Test(timeout = TEST_DELAY)
  public Promise<Void> testInjectUrlTopWindow() {

    return new Promise<Void>(
        (resolve, reject) -> {
          final String scriptUrl = "script_injector_test6.js";
          assertFalse(test6Worked());
          JavaScriptObject injectedElement =
              ScriptInjector.fromUrl(scriptUrl)
                  .setRemoveTag(false)
                  .setWindow(ScriptInjector.TOP_WINDOW)
                  .inject();

          // We'll check using a callback in another test. This test will poll to see
          // that the script had an effect.
          Scheduler.get()
              .scheduleFixedDelay(
                  new RepeatingCommand() {
                    int numLoops = 0;

                    @Override
                    public boolean execute() {
                      numLoops++;

                      boolean worked = test6Worked();
                      if (!worked && (numLoops * CHECK_DELAY < TEST_DELAY)) {
                        return true;
                      }
                      JavaScriptObject scriptElement = findScriptUrlInTopWindow(scriptUrl);
                      cleanupTopWindow("__ti6_var__", scriptElement);
                      assertFalse("cleanup failed", test6Worked());
                      assertTrue("__ti6_var not set in top window", worked);
                      assertNotNull("script element 6 not found", scriptElement);

                      resolve.onInvoke((Void) null);

                      // never reached
                      return false;
                    }
                  },
                  CHECK_DELAY);

          assertNotNull(injectedElement);
        });
  }

  /** Install a script in the top window by URL */
  @Test(timeout = TEST_DELAY)
  public Promise<Void> testInjectUrlTopWindowCallback() {

    return new Promise<Void>(
        (resolve, reject) -> {
          assertFalse(test7Worked());

          String scriptUrl = getTestBasePath() + "script_injector_test7.js";

          JavaScriptObject injectedElement =
              ScriptInjector.fromUrl(scriptUrl)
                  .setRemoveTag(false)
                  .setWindow(ScriptInjector.TOP_WINDOW)
                  .setCallback(
                      new Callback<Void, Exception>() {

                        @Override
                        public void onFailure(Exception reason) {
                          assertNotNull(reason);
                          reject.onInvoke("Injection failed: " + reason.toString());
                        }

                        @Override
                        public void onSuccess(Void result) {
                          boolean worked = test7Worked();
                          JavaScriptObject scriptElement = findScriptUrlInThisWindow(scriptUrl);
                          cleanupTopWindow("__ti7_var__", scriptElement);
                          assertFalse("cleanup failed", test7Worked());
                          assertTrue("__ti7_var not set in top window", worked);
                          assertNotNull("script element 7 not found", scriptElement);
                          resolve.onInvoke((Void) null);
                        }
                      })
                  .inject();

          assertNotNull(injectedElement);
        });
  }

  /** Tests encoding of the injected script (UTF-8) */
  @Test(timeout = TEST_DELAY)
  public Promise<Void> testInjectUrlUtf8() {

    return new Promise<Void>(
        (resolve, reject) -> {
          assertEquals("", getTestUtf8Var());

          String scriptUrl = getTestBasePath() + "script_injector_test_utf8.js";

          JavaScriptObject injectedElement =
              ScriptInjector.fromUrl(scriptUrl)
                  .setRemoveTag(false)
                  .setWindow(ScriptInjector.TOP_WINDOW)
                  .setCallback(
                      new Callback<Void, Exception>() {

                        @Override
                        public void onFailure(Exception reason) {
                          assertNotNull(reason);
                          reject.onInvoke("Injection failed: " + reason.toString());
                        }

                        @Override
                        public void onSuccess(Void result) {
                          String testVar = getTestUtf8Var();
                          JavaScriptObject scriptElement = findScriptUrlInTopWindow(scriptUrl);
                          cleanupTopWindow("__ti_utf8_var__", scriptElement);
                          assertEquals("cleanup failed", "", getTestUtf8Var());
                          assertEquals("__ti_utf8_var not set in top window", "à", testVar);
                          assertNotNull("script element not found", scriptElement);
                          resolve.onInvoke((Void) null);
                        }
                      })
                  .inject();

          assertNotNull(injectedElement);
        });
  }

  private void cleanupThisWindow(String property, JavaScriptObject scriptElement) {
    cleanupWindow(thisWindow(), property, scriptElement);
  }

  private void cleanupTopWindow(String property, JavaScriptObject scriptElement) {
    cleanupWindow(topWindow(), property, scriptElement);
  }

  private void cleanupWindow(Window wnd, String property, JavaScriptObject scriptElement) {

    Js.asPropertyMap(wnd).delete(property);

    Element typedScriptElement = Js.cast(scriptElement);

    if (typedScriptElement != null) {
      typedScriptElement.parentNode.removeChild(typedScriptElement);
    }
  };

  private JavaScriptObject findScriptTextInThisWindow(String text) {
    return findScriptText(Js.cast(thisWindow()), text);
  }

  private JavaScriptObject findScriptTextInTopWindow(String text) {
    return findScriptText(Js.cast(topWindow()), text);
  }

  private JavaScriptObject findScriptText(JavaScriptObject wnd, String text) {

    Document document = getDocumentFromWindow(wnd);
    NodeList<Element> scripts = document.getElementsByTagName("script");

    for (int i = 0; i < scripts.getLength(); i++) {

      Element script = scripts.getAt(i);

      if (script.textContent.matches("^" + text)) {
        return Js.cast(script);
      }
    }

    return null;
  };

  private JavaScriptObject findScriptUrlInThisWindow(String url) {
    return findScriptUrl(Js.cast(thisWindow()), url);
  }

  private JavaScriptObject findScriptUrlInTopWindow(String url) {
    return findScriptUrl(Js.cast(topWindow()), url);
  }

  /** Won't work for all urls, uses a regular expression match */
  private JavaScriptObject findScriptUrl(JavaScriptObject wnd, String url) {

    Document document = getDocumentFromWindow(wnd);
    NodeList<Element> scripts = document.getElementsByTagName("script");

    for (int i = 0; i < scripts.getLength(); i++) {

      Element script = scripts.getAt(i);

      if (script.getAttribute("src").matches(url)) {
        return Js.cast(script);
      }
    }

    return null;
  }

  private boolean absoluteTopUrlIsLoaded() {
    return testWorked(topWindow(), "__tiabsolutetop_var__", 102d);
  }

  private boolean injectUrlAbsoluteWorked() {
    return testWorked(thisWindow(), "__tiabsolute_var__", 101d);
  }

  private boolean test1Worked() {
    return testWorked(thisWindow(), "__ti1_var__", 1d);
  }

  private boolean test2Worked() {
    return testWorked(topWindow(), "__ti2_var__", 2d);
  }

  private boolean test3Worked() {
    return testWorked(thisWindow(), "__ti3_var__", 3d);
  };

  private boolean test4Worked() {
    return testWorked(thisWindow(), "__ti4_var__", 4d);
  };

  private boolean test5Worked() {
    return testWorked(thisWindow(), "__ti5_var__", 5d);
  };

  private boolean test6Worked() {
    return testWorked(thisWindow(), "__ti6_var__", 6d);
  };

  private boolean test7Worked() {
    return testWorked(thisWindow(), "__ti7_var__", 7d);
  };

  private String getTestUtf8Var() {

    JsPropertyMap<Object> jsPropertyMap = Js.asPropertyMap(thisWindow());
    String value = (String) jsPropertyMap.get("__ti_utf8_var__");

    if (value == null) {
      value = "";
    }

    return value;
  };

  private boolean testWorked(Window window, String varName, Double expectedValue) {

    JsPropertyMap<Object> jsPropertyMap = Js.asPropertyMap(window);
    Double value = (Double) jsPropertyMap.get(varName);

    return value != null && value == expectedValue;
  }

  private Window thisWindow() {
    // return window;
    // TODO Use correct window object!
    return DomGlobal.window;
  };

  private Window topWindow() {
    // return $wnd;
    return DomGlobal.window;
  };

  private Document getDocumentFromWindow(JavaScriptObject wnd) {
    return Js.<JsPropertyMap<Any>>cast(wnd).getAsAny("document").cast();
  }

  /** @return URL of test base path */
  private String getTestBasePath() {

    // TODO This is more a hack and should be improved!
    final String href = DomGlobal.location.href;
    final String basePath = href.substring(0, href.lastIndexOf("/") + 1);

    return basePath;
  }
}
