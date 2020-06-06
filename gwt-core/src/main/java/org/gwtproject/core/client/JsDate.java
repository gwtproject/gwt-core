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

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/** A simple wrapper around a native JS Date object. */
@Deprecated
@JsType(isNative = true, name = "Date", namespace = JsPackage.GLOBAL)
public class JsDate extends JavaScriptObject {

  /** Creates a new date with the current time. */
  @JsOverlay
  public static JsDate create() {
    return new JsDate();
  }

  /**
   * Creates a new date with the specified internal representation, which is the number of
   * milliseconds since midnight on January 1st, 1970. This is the same representation returned by
   * {@link #getTime()}.
   */
  @JsOverlay
  public static JsDate create(double milliseconds) {
    return new JsDate(milliseconds);
  }

  /** Creates a new date using the specified values. */
  @JsOverlay
  public static JsDate create(int year, int month) {
    return new JsDate(year, month);
  }

  /** Creates a new date using the specified values. */
  @JsOverlay
  public static JsDate create(int year, int month, int dayOfMonth) {
    return new JsDate(year, month, dayOfMonth);
  }

  /** Creates a new date using the specified values. */
  @JsOverlay
  public static JsDate create(int year, int month, int dayOfMonth, int hours) {
    return new JsDate(year, month, dayOfMonth, hours);
  }

  /** Creates a new date using the specified values. */
  @JsOverlay
  public static JsDate create(int year, int month, int dayOfMonth, int hours, int minutes) {
    return new JsDate(year, month, dayOfMonth, hours, minutes);
  }

  /** Creates a new date using the specified values. */
  @JsOverlay
  public static JsDate create(
      int year, int month, int dayOfMonth, int hours, int minutes, int seconds) {
    return new JsDate(year, month, dayOfMonth, hours, minutes, seconds);
  }

  /** Creates a new date using the specified values. */
  @JsOverlay
  public static JsDate create(
      int year, int month, int dayOfMonth, int hours, int minutes, int seconds, int millis) {
    return new JsDate(year, month, dayOfMonth, hours, minutes, seconds, millis);
  }

  /** Creates a new date from a string to be parsed. */
  @JsOverlay
  public static JsDate create(String dateString) {
    return new JsDate(dateString);
  }

  /**
   * Returns the numeric value corresponding to the current time - the number of milliseconds
   * elapsed since 1 January 1970 00:00:00 UTC.
   */
  public static native double now();

  /**
   * Parses a string representation of a date and time and returns the internal millisecond
   * representation. If the string cannot be parsed, the returned value will be <code>NaN</code>.
   * Use {@link Double#isNaN(double)} to check the result.
   */
  public static native double parse(String dateString);

  // CHECKSTYLE_OFF: Matching the spec.
  /** Returns the internal millisecond representation of the specified UTC date and time. */
  public static native double UTC(
      int year, int month, int dayOfMonth, int hours, int minutes, int seconds, int millis);

  // CHECKSTYLE_ON

  public JsDate() {}

  public JsDate(String dateString) {}

  public JsDate(double milliseconds) {}

  public JsDate(double yearNum, double monthNum) {}

  public JsDate(double yearNum, double monthNum, double dayNum) {}

  public JsDate(double yearNum, double monthNum, double dayNum, double hourNum) {}

  public JsDate(double yearNum, double monthNum, double dayNum, double hourNum, double minuteNum) {}

  public JsDate(
      double yearNum,
      double monthNum,
      double dayNum,
      double hourNum,
      double minuteNum,
      double secondNum) {}

  public JsDate(
      double yearNum,
      double monthNum,
      double dayNum,
      double hourNum,
      double minuteNum,
      double secondNum,
      double millisecondNum) {}

  /** Returns the day of the month. */
  public final native int getDate();

  /** Returns the day of the week, from <code>0</code> (Sunday) to <code>6</code> Saturday. */
  public final native int getDay();

  /** Returns the four-digit year. */
  public final native int getFullYear();

