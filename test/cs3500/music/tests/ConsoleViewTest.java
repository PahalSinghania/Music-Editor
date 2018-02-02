package cs3500.music.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import cs3500.music.view.ConcealModel;
import cs3500.music.model.IMusicEditor;
import cs3500.music.view.IView;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.view.ViewFactory;

import static org.junit.Assert.assertEquals;

/**
 * Tests the IViewModel implementation ConsoleView.
 */
public class ConsoleViewTest {
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
  Note k = new Note(8, 7, 3, Pitch.G);
  Note l = new Note(16, 8, 3, Pitch.G);
  Note m = new Note(24, 2, 3, Pitch.G);
  Note n = new Note(16, 2, 4, Pitch.D);
  Note o = new Note(18, 2, 4, Pitch.D);
  Note p = new Note(20, 4, 4, Pitch.D);
  Note q = new Note(24, 2, 4, Pitch.E);
  Note r = new Note(26, 2, 4, Pitch.G);
  Note s = new Note(28, 4, 4, Pitch.G);
  Note t = new Note(32, 8, 3, Pitch.G);
  Note u = new Note(32, 2, 4, Pitch.E);
  Note v = new Note(34, 2, 4, Pitch.D);
  Note w = new Note(36, 2, 4, Pitch.C);
  Note x = new Note(38, 2, 4, Pitch.D);
  Note y = new Note(40, 8, 3, Pitch.G);
  Note z = new Note(48, 8, 3, Pitch.G);
  Note a1 = new Note(40, 2, 4, Pitch.E);
  Note b1 = new Note(42, 2, 4, Pitch.E);
  Note c1 = new Note(44, 2, 4, Pitch.E);
  Note d1 = new Note(46, 2, 4, Pitch.E);
  Note e1 = new Note(52, 2, 4, Pitch.E);
  Note f1 = new Note(48, 2, 4, Pitch.D);
  Note g1 = new Note(50, 2, 4, Pitch.D);
  Note h1 = new Note(54, 2, 4, Pitch.D);
  Note i1 = new Note(56, 8, 3, Pitch.E);
  Note j1 = new Note(56, 8, 4, Pitch.C);

  IMusicEditor model = new MusicEditorModel();
  IMusicEditor model2 = new MusicEditorModel();
  ConcealModel conceal;
  ViewFactory factory = new ViewFactory();
  IView view = factory.create("console");

  private final ByteArrayOutputStream output = new ByteArrayOutputStream();
  private final ByteArrayOutputStream error = new ByteArrayOutputStream();

  @Before // setups streams
  public void setUpStreams() {
    System.setOut(new PrintStream(output));
    System.setErr(new PrintStream(error));
  }

