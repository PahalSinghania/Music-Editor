package cs3500.music;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.model.IMusicEditor;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.provider.CompositeViewImpl;
import cs3500.music.util.MusicReader;
import cs3500.music.provider.ViewModel;
import cs3500.music.provider.View;
import cs3500.music.provider.ViewFactory;
import cs3500.music.provider.GuiViewImpl;
import cs3500.music.provider.MidiViewImpl;
import cs3500.music.controller.Controller;

/**
 * Implements a Music Editor.
 */
public final class MusicEditor {


  /**
   * Creates and runs the music editor.
   *
   * @param args Takes in the name if the file as the first argument and the type of view to render
   *             in the second
   */
  public static void main(String[] args) {
    try {
      IMusicEditor composition = MusicReader.parseFile(new FileReader(args[0]),
              new MusicEditorModel.Builder());
      View display;
      if (args[1].equals("combo")) {
        Controller c = new Controller(new CompositeViewImpl(new GuiViewImpl(), new MidiViewImpl()),
                composition);

      } else {
        display = ViewFactory.create(args[1]);
        ViewModel m = new ViewModel(composition);
        display.display(m);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }
}
