package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.view.ConsoleView;
import cs3500.music.view.gui.GuiViewImpl;
import cs3500.music.view.IView;
import cs3500.music.view.ViewFactory;
import cs3500.music.view.midi.MidiViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * Tests the View Factory.
 */
public class ViewFactoryTest {
  ViewFactory f = new ViewFactory();

  @Test // tests the view factory.
  public void testCreate() {
    IView v = f.create("console");
    assertEquals(true, v instanceof ConsoleView);

    v = f.create("midi");
    assertEquals(true, v instanceof MidiViewImpl);

    v = f.create("visual");
    assertEquals(true, v instanceof GuiViewImpl);
  }

  @Test(expected = IllegalArgumentException.class) // tests the view factory failing
  public void testCreate1() {
    IView v = f.create(" ");
    assertEquals("", v);

  }
}
