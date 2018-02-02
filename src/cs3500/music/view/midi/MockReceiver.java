package cs3500.music.view.midi;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * Implements a Mock Reciever for the MidiViewImpl.
 * It is used for testing
 */
public class MockReceiver implements Receiver {
  private StringBuilder output;

  /**
   * Creates a {@code MockReceiver} object.
   *
   * @param s the Stringbuilder that logs the calls to send
   */
  public MockReceiver(StringBuilder s) {
    output = s;
  }

  @Override
  public void send(MidiMessage message, long timeStamp) {
    ShortMessage m = ((ShortMessage) message);
    String n = " stop ";
    if (m.getCommand() == 144) {
      n = " start ";
    }
    output.append("note ").append(timeStamp).append(n)
            .append(m.getChannel()).append(" ").append(m.getData1()).append(" ")
            .append(m.getData2()).append("\n");
  }

  @Override
  public void close() {
    output.append("Closed.");
  }
}
