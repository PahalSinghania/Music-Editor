package cs3500.music.provider;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public interface GuiView extends View {
  /**
   * Adds a {@link KeyListener} to the GUI view.
   *
   * @param k the {@link KeyListener} to be added
   */
  void addKeyListener(KeyListener k);

  /**
   * Adds a {@link MouseListener} to the GUI view.
   *
   * @param m the {@link MouseListener} to be added
   */
  void addMouseListener(MouseListener m);

  /**
   * Removes a {@link MouseListener} from the GUI view.
   *
   * @param m the {@link MouseListener} to be removed
   */
  void removeMouseListener(MouseListener m);

  /**
   * Controls the up arrow key.
   */
  void arrowUp();

  /**
   * Controls the down arrow key.
   */
  void arrowDown();

  /**
   * Controls the right arrow key.
   */
  void arrowRight();

  /**
   * Controls the left arrow key.
   */
  void arrowLeft();

  /**
   * Controls the home button key.
   */
  void jumpToBeginning();

  /**
   * Controls the end button key.
   */
  void jumpToEnd();

  /**
   * Updates the GUI view.
   *
   * @param currBeat the current beat of the piece of music
   */
  void update(int currBeat);

  /**
   * Controls the pause and play of the piece of music.
   */
  void pause();
}
