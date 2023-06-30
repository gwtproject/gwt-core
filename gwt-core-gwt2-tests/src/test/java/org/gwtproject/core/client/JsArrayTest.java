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

import com.google.gwt.junit.client.GWTTestCase;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/** Tests JsArray variants. */
public class JsArrayTest extends GWTTestCase {

  @JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
  private static class JsPoint extends JavaScriptObject {
    protected JsPoint() {}

    @JsProperty(name = "x")
    public final native int x();

    @JsProperty(name = "y")
    public final native int y();
  }

  @Override
  public String getModuleName() {
    return "org.gwtproject.core.Core";
  }

  public void testJsArray() {
    // All the test arrays start with 3 elements.
    JsArray<JsPoint> jsArray = makeJsArray();
    assertEquals(3, jsArray.length());

    // Get the three points and make sure they are what we think.
    JsPoint p0 = jsArray.get(0);
    JsPoint p1 = jsArray.get(1);
    JsPoint p2 = jsArray.get(2);

    assertEquals("JsPoint,JsPoint,JsPoint", jsArray.join());
    assertEquals("JsPoint:JsPoint:JsPoint", jsArray.join(":"));

    assertEquals(0, p0.x());
    assertEquals(1, p0.y());
    assertEquals(2, p1.x());
    assertEquals(3, p1.y());
    assertEquals(4, p2.x());
    assertEquals(5, p2.y());

    // Make sure the '3' element is null.
    assertNull(jsArray.get(3));

    // Make a new point and stick it in the '3' slot. It should come back with
    // reference equality intact, and the array length should be bumped to 4.
    JsPoint p3 = makeJsPoint(6, 7);
    jsArray.set(3, p3);
    assertEquals(p3, jsArray.get(3));
    assertEquals(4, jsArray.length());

    jsArray.setLength(0);
    assertEquals(0, jsArray.length());
  }

  public void testJsArrayBoolean() {
    // All the test arrays start with 3 elements.
    JsArrayBoolean jsArray = makeJsArrayBoolean();
    assertEquals(3, jsArray.length());

    // Get the three points and make sure they are what we think.
    assertEquals(true, jsArray.get(0));
    assertEquals(false, jsArray.get(1));
    assertEquals(true, jsArray.get(2));

    assertEquals("true,false,true", jsArray.join());
    assertEquals("true:false:true", jsArray.join(":"));

    // Stick a new boolean in the '3' slot. It should come back intact, and the
    // array length should be bumped to 4.
    jsArray.set(3, false);
    assertEquals(false, jsArray.get(3));
    assertEquals(4, jsArray.length());

    // Add another entry (holdover from when this test verified legacy dev mode)
    jsArray.set(4, false);

    // Add an element to the beginning of the array
    jsArray.unshift(true);
    assertEquals(6, jsArray.length());
    assertTrue(jsArray.get(0));
    assertTrue(jsArray.shift());
    assertEquals(5, jsArray.length());

    jsArray.setLength(0);
    assertEquals(0, jsArray.length());
  }

  public void testJsArrayInteger() {
    // All the test arrays start with 3 elements.
    JsArrayInteger jsArray = makeJsArrayInteger();
    assertEquals(3, jsArray.length());

    // Get the three points and make sure they are what we think.
    assertEquals(0, jsArray.get(0));
    assertEquals(1, jsArray.get(1));
    assertEquals(2, jsArray.get(2));

    assertEquals("0,1,2", jsArray.join());
    assertEquals("0:1:2", jsArray.join(":"));

    // Stick a new number in the '3' slot. It should come back intact, and the
    // array length should be bumped to 4.
    jsArray.set(3, 3);
    assertEquals(3, jsArray.get(3));
    assertEquals(4, jsArray.length());

    // Add another entry (holdover from when this test verified legacy dev mode)
    jsArray.set(4, 33);

    // Add an element to the beginning of the array
    jsArray.unshift(42);
    assertEquals(6, jsArray.length());
    assertEquals(42, jsArray.get(0));
    assertEquals(42, jsArray.shift());
    assertEquals(5, jsArray.length());

    jsArray.setLength(0);
    assertEquals(0, jsArray.length());
  }

