package cs3500.music.view.gui;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import cs3500.music.view.IView;

/**
 * A sub-interface GuiView for dealing with keyboard and mouse.
 */
public interface GuiView extends IView {

  /**
   * Adds a key Listener to the View.
   *
   * @param k The key Listner.
   */
  void addKeyListener(KeyListener k);

  /**
   * Adds a MouseListener.
   *
   * @param m MouseListener
   */
  void addMouseListener(MouseListener m);


  /**
   * Removes a MouseListener.
   *
   * @param m MouseListener
   */
  void removeMouseListener(MouseListener m);


  /**
   * Moves the view up when up arrow key is pressed.
   */
  void upKey();

  /**
   * Moves the view down when up arrow key is pressed.
   */
  void down();

  /**
   * Moves the view left when up arrow key is pressed.
   */
  void left();

  /**
   * Moves the view right when up arrow key is pressed.
   */
  void right();

  /**
   * Jumps to beginning when home key is pressed.
   */
  void jumpToBeginning();

  /**
   * jumps to end when end key is pressed.
   */
  void jumpToEnd();

  /**
   * pauses or resumes when space bar is pressed.
   */
  void space();

  /**
   * Updates the View.
   *
   * @param n The current beat.
   */
  void update(int n);

}
