package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.music.util.CompositionBuilder;

/**
 * Represents a version of the Music editor Model.
 *
 * <p> </p>- Added method implementations of getNotesAt and getNotesStartingAt
 * <p> </p>- Added tempo to the composition
 */
public class MusicEditorModel implements IMusicEditor {
  private List<List<Note>> composition = new ArrayList<List<Note>>();
  private int lowestTone = 150;
  private int highestTone = 0;
  private int totalDuration;
  private int tempo = 500000;

  /**
   * Constructs a {@code music editor} object.
   */
  public MusicEditorModel() {
    this.composition = new ArrayList<List<Note>>();
  }

  /**
   * Constructs a {@code music editor} object.
   *
   * @param tempo the tempo of the music
   * @param notes the notes in the piece
   */
  public MusicEditorModel(int tempo, List<Note> notes) {
    this.tempo = tempo;
    for (Note n : notes) {
      this.addNote(n);
    }
  }

  /**
   * Updates the information about the lowest, highest notes.
   * also updates the total duration with the addition of the new note
   *
   * @param n the note that is added
   */
  private void updateTones(Note n) {
    int temp = n.getTone();
    if (temp < lowestTone) {
      lowestTone = temp;
    }
    if (temp > highestTone) {
      highestTone = temp;
    }
    if (n.getStartTime() + n.getBeats() > totalDuration) {
      totalDuration = n.getStartTime() + n.getBeats() - 1;
    }

  }

  @Override
  public int getLowestTone() {
    return this.lowestTone;
  }

  @Override
  public int getHighestTone() {
    return this.highestTone;
  }

  @Override
  public int getDuration() {
    return this.totalDuration;
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public List<List<Note>> getComposition() {
    return composition;
  }

  @Override
  public List<Note> getNotesStartingAt(int i) {
    if (i < composition.size()) {
      return this.composition.get(i);
    } else {
      throw new IllegalArgumentException("No notes at i");
    }
  }

  @Override
  public List<Note> getNotesAt(int i) {
    List<Note> temp = new ArrayList<Note>();
    for (int x = 0; x < composition.size(); x++) {
      for (Note n : composition.get(x)) {
        if (n.getStartTime() == i
                || ((n.getStartTime() + n.getBeats() >= i) && (n.getStartTime() < i))) {
          temp.add(n);
        }
      }
    }
    return temp;
  }

  @Override
  public void addNote(Note n) {
    if (n == null) {
      throw new IllegalArgumentException("Null Given");
    }
    if (this.composition.size() <= n.getStartTime()) {
      for (int i = this.composition.size(); i <= n.getStartTime(); i++) {
        this.composition.add(new ArrayList<Note>());
      }
    }
    this.composition.get(n.getStartTime()).add(n);
    this.updateTones(n);
  }

  @Override
  public void removeNote(Note n) {
    if (n != null) {
      boolean b = false;
      if (n.getStartTime() < composition.size()) {
        if (!composition.get(n.getStartTime()).isEmpty()
                && this.composition.get(n.getStartTime()).contains(n)) {

          b = this.composition.get(n.getStartTime()).contains(n);

          if (b) {
            this.composition.get(n.getStartTime()).remove(n);
          }

          this.totalDuration = 0;
          this.lowestTone = 150;
          this.highestTone = 0;
          for (List<Note> notes : this.composition) {
            notes.forEach(this::updateTones);
          }
        }
      }
      if (!b) {
        throw new IllegalArgumentException("No such note exists");
      }
    } else {
      throw new IllegalArgumentException("Null Given");
    }
  }

  @Override
  public void editNote(Note n, Note p) {
    boolean b = true;
    if (p == null || n == null) {
      b = false;
    } else {
      try {
        this.removeNote(n);
      } catch (IllegalArgumentException e) {
        b = false;
      }
    }
    if (b) {
      this.addNote(p);
    } else {
      throw new IllegalArgumentException("Invalid Edit.");
    }
  }

  @Override
  public void playTogether(IMusicEditor m) {
    if (m == null) {
      throw new IllegalArgumentException("Null Model given");
    }
    for (int i = 0; i < m.getComposition().size(); i++) {
      m.getComposition().get(i).forEach(this::addNote);
    }
  }

  @Override
  public void queue(IMusicEditor m) {
    if (m == null) {
      throw new IllegalArgumentException("Null Model given");
    }
    for (List<Note> notes : m.getComposition()) {
      for (Note n : notes) {
        n.modify(this.getDuration());
        this.addNote(n);
      }
    }
  }

  /**
   * Represents a builder for a piece of music.
   */
  public static final class Builder implements CompositionBuilder<MusicEditorModel> {
    private int tempo = 500000;
    private List<Note> notes = new ArrayList<Note>();

    @Override
    public MusicEditorModel build() {
      return new MusicEditorModel(tempo, notes);
    }

    @Override
    public CompositionBuilder<MusicEditorModel> setTempo(int tempo) {
      if (tempo > 1) {
        this.tempo = tempo;
      } else {
        throw new IllegalArgumentException("Invalid Tempo");
      }
      return this;
    }

    @Override
    public CompositionBuilder<MusicEditorModel> addNote(
            int start, int end, int instrument, int pitch, int volume) {

      if (end < start) {
        throw new IllegalArgumentException("Invalid Note.");
      }
      this.notes.add(new Note(start, end - start, pitch / 12 - 1, Pitch.getPitch(pitch % 12),
              instrument, volume));
      return this;
    }
  }
}
