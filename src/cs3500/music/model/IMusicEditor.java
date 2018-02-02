package cs3500.music.model;

import java.util.List;

/**
 * Represents the Music Editor Interface.
 *
 * <p> </p>Added methods getTempo, getNotesAt and getNotesStartingAt
 */
public interface IMusicEditor {

  /**
   * Gets the Lowest tone in the composition.
   *
   * @return the value of the lowest tone
   */
  int getLowestTone();

  /**
   * Gets the Highest tone in the composition.
   *
   * @return the value of the highest tone
   */
  int getHighestTone();

  /**
   * Gets the duration of the composition.
   *
   * @return the duration of the composition
   */
  int getDuration();

  /**
   * Gets the tempo of the composition.
   *
   * @return the tempo of the composition
   */
  int getTempo();

  /**
   * Gets the composition.
   *
   * @return the composition
   */
  List<List<Note>> getComposition();

  /**
   * Gets the notes that start at a particular beat.
   *
   * @param i the given beat
   * @return list of notes that start at i
   */
  List<Note> getNotesStartingAt(int i);

  /**
   * Gets the notes that exist at a particular beat.
   *
   * @param i the given beat
   * @return list of notes that occur at i
   */
  List<Note> getNotesAt(int i);


  /**
   * Adds the given note to the composition.
   *
   * @param n Note to be added
   */
  void addNote(Note n);

  /**
   * Removes the given note from the composition.
   *
   * @param n Note to be removed
   */
  void removeNote(Note n);

  /**
   * Replaces the given note n with p.
   *
   * @param n Note to be replaced
   * @param p Note to be replace n with
   */
  void editNote(Note n, Note p);

  /**
   * Combines the given composition with this one.
   *
   * @param m the given piece of composition to be combined
   */
  void playTogether(IMusicEditor m);

  /**
   * Queues the given composition after this one.
   *
   * @param m the given composition to be queued
   */
  void queue(IMusicEditor m);

}
