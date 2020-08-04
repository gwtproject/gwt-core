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

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * A simple wrapper around a homogeneous native array of boolean values.
 *
 * <p>This class may not be directly instantiated, and can only be returned from a native method.
 * For example, <code>
 * native JsArrayBoolean getNativeArray() /*-{
 *   return [true, false, true];
 * }-* /;
 * </code>
 */
@Deprecated
@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class JsArrayBoolean extends JavaScriptObject {

  protected JsArrayBoolean() {}

  /**
   * Gets the value at a given index.
   *
   * <p>If an undefined or non-boolean value exists at the given index, a type-conversion error will
   * occur in Development Mode and unpredictable behavior may occur in Production Mode.
   *
   * @param index the index to be retrieved
   * @return the value at the given index
   */
  @JsOverlay
  public final boolean get(int index) {
    return this.<elemental2.core.JsArray<Boolean>>cast().getAt(index);
  }

  /**
   * Convert each element of the array to a String and join them with a comma separator. The value
   * returned from this method may vary between browsers based on how JavaScript values are
   * converted into strings.
   *
   * @return all elements jointed into a String separated by comma
   */
  @JsOverlay
  public final String join() {
    return this.<elemental2.core.JsArray<Boolean>>cast().join();
  }

  /**
   * Convert each element of the array to a String and join them with a comma separator. The value
   * returned from this method may vary between browsers based on how JavaScript values are
   * converted into strings.
   *
   * @param separator separator to use
   * @return all elements jointed into a String separated by the given separator
   */
  @JsOverlay
  public final String join(String separator) {
    return this.<elemental2.core.JsArray<Boolean>>cast().join(separator);
  }

  /**
   * Gets the length of the array.
   *
   * @return the array length
   */
  @JsOverlay
  public final int length() {
    return this.<elemental2.core.JsArray<Boolean>>cast().length;
  }

  /**
   * Pushes the given boolean onto the end of the array.
   *
   * @param value boolean value to push
   */
  public final native void push(boolean value);

  /**
   * Sets the value value at a given index.
   *
   * <p>If the index is out of bounds, the value will still be set. The array's length will be
   * updated to encompass the bounds implied by the added value.
   *
   * @param index the index to be set
   * @param value the value to be stored
   */
  @JsOverlay
  public final void set(int index, boolean value) {
    this.<elemental2.core.JsArray<Boolean>>cast().setAt(index, value);
  }

  /**
   * Reset the length of the array.
   *
   * @param newLength the new length of the array
   */
  @JsOverlay
  public final void setLength(int newLength) {
    this.<elemental2.core.JsArray<Boolean>>cast().length = newLength;
  }

  /**
   * Shifts the first value off the array.
   *
   * @return the shifted value
   */
  @JsOverlay
  public final boolean shift() {
    return this.<elemental2.core.JsArray<Boolean>>cast().shift();
  }

  /**
   * Shifts a value onto the beginning of the array.
   *
   * @param value the value to the stored
   */
  public final native void unshift(boolean value);
}
