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
import org.gwtproject.core.client.Scheduler.ScheduledCommand;

public class SchedulerTest extends GWTTestCase {

  private static final int TEST_DELAY = 500000;

  @Override
  public String getModuleName() {
    return "org.gwtproject.core.Core";
  }

  /**
   * Tests that a deferred command can schedule a finally command, which can schedule another
   * finally command
   */
  public void testEndToEnd() {
    final boolean[] ranEntry = {false};

    final ScheduledCommand finallyCommand =
        () -> {
          assertTrue(ranEntry[0]);
          Scheduler.get().scheduleFinally(() -> finishTest());
        };

    Scheduler.get()
        .scheduleDeferred(
            () -> {
              ranEntry[0] = true;
              Scheduler.get().scheduleFinally(finallyCommand);
            });

    delayTestFinish(TEST_DELAY);
  }

  //    /**
  //     * Tests that an entry command can schedule a finally command where the whole
  //     * thing is kicked off by a deferred command.
  //     */
  //    public void testEndToEndLegacy() {
  //        final boolean[] ranEntry = {false};
  //
  //        final ScheduledCommand finallyCommand = new ScheduledCommand() {
  //            @Override
  //            public void execute() {
  //                assertTrue(ranEntry[0]);
  //                finishTest();
  //            }
  //        };
  //
  //        Scheduler.get().scheduleEntry(new ScheduledCommand() {
  //            @Override
  //            public void execute() {
  //                ranEntry[0] = true;
  //                Scheduler.get().scheduleFinally(finallyCommand);
  //            }
  //        });
  //
  //        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
  //            @Override
  //            public void execute() {
  //                assertTrue(ranEntry[0]);
  //            }
  //        });
  //
  //        delayTestFinish(TEST_DELAY);
  //    }
}
