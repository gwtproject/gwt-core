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

import elemental2.core.JsArray;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import jsinterop.base.JsArrayLike;

/**
 * A simple wrapper around a homogeneous native array of numeric values.
 *
 * <p>All native JavaScript numeric values are implicitly double-precision, so only double values
 * may be set and retrieved.
 *
 * <p>This class may not be directly instantiated, and can only be returned from a native method.
 * For example, <code>
 * native JsArrayNumber getNativeArray() /*-{
 *   return [1.1, 2.2, 3.3];
 * }-* /;
 * </code>
 */
@Deprecated
@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class JsArrayNumber extends JavaScriptObject {

  protected JsArrayNumber() {}

  /**
   * Gets the value at a given index.
   *
   * <p>If an undefined or non-numeric value exists at the given index, a type-conversion error will
   * occur in Development Mode and unpredictable behavior may occur in Production Mode.
   *
   * @param index the index to be retrieved
   * @return the value at the given index
   */
  @JsOverlay
  public final double get(int index) {
    return this.<JsArrayLike<Number>>cast().getAt(index).doubleValue();
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
    return this.<JsArray<Number>>cast().join();
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
    return this.<JsArray<Number>>cast().join(separator);
  }

  /**
   * Gets the length of the array.
   *
   * @return the array length
   */
  @JsOverlay
  public final int length() {
    return this.<JsArrayLike<Number>>cast().getLength();
  }

  /**
   * Pushes the given number onto the end of the array.
   *
   * @param value double value to push
   */
  public final native void push(double value);

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
  public final void set(int index, double value) {
    this.<JsArrayLike<Number>>cast().setAt(index, Js.uncheckedCast(value));
  }

  /**
   * Reset the length of the array.
   *
   * @param newLength the new length of the array
   */
  @JsOverlay
  public final void setLength(int newLength) {
    this.<JsArray<Number>>cast().length = newLength;
  }

  /**
   * Shifts the first value off the array.
   *
   * @return the shifted value
   */
  public final native double shift();

  /**
   * Shifts a value onto the beginning of the array.
   *
   * @param value the value to the stored
   */
  public final native void unshift(double value);
}
