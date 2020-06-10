# GWT Core

![GWT3/J2CL compatible](https://img.shields.io/badge/GWT3/J2CL-compatible-brightgreen.svg)  [![License](https://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html) [![Chat on Gitter](https://badges.gitter.im/hal/elemento.svg)](https://gitter.im/gwtproject/gwt-modules) ![CI](https://github.com/gwtproject/gwt-core/workflows/CI/badge.svg)

A future-proof port of the `com.google.gwt.core.Core` GWT module, with no dependency on `gwt-user` (besides the Java Runtime Emulation), to prepare for GWT 3 / J2Cl.

This project is meant to roughly approximate the various classes and methods found in gwt-user's `com.google.gwt.core`
packages. In some cases, updated replacements are provided, in other cases working, but deprecated versions are offered,
and further classes or members are outright skipped, encouraging projects to either find an alternative way to manage
these operations before updating, or switch to a GWT3-version while performing the actual migration.

This project is not yet complete, there are doubtlessly better ways to handle some cases here.

##  Migrating from `com.google.gwt.core.Core`

1. Add the dependency to your build.

   For Maven:

   ```xml
   <dependency>
     <groupId>org.gwtproject.core</groupId>
     <artifactId>gwt-core</artifactId>
     <version>HEAD-SNAPSHOT</version>
   </dependency>
   ```

   For Gradle:

   ```gradle
   implementation("org.gwtproject.core:gwt-core:HEAD-SNAPSHOT")
   ```

2. Update your GWT module to use

   ```xml
   <inherits name="org.gwtproject.core.Core" />
   ```

3. Change the `import`s in your Java source files:

   ```java
   import org.gwtproject.core.client.GWT;
   ```

## Instructions

To build gwt-core:

* run `mvn clean verify`

on the parent directory. This will build the artifact and run tests against the JVM, J2CL, and GWT2.

## System Requirements

**GWT Core requires GWT 2.9.0 or newer!**


## Dependencies

GWT Core does not depend on any other module.


## Listing of classes from gwt-user's core

 * client
   * debug
     * JsoInspector - Skipped, at least for now, I'm not aware of any developer tools actually making use of this
   * impl
     * AsyncFragmentLoader - Skipped, used primarily by Split Points, not yet present in GWT3
     * CrossSiteLoadingStrategy - Skipped, split points
     * Impl - Skipped, internal details of GWT2, cannot be expected to exist at all in GWT3
     * JavaScriptExceptionBase - Skipped, exceptions are handled differently in GWT3, legacy dev mode is not a concern
     * LoadingStrategyBase - Skipped, split points
     * OnSuccessExecutor - Skipped, package-protected and used in split points
     * SchedulerImpl - Updated, missing scheduleEntry
     * ScriptTagLoadingStrategy - Skipped, split points
     * StackTraceCreator - Skipped, browsers are less crappy now
     * SuperDevModeLogger - Skipped, GWT.log either will not exist, or will only be implemented in this way anyway
     * SynchronousOnSuccessExecutor - Skipped, package-protected, split points
     * WeakMapping - TODO consider providing this, consider an approach similar to how J2CL handles hashcodes
     * XhrLoadingStrategy - Skipped, split points
   * prefetch - package skipped, split points
   * testing
     * StubScheduler - included, deliberately skipping scheduleEntry to match ScheduleImpl
   * AsyncProvided - Skipped, split points
   * Callback - Included, simple interface that eventually probably should be moved anyway
   * CodeDownloadExecution - Skipped, split points
   * Duration - Included for now, only as a util class for Scheduler
   * EntryPoint - Removed - GWT2 applications must continue to use `com.google.gwt.core.client.EntryPoint`, whereas 
   while J2CL applications have much more flexibility, they will probably use a different pattern.
   * GWT - Partially included for now to ease migration. This likely belongs in the "third" category, code which largely
   will not exist in GWT 3, so should be removed now, but this is used so extensively that would be difficult. Methods
   which can be supported are included, with others removed. Deprecated methods are either broken or should be phased
   out.
   * JavaScriptException - Skipped for now, unsure we can replace in a way that will still behave the same as before
   * JavaScriptObject - Included, deprecated: classes extending this will need additional updates to properly conform
   to JsInterop requirements
   * JsArray - Updated, deprecated: projects should migrate when feasible to `elemental2.core.JsArray<T>`
   * JsArrayBoolean - Updated, deprecated: projects should migrate when feasible to `elemental2.core.JsArray<Boolean>`
   * JsArrayInteger - Updated, deprecated: projects should migrate when feasible to `elemental2.core.JsArray<Double>`
   and cast or coerce appropriately
   * JsArrayMixed - Updated, deprecated: projects should migrate when feasible to `elemental2.core.JsArray<Any>` or
   use `getAnyAt(int)`
   * JsArrayNumber - Updated, deprecated: projects should migrate when feasible to `elemental2.core.JsArray<Double>`
   * JsArrayString - Updated, deprecated: projects should migrate when feasible to `elemental2.core.JsArray<JsString>`
   * JsArrayUtils - Updated, deprecated, consists only of calls to Js.uncheckedCast
   * JsDate - Updated, deprecated: projects should migrate to `elemental2.core.JsDate`
   * JsonUtils - Updated, deprecated: projects should migrate to elemental2's JSON.parse and JSON.stringify, no method
   in this class actually uses eval any longer
   * RunAsyncCallback - Skipped, split points
   * Scheduler - Updated, mising scheduleEntry
   * ScriptInjector - Updated
   * SingleJsoImpl - Skipped, JSO-related, shouldn't be needed
   * SingleJsoImplName - Skipped, JSO-related, shouldn't be needed
   
   
As with other ported modules, the expectation for the initial release is to as closely match as possible the API of the
original code, minus package structure, even when a better replacement exists. This will enable projects to first
migrate to the new packages, then later update to newer versions of the migrated modules where further changes and
improvements are made.


## Updating JavaScriptObject subclasses
As with most of the deprecated classes kept in this library, existing JavaScriptObject types should be rewritten using 
jsinterop annotations to correctly explain to the compiler what is happening. This class can still be convient to use
as an argument or return type, but generally should not be extended in your own projects any longer.

Some basic guidlines to follow:
 * The type itself must be marked as `@JsType(isNative=true)`, most likely with `namespace=JsPackage.GLOBAL`, and 
 depending on your use case, you might want to always use `name="Object"`, or might want to refer to a different JS
 class.
 * Existing native methods need to be rewritten either as overlay methods, or made native to describe how they call the
 actual JS. A `@JsMethod(name=...)` may be required if the method name is not the same as in JS, or a `@JsProperty` if
 the method exists to read/write a property. Fields can also be created - they will be assumed to be named for an
 existing JS property, but you can likewise rename them with `@JsProperty`.
 * Overlay methods need to be marked with `@JsOverlay`, and continue to need to be effectively final (i.e. never 
 overridden), but no longer need to be marked as `final`.

