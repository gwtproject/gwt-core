package org.gwtproject.core.client;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;

/**
 * A simple wrapper around an heterogeneous native array of values.
 *
 * This class may not be directly instantiated, and can only be returned from a
 * native method. For example,
 *
 * <code>
 * native JsArrayMixed getNativeArray() /*-{
 *   return [
 *     { x: 0, y: 1},
 *     "apple",
 *     12345,
 *   ];
 * }-* /;
 * </code>
 */
@JsType(isNative = true, name = "Array", namespace = JsPackage.GLOBAL)
public class JsArrayMixed extends JavaScriptObject {

    protected JsArrayMixed() {
    }

    /**
     * Gets the boolean at a given index.
     *
     * @param index the index to be retrieved
     * @return the object at the given index coerced to boolean.
     */
    public final boolean getBoolean(int index) {
      return Js.isTruthy(this.<elemental2.core.JsArray<Object>>cast().getAnyAt(index));
    }

    /**
     * Gets the double at a given index.
     *
     * @param index the index to be retrieved
     * @return the object at the given index coerced to number.
     */
    public final double getNumber(int index) {
      return Js.coerceToDouble(this.<elemental2.core.JsArray<Object>>cast().getAnyAt(index));
    }

    /**
     * Gets the {@link JavaScriptObject} at a given index.
     *
     * @param index the index to be retrieved
     * @return the {@code JavaScriptObject} at the given index, or
     *         <code>null</code> if none exists
     */
    public final <T extends JavaScriptObject> T getObject(int index) {
      return this.<elemental2.core.JsArray<Object>>cast().getAt(index) != null ? this.<elemental2.core.JsArray<Object>>cast().getAnyAt(index).cast() : null;
    }

    /**
     * Gets the String at a given index.
     *
     * @param index the index to be retrieved
     * @return the object at the given index, or <code>null</code> if none exists
     */
    public final String getString(int index) {
      Object value = this.<elemental2.core.JsArray<Object>>cast().getAt(index);
      return value == null ? null : value.toString();
    }

    /**
     * Convert each element of the array to a String and join them with a comma
     * separator. The value returned from this method may vary between browsers
     * based on how JavaScript values are converted into strings.
     */
    public final String join() {
        return this.<elemental2.core.JsArray<Object>>cast().join();
    }

    /**
     * Convert each element of the array to a String and join them with a comma
     * separator. The value returned from this method may vary between browsers
     * based on how JavaScript values are converted into strings.
     */
    public final String join(String separator) {
      return this.<elemental2.core.JsArray<Object>>cast().join(separator);
    }

    /**
     * Gets the length of the array.
     *
     * @return the array length
     */
    public final int length() {
      return this.<elemental2.core.JsArray<Object>>cast().length;
    }

    /**
     * Pushes the given boolean onto the end of the array.
     */
    public final void push(boolean value) {
        this.<elemental2.core.JsArray<Object>>cast().push(value);
    }

    /**
     * Pushes the given double onto the end of the array.
     */
    public final void push(double value) {
        this.<elemental2.core.JsArray<Object>>cast().push(value);
    }

    /**
     * Pushes the given {@link JavaScriptObject} onto the end of the array.
     */
    public final void push(JavaScriptObject value) {
        this.<elemental2.core.JsArray<Object>>cast().push(value);
    }

    /**
     * Pushes the given String onto the end of the array.
     */
    public final void push(String value) {
        this.<elemental2.core.JsArray<Object>>cast().push(value);
    }

    /**
     * Sets the boolean value at a given index.
     *
     * If the index is out of bounds, the value will still be set. The array's
     * length will be updated to encompass the bounds implied by the added value.
     *
     * @param index the index to be set
     * @param value the boolean to be stored
     */
    public final void set(int index, boolean value) {
        this.<elemental2.core.JsArray<Object>>cast().setAt(index, value);
    }

    /**
     * Sets the double value at a given index.
     *
     * If the index is out of bounds, the value will still be set. The array's
     * length will be updated to encompass the bounds implied by the added value.
     *
     * @param index the index to be set
     * @param value the double to be stored
     */
    public final void set(int index, double value) {
        this.<elemental2.core.JsArray<Object>>cast().setAt(index, value);
    }

    /**
     * Sets the object value at a given index.
     *
     * If the index is out of bounds, the value will still be set. The array's
     * length will be updated to encompass the bounds implied by the added object.
     *
     * @param index the index to be set
     * @param value the {@link JavaScriptObject} to be stored
     */
    public final void set(int index, JavaScriptObject value) {
        this.<elemental2.core.JsArray<Object>>cast().setAt(index, value);
    }

    /**
     * Sets the String value at a given index.
     *
     * If the index is out of bounds, the value will still be set. The array's
     * length will be updated to encompass the bounds implied by the added String.
     *
     * @param index the index to be set
     * @param value the String to be stored
     */
    public final void set(int index, String value) {
        this.<elemental2.core.JsArray<Object>>cast().setAt(index, value);
    }

    /**
     * Reset the length of the array.
     *
     * @param newLength the new length of the array
     */
    public final void setLength(int newLength) {
        this.<elemental2.core.JsArray<Object>>cast().length = newLength;
    }

    /**
     * Shifts the first value off the array.
     *
     * @return the shifted item coerced to a boolean
     */
    public final boolean shiftBoolean() {
      return Js.isTruthy(this.<elemental2.core.JsArray<Object>>cast().shift());
    }

    /**
     * Shifts the first value off the array.
     *
     * @return the shifted item coerced to a number
     */
    public final double shiftNumber() {
      return Js.coerceToDouble(this.<elemental2.core.JsArray<Object>>cast().shift());
    }

    /**
     * Shifts the first value off the array.
     *
     * @return the shifted {@link JavaScriptObject}
     */
    public final <T extends JavaScriptObject> T shiftObject() {
      return Js.uncheckedCast(this.<elemental2.core.JsArray<Object>>cast().shift());
    }

    /**
     * Shifts the first value off the array.
     *
     * @return the shifted String
     */
    public final String shiftString() {
      return this.<elemental2.core.JsArray<Object>>cast().shift().toString();
    }

    /**
     * Shifts a boolean onto the beginning of the array.
     *
     * @param value the value to the stored
     */
    public final void unshift(boolean value) {
        this.<elemental2.core.JsArray<Object>>cast().unshift(value);
    }

    /**
     * Shifts a double onto the beginning of the array.
     *
     * @param value the value to store
     */
    public final void unshift(double value) {
        this.<elemental2.core.JsArray<Object>>cast().unshift(value);
    }

    /**
     * Shifts a {@link JavaScriptObject} onto the beginning of the array.
     *
     * @param value the value to store
     */
    public final void unshift(JavaScriptObject value) {
        this.<elemental2.core.JsArray<Object>>cast().unshift(value);
    }

    /**
     * Shifts a String onto the beginning of the array.
     *
     * @param value the value to store
     */
    public final void unshift(String value) {
        this.<elemental2.core.JsArray<Object>>cast().unshift(value);
    }

}

