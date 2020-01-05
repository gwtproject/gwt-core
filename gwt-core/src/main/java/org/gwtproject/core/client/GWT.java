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

import elemental2.dom.DomGlobal;
import elemental2.dom.Window;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;

/**
 * Supports core functionality that in some cases requires direct support from the compiler and
 * runtime systems such as runtime type information and deferred binding.
 */
public final class GWT {

  /**
   * This interface is used to catch exceptions at the "top level" just before they escape to the
   * browser. This is used in places where the browser calls into user code such as event callbacks,
   * timers, and RPC.
   *
   * <p>In Development Mode, the default handler prints a stack trace to the log window. In
   * Production Mode, the default handler is null and thus exceptions are allowed to escape, which
   * provides an opportunity to use a JavaScript debugger.
   */
  public interface UncaughtExceptionHandler {
    void onUncaughtException(Throwable e);
  }

  private static UncaughtExceptionHandler uncaughtExceptionHandler = null;
  private static boolean onErrorInitialized;

  @Deprecated
  public static <T> T create(Class<?> clazz) {
    return org.gwtproject.core.shared.GWT.create(clazz);
  }

  /**
   * Throws an exception so that it is handled by the browser, but doesn't interrupt the execution
   * of this iteration of the event loop.
   *
   * @param e the exception to report to the browser
   */
  @Deprecated
  public static void reportUncaughtException(Throwable e) {
    // throw an exception "later" so that it ends up handled by the global
    // error handler. Same code as in GWT2's Impl.reportToBrowser()
    DomGlobal.setTimeout(
        ignore -> {
          throw_(e);
        },
        0);
  }

  /**
   * Returns the currently active uncaughtExceptionHandler.
   *
   * @return the currently active handler, or null if no handler is active.
   * @see #reportUncaughtException(Throwable)
   */
  public static UncaughtExceptionHandler getUncaughtExceptionHandler() {
    return uncaughtExceptionHandler;
  }

  /**
   * Sets a custom uncaught exception handler. See {@link #getUncaughtExceptionHandler()} for
   * details.
   *
   * @param handler the handler that should be called when an exception is about to escape to the
   *     browser, or <code>null</code> to clear the handler and allow exceptions to escape.
   */
  public static void setUncaughtExceptionHandler(UncaughtExceptionHandler handler) {
    uncaughtExceptionHandler = handler;
    if (handler == null) {
      return;
    }
    if (onErrorInitialized) {
      return;
    }
    onErrorInitialized = true;

    // transliterated from gwt2's Impl.registerWindowOnError
    Window.OnerrorFn errorHandler =
        (msg, url, line, column, error) -> {
          if (uncaughtExceptionHandler == null) {
            return null;
          }
          // IE8, IE9, IE10, safari 9, do not have an error passed. While
          // we don't necessarily support these browsers, when chasing an
          // error there is already enough frustration, so we'll still
          // synthesize a new one

          if (error == null) {
            String errorString = msg + " (" + url + ":" + line;
            // IE8 and IE9 do not have the column number
            if (Js.asAny(column) == null) {
              errorString += ":" + column;
            }
            errorString += ")";
            uncaughtExceptionHandler.onUncaughtException(fromObject(errorString));

          } else {
            uncaughtExceptionHandler.onUncaughtException(fromObject(error));
          }

          return null;
        };

    addOnErrorHandler(DomGlobal.window, errorHandler);
    if (DomGlobal.window != InnerWindow.window) {
      // if the local window is the same as the global one (SSO linker in gwt2, or default in j2cl)
      // we skip this
      addOnErrorHandler(InnerWindow.window, errorHandler);
    }
  }

  /**
   * Appends a new onerror handler so that both original and new are called, or just assigns the new
   * one if there was no existing one.
   */
  private static void addOnErrorHandler(Window window, Window.OnerrorFn onerrorFn) {
    Window.OnerrorFn original = window.onerror;
    if (original == null) {
      window.onerror = onerrorFn;
    }
    window.onerror =
        (p0, p1, p2, p3, p4) -> {
          onerrorFn.onInvoke(p0, p1, p2, p3, p4);
          original.onInvoke(p0, p1, p2, p3, p4);
          return null;
        };
  }

  /**
   * Ugly hack to let j2cl and gwt both call the fake method Throwable.of, which only exists in our
   * emul code, not in the proper JRE.
   */
  @JsMethod
  private static native Throwable fromObject(Object obj) /*-{
    //GWT2 impl using JSNI, see GWT.native.js for the j2cl impl
    var throwable = @java.lang.Throwable::of(*)(obj);
  }-*/;

  @JsType(isNative = true, name = "window", namespace = "<window>")
  private static class InnerWindow {
    static Window window;
  }

  @JsMethod(namespace = "<window>", name = "throw")
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
    throw new UnsupportedOperationException(
        "Pick either GWT2 split point or Closure-Compiler chunks");
  }

  public static void runAsync(Object ignore) {
    throw new UnsupportedOperationException(
        "Pick either GWT2 split point or Closure-Compiler chunks");
  }
}
