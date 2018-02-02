package cs3500.music.view.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import cs3500.music.model.Pitch;
import cs3500.music.view.ConcealModel;

/**
 * Creates the implementation of the visual view.
 *
 * <P> </P>New methods implemented as GuiViewImpl now extends GuiView
 */
public class GuiViewImpl extends JFrame implements GuiView {
  private int blockSize = 20;
  private final JFrame frame = new JFrame("Music Editor");
  private final JPanel display_panel = new JPanel(new BorderLayout());
  private final JScrollPane scroll = new JScrollPane(display_panel);
  private ConcealModel model;
  public static int BEAT = 0;

  @Override
  public void render(ConcealModel m) {
    this.model = m;
    this.initialise();
  }

  /* Initializes the view. */
  private void initialise() {
    DrawComposition composition = new DrawComposition(this.model, BEAT);
    this.display_panel.add(this.drawTimes(), BorderLayout.NORTH);
    this.display_panel.add(this.drawPitches(), BorderLayout.WEST);
    this.display_panel.add(composition, BorderLayout.CENTER);
    this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.frame.setPreferredSize(new Dimension(Math.min(1300, 25 * model.getDuration()),
            Math.min(800, 25 * (model.getHighestTone() - model.getLowestTone()))));
    this.frame.setBackground(Color.lightGray);
    this.frame.add(scroll);
    this.frame.pack();
    this.frame.setVisible(true);
  }


  /**
   * Draws the pitches.
   *
   * @return All the pitches as an image
   */
  private Box drawPitches() {
    Box pitches = Box.createVerticalBox();
    for (int i = this.model.getHighestTone(); i >= this.model.getLowestTone(); i--) {
      JLabel tone = new JLabel(Pitch.get(i % 12) + (i / 12));
      pitches.add(Box.createVerticalStrut(4));
      pitches.add(tone);
    }
    return pitches;
  }


  /**
   * Draws the time.
   *
   * @return An image that contains all the timestamps
   */
  private Box drawTimes() {
    int t = model.getDuration();
    while (t % 4 != 0) {
      t += 1;
    }
    Box times = Box.createHorizontalBox();
    times.add(Box.createHorizontalStrut(23));
    for (int i = 0; i <= t; i += 16) {
      JLabel time = new JLabel(Integer.toString(i));
      times.add(time);
      times.add(Box.createHorizontalStrut(blockSize * 16
              - (int) time.getPreferredSize().getWidth()));
    }
    return times;
  }

  @Override
  public void addKeyListener(KeyListener k) {
    this.frame.addKeyListener(k);
  }

  @Override
  public void upKey() {
    this.scroll.getVerticalScrollBar().setValue(this.scroll.getVerticalScrollBar().getValue() - 20);
  }

  @Override
  public void down() {
    this.scroll.getVerticalScrollBar().setValue(this.scroll.getVerticalScrollBar().getValue() + 20);
  }

  @Override
  public void left() {
    this.scroll.getHorizontalScrollBar().setValue(this.scroll.getHorizontalScrollBar().getValue()
            - 20);
  }

  @Override
  public void right() {
    this.scroll.getHorizontalScrollBar().setValue(this.scroll.getHorizontalScrollBar().getValue()
            + 20);
  }

 

  @Override
  public void update(int n) {
    BEAT = n;
    if (this.BEAT * blockSize >= this.frame.getBounds().getSize().getWidth()
            - blockSize) {
      this.scroll.getHorizontalScrollBar().setValue(BEAT * blockSize);
    }
  }
}
