package cs3500.music.view;

import java.util.List;

import cs3500.music.model.IMusicEditor;
import cs3500.music.model.Note;

/**
 * A model that can be passed to the view.
 * while concealing the information of the model .
 */
public final class ConcealModel {
  private IMusicEditor model;

  /**
   * Constructs a {@code ConcealModel} object.
   *
   * @param m the composition represented as a model
   */
  public ConcealModel(IMusicEditor m) {
    this.model = m;
  }

  /**
   * Gets the Highest tone in the composition.
   *
   * @return the value of the highest tone
   */
  public int getHighestTone() {
    return model.getHighestTone();
  }

  /**
   * Gets the Lowest tone of the model.
   *
   * @return the value of the lowest tone
   */
  public int getLowestTone() {
    return model.getLowestTone();
  }

  /**
   * Gets the duration of the composition.
   *
   * @return the duration of the composition
   */
  public int getDuration() {
    return model.getDuration();
  }

  /**
   * Gets the composition.
   *
   * @return the composition
   */
  public List<List<Note>> getComposition() {
    return model.getComposition();
  }

  /**
   * Gets the notes that exist at a particular beat.
   *
   * @param i the given beat
   * @return list of notes that occur at i
   */
  public List<Note> getNotesAt(int i) {
    return model.getNotesAt(i);
  }

  /**
   * Gets the tempo of the composition.
   *
   * @return the tempo
   */
  public long getTempo() {
    return model.getTempo();
  }
}
