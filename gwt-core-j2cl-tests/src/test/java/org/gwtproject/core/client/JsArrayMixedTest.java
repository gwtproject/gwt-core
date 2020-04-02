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

import static org.junit.Assert.*;

import com.google.j2cl.junit.apt.J2clTestInput;
import elemental2.core.Global;
import elemental2.core.JsArray;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import jsinterop.base.JsPropertyMap;
import org.junit.Test;

/** Tests JsArrayMixed methods. */
@J2clTestInput(JsArrayMixedTest.class)
public class JsArrayMixedTest {

  @JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
  private static class JsTestFruit extends JavaScriptObject {
    @SuppressWarnings("unused")
    protected JsTestFruit() {}
  }

  {
    mixedArray = makeArray();
  }

  JsArrayMixed mixedArray;

  @Test
  public void testGetBoolean() {
    assertTrue(mixedArray.getBoolean(0));
    // Test automatic type casting
    mixedArray.set(0, 0);
    assertFalse(mixedArray.getBoolean(0));
  }

  @Test
  public void testGetNumber() {
    assertEquals(2.5, mixedArray.getNumber(1), 0);
    assertEquals(1.0, mixedArray.getNumber(2), 0);
    // Cast from boolean
    assertEquals(1.0, mixedArray.getNumber(0), 0);
  }

  @Test
  public void testGetObject() {
    assertTrue(compareObjects(makeObject("pear"), mixedArray.<JsTestFruit>getObject(3)));
  }

  @Test
  public void testGetString() {
    assertEquals("orange", mixedArray.getString(4));
    assertEquals("true", mixedArray.getString(0));
    assertEquals("2.5", mixedArray.getString(1));
    assertEquals("1", mixedArray.getString(2));
  }

  @Test
  public void testJoin() {
    assertEquals("true,2.5,1,[object Object],orange", mixedArray.join());
  }

  @Test
  public void testJoinString() {
    assertEquals("true<br/>2.5<br/>1<br/>[object Object]<br/>orange", mixedArray.join("<br/>"));
  }

  @Test
  public void testLength() {
    assertEquals(5, mixedArray.length());
  }

  @Test
  public void testPushBoolean() {
    mixedArray.push(false);
    assertEquals(6, mixedArray.length());
    assertFalse(mixedArray.getBoolean(5));
  }

  @Test
  public void testPushDouble() {
    mixedArray.push(1.5);
    assertEquals(6, mixedArray.length());
    assertEquals(1.5, mixedArray.getNumber(5), 0);
  }

  @Test
  public void testPushJavaScriptObject() {
    JsTestFruit fruit = makeObject("strawberry");
    mixedArray.push(fruit);
    assertEquals(6, mixedArray.length());
    assertEquals(fruit, mixedArray.<JsTestFruit>getObject(5));
  }

  @Test
  public void testPushString() {
    mixedArray.push("kiwi");
    assertEquals(6, mixedArray.length());
    assertEquals("kiwi", mixedArray.getString(5));
  }

  @Test
  public void testSetIntBoolean() {
    mixedArray.set(1, false);
    assertFalse(mixedArray.getBoolean(1));
  }

  @Test
  public void testSetIntDouble() {
    mixedArray.set(0, 4.1);
    assertEquals(4.1, mixedArray.getNumber(0), 0);
  }

  @Test
  public void testSetIntJavaScriptObject() {
    JsTestFruit fruit = makeObject("kiwi");
    mixedArray.set(0, fruit);
    assertEquals(fruit, mixedArray.<JsTestFruit>getObject(0));
  }

  @Test
  public void testSetIntString() {
    mixedArray.set(0, "apple");
    assertEquals("apple", mixedArray.getString(0));
  }

  @Test
  public void testSetLength() {
    mixedArray.setLength(10);
    assertEquals(10, mixedArray.length());
  }