  public void testJsArrayNumber() {
    // All the test arrays start with 3 elements.
    JsArrayNumber jsArray = makeJsArrayNumber();
    assertEquals(3, jsArray.length());

    // Get the three points and make sure they are what we think.
    assertEquals(0.0, jsArray.get(0));
    assertEquals(1.1, jsArray.get(1));
    assertEquals(2.2, jsArray.get(2));

    assertEquals("0,1.1,2.2", jsArray.join());
    assertEquals("0:1.1:2.2", jsArray.join(":"));

    // Stick a new number in the '3' slot. It should come back intact, and the
    // array length should be bumped to 4.
    jsArray.set(3, 3.0);
    assertEquals(3.0, jsArray.get(3));
    assertEquals(4, jsArray.length());

    // Add another entry (holdover from when this test verified legacy dev mode)
    jsArray.set(4, 4.4);

    // Add an element to the beginning of the array
    jsArray.unshift(42.0);
    assertEquals(6, jsArray.length());
    assertEquals(42.0, jsArray.get(0));
    assertEquals(42.0, jsArray.shift());
    assertEquals(5, jsArray.length());

    jsArray.setLength(0);
    assertEquals(0, jsArray.length());
  }

  public void testJsArrayString() {
    // All the test arrays start with 3 elements.
    JsArrayString jsArray = makeJsArrayString();
    assertEquals(3, jsArray.length());

    // Get the three points and make sure they are what we think.
    String s0 = jsArray.get(0);
    String s1 = jsArray.get(1);
    String s2 = jsArray.get(2);

    assertEquals("foo", s0);
    assertEquals("bar", s1);
    assertEquals("baz", s2);

    assertEquals("foo,bar,baz", jsArray.join());
    assertEquals("foo:bar:baz", jsArray.join(":"));

    // Make sure the '3' element is null.
    assertNull(jsArray.get(3));

    // Stick a new string in the '3' slot. It should come back intact, and the
    // array length should be bumped to 4.
    jsArray.set(3, "tintin");
    assertEquals("tintin", jsArray.get(3));
    assertEquals(4, jsArray.length());

    // Add another entry (holdover from when this test verified legacy dev mode)
    jsArray.set(4, "quux");

    // Add an element to the beginning of the array
    jsArray.unshift("42");
    assertEquals(6, jsArray.length());
    assertEquals("42", jsArray.get(0));
    assertEquals("42", jsArray.shift());
    assertEquals(5, jsArray.length());

    jsArray.setLength(0);
    assertEquals(0, jsArray.length());
  }

  /**
   * Checks that get, length and set methods work even if the JS object
   * is not actual Array
   */
  public void testFakeArray() {
    JsArray<JsPoint> points = makeFakeArray();
    points.set(0, makeJsPoint(1, 2));
    JsPoint pt = points.get(0);
    assertEquals(1, pt.x());
    assertEquals(7, points.length());
  }

  private native JsArray<JsPoint> makeFakeArray() /*-{
    return {length: 7}
  }-*/;

  private native JsArray<JsPoint> makeJsArray() /*-{
      return [
        { x: 0, y: 1, toString: function() { return 'JsPoint';} },
        { x: 2, y: 3, toString: function() { return 'JsPoint';} },
        { x: 4, y: 5, toString: function() { return 'JsPoint';} },
      ];
    }-*/;

  private native JsArrayBoolean makeJsArrayBoolean() /*-{
      return [true, false, true];
    }-*/;

  private native JsArrayInteger makeJsArrayInteger() /*-{
      return [0, 1, 2];
    }-*/;

  private native JsArrayNumber makeJsArrayNumber() /*-{
      return [0.0, 1.1, 2.2];
    }-*/;

  private native JsArrayString makeJsArrayString() /*-{
      return ['foo', 'bar', 'baz'];
    }-*/;

  private native JsPoint makeJsPoint(int newx, int newy) /*-{
      return {x: newx, y: newy};
    }-*/;
}
