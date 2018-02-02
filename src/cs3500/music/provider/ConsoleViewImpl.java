package cs3500.music.provider;

import java.util.Collection;

import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

public class ConsoleViewImpl implements View {

  @Override
  public void display(ViewModel model) {
    int buffer = (Integer.toString(model.getLastBeat()).length());

    // For the empty buffer of beats
    StringBuilder result = new StringBuilder(String.format("%" + buffer
            + "s", ""));

    // First line: for the first pitch
    int i = model.getLowestNote();
    result.append(String.format("%" + 4 + "s", Pitch.getPitch(i % 12).toString()));

    // First line: for the rest pitches
    for (i = model.getLowestNote() + 1; i <= model.getHighestNote(); i++) {
      result.append(String.format("%" + 5 + "s", Pitch.getPitch(i % 12).toString()));
    }

    // Note rows
    for (i = 0; i < model.getLastBeat(); i++) {
      result.append(String.format("\n%" + buffer + "d ", i)).append(
              makeRow(model, i, buffer));
    }

    System.out.println(result);
  }

  /**
   * Creates a string representation of the {@link Note}s at a given beat in a line.
   *
   * @param model      the particular editor to be displayed
   * @param beat       the beat to be printed
   * @param beatBuffer buffer size ahead
   * @return a string representation of the {@link Note}s at the given {@code currBeat}
   */
  private StringBuilder makeRow(ViewModel model, int beat, int beatBuffer) {
    Collection<Note> list = model.getNotesAtBeat(beat);
    StringBuilder result;
    int buffer = (5 * (model.getHighestNote() - model.getLowestNote()))
            + beatBuffer;
    result = new StringBuilder(String.format("%" + buffer + "s", ""));
    for (Note note : list) {
      int index = (5 * (note.getTone() - model.getLowestNote())) + 1;
      if (beatBuffer == 1 && index == 1) {
        index = 0;
      }
      if (note.getStartTime() == beat) {
        result.setCharAt(index, 'X');
      } else if (beat > note.getStartTime() && beat < note.getBeats() + note.getStartTime()) {
        if (result.charAt(index) == 'X') {
          continue;
        }
        result.setCharAt(index, '|');
      }
    }

    return result;
  }

}
