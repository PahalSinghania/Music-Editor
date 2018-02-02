package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

import static org.junit.Assert.assertEquals;

/**
 * Tests the note class.
 */
public class NoteTest {

  private Note a = new Note(1, 2, 3, Pitch.E);
  private Note b = new Note(1, 2, 3, Pitch.E);
  private Note c = new Note(1, 2, 3, Pitch.ASHARP);

  @Test(expected = IllegalArgumentException.class) // tests the parameters for a note
  public void testNote() {
    Note a = new Note(0, 0, 0, Pitch.E);
  }

  @Test(expected = IllegalArgumentException.class)// tests the parameters for a note
  public void testNote1() {
    Note a = new Note(0, 2, 0, null);
  }

  // tests the parameters for a note
  @Test(expected = IllegalArgumentException.class)
  public void testNote2() {
    Note a = new Note(0, 2, 11, Pitch.E);
  }

  // tests the parameters for a note
  @Test(expected = IllegalArgumentException.class)
  public void testNote3() {
    Note a = new Note(-1, 2, 2, Pitch.E);
  }

  @Test// tests the get tone method
  public void testGetTone() {
    assertEquals(a.getTone(), 40);
  }

  @Test // tests the get beats method
  public void testGetBeats() {
    assertEquals(a.getBeats(), 2);
  }

  @Test // tests the get start time method
  public void testGetStartTime() {
    assertEquals(a.getStartTime(), 1);
  }

  @Test // tests the modify method
  public void testModify() {
    assertEquals(a.getStartTime(), 1);
    a.modify(11);
    assertEquals(a.getStartTime(), 12);
  }

  @Test // tests the eaquals method
  public void testEquals() {
    assertEquals(a.equals(b), true);
    assertEquals(a.equals(c), false);
  }
}
