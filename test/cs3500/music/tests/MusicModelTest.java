package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.model.IMusicEditor;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

import static org.junit.Assert.assertEquals;

/**
 * Tests the Music Model.
 */
public class MusicModelTest {

  Note a = new Note(0, 2, 9, Pitch.A);
  Note b = new Note(1, 3, 5, Pitch.B);

  IMusicEditor model = new MusicEditorModel();
  IMusicEditor model2 = new MusicEditorModel();


  @Test
  public void testLowestTone() {
    assertEquals(model.getLowestTone(), 150);
    model.addNote(a);
    assertEquals(model.getLowestTone(), 117);
  }

  @Test
  public void testHighestTone() {
    assertEquals(model.getHighestTone(), 0);
    model.addNote(a);
    assertEquals(model.getHighestTone(), 117);
  }

  @Test
  public void testDuration() {
    assertEquals(model.getDuration(), 0);
    model.addNote(a);
    assertEquals(model.getDuration(), 1);
  }

  @Test
  public void testGetComposition() {
    assertEquals(model.getComposition().size(), 0);
    model.addNote(a);
    assertEquals(model.getComposition().size(), 1);
  }

  @Test
  public void testAddNote() {
    assertEquals(model.getLowestTone(), 150);
    model.addNote(a);
    assertEquals(model.getLowestTone(), 117);
    model.addNote(b);
    assertEquals(model.getLowestTone(), 71);
  }

  // add a null note
  @Test(expected = IllegalArgumentException.class)
  public void testAddNull() {
    model.addNote(null);
  }

  @Test
  public void testRemoveNote() {
    assertEquals(model.getLowestTone(), 150);
    model.addNote(a);
    assertEquals(model.getLowestTone(), 117);
    model.addNote(b);
    assertEquals(model.getLowestTone(), 71);
    model.removeNote(b);
    assertEquals(model.getLowestTone(), 117);
  }

  // remove note that doesn't exist
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNote1() {
    model.addNote(a);
    model.removeNote(b);
  }

  // remove null
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNote2() {
    model.addNote(a);
    model.removeNote(null);
  }

  // remove note from empty model
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNote3() {
    model.removeNote(a);
  }

  @Test
  public void testEditNote() {
    assertEquals(model.getLowestTone(), 150);
    model.addNote(a);
    assertEquals(model.getLowestTone(), 117);
    model.editNote(a, b);
    assertEquals(model.getLowestTone(), 71);
  }

  // change to a null note
  @Test(expected = IllegalArgumentException.class)
  public void testEditNote1() {
    model.addNote(a);
    model.editNote(a, null);
  }

  //change null note
  @Test(expected = IllegalArgumentException.class)
  public void testEditNote2() {
    model.addNote(a);
    model.editNote(null, b);
  }

  // edit note that which is after the total duration
  @Test(expected = IllegalArgumentException.class)
  public void testEditNote3() {
    model.addNote(a);
    model.editNote(b, a);
  }

  // edit note that doesn't exist
  @Test(expected = IllegalArgumentException.class)
  public void testEditNote4() {
    model.addNote(b);
    model.editNote(a, b);
  }

  @Test
  public void testPlayTogether() {
    model.addNote(a);
    model2.addNote(b);
    assertEquals(model.getLowestTone(), 117);
    assertEquals(model.getDuration(), 1);
    model.playTogether(model2);
    assertEquals(model.getDuration(), 3);
    assertEquals(model.getLowestTone(), 71);
  }

  // play with null
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlayTogether() {
    model.addNote(a);
    model.playTogether(null);
  }

  @Test
  public void testQueue() {
    model.addNote(a);
    model2.addNote(b);
    assertEquals(model.getLowestTone(), 117);
    assertEquals(model.getDuration(), 1);
    model.queue(model2);
    assertEquals(model.getDuration(), 4);
    assertEquals(model.getLowestTone(), 71);
  }

  // play with null
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidQueue() {
    model.addNote(a);
    model.queue(null);
  }
}
