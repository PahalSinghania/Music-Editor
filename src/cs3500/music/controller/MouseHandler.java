package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

/**
 * MouseHandler handles the mouse input.
 */
public class MouseHandler implements MouseListener {
  private HashMap<Integer, Runnable> onClick;
  private HashMap<Integer, Runnable> onPress;
  private HashMap<Integer, Runnable> onRelease;
  private HashMap<Integer, Runnable> onEntry;
  private HashMap<Integer, Runnable> onExit;
  private MouseEvent event;

  /**
   * MouseHandler handles the mouse input.
   */
  public MouseHandler() {
    this.onClick = new HashMap<>();
    this.onPress = new HashMap<>();
    this.onRelease = new HashMap<>();
    this.onEntry = new HashMap<>();
    this.onExit = new HashMap<>();
  }

  /**
   * setOnClick specifies the desired behavior when a mouse button is clicked.
   *
   * @param code the buttons code
   * @param r    the Runnable that has the desired behavior
   */
  public void setOnClick(int code, Runnable r) {
    this.onClick.put(code, r);
  }

  /**
   * setOnPress specifies the desired behavior when a mouse button is pressed.
   *
   * @param code the buttons code
   * @param r    the Runnable that has the desired behavior
   */
  public void setOnPress(int code, Runnable r) {
    this.onPress.put(code, r);
  }

  /**
   * setOnRelease specifies the desired behavior when a mouse button is released.
   *
   * @param code the buttons code
   * @param r    the Runnable that has the desired behavior
   */
  public void setOnRelease(int code, Runnable r) {
    this.onRelease.put(code, r);
  }

  /**
   * setOnEntry specifies the desired behavior when the mouse cursor goes onto the window.
   *
   * @param code the code for mouse entry
   * @param r    the Runnable that has the desired behavior
   */
  public void setOnEntry(int code, Runnable r) {
    this.onEntry.put(code, r);
  }

  /**
   * setOnExit specifies the desired behavior when the mouse cursor leaves the window.
   *
   * @param code the code for the mouse exit
   * @param r    the Runnable that has the desired behavior
   */
  public void setOnExit(int code, Runnable r) {
    this.onExit.put(code, r);
  }

  /**
   * getEvent gets the last event processed by MouseHandler.
   *
   * @return the last event processed by MouseHandler
   */
  public MouseEvent getEvent() {
    return event;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    this.event = e;
    if (this.onClick != null) {
      this.onClick.get(e.getButton());
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    this.event = e;
    if (this.onPress != null) {
      this.onPress.get(e.getButton());
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    this.event = e;
    if (this.onRelease != null) {
      this.onRelease.get(e.getButton());
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    this.event = e;
    if (this.onEntry != null) {
      this.onEntry.get(e.getButton());
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {
    this.event = e;
    if (this.onExit != null) {
      this.onExit.get(e.getButton());
    }
  }
}
