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

import elemental2.dom.Document;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLScriptElement;
import elemental2.dom.Window;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.Any;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;

public class ScriptInjector {

  /** Builder for directly injecting a script body into the DOM. */
  public static class FromString {
    private boolean removeTag = true;
    private final String scriptBody;
    private Object window;

    /** @param scriptBody The script text to install into the document. */
    public FromString(String scriptBody) {
      this.scriptBody = scriptBody;
    }

    /**
     * Injects a script into the DOM. The JavaScript is evaluated and will be available immediately
     * when this call returns.
     *
     * <p>By default, the script is installed in the same window that the GWT code is installed in.
     *
     * @return the script element created for the injection. Note that it may be removed from the
     *     DOM.
     */
    public JavaScriptObject inject() {
      Object wnd = (window == null) ? GwtAppHostWindow.nativeWindow : window;
      assert wnd != null;
      Document doc =
          Js.<JsPropertyMap<Any>>uncheckedCast(wnd)
              .getAsAny("document")
              .uncheckedCast(); // window has no document property... TODO
      assert doc != null;

      // skipping the cast here since Chrome has a different HTMLScriptElement in the ifram
      HTMLScriptElement scriptElement = Js.uncheckedCast(doc.createElement("script"));
      assert scriptElement != null;
      scriptElement.text = scriptBody;
      doc.head.appendChild(scriptElement);
      if (removeTag) {
        scriptElement.parentNode.removeChild(scriptElement);
      }
      return Js.cast(scriptElement);
    }

    /**
     * @param removeTag If true, remove the tag immediately after injecting the source. This shrinks
     *     the DOM, possibly at the expense of readability if you are debugging javaScript.
     *     <p>Default value is {@code true}.
     * @return this
     */
    public FromString setRemoveTag(boolean removeTag) {
      this.removeTag = removeTag;
      return this;
    }

    /**
     * @param window Specify which window to use to install the script. If not specified, the top
     *     current window GWT is loaded in is used.
     * @return this
     */
    public FromString setWindow(Object window) {
      this.window = window;
      return this;
    }
  }

  @JsType(isNative = true)
  private static final class GwtAppHostWindow {
    @JsProperty(namespace = "<window>", name = "window")
    private static Window nativeWindow;
  }

  /** Build an injection call for adding a script by URL. */
  public static class FromUrl {

    private Callback<Void, Exception> callback;
    private boolean removeTag = false;
    private final String scriptUrl;
    private Window window;

    private FromUrl(String scriptUrl) {
      this.scriptUrl = scriptUrl;
    }

    /**
     * Injects an external JavaScript reference into the document and optionally calls a callback
     * when it finishes loading.
     *
     * @return the script element created for the injection.
     */
    public JavaScriptObject inject() {
      Window wnd = (window == null) ? GwtAppHostWindow.nativeWindow : window;
      assert wnd != null;
      Document doc =
          Js.<JsPropertyMap<Any>>uncheckedCast(wnd)
              .getAsAny("document")
              .uncheckedCast(); // window has no document property... TODO
      assert doc != null;

      // skipping the cast here since Chrome has a different HTMLScriptElement in the ifram
      HTMLScriptElement scriptElement = Js.uncheckedCast(doc.createElement("script"));
      assert scriptElement != null;
      if (callback != null || removeTag) {
        attachListeners(scriptElement, callback, removeTag);
      }
      scriptElement.src = scriptUrl;
      doc.head.appendChild(scriptElement);
      return Js.cast(scriptElement);
    }

    /**
     * Specify a callback to be invoked when the script is loaded or loading encounters an error.
     *
     * <p><b>Warning:</b> This class <b>does not</b> control whether or not a URL has already been
     * injected into the document. The client of this class has the responsibility of keeping score
     * of the injected JavaScript files.
     *
     * <p><b>Known bugs:</b> This class uses the script tag's <code>onerror()
     * </code> callback to attempt to invoke onFailure() if the browser detects a load failure. This
     * is not reliable on all browsers (Doesn't work on IE or Safari 3 or less).
     *
     * <p>On Safari version 3 and prior, the onSuccess() callback may be invoked even when the load
     * of a page fails.
     *
     * <p>To support failure notification on IE and older browsers, you should check some side
     * effect of the script (such as a defined function) to see if loading the script worked and
     * include timeout logic.
     *
     * @param callback callback that gets invoked asynchronously.
     * @return this
     */
    public FromUrl setCallback(Callback<Void, Exception> callback) {
      this.callback = callback;
      return this;
    }

