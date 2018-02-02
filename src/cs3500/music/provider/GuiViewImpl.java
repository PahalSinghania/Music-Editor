package cs3500.music.provider;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cs3500.music.model.Pitch;

/**
 * A skeleton Frame (i.e., a window) in Swing.
 */
public class GuiViewImpl extends javax.swing.JFrame implements GuiView {

  public static final int BLOCK = 20;
  public static final int UNIT = 16;
  private ViewModel model;
  private JScrollPane scroll;
  private JPanel gridPanel;
  public static int CURRBEAT = 0;

  /**
   * Constructs a GuiViewImpl.
   */
  public GuiViewImpl() {
    setTitle("Music Editor");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(getPreferredSize());
    setBackground(Color.WHITE);

    gridPanel = new JPanel(new BorderLayout());
    scroll = new JScrollPane(gridPanel);
  }

  @Override
  public void display(ViewModel model) {
    this.model = model;
    this.init();
  }

  /**
   * Initializes the view.
   */
  private void init() {
    gridPanel.add(pitchesLine(), BorderLayout.WEST);
    gridPanel.add(beatLine(), BorderLayout.NORTH);
    gridPanel.add(notesPanel(), BorderLayout.CENTER);

    scroll.getVerticalScrollBar().setUnitIncrement(UNIT);
    scroll.getHorizontalScrollBar().setUnitIncrement(UNIT);

    add(scroll);
    pack();
    setVisible(true);
  }

  /**
   * Draws the pitches.
   *
   * @return a vertical box with the pitches
   */
  private Box pitchesLine() {
    Box pitches = Box.createVerticalBox();
    for (int i = model.getHighestNote(); i >= model.getLowestNote(); i--) {
      JLabel label = new JLabel(Pitch.getPitch(i % 12).toString() + i / 12);
      pitches.add(Box.createVerticalStrut(BLOCK / 5));
      pitches.add(label);
    }
    return pitches;
  }

  /**
   * Draws the beats.
   *
   * @return a horizontal box with the beats
   */
  private Box beatLine() {
    Box times = Box.createHorizontalBox();
    times.add(Box.createHorizontalStrut((BLOCK * 2) - UNIT));
    for (int i = 0; i <= model.getLastBeat(); i++) {
      if (i % UNIT == 0) {
        Box container = Box.createHorizontalBox();
        container.setMinimumSize(new Dimension(BLOCK * 4, BLOCK));
        container.setMaximumSize(new Dimension(BLOCK * 4, BLOCK));
        container.setPreferredSize(new Dimension(BLOCK * 4, BLOCK));
        JLabel label = new JLabel(Integer.toString(i));
        container.add(label);
        times.add(container);
        i += 3;
      } else {
        times.add(Box.createHorizontalStrut(BLOCK));
      }
    }
    return times;
  }

  /**
   * Draws the grids and notes.
   *
   * @return a grid panel
   */
  private JPanel notesPanel() {
    JPanel panel = new NotesGridPanel(model);
    return panel;
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(
            (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()),
            500);
  }

  @Override
  public void arrowUp() {
    scroll.getVerticalScrollBar().setValue(
            scroll.getVerticalScrollBar().getValue()
                    - scroll.getVerticalScrollBar().getUnitIncrement());
  }

  @Override
  public void arrowDown() {
    scroll.getVerticalScrollBar().setValue(
            scroll.getVerticalScrollBar().getValue()
                    + scroll.getVerticalScrollBar().getUnitIncrement());
  }

  @Override
  public void arrowRight() {
    scroll.getHorizontalScrollBar().setValue(
            scroll.getHorizontalScrollBar().getValue()
                    + scroll.getHorizontalScrollBar().getUnitIncrement());
  }

  @Override
  public void arrowLeft() {
    scroll.getHorizontalScrollBar().setValue(
            scroll.getHorizontalScrollBar().getValue()
                    - scroll.getHorizontalScrollBar().getUnitIncrement());
  }

  @Override
  public void jumpToBeginning() {
    scroll.getHorizontalScrollBar().setValue(0);
  }

  @Override
  public void jumpToEnd() {
    scroll.getHorizontalScrollBar().setValue(model.getLastBeat() * BLOCK);
  }

  @Override
  public void update(int curBeat) {
    GuiViewImpl.CURRBEAT = curBeat;
    if (GuiViewImpl.CURRBEAT * BLOCK >= getBounds().getSize().getWidth()
            - BLOCK) {
      scroll.getHorizontalScrollBar().setValue(
              GuiViewImpl.CURRBEAT * BLOCK);
    }
  }

  @Override
  public void addMouseListener(MouseListener m) {
    gridPanel.addMouseListener(m);
  }

  @Override
  public void removeMouseListener(MouseListener m) {
    gridPanel.removeMouseListener(m);
  }

  @Override
  public void pause() {
    // TODO Auto-generated method stub
  }
}
