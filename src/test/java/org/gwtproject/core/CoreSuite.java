package org.gwtproject.core;

import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import org.gwtproject.core.client.JavaScriptObjectTest;
import org.gwtproject.core.client.SchedulerTest;
import org.gwtproject.core.client.ScriptInjectorTest;
import org.gwtproject.core.client.impl.SchedulerImplTest;

public class CoreSuite {
    public static Test suite() {
        GWTTestSuite suite = new GWTTestSuite("All core tests");

        suite.addTestSuite(JavaScriptObjectTest.class);
        suite.addTestSuite(SchedulerTest.class);
        suite.addTestSuite(SchedulerImplTest.class);
        suite.addTestSuite(ScriptInjectorTest.class);

        return suite;
    }

}