    /**
     * @param removeTag If true, remove the tag after the script finishes loading. This shrinks the
     *     DOM, possibly at the expense of readability if you are debugging javaScript.
     *     <p>Default value is {@code false}, but this may change in a future release.
     * @return this
     */
    public FromUrl setRemoveTag(boolean removeTag) {
      this.removeTag = removeTag;
      return this;
    }

    /**
     * This call allows you to specify which DOM window object to install the script tag in. To
     * install into the Top level window call <code>
     *   builder.setWindow(ScriptInjector.TOP_WINDOW);
     * </code>
     *
     * @param window Specifies which window to install in.
     * @return this
     */
    public FromUrl setWindow(Object window) {
      this.window = Js.uncheckedCast(window);
      return this;
    }
  }

  /**
   * Returns the top level window object. Use this to inject a script so that global variable
   * references are available under <code>$wnd</code> in JSNI access.
   *
   * <p>Note that if your GWT app is loaded from a different domain than the top window, you may not
   * be able to add a script element to the top window.
   */
  public static final Object TOP_WINDOW = DomGlobal.window;

  /**
   * Build an injection call for directly setting the script text in the DOM.
   *
   * @param scriptBody the script text to be injected and immediately executed.
   * @return new FromString
   */
  public static FromString fromString(String scriptBody) {
    return new FromString(scriptBody);
  }

  /**
   * Build an injection call for adding a script by URL.
   *
   * @param scriptUrl URL of the JavaScript to be injected.
   * @return new FromUrl
   */
  public static FromUrl fromUrl(String scriptUrl) {
    return new FromUrl(scriptUrl);
  }

  /**
   * Attaches event handlers to a script DOM element that will run just once a callback when it gets
   * successfully loaded.
   *
   * <p><b>IE Notes:</b> Internet Explorer calls {@code onreadystatechanged} several times while
   * varying the {@code readyState} property: in theory, {@code "complete"} means the content is
   * loaded, parsed and ready to be used, but in practice, {@code "complete"} happens when the JS
   * file was already cached, and {@code "loaded"} happens when it was transferred over the network.
   * Other browsers just call the {@code onload} event handler. To ensure the callback will be
   * called at most once, we clear out both event handlers when the callback runs for the first
   * time. More info at the <a
   * href="http://www.phpied.com/javascript-include-ready-onload/">phpied.com blog</a>.
   *
   * <p>In IE, do not trust the "order" of {@code readyState} values. For instance, in IE 8 running
   * in Vista, if the JS file is cached, only {@code "complete"} will happen, but if the file has to
   * be downloaded, {@code "loaded"} can fire in parallel with {@code "loading"}.
   *
   * @param scriptElement element to which the event handlers will be attached
   * @param callback callback that runs when the script is loaded and parsed.
   */
  private static void attachListeners(
      HTMLScriptElement scriptElement, Callback<Void, Exception> callback, boolean removeTag) {
    scriptElement.onload =
        e -> {
          if (callback != null) {
            callback.onSuccess(null);
          }
          if (removeTag) {
            scriptElement.parentNode.removeChild(scriptElement);
          }
        };
    // or possibly more portable script_tag.addEventListener('error', function(){...}, true);
    scriptElement.onerror =
        e -> {
          if (callback != null) {
            callback.onFailure(new Exception("onerror() called."));
          }
          if (removeTag) {
            scriptElement.parentNode.removeChild(scriptElement);
          }
          return null;
        };
  }

  /** Utility class - do not instantiate */
  private ScriptInjector() {}
}