  @After //clears streams
  public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
  }


  @Test // tests the render function
  public void testRender() {
    assertEquals(model.getLowestTone(), 150);
    model.addNote(a);
    assertEquals(model.getLowestTone(), 81);
    model.addNote(b);
    assertEquals(model.getLowestTone(), 71);
    conceal = new ConcealModel(model);
    view.render(conceal);
    assertEquals(output.toString(), "   B5   C6  C#6   D6  D#6   E6   F6  F#6   G6  G#6   A6 \n"
            + "0                                                    X  \n"
            + "1  X                                                 |  \n"
            + "2  |                                                    \n"
            + "3  |                                                    \n");
  }

  @Test // tests the render function with no input
  public void testRender1() {
    conceal = new ConcealModel(model);
    view.render(conceal);
    assertEquals(output.toString(), "");
  }

  // tests the render function with a null as input
  @Test(expected = IllegalArgumentException.class)
  public void renderNull() {
    view.render(null);
    assertEquals(output.toString(), "");
  }

  @Test // tests the render function for mary had a little lamb
  public void testRender2() {
    model.addNote(c);
    model.addNote(d);
    model.addNote(e);
    model.addNote(f);
    model.addNote(g);
    model.addNote(h);
    model.addNote(i);
    model.addNote(j);
    model.addNote(k);
    model.addNote(l);
    model.addNote(m);
    model.addNote(n);
    model.addNote(o);
    model.addNote(p);
    model.addNote(q);
    model.addNote(r);
    model.addNote(s);
    model.addNote(t);
    model.addNote(u);
    model.addNote(v);
    model.addNote(w);
    model.addNote(x);
    model.addNote(y);
    model.addNote(z);
    model.addNote(a1);
    model.addNote(b1);
    model.addNote(c1);
    model.addNote(d1);
    model.addNote(e1);
    model.addNote(f1);
    model.addNote(g1);
    model.addNote(h1);
    model.addNote(i1);
    model.addNote(j1);
    conceal = new ConcealModel(model);
    view.render(conceal);

    assertEquals(output.toString(), "    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4"
            + "   E4   F4  F#4   G4 \n"
            + " 0                 X                                            X                 \n"
            + " 1                 |                                            |                 \n"
            + " 2                 |                                  X                           \n"
            + " 3                 |                                  |                           \n"
            + " 4                 |                        X                                     \n"
            + " 5                 |                        |                                     \n"
            + " 6                 |                                  X                           \n"
            + " 7                                                    |                           \n"
            + " 8                 X                                            X                 \n"
            + " 9                 |                                            |                 \n"
            + "10                 |                                            X                 \n"
            + "11                 |                                            |                 \n"
            + "12                 |                                            X                 \n"
            + "13                 |                                            |                 \n"
            + "14                 |                                            |                 \n"
            + "15                                                                                \n"
            + "16                 X                                  X                           \n"
            + "17                 |                                  |                           \n"
            + "18                 |                                  X                           \n"
            + "19                 |                                  |                           \n"
            + "20                 |                                  X                           \n"
            + "21                 |                                  |                           \n"
            + "22                 |                                  |                           \n"
            + "23                 |                                  |                           \n"
            + "24                 X                                            X                 \n"
            + "25                 |                                            |                 \n"
            + "26                                                                             X  \n"
            + "27                                                                             |  \n"
            + "28                                                                             X  \n"
            + "29                                                                             |  \n"
            + "30                                                                             |  \n"
            + "31                                                                             |  \n"
            + "32                 X                                            X                 \n"
            + "33                 |                                            |                 \n"
            + "34                 |                                  X                           \n"
            + "35                 |                                  |                           \n"
            + "36                 |                        X                                     \n"
            + "37                 |                        |                                     \n"
            + "38                 |                                  X                           \n"
            + "39                 |                                  |                           \n"
            + "40                 X                                            X                 \n"
            + "41                 |                                            |                 \n"
            + "42                 |                                            X                 \n"
            + "43                 |                                            |                 \n"
            + "44                 |                                            X                 \n"
            + "45                 |                                            |                 \n"
            + "46                 |                                            X                 \n"
            + "47                 |                                            |                 \n"
            + "48                 X                                  X                           \n"
            + "49                 |                                  |                           \n"
            + "50                 |                                  X                           \n"
            + "51                 |                                  |                           \n"
            + "52                 |                                            X                 \n"
            + "53                 |                                            |                 \n"
            + "54                 |                                  X                           \n"
            + "55                 |                                  |                           \n"
            + "56  X                                       X                                     \n"
            + "57  |                                       |                                     \n"
            + "58  |                                       |                                     \n"
            + "59  |                                       |                                     \n"
            + "60  |                                       |                                     \n"
            + "61  |                                       |                                     \n"
            + "62  |                                       |                                     \n"
            + "63  |                                       |                                     \n"
    );
  }

  @Test// tests the render function when two compositions are queued.
  public void testRenderQueue() {
    model.addNote(a);
    model2.addNote(b);
    model.queue(model2);
    conceal = new ConcealModel(model);
    view.render(conceal);
    assertEquals(output.toString(), "   B5   C6  C#6   D6  D#6   E6   F6  F#6   G6  G#6   A6 \n"
            + "0                                                    X  \n"
            + "1                                                    |  \n"
            + "2  X                                                    \n"
            + "3  |                                                    \n"
            + "4  |                                                    \n");
  }

  @Test
  public void testRenderSimultaneous() {
    model.addNote(a);
    model2.addNote(b);
    model.playTogether(model2);
    conceal = new ConcealModel(model);
    view.render(conceal);
    assertEquals(output.toString(), "   B5   C6  C#6   D6  D#6   E6   F6  F#6   G6  G#6   A6 \n"
            + "0                                                    X  \n"
            + "1  X                                                 |  \n"
            + "2  |                                                    \n"
            + "3  |                                                    \n");
  }
}
