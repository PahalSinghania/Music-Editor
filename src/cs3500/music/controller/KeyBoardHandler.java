package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * KeyBoardHandler handles keyboard input.
 */
public class KeyBoardHandler implements KeyListener {
  private HashMap<Integer, Runnable> typed;
  private HashMap<Integer, Runnable> pressed;
  private HashMap<Integer, Runnable> released;

  /**
   * KeyBoardhandler handles keyboard input.
   */
  public KeyBoardHandler() {
    this.typed = new HashMap<>();
    this.pressed = new HashMap<>();
    this.released = new HashMap<>();
  }

  /**
   * addEffectForKeyPressed specifies the desired behavior when a key is pressed.
   *
   * @param code the keycode
   * @param r    the Runnable which has the desired behavior
   */
  public void addEffectForKeyPressed(int code, Runnable r) {
    this.pressed.put(code, r);
  }

  /**
   * addEffectForKeyReleased specifies the desired behavoir when a key is released.
   *
   * @param code the keycode
   * @param r    the Runnable which has the desired behavior
   */
  public void addEffectForKeyReleased(int code, Runnable r) {
    this.released.put(code, r);
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // does nothing
  }

  @Override
  public void keyPressed(KeyEvent e) {
    System.out.println("Pressed Key\n");
    System.out.println(e.toString());
    if (this.pressed.containsKey(e.getKeyCode())) {
      this.pressed.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (this.released.containsKey(e.getKeyCode())) {
      this.released.get(e.getKeyCode()).run();
    }
  }
}