  /** Returns the hour, between <code>0</code> (midnight) and <code>23</code>. */
  public final native int getHours();

  /** Returns the milliseconds, between <code>0</code> and <code>999</code>. */
  public final native int getMilliseconds();

  /** Returns the minutes, between <code>0</code> and <code>59</code>. */
  public final native int getMinutes();

  /** Returns the month, from <code>0</code> (January) to <code>11</code> December. */
  public final native int getMonth();

  /** Returns the seconds, between <code>0</code> and <code>59</code>. */
  public final native int getSeconds();

  /**
   * Returns the internal millisecond representation of the date, the number of milliseconds since
   * midnight on January 1st, 1970. This is the same representation returned by {@link #getTime()}.
   */
  public final native double getTime();

  /**
   * Returns the difference, in minutes, between the local and UTC representations of this date. The
   * value returned is affected by whether or not daylight savings time would be in effect on
   * specified date.
   */
  public final native int getTimezoneOffset();

  /** Returns the day of the month, in UTC. */
  public final native int getUTCDate();

  /**
   * Returns the day of the week, from <code>0</code> (Sunday) to <code>6</code> Saturday, in UTC.
   */
  public final native int getUTCDay();

  /** Returns the four-digit year, in UTC. */
  public final native int getUTCFullYear();

  /** Returns the hour, between <code>0</code> (midnight) and <code>23</code>, in UTC. */
  public final native int getUTCHours();

  /** Returns the milliseconds, between <code>0</code> and <code>999</code>, in UTC. */
  public final native int getUTCMilliseconds();

  /** Returns the minutes, between <code>0</code> and <code>59</code>, in UTC. */
  public final native int getUTCMinutes();

  /** Returns the month, from <code>0</code> (January) to <code>11</code> December, in UTC. */
  public final native int getUTCMonth();

  /** Returns the seconds, between <code>0</code> and <code>59</code>, in UTC. */
  public final native int getUTCSeconds();

  /**
   * Returns the year minus 1900.
   *
   * @deprecated Use {@link #getFullYear()}.
   */
  @Deprecated
  public final native int getYear();

  /** Sets the day of the month. Returns the millisecond representation of the adjusted date. */
  public final native double setDate(int dayOfMonth);

  /** Sets the year. Returns the millisecond representation of the adjusted date. */
  public final native double setFullYear(int year);

  /** Sets the year and month. Returns the millisecond representation of the adjusted date. */
  public final native double setFullYear(int year, int month);

  /** Sets the year, month, and day. Returns the millisecond representation of the adjusted date. */
  public final native double setFullYear(int year, int month, int day);

  /** Sets the hour. Returns the millisecond representation of the adjusted date. */
  public final native double setHours(int hours);

  /** Sets the hour and minutes. Returns the millisecond representation of the adjusted date. */
  public final native double setHours(int hours, int mins);

  /**
   * Sets the hour, minutes, and seconds. Returns the millisecond representation of the adjusted
   * date.
   */
  public final native double setHours(int hours, int mins, int secs);

  /**
   * Sets the hour, minutes, seconds, and milliseconds. Returns the millisecond representation of
   * the adjusted date.
   */
  public final native double setHours(int hours, int mins, int secs, int ms);

  /** Sets the minutes. Returns the millisecond representation of the adjusted date. */
  public final native double setMinutes(int minutes);

  /** Sets the minutes and seconds. Returns the millisecond representation of the adjusted date. */
  public final native double setMinutes(int minutes, int seconds);

  /**
   * Sets the minutes, seconds, and milliseconds. Returns the millisecond representation of the
   * adjusted date.
   */
  public final native double setMinutes(int minutes, int seconds, int millis);

  /** Sets the month. Returns the millisecond representation of the adjusted date. */
  public final native double setMonth(int month);

  /** Sets the month and day. Returns the millisecond representation of the adjusted date. */
  public final native double setMonth(int month, int dayOfMonth);

