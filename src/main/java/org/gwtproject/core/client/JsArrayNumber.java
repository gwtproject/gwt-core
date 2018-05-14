package org.gwtproject.core.client;

import elemental2.core.JsArray;
import elemental2.core.JsNumber;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;

/**
 * A simple wrapper around a homogeneous native array of numeric values.
 *
 * All native JavaScript numeric values are implicitly double-precision, so only
 * double values may be set and retrieved.
 *
 * This class may not be directly instantiated, and can only be returned from a
 * native method. For example,
 *
 * <code>
 * native JsArrayNumber getNativeArray() /*-{
 *   return [1.1, 2.2, 3.3];
 * }-* /;
 * </code>
 */
@JsType(isNative = true, name = "Array", namespace = JsPackage.GLOBAL)
public class JsArrayNumber extends JavaScriptObject {

    protected JsArrayNumber() {
    }

    /**
     * Gets the value at a given index.
     *
     * If an undefined or non-numeric value exists at the given index, a
     * type-conversion error will occur in Development Mode and unpredictable
     * behavior may occur in Production Mode.
     *
     * @param index the index to be retrieved
     * @return the value at the given index
     */
    public final double get(int index)  {
        return this.<JsArray<JsNumber>>cast().getAt(index).valueOf();
    }

    /**
     * Convert each element of the array to a String and join them with a comma
     * separator. The value returned from this method may vary between browsers
     * based on how JavaScript values are converted into strings.
     */
    public final String join() {
        return this.<JsArray<JsNumber>>cast().join();
    }

    /**
     * Convert each element of the array to a String and join them with a comma
     * separator. The value returned from this method may vary between browsers
     * based on how JavaScript values are converted into strings.
     */
    public final String join(String separator)  {
        return this.<JsArray<JsNumber>>cast().join(separator);
    }

    /**
     * Gets the length of the array.
     *
     * @return the array length
     */
    public final int length()  {
        return this.<JsArray<JsNumber>>cast().length;
    }

    /**
     * Pushes the given number onto the end of the array.
     */
    public final void push(double value)  {
        this.<JsArray<JsNumber>>cast().push(Js.uncheckedCast(value));
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
    public final void set(int index, double value)  {
        this.<JsArray<JsNumber>>cast().setAt(index, Js.uncheckedCast(value));
    }

    /**
     * Reset the length of the array.
     *
     * @param newLength the new length of the array
     */
    public final void setLength(int newLength)  {
        this.<JsArray<JsNumber>>cast().length = newLength;
    }

    /**
     * Shifts the first value off the array.
     *
     * @return the shifted value
     */
    public final double shift()  {
        return this.<JsArray<JsNumber>>cast().shift().valueOf();
    }

    /**
     * Shifts a value onto the beginning of the array.
     *
     * @param value the value to the stored
     */
    public final void unshift(double value)  {
        this.<JsArray<JsNumber>>cast().unshift(Js.uncheckedCast(value));
    }
}
