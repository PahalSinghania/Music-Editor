package cs3500.music.view;

import java.util.List;

import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

/**
 * Implements the IViewModel interface and renders to the console.
 */
public class ConsoleView implements IView {
  private int time;
  private int highest;
  private int lowest;
  private StringBuilder output;
  private List<List<Note>> composition;
  private int lineLength;


  @Override
  public void render(ConcealModel m) {
    if (m != null) {
      highest = m.getHighestTone();
      lowest = m.getLowestTone();
      output = new StringBuilder();
      if (highest > lowest) {
        time = Integer.toString(m.getDuration()).length();

        // add the header
        output.append(String.format("%" + time + "s", ""));
        for (int i = lowest; i <= highest; i++) {
          output.append(String.format("%" + 4 + "s", Pitch.get(i % 12) + i / 12))
                  .append(" ");
        }

        lineLength = output.length() + 1;
        // add each line
        for (int i = 0; i <= m.getDuration(); i++) {
          output.append(String.format("\n%" + time + "d ", i));
          output.append(String.format("%" + (((highest - lowest + 1) * 5) - 1) + "s", " "));
        }

        composition = m.getComposition();

        // add the notes
        for (List<Note> notes : composition) {
          notes.forEach(this::drawNote);
        }
        output.append("\n");
      }
      System.out.print(output);
    } else {
      throw new IllegalArgumentException("Model is null");
    }
  }

  /**
   * Draw's each individual note on to the output for the console.
   */
  private void drawNote(Note n) {
    int lineIndex = (n.getStartTime() + 1) * lineLength;
    int noteIndex = lineIndex + time + 2 + (n.getTone() - lowest) * 5;
    output.setCharAt(noteIndex, 'X');
    int duration = n.getBeats();
    while (duration > 1) {
      noteIndex = noteIndex + lineLength;
      output.setCharAt(noteIndex, '|');
      duration = duration - 1;
    }
  }
}
