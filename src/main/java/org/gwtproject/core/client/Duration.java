package org.gwtproject.core.client;

import elemental2.core.JsDate;
import jsinterop.base.Js;

public class Duration {

    /**
     * Returns the same result as {@link System#currentTimeMillis()}, but as a
     * double. Because emulated long math is significantly slower than doubles in
     * Production Mode, this method is to be preferred.
     */
    public static double currentTimeMillis() {
        return JsDate.now();
    }

    private double start = currentTimeMillis();

    /**
     * Creates a new Duration whose start time is now.
     */
    public Duration() {
    }

    /**
     * Returns the number of milliseconds that have elapsed since this object was
     * created.
     */
    public int elapsedMillis() {
        return Js.coerceToInt(currentTimeMillis() - start);
    }

    /**
     * Returns the time when the object was created.
     */
    public double getStartMillis() {
        return start;
    }
}
