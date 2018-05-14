package org.gwtproject.core.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.junit.client.GWTTestCase;

public class JavaScriptObjectTest extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "org.gwtproject.core.Core";
    }

    public void testJsArray() {
        JsArrayString array = JavaScriptObject.createArray(20).cast();
        assertEquals(20, array.length());
    }
}