  @Test
  public void testShiftBoolean() {
    assertEquals(5, mixedArray.length());
    assertTrue(mixedArray.shiftBoolean());
    assertEquals(4, mixedArray.length());
    assertTrue(mixedArray.shiftBoolean());
    assertTrue(mixedArray.shiftBoolean());
    assertTrue(mixedArray.shiftBoolean());
    assertTrue(mixedArray.shiftBoolean());
    assertEquals(0, mixedArray.length());
  }

  @Test
  public void testShiftNumber() {
    assertEquals(5, mixedArray.length());
    assertEquals(1.0, mixedArray.shiftNumber(), 0);
    assertEquals(4, mixedArray.length());
    assertEquals(2.5, mixedArray.shiftNumber(), 0);
    assertEquals(1.0, mixedArray.shiftNumber(), 0);
    assertTrue(Double.isNaN(mixedArray.shiftNumber()));
    assertTrue(Double.isNaN(mixedArray.shiftNumber()));
    assertEquals(0, mixedArray.length());
  }

  @Test
  public void testShiftObject() {
    assertEquals(5, mixedArray.length());
    assertEquals("true", mixedArray.<JavaScriptObject>shiftObject().toString());
    assertEquals(4, mixedArray.length());
    assertEquals("2.5", mixedArray.<JavaScriptObject>shiftObject().toString());
    assertEquals("1", mixedArray.<JavaScriptObject>shiftObject().toString());
    assertTrue(compareObjects(makeObject("pear"), mixedArray.<JsTestFruit>shiftObject()));
    assertEquals(1, mixedArray.length());
  }

  @Test
  public void testShiftString() {
    assertEquals(5, mixedArray.length());
    assertEquals("true", mixedArray.shiftString());
    assertEquals(4, mixedArray.length());
    assertEquals("2.5", mixedArray.shiftString());
    assertEquals("1", mixedArray.shiftString());
    assertEquals("[object Object]", mixedArray.shiftString());
    assertEquals("orange", mixedArray.shiftString());
    assertEquals(0, mixedArray.length());
  }

  @Test
  public void testUnshiftBoolean() {
    assertEquals(5, mixedArray.length());
    mixedArray.unshift(false);
    assertEquals(6, mixedArray.length());
    assertFalse(mixedArray.getBoolean(0));
  }

  @Test
  public void testUnshiftDouble() {
    assertEquals(5, mixedArray.length());
    mixedArray.unshift(0.5);
    assertEquals(6, mixedArray.length());
    assertEquals(0.5, mixedArray.getNumber(0), 0);
  }

  @Test
  public void testUnshiftJavaScriptObject() {
    JsTestFruit fruit = makeObject("kiwi");
    assertEquals(5, mixedArray.length());
    mixedArray.unshift(fruit);
    assertEquals(6, mixedArray.length());
    assertEquals(fruit, mixedArray.<JsTestFruit>getObject(0));
  }

  @Test
  public void testUnshiftString() {
    assertEquals(5, mixedArray.length());
    mixedArray.unshift("kiwi");
    assertEquals(6, mixedArray.length());
    assertEquals("kiwi", mixedArray.getString(0));
  }

