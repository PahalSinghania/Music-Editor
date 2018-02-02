package cs3500.music.provider;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public class CompositeViewImpl implements GuiView {
  private final GuiView guiView;
  private final View midiView;
  private ViewModel model;
  private boolean paused = false;

  public CompositeViewImpl(GuiView guiView, View midiView) {
    this.guiView = guiView;
    this.midiView = midiView;
  }

  @Override
  public void display(ViewModel model) {
    this.model = model;
    guiView.display(model);
  }

  @Override
  public void addKeyListener(KeyListener k) {
    guiView.addKeyListener(k);
  }

  @Override
  public void addMouseListener(MouseListener m) {
    guiView.addMouseListener(m);
  }

  @Override
  public void removeMouseListener(MouseListener m) {
    guiView.removeMouseListener(m);
  }

  @Override
  public void arrowUp() {
    guiView.arrowUp();
  }

  @Override
  public void arrowDown() {
    guiView.arrowDown();
  }

  @Override
  public void arrowRight() {
    guiView.arrowRight();
  }

  @Override
  public void arrowLeft() {
    guiView.arrowLeft();
  }

  @Override
  public void jumpToBeginning() {
    guiView.jumpToBeginning();
  }

  @Override
  public void jumpToEnd() {
    guiView.jumpToEnd();
  }

  @Override
  public void update(int curBeat) {
    if (!paused) {
      guiView.update(curBeat);
      midiView.display(model);
    }
  }

  @Override
  public void pause() {
    paused = !paused;
  }

  public GuiView getGuiView() {
    return guiView;
  }

  public View getMidiView() {
    return midiView;
  }

  public ViewModel getModel() {
    return model;
  }

  public void setModel(ViewModel model) {
    this.model = model;
  }

  public boolean isPaused() {
    return paused;
  }

  public void setPaused(boolean paused) {
    this.paused = paused;
  }

}
