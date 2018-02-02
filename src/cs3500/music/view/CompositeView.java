package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import cs3500.music.view.gui.GuiView;
import cs3500.music.view.gui.GuiViewImpl;
import cs3500.music.view.midi.MidiViewImpl;

/**
 * Implements a composite view.
 */
public class CompositeView implements GuiView {
  private GuiView guiView;
  private MidiViewImpl midiView;
  private ConcealModel model;
  private boolean pause = false;

  /**
   * Creates a {@code compositeView} object.
   *
   * @param g GuiViewImpl
   * @param m MidiViewImpl
   */
  public CompositeView(GuiViewImpl g, MidiViewImpl m) {
    this.guiView = g;
    this.midiView = m;
  }

  @Override
  public void render(ConcealModel m) {
    this.model = m;
    this.guiView.render(model);
    //this.midiView.render(model);
  }

  @Override
  public void addKeyListener(KeyListener k) {
    this.guiView.addKeyListener(k);
  }

  @Override
  public void addMouseListener(MouseListener m) {
    this.guiView.addMouseListener(m);
  }

  @Override
  public void removeMouseListener(MouseListener m) {
    this.guiView.removeMouseListener(m);
  }

  @Override
  public void upKey() {
    this.guiView.upKey();
  }

  @Override
  public void down() {
    this.guiView.down();
  }

  @Override
  public void left() {
    this.guiView.left();
  }

  @Override
  public void right() {
    this.guiView.right();
  }

  @Override
  public void jumpToBeginning() {
    this.guiView.jumpToBeginning();
  }

  @Override
  public void jumpToEnd() {
    this.guiView.jumpToEnd();
  }

  @Override
  public void space() {
    this.pause = !this.pause;
  }

  @Override
  public void update(int n) {
    if (!pause) {
      this.guiView.update(n);
    }
    this.midiView.renderCurrent(model);
  }
}