  @Test
  public void testEdgeCases() {
    JsArrayMixed weirdArray = makeEdgeCaseArray();

    // boolean values
    assertFalse(weirdArray.getBoolean(0));
    assertTrue(weirdArray.getBoolean(1));
    assertTrue(weirdArray.getBoolean(2));
    assertTrue(weirdArray.getBoolean(3));
    assertFalse(weirdArray.getBoolean(4));
    assertTrue(weirdArray.getBoolean(5));
    assertFalse(weirdArray.getBoolean(6));
    assertTrue(weirdArray.getBoolean(7));
    assertTrue(weirdArray.getBoolean(8));
    assertTrue(weirdArray.getBoolean(9));
    assertTrue(weirdArray.getBoolean(10));

    // number values
    assertEquals(0.0, weirdArray.getNumber(0), 0);
    assertEquals(0.0, weirdArray.getNumber(1), 0);
    assertEquals(1.0, weirdArray.getNumber(2), 0);
    assertTrue(Double.isNaN(weirdArray.getNumber(3)));
    assertEquals(0.0, weirdArray.getNumber(4), 0);
    assertEquals(1.0, weirdArray.getNumber(5), 0);
    assertTrue(Double.isNaN(weirdArray.getNumber(6)));
    assertEquals(Double.POSITIVE_INFINITY, weirdArray.getNumber(7), 0);
    assertEquals(0.0, weirdArray.getNumber(8), 0);
    assertEquals(0.0, weirdArray.getNumber(9), 0);
    assertEquals(1.0, weirdArray.getNumber(10), 0);

    // TODO: arrary.toString() will return Object.toString() instead of a normal
    //       String as expeced in JS. J2CL handels it in a different way, than
    //       GWT does. Atm we will not fix this behavior.
    //      DomGlobal.console.log("0 -> " + weirdArray.getString(0));
    //      DomGlobal.console.log("1 -> " + weirdArray.getString(1));
    //      DomGlobal.console.log("2 -> " + weirdArray.getString(2));
    //      DomGlobal.console.log("3 -> " + weirdArray.getString(3));
    //      DomGlobal.console.log("4 -> " + weirdArray.getString(4));
    //      DomGlobal.console.log("5 -> " + weirdArray.getString(5));
    //      DomGlobal.console.log("6 -> " + weirdArray.getString(6));
    //      DomGlobal.console.log("7 -> " + weirdArray.getString(7));
    //      DomGlobal.console.log("8 -> " + weirdArray.getString(8));
    //      DomGlobal.console.log("9 -> " + weirdArray.getString(9));
    //      DomGlobal.console.log("10 -> " + weirdArray.getString(10));
    //      // String values
    //      assertEquals("", weirdArray.getString(0));
    //      assertEquals("0", weirdArray.getString(1));
    //      assertEquals("1", weirdArray.getString(2));
    //      assertEquals("NaN", weirdArray.getString(3));
    //      assertEquals("0", weirdArray.getString(4));
    //      assertEquals("1", weirdArray.getString(5));
    //      assertEquals("NaN", weirdArray.getString(6));
    //      assertEquals("Infinity", weirdArray.getString(7));
    //      assertEquals("", weirdArray.getString(8));
    //      assertEquals("0", weirdArray.getString(9));
    //      assertEquals("1", weirdArray.getString(10));
  }

  private boolean compareObjects(JavaScriptObject expected, JavaScriptObject actual) {
    final boolean[] equals = {true};
    Js.asPropertyMap(expected)
        .forEach(
            key -> {
              if (!Js.asPropertyMap(expected).get(key).equals(Js.asPropertyMap(actual).get(key))) {
                if (equals[0]) {
                  equals[0] = false;
                  return;
                }
              }
            });
    return equals[0];
  }

  private JsArrayMixed makeArray() {
    return Js.cast(
        elemental2.core.JsArray.of(true, 2.5, 1, JsPropertyMap.of("kind", "pear"), "orange"));
  }

  private JsArrayMixed makeEdgeCaseArray() {
    return Js.cast(
        elemental2.core.JsArray.of(
            "",
            "0",
            "1",
            "NaN",
            Js.asAny(0),
            Js.asAny(1),
            Global.NaN,
            Global.Infinity,
            new JsArray<>(),
            new JsArray<>(0),
            new JsArray<>(1)));
  }

  //  private native JsArrayMixed makeEdgeCaseArray() /*-{
  //      return ['', '0', '1', 'NaN', 0, 1, NaN, Infinity, [], [0], [1]];
  //    }-*/;

  private JsTestFruit makeObject(String theKind) {
    return Js.cast(JsPropertyMap.of("kind", theKind));
  }
}
