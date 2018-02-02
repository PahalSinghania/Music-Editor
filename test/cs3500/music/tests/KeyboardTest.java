package cs3500.music.tests;

import org.junit.Test;

import java.awt.AWTException;

import java.awt.Robot;

import cs3500.music.controller.KeyBoardHandler;

import static java.awt.event.KeyEvent.VK_1;
import static java.awt.event.KeyEvent.VK_P;
import static junit.framework.TestCase.assertEquals;

/**
 * test the keyboard handler class to assure functionality works.
 */
public class KeyboardTest {

  /**
   * testAddAndRunRunnableOnPress tests adding and running two Runnable objects for key presses.
   */
  @Test
  public void testAddAndRunRunnableOnPress() throws AWTException {
    KeyBoardHandler handler = new KeyBoardHandler();
    Runnable runnable1 = new SimpleRunnable1();
    Runnable runnable2 = new SimpleRunnable2();
    handler.addEffectForKeyPressed(VK_1, new SimpleRunnable1());
    handler.addEffectForKeyPressed(VK_P, new SimpleRunnable2());
    assertEquals(runnable1.toString(), "");
    assertEquals(runnable2.toString(), "");
    Robot robot = new Robot();
    robot.keyPress(VK_1);
    robot.keyRelease(VK_1);
    robot.keyPress(VK_P);
    robot.keyRelease(VK_P);
    assertEquals(runnable1.toString(), "simpleRun1");
    assertEquals(runnable2.toString(), "simpleRun2");
  }

  /**
   * testAddAndRunRunnableOnRelease tests adding and running two Runnable objects for key releases.
   */
  @Test
  public void testAddAndRunRunnableOnRelease() throws AWTException {
    KeyBoardHandler handler = new KeyBoardHandler();
    Runnable runnable1 = new SimpleRunnable1();
    Runnable runnable2 = new SimpleRunnable2();
    handler.addEffectForKeyPressed(VK_1, new SimpleRunnable1());
    handler.addEffectForKeyPressed(VK_P, new SimpleRunnable2());
    assertEquals(runnable1.toString(), "");
    assertEquals(runnable2.toString(), "");
    Robot robot = new Robot();
    robot.keyPress(VK_1);
    robot.keyRelease(VK_1);
    robot.keyPress(VK_P);
    robot.keyRelease(VK_P);
    assertEquals(runnable1.toString(), "simpleRun1");
    assertEquals(runnable2.toString(), "simpleRun2");
  }

  /**
   * testAddAndRunRunnableOnTyped tests adding and running two Runnable objects for key Types.
   */
  @Test
  public void testAddAndRunRunnableOnType() throws AWTException {
    KeyBoardHandler handler = new KeyBoardHandler();
    Runnable runnable1 = new SimpleRunnable1();
    Runnable runnable2 = new SimpleRunnable2();
    handler.addEffectForKeyPressed(VK_1, new SimpleRunnable1());
    handler.addEffectForKeyPressed(VK_P, new SimpleRunnable2());
    assertEquals(runnable1.toString(), "");
    assertEquals(runnable2.toString(), "");
    Robot robot = new Robot();
    robot.keyPress(VK_1);
    robot.keyRelease(VK_1);
    robot.keyPress(VK_P);
    robot.keyRelease(VK_P);
    assertEquals(runnable1.toString(), "simpleRun1");
    assertEquals(runnable2.toString(), "simpleRun2");
  }
}



