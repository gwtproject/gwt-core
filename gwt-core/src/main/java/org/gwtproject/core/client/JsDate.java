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

  /**
   * Creates a new date with the current time.
   *
   * @return instance of JsDate
   */
  @JsOverlay
  public static JsDate create() {
    return new JsDate();
  }

  /**
   * Creates a new date with the specified internal representation, which is the number of
   * milliseconds since midnight on January 1st, 1970. This is the same representation returned by
   * {@link #getTime()}.
   *
   * @param milliseconds time in milliseconds
   * @return the new date
   */
  @JsOverlay
  public static JsDate create(double milliseconds) {
    return new JsDate(milliseconds);
  }

  /**
   * Creates a new date using the specified values.
   *
   * @param month month of date (starting from zero)
   * @param year year of date
   * @return the new date
   */
  @JsOverlay
  public static JsDate create(int year, int month) {
    return new JsDate(year, month);
  }

  /**
   * Creates a new date using the specified values.
   *
   * @param dayOfMonth day of date
   * @param month month of date (starting from zero)
   * @param year year of date
   * @return the new date
   */
  @JsOverlay
  public static JsDate create(int year, int month, int dayOfMonth) {
    return new JsDate(year, month, dayOfMonth);
  }

  /**
   * Creates a new date using the specified values.
   *
   * @param dayOfMonth day of date
   * @param month month of date (starting from zero)
   * @param year year of date
   * @param hours hours
   * @return the new date
   */
  @JsOverlay
  public static JsDate create(int year, int month, int dayOfMonth, int hours) {
    return new JsDate(year, month, dayOfMonth, hours);
  }

  /**
   * Creates a new date using the specified values.
   *
   * @param dayOfMonth day of date
   * @param month month of date (starting from zero)
   * @param year year of date
   * @param hours hours
   * @param minutes minutes
   * @return the new date
   */
  @JsOverlay
  public static JsDate create(int year, int month, int dayOfMonth, int hours, int minutes) {
    return new JsDate(year, month, dayOfMonth, hours, minutes);
  }

  /**
   * Creates a new date using the specified values.
   *
   * @param dayOfMonth day of date
   * @param month month of date (starting from zero)
   * @param year year of date
   * @param hours hours
   * @param minutes minutes
   * @param seconds seconds
   * @return the new date
   */
  @JsOverlay
  public static JsDate create(
      int year, int month, int dayOfMonth, int hours, int minutes, int seconds) {
    return new JsDate(year, month, dayOfMonth, hours, minutes, seconds);
  }

  /**
   * Creates a new date using the specified values.
   *
   * @param dayOfMonth day of date
   * @param month month of date (starting from zero)
   * @param year year of date
   * @param hours hours
   * @param minutes minutes
   * @param seconds seconds
   * @param millis milli-seconds
   * @return the new date
   */
  @JsOverlay
  public static JsDate create(
      int year, int month, int dayOfMonth, int hours, int minutes, int seconds, int millis) {
    return new JsDate(year, month, dayOfMonth, hours, minutes, seconds, millis);
  }

  /**
   * Creates a new date from a string to be parsed.
   *
   * @param dateString date as string
   * @return JavaScript date
   */
  @JsOverlay
  public static JsDate create(String dateString) {
    return new JsDate(dateString);
  }

  /**
   * Returns the numeric value corresponding to the current time - the number of milliseconds
   * elapsed since 1 January 1970 00:00:00 UTC.
   *
   * @return current time in milliseconds
   */
  public static native double now();

  /**
   * Parses a string representation of a date and time and returns the internal millisecond
   * representation. If the string cannot be parsed, the returned value will be <code>NaN</code>.
   * Use {@link Double#isNaN(double)} to check the result.
   *
   * @param dateString date as String
   * @return time in milliseconds
   */
  public static native double parse(String dateString);

  // CHECKSTYLE_OFF: Matching the spec.
  /**
   * Returns the internal millisecond representation of the specified UTC date and time.
   *
   * @param dayOfMonth day of date
   * @param month month of date (starting from zero)
   * @param year year of date
   * @param hours hours
   * @param minutes minutes
   * @param seconds seconds
   * @param millis milliseconds
   * @return time in milliseconds
   */
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

  /**
   * Returns the day of the month.
   *
   * @return date in seconds
   */
  public final native int getDate();

  /**
   * Returns the day of the week, from <code>0</code> (Sunday) to <code>6</code> Saturday.
   *
   * @return day of date
   */
  public final native int getDay();

  /**
   * Returns the four-digit year.
   *
   * @return year of date
   */
  public final native int getFullYear();

  /**
   * Returns the hour, between <code>0</code> (midnight) and <code>23</code>.
   *
   * @return hours of date
   */
  public final native int getHours();

  /**
   * Returns the milliseconds, between <code>0</code> and <code>999</code>.
   *
   * @return milles of date
   */
  public final native int getMilliseconds();

  /**
   * Returns the minutes, between <code>0</code> and <code>59</code>.
   *
   * @return minutes of date
   */
  public final native int getMinutes();

  /**
   * Returns the month, from <code>0</code> (January) to <code>11</code> December.
   *
   * @return month of date
   */
  public final native int getMonth();

  /**
   * Returns the seconds, between <code>0</code> and <code>59</code>.
   *
   * @return seconds of date
   */
  public final native int getSeconds();

  /**
   * Returns the internal millisecond representation of the date, the number of milliseconds since
   * midnight on January 1st, 1970. This is the same representation returned by {@link #getTime()}.
   *
   * @return date in miilseconds
   */
  public final native double getTime();

  /**
   * Returns the difference, in minutes, between the local and UTC representations of this date. The
   * value returned is affected by whether or not daylight savings time would be in effect on
   * specified date.
   *
   * @return difference between the local and UTC representations of this date
   */
  public final native int getTimezoneOffset();

  /**
   * Returns the day of the month, in UTC.
   *
   * @return UTC representation of the date
   */
  public final native int getUTCDate();

  /**
   * Returns the day of the week, from <code>0</code> (Sunday) to <code>6</code> Saturday, in UTC.
   *
   * @return day of week
   */
  public final native int getUTCDay();

  /**
   * Returns the four-digit year, in UTC.
   *
   * @return full year of date
   */
  public final native int getUTCFullYear();

  /**
   * Returns the hour, between <code>0</code> (midnight) and <code>23</code>, in UTC.
   *
   * @return hours of date
   */
  public final native int getUTCHours();

  /**
   * Returns the milliseconds, between <code>0</code> and <code>999</code>, in UTC.
   *
   * @return milliseconds of date
   */
  public final native int getUTCMilliseconds();

  /**
   * Returns the minutes, between <code>0</code> and <code>59</code>, in UTC.
   *
   * @return minutes of date
   */
  public final native int getUTCMinutes();

  /**
   * Returns the month, from <code>0</code> (January) to <code>11</code> December, in UTC.
   *
   * @return month of date
   */
  public final native int getUTCMonth();

  /**
   * Returns the seconds, between <code>0</code> and <code>59</code>, in UTC.
   *
   * @return seconds of date
   */
  public final native int getUTCSeconds();

  /**
   * Returns the year minus 1900.
   *
   * @return year of date
   * @deprecated Use {@link #getFullYear()}.
   */
  @Deprecated
  public final native int getYear();

  /**
   * Sets the day of the month. Returns the millisecond representation of the adjusted date.
   *
   * @param dayOfMonth day of month
   * @return milliseconds of the adjusted date
   */
  public final native double setDate(int dayOfMonth);

  /**
   * Sets the year.
   *
   * @param year year of month
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setFullYear(int year);

  /**
   * Sets the year and month.
   *
   * @param month month of month
   * @param year year of month
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setFullYear(int year, int month);

  /**
   * Sets the year, month, and day.
   *
   * @param day day of month
   * @param month month of month
   * @param year year of month
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setFullYear(int year, int month, int day);

  /**
   * Sets the hour.
   *
   * @param hours hours of month
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setHours(int hours);

  /**
   * Sets the hour and minutes.
   *
   * @param hours hours of month
   * @param mins minutes of month
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setHours(int hours, int mins);

  /**
   * Sets the hour, minutes, and seconds.
   *
   * @param hours hours of month
   * @param mins minutes of month
   * @param secs seconds to set
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setHours(int hours, int mins, int secs);

  /**
   * Sets the hour, minutes, seconds, and milliseconds.
   *
   * @param hours hours to set
   * @param mins minutes to set
   * @param secs seconds to set
   * @param ms milliseconds to set
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setHours(int hours, int mins, int secs, int ms);

  /**
   * Sets the minutes.
   *
   * @param minutes minutes to set
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setMinutes(int minutes);

  /**
   * Sets the minutes and seconds.
   *
   * @param minutes minutes to set
   * @param seconds seconds to set
   * @return Returns the millisecond representation of the adjusted date.
   */
  public final native double setMinutes(int minutes, int seconds);

  /**
   * Sets the minutes, seconds, and milliseconds.
   *
   * @param minutes minutes to set
   * @param seconds seconds to set
   * @param millis millis to set
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setMinutes(int minutes, int seconds, int millis);

  /**
   * Sets the month.
   *
   * @param month month to set
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setMonth(int month);

  /**
   * Sets the month and day.
   *
   * @param month month to set
   * @param dayOfMonth day of month to set
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setMonth(int month, int dayOfMonth);

  /**
   * Sets the seconds. Returns the millisecond representation of the adjusted date.
   *
   * @param seconds seconds
   * @return millisecond representation of the adjusted time
   */
  public final native double setSeconds(int seconds);

  /**
   * Sets the seconds and milliseconds. Returns the millisecond representation of the adjusted date.
   *
   * @param seconds seconds
   * @param millis milliseconds
   * @return millisecond representation of the adjusted time
   */
  public final native double setSeconds(int seconds, int millis);

  /**
   * Sets the internal date representation. Returns the <code>milliseconds</code> argument.
   *
   * @param milliseconds internal time
   * @return millisecond representation of the adjusted time
   */
  public final native double setTime(double milliseconds);

  /**
   * Sets the day of the month, in UTC. Returns the millisecond representation of the adjusted date.
   *
   * @param dayOfMonth day of month
   * @return millisecond representation of the adjusted date
   */
  public final native double setUTCDate(int dayOfMonth);

  /**
   * Sets the year, in UTC. Returns the millisecond representation of the adjusted date.
   *
   * @param year year to set
   * @return millisecond representation of the adjusted date
   */
  public final native double setUTCFullYear(int year);

  /**
   * Sets the year and month, in UTC. Returns the millisecond representation of the adjusted date.
   *
   * @param month month to set
   * @param year year to set
   * @return millisecond representation of the adjusted date
   */
  public final native double setUTCFullYear(int year, int month);

  /**
   * Sets the year, month, and day, in UTC.
   *
   * @param day day to set
   * @param month month to set
   * @param year year to set
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setUTCFullYear(int year, int month, int day);

  /**
   * Sets the hour, in UTC.
   *
   * @param hours hours to set
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setUTCHours(int hours);

  /**
   * Sets the hour and minutes, in UTC.
   *
   * @param hours hours to set
   * @param mins minutes to set
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setUTCHours(int hours, int mins);

  /**
   * Sets the hour, minutes, and seconds, in UTC.
   *
   * @param hours hours to set
   * @param mins minutes to set
   * @param secs seconds to set
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setUTCHours(int hours, int mins, int secs);

  /**
   * Sets the hour, minutes, seconds, and milliseconds, in UTC.
   *
   * @param hours hours to set
   * @param mins minutes to set
   * @param secs seconds to set
   * @param ms milliseconds to set
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setUTCHours(int hours, int mins, int secs, int ms);

  /**
   * Sets the minutes, in UTC.
   *
   * @param minutes minutes to set
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setUTCMinutes(int minutes);

  /**
   * Sets the minutes and seconds, in UTC.
   *
   * @param minutes minutes to set
   * @param seconds seconds to set
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setUTCMinutes(int minutes, int seconds);

  /**
   * Sets the minutes, seconds, and milliseconds, in UTC.
   *
   * @param minutes minutes to set
   * @param seconds seconds to set
   * @param millis milliseconds to set
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setUTCMinutes(int minutes, int seconds, int millis);

  /**
   * Sets the month, in UTC.
   *
   * @param month month to set
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setUTCMonth(int month);

  /**
   * Sets the month and day, in UTC.
   *
   * @param month month to set
   * @param dayOfMonth day of month to set
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setUTCMonth(int month, int dayOfMonth);

  /**
   * Sets the seconds, in UTC.
   *
   * @param seconds seconds to set
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setUTCSeconds(int seconds);

  /**
   * Sets the seconds and milliseconds, in UTC.
   *
   * @param seconds seconds to set
   * @param millis milliseconds to set
   * @return the millisecond representation of the adjusted date.
   */
  public final native double setUTCSeconds(int seconds, int millis);

  /**
   * Sets a two-digit year.
   *
   * @param year year to set
   * @return the millisecond representation of the adjusted date.
   * @deprecated Use {@link #setFullYear(int)}.
   */
  @Deprecated
  public final native double setYear(int year);

  /**
   * Get a date string in the local time zone
   *
   * @return a date string in the local time zone.
   */
  public final native String toDateString();

  /**
   * Get a date and time string in GMT.
   *
   * @return a date and time string in GMT.
   * @deprecated Use {@link #toUTCString()}.
   */
  @Deprecated
  public final native String toGMTString();

  /**
   * Get a date string in the local time zone according to local formatting conventions.
   *
   * @return a date string in the local time zone according to local formatting conventions.
   */
  public final native String toLocaleDateString();

  /**
   * Get a date and time string in the local time zone according to local formatting conventions.
   *
   * @return a date and time string in the local time zone according to local formatting
   *     conventions.
   */
  public final native String toLocaleString();

  /**
   * Get a time string in the local time zone according to local formatting conventions.
   *
   * @return a time string in the local time zone according to local formatting conventions.
   */
  public final native String toLocaleTimeString();

  /**
   * Get a time string in the local time zone.
   *
   * @return a time string in the local time zone.
   */
  public final native String toTimeString();

  /**
   * Get a date and time string in UTC.
   *
   * @return a date and time string in UTC.
   */
  public final native String toUTCString();

  /**
   * Get the millisecond representation, as {@link #getTime()}.
   *
   * @return the millisecond representation, as {@link #getTime()}.
   */
  public final native double valueOf();
}
