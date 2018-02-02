package cs3500.music.view;

import cs3500.music.view.gui.GuiViewImpl;
import cs3500.music.view.midi.MidiViewImpl;

/**
 * A factory that returns the type of view as prompted.
 */
public class ViewFactory {

  /**
   * Creates a IView object of the prompted type.
   *
   * @param view the type of IView object to be returned
   * @return IView object
   */
  public static IView create(String view) {
    switch (view) {
      case "console":
        return new ConsoleView();
      case "midi":
        return new MidiViewImpl();
      case "visual":
        return new GuiViewImpl();
      case "combo":
        return new CompositeView(new GuiViewImpl(), new MidiViewImpl());
      default:
        throw new IllegalArgumentException("Please enter one of console, midi or visual.");

    }
  }
}
