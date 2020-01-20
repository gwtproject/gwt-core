package org.gwtproject.core.client;

import elemental2.dom.DomGlobal;
import jsinterop.annotations.JsMethod;

/**
 * Supports core functionality that in some cases requires direct support from
 * the compiler and runtime systems such as runtime type information and
 * deferred binding.
 */
public final class GWT {

    /**
     * This interface is used to catch exceptions at the "top level" just before
     * they escape to the browser. This is used in places where the browser calls
     * into user code such as event callbacks, timers, and RPC.
     *
     * In Development Mode, the default handler prints a stack trace to the log
     * window. In Production Mode, the default handler is null and thus exceptions
     * are allowed to escape, which provides an opportunity to use a JavaScript
     * debugger.
     */
    public interface UncaughtExceptionHandler {
        void onUncaughtException(Throwable e);
    }

    @Deprecated
    public static <T> T create(Class<?> clazz) {
        return org.gwtproject.core.shared.GWT.create(clazz);
    }

    /**
     * Throws an exception so that it is handled by the browser, but doesn't interrupt
     * the execution of this iteration of the event loop.
     * @param e the exception to report to the browser
     */
    @Deprecated
    public static void reportUncaughtException(Throwable e) {
        // throw an exception "later" so that it ends up handled by the global
        // error handler. Same code as in GWT2's Impl.reportToBrowser()
        DomGlobal.setTimeout(ignore -> {
                    throw_(e);
                },
                0);
    }

    @JsMethod(namespace ="<window>", name = "throw")
    private static native void throw_(Object object);

    public static boolean isClient() {
        return org.gwtproject.core.shared.GWT.isClient();
    }

    public static boolean isProdMode() {
        return org.gwtproject.core.shared.GWT.isProdMode();
    }

    public static boolean isScript() {
        return org.gwtproject.core.shared.GWT.isScript();
    }

    public static void log(String message) {
        org.gwtproject.core.shared.GWT.log(message);
    }

    public static void log(String message, Throwable e) {
        org.gwtproject.core.shared.GWT.log(message, e);
    }

    public static void debugger() {
        org.gwtproject.core.shared.GWT.debugger();
    }

    public static void runAsync(Class<?> name, Object ignore) {
        throw new UnsupportedOperationException("Pick either GWT2 split point or Closure-Compiler chunks");
    }

    public static void runAsync(Object ignore) {
        throw new UnsupportedOperationException("Pick either GWT2 split point or Closure-Compiler chunks");
    }
}