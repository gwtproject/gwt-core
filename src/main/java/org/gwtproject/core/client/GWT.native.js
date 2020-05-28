const javaLangThrowable = goog.forwardDeclare('java.lang.Throwable');

// fully qualified name apparently required since there are two non-native
// types with the same name "GWT"
org_gwtproject_core_client_GWT.fromObject = function(obj) {
    //j2cl impl to invoke java.lang.Throwable.of(Object)
    return javaLangThrowable.of(obj);
};