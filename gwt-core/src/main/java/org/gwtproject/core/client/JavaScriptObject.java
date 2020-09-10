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

import elemental2.core.Function;
import elemental2.core.JsArray;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;

/**
 * Deprecated, only exists to ease migration of existing JSO types.
 *
 * <p>To update a JSO to support this type, add the appropriate @JsType annotation (may simply be a
 * copy from this class), and update each method. Each will either be * a native @JsProperty
 * (possibly with a name), * a native @JsMethod (possibly with a name), or * a final method written
 * in Java annotated with @JsOverlay, which may call any other methods.
 *
 * <p>Additionally, constructors may still exist as protected, no arg markers (as in the old
 * JavaScriptObject), or they may describe the actual underlying JS native type, but they must be
 * used in a manner consistent with JsInterop.
 */
@Deprecated
@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class JavaScriptObject {
  /**
   * Returns a new array.
   *
   * @return a new array
   */
  @JsOverlay
  public static JavaScriptObject createArray() {
    return Js.cast(new JsArray<>());
  }

  /**
   * Returns a new array with a given size.
   *
   * <p>Consider using this method in performance critical code instead of using {@link
   * #createArray()}, since this gives more hints to the underlying JavaScript VM for optimizations.
   *
   * @param size size of array
   * @return array as JavaScript object
   */
  @JsOverlay
  public static JavaScriptObject createArray(int size) {
    return Js.cast(new JsArray<>((double) size));
  }

  /**
   * Returns an empty function.
   *
   * @return function as JavaScript object
   */
  @JsOverlay
  public static JavaScriptObject createFunction() {
    return Js.cast(new Function());
  }

  /**
   * Returns a new object.
   *
   * @return object as JavaScript object
   */
  @JsOverlay
  public static JavaScriptObject createObject() {
    return new JavaScriptObject();
  }

  @JsConstructor
  protected JavaScriptObject() {}

  @JsOverlay
  public final <T> T cast() {
    return Js.uncheckedCast(this);
  }
}
