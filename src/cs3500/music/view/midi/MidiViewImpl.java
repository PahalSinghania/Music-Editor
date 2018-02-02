package cs3500.music.view.midi;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Receiver;
import javax.sound.midi.MidiSystem;

import cs3500.music.model.Note;
import cs3500.music.view.ConcealModel;
import cs3500.music.view.IView;

/**
 * An Audible representation of the music.
 */
public class MidiViewImpl implements IView {
  private Synthesizer synth = null;
  private Receiver receiver = null;
  private int currentBeat = 0;
  private long tempo;

  /**
   * Creates a {@code MidiViewImpl} object.
   */
  public MidiViewImpl() {
    try {
      this.synth = MidiSystem.getSynthesizer();
      this.receiver = synth.getReceiver();
      this.synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates a {@code MidiViewImpl} object.
   * Specifically created for testing
   *
   * @param s The Mock Synthesizer used to log outputs
   */
  public MidiViewImpl(MockSynthesizer s) {
    try {
      this.synth = s;
      this.receiver = synth.getReceiver();
      this.synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  /**
   * Plays the given note.
   *
   * @param n The note to be played
   */
  private void playNote(Note n) throws InvalidMidiDataException {
    Instrument[] i = this.synth.getDefaultSoundbank().getInstruments();
    this.synth.getChannels()[0].programChange(i[n.getInstrument()].getPatch().getProgram());

    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, n.getTone() + 12, n.getVolume());
    this.receiver.send(start, n.getStartTime() * tempo);
  }

  /**
   * Stops playing the given note.
   *
   * @param n The note to be stopped playing
   */
  private void stopNote(Note n) throws InvalidMidiDataException {
    Instrument[] i = this.synth.getDefaultSoundbank().getInstruments();
    this.synth.getChannels()[0].programChange(i[n.getInstrument()].getPatch().getProgram());

    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0, n.getTone() + 12, n.getVolume());
    this.receiver.send(stop, (n.getStartTime() + n.getBeats()) * tempo);
    this.synth.getMicrosecondPosition();
  }

  /**
   * Renders an audio representation of the current beat.
   *
   * @param m The composition to be rendered
   */
  public void renderCurrent(ConcealModel m) {
    for (Note n : m.getNotesAt(currentBeat)) {
      try {
        if (n.getStartTime() + n.getBeats() - 1 == currentBeat) {
          stopNote(n);
        }
        if (n.getStartTime() == currentBeat) {
          playNote(n);
        }
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    }
    this.currentBeat++;
    if (currentBeat >= m.getDuration()) {
      this.receiver.close();
    }
  }


  @Override
  public void render(ConcealModel m) {
    if (m == null) {
      throw new IllegalArgumentException("Null Given.");
    }
    tempo = m.getTempo();
    while (this.currentBeat <= m.getDuration()) {
      for (Note n : m.getNotesAt(currentBeat)) {
        try {
          if (n.getStartTime() == currentBeat) {
            playNote(n);
          }
          if (n.getStartTime() + n.getBeats() - 1 == currentBeat) {
            stopNote(n);
          }
        } catch (InvalidMidiDataException e) {
          e.printStackTrace();
        }
      }
      this.currentBeat++;
    }
    try {
      Thread.sleep(tempo * m.getDuration());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    this.receiver.close();
  }

}

