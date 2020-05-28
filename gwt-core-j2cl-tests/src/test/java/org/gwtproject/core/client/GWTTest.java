package org.gwtproject.core.client;

import static org.junit.Assert.assertTrue;

import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Test;

@J2clTestInput(GWTTest.class)
public class GWTTest {

  @Test
  public void testIsScript() {
    assertTrue(GWT.isScript());
    assertTrue(org.gwtproject.core.shared.GWT.isScript());
  }

  @Test
  public void testIsClient() {
    assertTrue(GWT.isClient());
    assertTrue(org.gwtproject.core.shared.GWT.isClient());
  }
}
