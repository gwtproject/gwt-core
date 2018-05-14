package org.gwtproject.core.client;

/**
 * Implement this interface to allow a class to act as a module entry point.
 * Please see the developer guide for more information on modules.
 */
public interface EntryPoint {

    /**
     * The entry point method, called automatically by loading a module that
     * declares an implementing class as an entry point.
     */
    void onModuleLoad();
}
