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

import elemental2.dom.DomGlobal;
import jsinterop.annotations.JsMethod;

/**
 * Supports core functionality that in some cases requires direct support from the compiler and
 * runtime systems such as runtime type information and deferred binding.
 */
public final class GWT {

  @Deprecated
  public <T> T create(Class<?> clazz) {
    return null;
  }

  @Deprecated
  public static void reportUncaughtException(Throwable e) {
    DomGlobal.setTimeout(
        ignore -> {
          throw_(e);
        },
        0);
  }

  @JsMethod(namespace = "<window>", name = "throw")
  private static native void throw_(Object object);

  public static boolean isScript() {
    return true;
  }
}