  /** Sets the seconds. Returns the millisecond representation of the adjusted date. */
  public final native double setSeconds(int seconds);

  /**
   * Sets the seconds and milliseconds. Returns the millisecond representation of the adjusted date.
   */
  public final native double setSeconds(int seconds, int millis);

  /** Sets the internal date representation. Returns the <code>milliseconds</code> argument. */
  public final native double setTime(double milliseconds);

  /**
   * Sets the day of the month, in UTC. Returns the millisecond representation of the adjusted date.
   */
  public final native double setUTCDate(int dayOfMonth);

  /** Sets the year, in UTC. Returns the millisecond representation of the adjusted date. */
  public final native double setUTCFullYear(int year);

  /**
   * Sets the year and month, in UTC. Returns the millisecond representation of the adjusted date.
   */
  public final native double setUTCFullYear(int year, int month);

  /**
   * Sets the year, month, and day, in UTC. Returns the millisecond representation of the adjusted
   * date.
   */
  public final native double setUTCFullYear(int year, int month, int day);

  /** Sets the hour, in UTC. Returns the millisecond representation of the adjusted date. */
  public final native double setUTCHours(int hours);

  /**
   * Sets the hour and minutes, in UTC. Returns the millisecond representation of the adjusted date.
   */
  public final native double setUTCHours(int hours, int mins);

  /**
   * Sets the hour, minutes, and seconds, in UTC. Returns the millisecond representation of the
   * adjusted date.
   */
  public final native double setUTCHours(int hours, int mins, int secs);

  /**
   * Sets the hour, minutes, seconds, and milliseconds, in UTC. Returns the millisecond
   * representation of the adjusted date.
   */
  public final native double setUTCHours(int hours, int mins, int secs, int ms);

  /** Sets the minutes, in UTC. Returns the millisecond representation of the adjusted date. */
  public final native double setUTCMinutes(int minutes);

  /**
   * Sets the minutes and seconds, in UTC. Returns the millisecond representation of the adjusted
   * date.
   */
  public final native double setUTCMinutes(int minutes, int seconds);

  /**
   * Sets the minutes, seconds, and milliseconds, in UTC. Returns the millisecond representation of
   * the adjusted date.
   */
  public final native double setUTCMinutes(int minutes, int seconds, int millis);

  /** Sets the month, in UTC. Returns the millisecond representation of the adjusted date. */
  public final native double setUTCMonth(int month);

  /**
   * Sets the month and day, in UTC. Returns the millisecond representation of the adjusted date.
   */
  public final native double setUTCMonth(int month, int dayOfMonth);

  /** Sets the seconds, in UTC. Returns the millisecond representation of the adjusted date. */
  public final native double setUTCSeconds(int seconds);

  /**
   * Sets the seconds and milliseconds, in UTC. Returns the millisecond representation of the
   * adjusted date.
   */
  public final native double setUTCSeconds(int seconds, int millis);

  /**
   * Sets a two-digit year.
   *
   * @deprecated Use {@link #setFullYear(int)}.
   */
  @Deprecated
  public final native double setYear(int year);

  /** Returns a date string in the local time zone. */
  public final native String toDateString();

  /**
   * Returns a date and time string in GMT.
   *
   * @deprecated Use {@link #toUTCString()}.
   */
  @Deprecated
  public final native String toGMTString();

  /** Returns a date string in the local time zone according to local formatting conventions. */
  public final native String toLocaleDateString();

  /**
   * Returns a date and time string in the local time zone according to local formatting
   * conventions.
   */
  public final native String toLocaleString();

  /** Returns a time string in the local time zone according to local formatting conventions. */
  public final native String toLocaleTimeString();

  /** Returns a time string in the local time zone. */
  public final native String toTimeString();

  /** Returns a date and time string in UTC. */
  public final native String toUTCString();

  /** Returns the millisecond representation, as {@link #getTime()}. */
  public final native double valueOf();
}
