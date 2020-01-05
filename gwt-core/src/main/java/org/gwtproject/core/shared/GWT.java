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
package org.gwtproject.core.shared;

import java.util.logging.Level;
import java.util.logging.Logger;
import jsinterop.base.Js;

public final class GWT {
  private static final Logger log = Logger.getLogger("GWT.log()");

  @Deprecated
  public static <T> T create(Class<?> classLiteral) {
    throw new UnsupportedOperationException(
        "GWT.create() no longer produces generated code. For legacy generators, use GWT2's GWT.create(), for annotation processors, read that library's documentation.");
  }

  public static boolean isClient() {
    return isScript();
  }

  public static boolean isProdMode() {
    return isScript()
        && !("true".equals(System.getProperty("superdevmode", "false"))
            || "true".equals(System.getProperty("goog.DEBUG", "false")));
  }

  public static boolean isScript() {
    return new JreImpl().isScript();
  }

  public static void log(String message) {
    log(message, null);
  }

  public static void log(String message, Throwable e) {
    // forward all logs to juli
    log.log(Level.INFO, message, e);
  }

  public static void debugger() {
    // only insert debugger statement if in dev mode JS
    if (isScript() && !isProdMode()) {
      Js.debugger();
    }
  }

  private static class GwtImpl {
    boolean isScript() {
      return true;
    }
  }

  private static class JreImpl extends GwtImpl {
    @GwtIncompatible
    @Override
    boolean isScript() {
      return false;
    }
  }
}
