package org.gwtproject.core.client;

import elemental2.core.Function;
import elemental2.core.JsArray;
import jsinterop.annotations.*;
import jsinterop.base.Any;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;

/**
 * Deprecated, only exists to ease migration of existing JSO types.
 *
 * To update a JSO to support this type, add the appropriate @JsType annotation (may
 * simply be a copy from this class), and update each method. Each will either be
 *   * a native @JsProperty (possibly with a name),
 *   * a native @JsMethod   (possibly with a name), or
 *   * a final method written in Java annotated with @JsOverlay, which may call any other
 *     methods.
 *
 * Additionally, constructors may still exist as protected, no arg markers (as in the old
 * JavaScriptObject), or they may describe the actual underlying JS native type, but they must
 * be used in a manner consistent with JsInterop.
 *
 */
@Deprecated
@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class JavaScriptObject {
    /**
     * Returns a new array.
     */
    @JsOverlay
    public static JavaScriptObject createArray() {
        return Js.cast(new JsArray<>());
    }

    /**
     * Returns a new array with a given size.
     *
     * <p>Consider using this method in performance critical code instead of using
     * {@link #createArray()}, since this gives more hints to the underlying
     * JavaScript VM for optimizations.
     */
    @JsOverlay
    public static JavaScriptObject createArray(int size) {
        return Js.cast(new JsArray<>((double)size));
    }

    /**
     * Returns an empty function.
     */
    @JsOverlay
    public static JavaScriptObject createFunction()  {
        return Js.cast(new Function());
    }

    /**
     * Returns a new object.
     */
    @JsOverlay
    public static JavaScriptObject createObject() {
        return new JavaScriptObject();
    }

    @JsConstructor
    protected JavaScriptObject() {
    }

    @JsOverlay
    public final <T> T cast() {
        return Js.uncheckedCast(this);
    }
}
