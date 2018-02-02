package cs3500.music.view.gui;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import cs3500.music.model.Note;
import cs3500.music.view.ConcealModel;

/**
 * DrawComposition draws the notes and the grid.
 */
public class DrawComposition extends JPanel {
  private ConcealModel model;
  private int blockSize = 20;
  private int highestTone;
  private int beat = -1;

  /**
   * Creates a {@code DrawComposition} object.
   *
   * @param m conceals the model from the views.
   */
  DrawComposition(ConcealModel m, int b) {
    this.model = m;
    this.highestTone = model.getHighestTone();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.drawNotes(g);
    this.drawGrid(g);
    this.drawRedLine(g);
    this.repaint();
  }

  /**
   * Draw's a red Line.
   */
  private void drawRedLine(Graphics g) {
    beat = GuiViewImpl.BEAT;
    g.setColor(Color.red);
    g.drawLine(beat * blockSize, 0, beat * blockSize,
            (this.highestTone - this.model.getLowestTone() + 1) * blockSize);
  }

  /**
   * Draw's a grid.
   */
  private void drawGrid(Graphics g) {
    int c = -1;
    int time = model.getDuration();
    while (time % 4 != 0) {
      time += 1;
    }
    g.setColor(Color.black);
    for (int i = 0; i <= highestTone - this.model.getLowestTone() + 1; i++) {
      if ((highestTone - i + 1) % 12 == 0) {
        ((Graphics2D) g).setStroke(new BasicStroke(3));
      } else {
        ((Graphics2D) g).setStroke(new BasicStroke(2));
      }
      g.drawLine(0, i * blockSize, time * blockSize, i * blockSize);
      c = c + 1;
    }
    for (int i = 0; i <= time; i++) {
      if (i % 4 == 0) {
        g.drawLine(i * blockSize, 0, i * blockSize, c * blockSize);
      }
    }
  }

  /**
   * Draw's all the notes.
   */
  private void drawNotes(Graphics g) {
    super.paintComponent(g);
    for (int i = 0; i < this.model.getComposition().size(); i++) {
      for (Note n : this.model.getComposition().get(i)) {
        this.drawNote(g, n);
      }
    }
  }

  /**
   * Draw the given note.
   */
  private void drawNote(Graphics g, Note n) {
    g.setColor(Color.green);
    g.fillRect(n.getStartTime() * blockSize, (highestTone - n.getTone()) * blockSize,
            n.getBeats() * blockSize, blockSize);
    g.setColor(Color.black);
    g.fillRect(n.getStartTime() * blockSize, (highestTone - n.getTone()) * blockSize,
            blockSize, blockSize);
  }
}
