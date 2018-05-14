package org.gwtproject.core.client;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * A simple wrapper around a homogeneous native array of integer values.
 *
 * This class may not be directly instantiated, and can only be returned from a
 * native method. For example,
 *
 * <code>
 * native JsArrayInteger getNativeArray() /*-{
 *   return [1, 2, 3];
 * }-* /;
 * </code>
 */
@JsType(isNative = true, name = "Array", namespace = JsPackage.GLOBAL)
public class JsArrayInteger extends JavaScriptObject {

    protected JsArrayInteger() {
    }

    /**
     * Gets the value at a given index.
     *
     * If no value exists at the given index, a type-conversion error will occur
     * in Development Mode and unpredictable behavior may occur in Production
     * Mode. If the numeric value returned is non-integral, it will cause a
     * warning in Development Mode, and may affect the results of mathematical
     * expressions.
     *
     * @param index the index to be retrieved
     * @return the value at the given index
     */
    public final int get(int index) {
      return (int) (double) this.<elemental2.core.JsArray<Double>>cast().getAt(index);
    }

    /**
     * Convert each element of the array to a String and join them with a comma
     * separator. The value returned from this method may vary between browsers
     * based on how JavaScript values are converted into strings.
     */
    public final String join() {
        return this.<elemental2.core.JsArray<Double>>cast().join();
    }

    /**
     * Convert each element of the array to a String and join them with a comma
     * separator. The value returned from this method may vary between browsers
     * based on how JavaScript values are converted into strings.
     */
    public final String join(String separator) {
      return this.<elemental2.core.JsArray<Double>>cast().join(separator);
    }

    /**
     * Gets the length of the array.
     *
     * @return the array length
     */
    public final int length() {
      return this.<elemental2.core.JsArray<Double>>cast().length;
    }

    /**
     * Pushes the given integer onto the end of the array.
     */
    public final void push(int value) {
        this.<elemental2.core.JsArray<Double>>cast().push((double) value);
    }

    /**
     * Sets the value value at a given index.
     *
     * If the index is out of bounds, the value will still be set. The array's
     * length will be updated to encompass the bounds implied by the added value.
     *
     * @param index the index to be set
     * @param value the value to be stored
     */
    public final void set(int index, int value) {
        this.<elemental2.core.JsArray<Double>>cast().setAt(index, (double) value);
    }

    /**
     * Reset the length of the array.
     *
     * @param newLength the new length of the array
     */
    public final void setLength(int newLength) {
        this.<elemental2.core.JsArray<Double>>cast().length = newLength;
    }

    /**
     * Shifts the first value off the array.
     *
     * @return the shifted value
     */
    public final int shift() {
      return (int) (double) this.<elemental2.core.JsArray<Double>>cast().shift();
    }

    /**
     * Shifts a value onto the beginning of the array.
     *
     * @param value the value to the stored
     */
    public final void unshift(int value) {
        this.<elemental2.core.JsArray<Double>>cast().unshift((double) value);
    }
}
