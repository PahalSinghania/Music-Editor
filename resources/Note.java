package cs3500.music.model;

import cs3500.music.provider.IMusicNote;

/**
 * Interface for an IMusicNote. Each IMusicNote should be able to return: - pitch - accidental -
 * octave - starting beat - ending beat - instrument - volume Each IMusicNote is also able to edit
 * any of these fields, as long as the edit is within the constraints of what each field represents
 * (i.e. setting the ending beat of an IMusicNote to a number that is before the starting beat is
 * not allowed.).
 */
public interface Note extends IMusicNote {

  /**
   * Gets the Pitch of the MusicNote in String form.
   *
   * @return the Pitch of the Note in String form
   */
  String getPitch();

  /**
   * Gets the Octave of the MusicNote.
   *
   * @return the Octave of the Note
   */
  int getOctave();

  /**
   * Indicates when the Note starts in the MusicCompositionModel.
   *
   * @return starting beat of the Note
   */
  int getStart();

  /**
   * Indicates when the Note ends in the MusicCompositionModel.
   *
   * @return starting beat of the Note
   */
  int getEnd();

  /**
   * Gets the duration of the Note.
   *
   * @return the duration of the Note in beats
   */
  int getDuration();

  /**
   * Gets the volume of the Note.
   *
   * @return the volume of the Note
   */
  int getVolume();

  /**
   * Gets the Instrument ID of the Note.
   *
   * @return the instrument id of the music Note
   */
  int getInstrument();

  /**
   * Gets the {@code noteValue} of this {@link Note} on a 0 - 127 scale.
   *
   * @return the {@code noteValue} of this {@link Note}
   */
  int getNoteValue();


  /**
   * Updates the Octave of the Note to the given Octave.
   *
   * @param o new Octave
   * @throws IllegalArgumentException when given number is negative
   */
  void setOctave(int o);

  /**
   * Updates the Pitch of the Note to the given String.
   * Must be: "A", "B", "C", "D", "E", "F" or "G".
   *
   * @param p new pitch
   */
  void setPitch(String p);

  /**
   * Changes when the Note starts in the measure.
   *
   * @param s new starting beat
   * @throws IllegalArgumentException when starting point is negative
   * @throws IllegalArgumentException when starting point is after ending point
   */
  void setStart(int s);

  /**
   * Changes when the MusicNote starts in the measure.
   *
   * @param e new ending beat
   * @throws IllegalArgumentException when ending point is negative or before the start
   */
  void setEnd(int e);

  /**
   * Changes the instrument id in the measure.
   *
   * @param instrument the new instrument
   */
  void setInstrument(int instrument);

  /**
   * Changes the volume in the measure.
   *
   * @param volume the new volume
   */
  void setVolume(int volume);

  /**
   * Compares two Notes to determine which is higher or lower on the scale.
   *
   * @param other Note being compared to
   * @return positive int if this is higher than other, 0 if it's the same or negative if other is
   *         higher than this
   */
  int compareTo(Note other);

  /**
   * Determines if this IMusicNote is equal to the object given.
   *
   * @param o object given
   * @return whether that object is equal to this MusicNote
   */
  @Override
  boolean equals(Object o);

  @Override
  int hashCode();

  @Override
  String toString();
}
