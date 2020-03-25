/*
 * Copyright © 2020 The GWT Authors
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.gwtproject.core.client.Scheduler.ScheduledCommand;
import org.junit.Test;

@J2clTestInput(SchedulerTest.class)
public class SchedulerTest {

  @Test
  public void testEndToEnd() {

      final boolean[] ranDeferred = {false};
      final boolean[] ranFinally = {false};

      final ScheduledCommand finallyCommand = () -> {

          assertTrue(ranDeferred[0]);
          assertFalse(ranFinally[0]);
          ranFinally[0] = true;

          Scheduler.get().scheduleFinally(new ScheduledCommand() {

              @Override
              public void execute() {
                  assertTrue(ranFinally[0]);
              }
          });

      };

      Scheduler.get().scheduleDeferred(() -> {
          ranDeferred[0] = true;
          Scheduler.get().scheduleFinally(finallyCommand);
      });
  }

}
