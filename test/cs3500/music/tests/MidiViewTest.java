package cs3500.music.tests;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ConcealModel;
import cs3500.music.view.midi.MidiViewImpl;
import cs3500.music.view.midi.MockSynthesizer;

import static org.junit.Assert.assertEquals;

/**
 * Tests the MidiViewImpl.
 */
public class MidiViewTest {
  Note a = new Note(0, 2, 6, Pitch.A);
  Note b = new Note(1, 3, 5, Pitch.B);
  Note c = new Note(0, 7, 3, Pitch.G);
  Note d = new Note(0, 2, 4, Pitch.E);
  Note e = new Note(2, 2, 4, Pitch.D);
  Note f = new Note(4, 2, 4, Pitch.C);
  Note g = new Note(6, 2, 4, Pitch.D);
  Note h = new Note(8, 2, 4, Pitch.E);
  Note i = new Note(10, 2, 4, Pitch.E);
  Note j = new Note(12, 3, 4, Pitch.E);

  MidiViewImpl view;
  MusicEditorModel model = new MusicEditorModel();
  ConcealModel conceal;

  //Doesnt use the ViewFactory as the default implementation uses the Midi
  // Synthesizer and not the mock Synthesizer
  @Test// tests the render function
  public void testRender() {
    StringBuilder s = new StringBuilder("");
    MockSynthesizer synth = new MockSynthesizer(s);
    conceal = new ConcealModel(model);
    view = new MidiViewImpl(synth);
    view.render(conceal);
    assertEquals("Closed.", s.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRenderNull() {
    StringBuilder s = new StringBuilder("");
    MockSynthesizer synth = new MockSynthesizer(s);
    view = new MidiViewImpl(synth);
    view.render(null);
    assertEquals("", s.toString());
  }

  @Test // tests the render function.
  public void testRender1() {
    StringBuilder s = new StringBuilder("");
    MockSynthesizer synth = new MockSynthesizer(s);
    model.addNote(a);
    model.addNote(b);
    model.addNote(c);
    model.addNote(d);
    model.addNote(e);
    model.addNote(f);
    model.addNote(g);
    model.addNote(h);
    model.addNote(i);
    model.addNote(j);
    conceal = new ConcealModel(model);
    view = new MidiViewImpl(synth);
    view.render(conceal);
    assertEquals("note 0 start 0 93 1\n" + "note 0 start 0 55 1\n" + "note 0 start 0 64 1\n"
            + "note 1000000 stop 0 93 1\n" + "note 1000000 stop 0 64 1\n"
            + "note 500000 start 0 83 1\n" + "note 1000000 start 0 62 1\n"
            + "note 2000000 stop 0 83 1\n" + "note 2000000 stop 0 62 1\n"
            + "note 2000000 start 0 60 1\n" + "note 3000000 stop 0 60 1\n"
            + "note 3500000 stop 0 55 1\n" + "note 3000000 start 0 62 1\n"
            + "note 4000000 stop 0 62 1\n" + "note 4000000 start 0 64 1\n"
            + "note 5000000 stop 0 64 1\n" + "note 5000000 start 0 64 1\n"
            + "note 6000000 stop 0 64 1\n" + "note 6000000 start 0 64 1\n"
            + "note 7500000 stop 0 64 1\n" + "Closed.", s.toString());
  }

  @Test // tests the render function for mary had a little lamb
  public void testRender2() {
    StringBuilder s = new StringBuilder("");
    MockSynthesizer synth = new MockSynthesizer(s);
    try {
      model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"),
              new MusicEditorModel.Builder());
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    }
    conceal = new ConcealModel(model);
    view = new MidiViewImpl(synth);
    view.render(conceal);
    assertEquals("note 7600000 start 0 62 80\n" + "note 8000000 stop 0 55 78\n"
            + "note 8000000 stop 0 62 80\n" + "note 8000000 start 0 55 79\n"
            + "note 8000000 start 0 64 84\n" + "note 8400000 stop 0 64 84\n"
            + "note 8400000 start 0 64 76\n" + "note 8800000 stop 0 64 76\n"
            + "note 8800000 start 0 64 74\n" + "note 9200000 stop 0 64 74\n"
            + "note 9200000 start 0 64 77\n" + "note 9600000 stop 0 55 79\n"
            + "note 9600000 stop 0 64 77\n" + "note 9600000 start 0 55 78\n"
            + "note 9600000 start 0 62 75\n" + "note 10000000 stop 0 62 75\n"
            + "note 10000000 start 0 62 74\n" + "note 10400000 stop 0 62 74\n"
            + "note 10400000 start 0 64 81\n" + "note 10800000 stop 0 64 81\n"
            + "note 10800000 start 0 62 70\n" + "note 11200000 stop 0 55 78\n"
            + "note 11200000 stop 0 62 70\n" + "note 11200000 start 0 52 72\n"
            + "note 11200000 start 0 60 73\n" + "note 12800000 stop 0 52 72\n"
            + "note 12800000 stop 0 60 73\n" + "Closed.", s.toString().substring(1071));

  }
}
