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
import jsinterop.base.JsArrayLike;

/**
 * A simple wrapper around a homogeneous native array of {@link
 * org.gwtproject.core.client.JavaScriptObject} values.
 *
 * <p>This class may not be directly instantiated, and can only be returned from a native method.
 * For example, <code>
 * native JsArray&lt;JavaScriptObject&gt; getNativeArray() /*-{
 *   return [
 *     { x: 0, y: 1},
 *     { x: 2, y: 3},
 *     { x: 4, y: 5},
 *   ];
 * }-* /;
 * </code>
 *
 * @param <T> the concrete type of object contained in this array
 */
@Deprecated
@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class JsArray<T extends JavaScriptObject> extends JavaScriptObject {

  protected JsArray() {}

  /**
   * Gets the object at a given index.
   *
   * @param index the index to be retrieved
   * @return the object at the given index, or <code>null</code> if none exists
   */
  @JsOverlay
  public final T get(int index) {
    return this.<JsArrayLike<T>>cast().getAt(index);
  }

  /**
   * Convert each element of the array to a String and join them with a comma separator. The value
   * returned from this method may vary between browsers based on how JavaScript values are
   * converted into strings.
   *
   * @return resulting String
   */
  @JsOverlay
  public final String join() {
    return this.<elemental2.core.JsArray<T>>cast().join();
  }

  /**
   * Convert each element of the array to a String and join them with a comma separator. The value
   * returned from this method may vary between browsers based on how JavaScript values are
   * converted into strings.
   *
   * @param separator separator to use
   * @return String containing all elements of the array. Each element separated using the separator
   */
  @JsOverlay
  public final String join(String separator) {
    return this.<elemental2.core.JsArray<T>>cast().join(separator);
  }

  /**
   * Gets the length of the array.
   *
   * @return the array length
   */
  @JsOverlay
  public final int length() {
    return this.<JsArrayLike<T>>cast().getLength();
  }

  /**
   * Pushes the given value onto the end of the array.
   *
   * @param value value to push
   */
  public final native void push(T value);

  /**
   * Sets the object value at a given index.
   *
   * <p>If the index is out of bounds, the value will still be set. The array's length will be
   * updated to encompass the bounds implied by the added object.
   *
   * @param index the index to be set
   * @param value the object to be stored
   */
  @JsOverlay
  public final void set(int index, T value) {
    this.<JsArrayLike<T>>cast().setAt(index, value);
  }

  /**
   * Reset the length of the array.
   *
   * @param newLength the new length of the array
   */
  @JsOverlay
  public final void setLength(int newLength) {
    this.<elemental2.core.JsArray<T>>cast().length = newLength;
  }

  /**
   * Shifts the first value off the array.
   *
   * @return the shifted value
   */
  @JsOverlay
  public final T shift() {
    return this.<elemental2.core.JsArray<T>>cast().shift();
  }

  /**
   * Shifts a value onto the beginning of the array.
   *
   * @param value the value to the stored
   */
  public final native void unshift(T value);
}
