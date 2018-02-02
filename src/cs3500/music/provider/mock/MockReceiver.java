package cs3500.music.provider.mock;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

import cs3500.music.model.Pitch;
import cs3500.music.provider.MidiViewSequencerImpl;

public class MockReceiver implements Receiver {

  @Override
  public void send(MidiMessage message, long timeStamp) {
    ShortMessage shortMessage = (ShortMessage) message;

    int pitch = shortMessage.getData1();
    int volume = shortMessage.getData2();
    int i = pitch / 12;
    MidiViewSequencerImpl.LOG.append("Note ").append(timeStamp).append(" ")
            .append(Pitch.getPitch(pitch % 12)).append(i).append(" ").append(volume)
            .append("\n");

  }

  @Override
  public void close() {
    // NOTHING
  }

}
