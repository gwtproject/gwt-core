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
const javaLangThrowable = goog.forwardDeclare('java.lang.Throwable');

// fully qualified name apparently required since there are two non-native
// types with the same name "GWT"
org_gwtproject_core_client_GWT.fromObject = function(obj) {
    //j2cl impl to invoke java.lang.Throwable.of(Object)
    return javaLangThrowable.of(obj);
};