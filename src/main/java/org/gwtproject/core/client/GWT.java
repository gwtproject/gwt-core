package org.gwtproject.core.client;

import elemental2.dom.DomGlobal;
import jsinterop.annotations.JsMethod;

/**
 * Supports core functionality that in some cases requires direct support from
 * the compiler and runtime systems such as runtime type information and
 * deferred binding.
 */
public final class GWT {

    @Deprecated
    public <T> T create(Class<?> clazz) {
        return null;
    }

    @Deprecated
    public static void reportUncaughtException(Throwable e) {
        DomGlobal.setTimeout(ignore -> {
                    throw_(e);
                },
                0);
    }

    @JsMethod(namespace ="<window>", name = "throw")
    private static native void throw_(Object object);

    public static boolean isScript() {
        return true;
    }
}