package org.gwtproject.core.client;

import com.google.gwt.junit.client.GWTTestCase;

public class GWTTest extends GWTTestCase {
  @Override
  public String getModuleName() {
    return "org.gwtproject.core.Core";
  }

  public void testIsScript() {
    assertTrue(GWT.isScript());
    assertTrue(org.gwtproject.core.shared.GWT.isScript());
  }

  public void testIsClient() {
    assertTrue(GWT.isClient());
    assertTrue(org.gwtproject.core.shared.GWT.isClient());
  }
}
