package cs3500.music.provider;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import cs3500.music.model.Note;

/**
 * Represents the grid and notes of an {@link GuiViewImpl}.
 */
public final class NotesGridPanel extends JPanel {
  private static final long serialVersionUID = 1L;
  private final ViewModel model;

  /**
   * Constructor for a {@link NotesGridPanel}.
   *
   * @param model the view-model
   */
  public NotesGridPanel(ViewModel model) {
    this.model = model;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    paintNotes(g);
    paintGrid(g);
    paintCurBeat(GuiViewImpl.CURRBEAT, g);
    this.repaint();
  }

  /**
   * Draws the current beat red line.
   *
   * @param curBeat the current beat of the piece of music
   * @param g       graphics
   */
  private void paintCurBeat(int curBeat, Graphics g) {
    if (curBeat >= model.getLastBeat() - 1) {
      curBeat++;
    }
    g.setColor(Color.RED);
    g.drawLine(curBeat * GuiViewImpl.BLOCK, 0, curBeat * GuiViewImpl.BLOCK,
            (model.getHighestNote() - model.getLowestNote() + 1)
                    * GuiViewImpl.BLOCK);
  }

  /**
   * Draws the grid.
   *
   * @param g graphics
   */
  private void paintGrid(Graphics g) {
    g.setColor(Color.BLACK);
    for (int i = 0; i <= this.model.getHighestNote()
            - this.model.getLowestNote() + 1; i++) {
      ((Graphics2D) g).setStroke(new BasicStroke(2));
      if ((this.model.getHighestNote() - i + 1) % 12 == 0) {
        ((Graphics2D) g).setStroke(new BasicStroke(3));
      }
      g.drawLine(0, i * GuiViewImpl.BLOCK, this.model.getLastBeat()
              * GuiViewImpl.BLOCK, i * GuiViewImpl.BLOCK);
    }

    for (int i = 0; i <= this.model.getLastBeat(); i++) {
      if (i % 4 == 0 || i == this.model.getLastBeat()) {
        g.drawLine(
                i * GuiViewImpl.BLOCK,
                0,
                i * GuiViewImpl.BLOCK,
                (this.model.getHighestNote()
                        - this.model.getLowestNote() + 1)
                        * GuiViewImpl.BLOCK);
      }
    }
  }

  /**
   * Draws all of the {@link Note}s in the piece of music.
   *
   * @param g graphics
   */
  private void paintNotes(Graphics g) {
    super.paintComponent(g);
    for (int i = 0; i < this.model.getLastBeat(); i++) {
      for (Note p : this.model.getNotesAtBeat(i)) {
        if (p.getStartTime() == i) {
          this.paintNote(p, g);
        }
      }
    }
  }

  /**
   * Draws one {@link Note}.
   *
   * @param note the {@link Note} to draw
   * @param g graphics
   */
  private void paintNote(Note note, Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(note.getStartTime() * GuiViewImpl.BLOCK,
            (this.model.getHighestNote() - note.getTone())
                    * GuiViewImpl.BLOCK, GuiViewImpl.BLOCK,
            GuiViewImpl.BLOCK);
    g.setColor(Color.GREEN);
    g.fillRect((note.getStartTime() + 1) * GuiViewImpl.BLOCK,
            (this.model.getHighestNote() - note.getTone())
                    * GuiViewImpl.BLOCK, (note.getBeats() - 1)
                    * GuiViewImpl.BLOCK, GuiViewImpl.BLOCK);
  }
}
