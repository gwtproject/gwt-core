package org.gwtproject.core.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * Tests {@link com.google.gwt.core.client.JsonUtils}.
 */
public class JsonUtilsTest extends GWTTestCase {
    @Override
    public String getModuleName() {
        return "org.gwtproject.core.CoreTest";
    }

    public void testStringify() throws Exception {
        if (isFirefox40OrEarlier()) {
            return;
        }
        assertEquals("{\"a\":2}", com.google.gwt.core.client.JsonUtils.stringify(createJson()));
        assertEquals("{\n\t\"a\": 2\n}", com.google.gwt.core.client.JsonUtils.stringify(createJson(), "\t"));
        assertEquals("{\nXYZ\"a\": 2\n}", JsonUtils.stringify(createJson(), "XYZ"));
    }

    private native JavaScriptObject createJson() /*-{
      return { a: 2 };
    }-*/;

    private static native boolean isFirefox40OrEarlier() /*-{
      return @com.google.gwt.dom.client.DOMImplMozilla::isGecko2OrBefore()();
    }-*/;
}

