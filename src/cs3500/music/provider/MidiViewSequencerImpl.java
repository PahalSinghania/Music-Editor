package cs3500.music.provider;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;

import cs3500.music.model.Note;
import cs3500.music.provider.mock.MidiDevice;

public class MidiViewSequencerImpl implements View {
  private final Synthesizer synth;
  private final Receiver receiver;
  private final Sequencer sequencer;
  private final Sequence sequence;
  private final Track track;
  public static StringBuilder LOG = new StringBuilder();

  /**
   * Constructs a MidiViewSequencerImpl.
   */
  public MidiViewSequencerImpl() {
    Synthesizer tmpSynth = null;
    Receiver tmpReceiver = null;
    Sequencer tmpSequencer = null;
    Sequence tmpSequence = null;
    try {
      tmpSequencer = MidiSystem.getSequencer();
      tmpSynth = MidiSystem.getSynthesizer();
      tmpReceiver = tmpSynth.getReceiver();
      tmpSequence = new Sequence(Sequence.PPQ, 1);
      tmpSequencer.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    this.synth = tmpSynth;
    this.receiver = tmpReceiver;
    this.sequencer = tmpSequencer;
    this.sequence = tmpSequence;
    this.track = sequence.createTrack();
  }

  /**
   * For mock testing.
   */
  public MidiViewSequencerImpl(StringBuilder sb) {
    MidiViewSequencerImpl.LOG = sb;
    Synthesizer tmpSynth = null;
    Receiver tmpReceiver = null;
    Sequencer tmpSequencer = null;
    Sequence tmpSequence = null;
    try {
      tmpSynth = new MidiDevice();
      tmpReceiver = tmpSynth.getReceiver();
      tmpSynth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.synth = tmpSynth;
    this.receiver = tmpReceiver;
    this.sequencer = tmpSequencer;
    this.sequence = tmpSequence;
    this.track = sequence.createTrack();
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
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0,
            note.getTone(), note.getVolume());
    MidiEvent eventStart = new MidiEvent(start, note.getStartTime());
    track.add(eventStart);
  }

  @Override
  public void display(ViewModel model) {
    int currentBeat = 0;
    this.initialize(model);
    try {
      while (currentBeat < model.getLastBeat() + 1) {
        for (Note note : model.getNotesAtBeat(currentBeat)) {
          if (currentBeat == note.getStartTime()) {
            playNote(note);
          } else if (currentBeat == note.getStartTime() + note.getBeats()) {
            stopNote(note);
          }
        }
        currentBeat++;
      }
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }

    sequencer.start();
    while (sequencer.isRunning()) {
      Thread.yield();
    }

    sequencer.stop();
    receiver.close();
    System.exit(0);
  }

  /**
   * Stops the given {@link Note}.
   *
   * @param note the {@link Note} to stop
   */
  public void stopNote(Note note) throws InvalidMidiDataException {
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0,
            note.getTone(), note.getVolume());
    MidiEvent eventEnd = new MidiEvent(stop, note.getStartTime() + note.getBeats());
    track.add(eventEnd);
  }

  /**
   * Initializes this Midi View.
   *
   * @param model which view-model this view is using
   */
  private void initialize(ViewModel model) {
    try {
      synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    sequencer.setTempoInMPQ(model.getTempo());
    sequencer.setMasterSyncMode(Sequencer.SyncMode.MIDI_SYNC);
    sequencer.setSlaveSyncMode(Sequencer.SyncMode.MIDI_SYNC);

    try {
      sequencer.setSequence(sequence);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

}
