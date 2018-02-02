package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.model.Pitch;

import static org.junit.Assert.assertEquals;

/**
 * Tests the Pitch class.
 */
public class PitchTest {

  Pitch a = Pitch.A;
  Pitch b = Pitch.CSHARP;

  @Test // tests the toString method
  public void testToString() {
    assertEquals(a.toString(), "A");
    assertEquals(b.toString(), "C#");
  }

  @Test // tests the get method
  public void testGet() {
    assertEquals(Pitch.get(0), "C");
    assertEquals(Pitch.get(1), "C#");
  }

  // tests the get method for inaccurate input
  @Test(expected = IllegalArgumentException.class)
  public void testGet1() {
    assertEquals(Pitch.get(0), "C");
    assertEquals(Pitch.get(12), "Out of bounds");
  }
}
