package cs3500.music.provider;

import java.util.Collection;

import cs3500.music.model.IMusicEditor;
import cs3500.music.model.Note;

/**
 * A class that encapsulates the business model object It provides the same interface but they just
 * do reading.
 */
public class ViewModel {
  private IMusicEditor model;

  public ViewModel(IMusicEditor model) {
    this.model = model;
  }

  public int getLowestNote() {
    return model.getLowestTone();
  }

  public int getHighestNote() {
    return model.getHighestTone();
  }

  public int getLastBeat() {
    return model.getDuration();
  }

  public Collection<Note> getNotesAtBeat(int beat) {
    return model.getNotesAt(beat);
  }

  public int getTempo() {
    return model.getTempo();
  }
}
