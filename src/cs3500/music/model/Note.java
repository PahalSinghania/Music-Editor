package cs3500.music.model;

import java.util.Objects;

/**
 * Represents a single musical note.
 * - Made the constructor public in order to ensure
 * working of the program post refactoring
 * - Made methods modify and gt start time public
 * - Introduced the instrument parameter
 * - Created getters for the volume and instrument parameters
 */
public class Note {
  private int time;
  private int beats;
  private int volume;
  private int octave;
  private Pitch pitch;
  private int instrument;

  /**
   * Constructs a {@code Note} object.
   *
   * @param t the start time
   * @param b the number of beats
   * @param o the octave of the note
   * @param p the pitch of the note
   */
  public Note(int t, int b, int o, Pitch p) {
    if (t < 0 || b < 1 || o < 0 || o > 10 || p == null) {
      throw new IllegalArgumentException("Invalid Note");
    }
    this.time = t;
    this.beats = b;
    this.octave = o;
    this.pitch = p;
    this.volume = 1;
    this.instrument = 1;
  }

  /**
   * Constructs a {@code Note} object.
   *
   * @param t the start time
   * @param b the number of beats
   * @param o the octave of the note
   * @param p the pitch of the note
   * @param i the instrument
   * @param v the volume
   */
  public Note(int t, int b, int o, Pitch p, int i, int v) {
    if (t < 0 || b < 1 || o < 0 || o > 10 || p == null || i <= 0 || v < 0) {
      throw new IllegalArgumentException("Invalid Note");
    }
    this.time = t;
    this.beats = b;
    this.octave = o;
    this.pitch = p;
    this.volume = v;
    this.instrument = i;
  }

  /**
   * Gets the start time for a given note.
   *
   * @return start time for a given note.
   */
  public int getStartTime() {
    return this.time;
  }

  /**
   * Gets the tone of a given note.
   *
   * @return tone a given note.
   */
  public int getTone() {
    return octave * 12 + pitch.ordinal();
  }

  /**
   * Gets the number of beats.
   *
   * @return the number if beats
   */
  public int getBeats() {
    return beats;
  }

  /**
   * Gets the instrument number.
   *
   * @return the instrument number
   */
  public int getInstrument() {
    return this.instrument;
  }

  /**
   * Gets the volume of the note.
   *
   * @return the volume of the note
   */
  public int getVolume() {
    return this.volume;
  }

  /**
   * Modifies a note to start t seconds later.
   *
   * @param t the amount to be added to start time
   */
  public void modify(int t) {
    this.time += t;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Note)) {
      return false;
    }
    Note other = (Note) obj;
    return this.beats == other.beats && this.pitch == other.pitch
            && this.octave == other.octave && this.time == other.time
            && this.volume == other.volume && this.instrument == other.instrument;
  }

  @Override
  public int hashCode() {
    return Objects.hash(time, beats, octave, pitch, volume, instrument);
  }
}
