package cs3500.music.provider;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.model.Note;
import cs3500.music.provider.mock.MidiDevice;

/**
 * A skeleton for MIDI playback.
 */
public class MidiViewImpl implements View {
  private final Synthesizer synth;
  private final Receiver receiver;
  private int curBeat;
  public static StringBuilder LOG = new StringBuilder();

  /**
   * Constructs a MidiViewImpl.
   */
  public MidiViewImpl() {
    Synthesizer tmpSynth = null;
    Receiver tmpReceiver = null;
    try {
      tmpSynth = MidiSystem.getSynthesizer();
      tmpReceiver = tmpSynth.getReceiver();
      tmpSynth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.synth = tmpSynth;
    this.receiver = tmpReceiver;
    curBeat = 0;
  }

  /**
   * For mock testing.
   */
  public MidiViewImpl(StringBuilder sb) {
    MidiViewImpl.LOG = sb;
    Synthesizer tmpSynth = null;
    Receiver tmpReceiver = null;
    try {
      tmpSynth = new MidiDevice();
      tmpReceiver = tmpSynth.getReceiver();
      tmpSynth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.synth = tmpSynth;
    this.receiver = tmpReceiver;
    curBeat = 0;
  }

  /**
   * Relevant classes and methods from the javax.sound.midi library: <ul> <li>{@link
   * MidiSystem#getSynthesizer()}</li> <li>{@link Synthesizer} <ul> <li>{@link
   * Synthesizer#open()}</li> <li>{@link Synthesizer#getReceiver()}</li> <li>{@link
   * Synthesizer#getChannels()}</li> </ul> </li> <li>{@link Receiver} <ul> <li>{@link
   * Receiver#send(MidiMessage, long)}</li> <li>{@link Receiver#close()}</li> </ul> </li> <li>{@link
   * MidiMessage}</li> <li>{@link ShortMessage}</li> <li>{@link MidiChannel} <ul> <li>{@link
   * MidiChannel#getProgram()}</li> <li>{@link MidiChannel#programChange(int)}</li> </ul> </li>
   * </ul>
   *
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI"> https://en.wikipedia.org/wiki/General_MIDI
   * </a>
   */

  /**
   * Plays the given {@link Note}.
   *
   * @param note the {@link Note} to tick
   */
  public void playNote(Note note) throws InvalidMidiDataException {
    Instrument[] instruments = this.synth.getDefaultSoundbank()
            .getInstruments();
    this.synth.getChannels()[0].programChange(instruments[note
            .getInstrument()].getPatch().getProgram());
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0,
            note.getTone(), note.getVolume());
    this.receiver.send(start, note.getStartTime());
  }

  @Override
  public void display(ViewModel model) {
    for (Note note : model.getNotesAtBeat(curBeat)) {
      try {
        if (curBeat == note.getStartTime()) {
          playNote(note);
        } else if (curBeat == note.getBeats() + note.getStartTime()) {
          stopNote(note);
        }
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    }
    curBeat++; // Very important!!
    if (curBeat >= model.getLastBeat()) {
      this.receiver.close();
    }
  }

  /**
   * Stops the given {@link Note}.
   *
   * @param note the {@link Note} to stop
   */
  public void stopNote(Note note) throws InvalidMidiDataException {
    Instrument[] instruments = this.synth.getDefaultSoundbank()
            .getInstruments();
    this.synth.getChannels()[0].programChange(instruments[note
            .getInstrument()].getPatch().getProgram());
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0,
            note.getTone(), note.getVolume());
    this.receiver.send(stop, note.getStartTime() + note.getBeats() - 1);
  }
}
