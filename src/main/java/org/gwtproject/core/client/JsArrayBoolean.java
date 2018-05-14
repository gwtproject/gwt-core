package org.gwtproject.core.client;

import elemental2.core.JsBoolean;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * A simple wrapper around a homogeneous native array of boolean values.
 *
 * This class may not be directly instantiated, and can only be returned from a
 * native method. For example,
 *
 * <code>
 * native JsArrayBoolean getNativeArray() /*-{
 *   return [true, false, true];
 * }-* /;
 * </code>
 */
@JsType(isNative = true, name = "Array", namespace = JsPackage.GLOBAL)
public class JsArrayBoolean extends JavaScriptObject {

    protected JsArrayBoolean() {
    }

    /**
     * Gets the value at a given index.
     *
     * If an undefined or non-boolean value exists at the given index, a
     * type-conversion error will occur in Development Mode and unpredictable
     * behavior may occur in Production Mode.
     *
     * @param index the index to be retrieved
     * @return the value at the given index
     */
    public final boolean get(int index) {
        return this.<elemental2.core.JsArray<Boolean>>cast().getAt(index);
    }

    /**
     * Convert each element of the array to a String and join them with a comma
     * separator. The value returned from this method may vary between browsers
     * based on how JavaScript values are converted into strings.
     */
    public final String join() {
        return this.<elemental2.core.JsArray<Boolean>>cast().join();
    }

    /**
     * Convert each element of the array to a String and join them with a comma
     * separator. The value returned from this method may vary between browsers
     * based on how JavaScript values are converted into strings.
     */
    public final String join(String separator) {
        return this.<elemental2.core.JsArray<Boolean>>cast().join(separator);
    }

    /**
     * Gets the length of the array.
     *
     * @return the array length
     */
    public final int length() {
        return this.<elemental2.core.JsArray<Boolean>>cast().length;
    }

    /**
     * Pushes the given boolean onto the end of the array.
     */
    public final void push(boolean value) {
        this.<elemental2.core.JsArray<Boolean>>cast().push(value);
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
    public final void set(int index, boolean value) {
        this.<elemental2.core.JsArray<Boolean>>cast().setAt(index, value);
    }

    /**
     * Reset the length of the array.
     *
     * @param newLength the new length of the array
     */
    public final void setLength(int newLength) {
        this.<elemental2.core.JsArray<Boolean>>cast().length = newLength;
    }

    /**
     * Shifts the first value off the array.
     *
     * @return the shifted value
     */
    public final boolean shift() {
        return this.<elemental2.core.JsArray<Boolean>>cast().shift();
    }

    /**
     * Shifts a value onto the beginning of the array.
     *
     * @param value the value to the stored
     */
    public final void unshift(boolean value) {
        this.<elemental2.core.JsArray<Boolean>>cast().unshift(value);
    }
}

