package cs3500.music.provider;

import cs3500.music.provider.CompositeViewImpl;
import cs3500.music.provider.ConsoleViewImpl;
import cs3500.music.provider.View;
import cs3500.music.provider.GuiViewImpl;
import cs3500.music.provider.MidiViewImpl;

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
  public static View create(String view) {
    switch (view) {
      case "console":
        return new ConsoleViewImpl();
      case "midi":
        return new MidiViewImpl();
      case "visual":
        return new GuiViewImpl();
      case "combo":
        return new CompositeViewImpl(new GuiViewImpl(), new MidiViewImpl());
      default:
        throw new IllegalArgumentException("Please enter one of console, midi or visual.");

    }
  }
}
