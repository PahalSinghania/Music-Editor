package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import cs3500.music.model.IMusicEditor;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.provider.GuiView;
import cs3500.music.provider.ViewModel;

import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_END;
import static java.awt.event.KeyEvent.VK_HOME;
import static java.awt.event.KeyEvent.VK_UP;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_SPACE;

/**
 * Implements the controller.
 */
public class Controller {
  private IMusicEditor model;
  private GuiView view;
  private KeyBoardHandler keyHandler;
  private boolean isPaused = false;
  private boolean isRemoving = false;
  private boolean isAdding = false;
  private MouseHandler mouseHandler;
  private Timer timer = new Timer();
  private MouseHandler remover;
  private int beat = 0;
  Note note;

  // pauses the model
  private final Runnable pause = () -> {
    this.isPaused = !this.isPaused;
    this.view.pause();
  };

  // moves left
  private final Runnable left = () -> this.view.arrowLeft();

  // moves right
  private final Runnable right = () -> this.view.arrowRight();

  // move up
  private final Runnable up = () -> this.view.arrowUp();

  // moves down
  private final Runnable down = () -> this.view.arrowDown();

  // Jumps to beginning
  private final Runnable beginning = () -> this.view.jumpToBeginning();

  // jumps to end
  private final Runnable end = () -> this.view.jumpToEnd();

  // removes a note
  private final Runnable remove = () -> {
    this.isRemoving = !this.isRemoving;
    if (this.isRemoving) {
      this.view.removeMouseListener(this.mouseHandler);
      this.view.addMouseListener(this.remover);
    } else {
      this.view.removeMouseListener(this.remover);
      this.view.addMouseListener(this.mouseHandler);
    }
  };

  // adds a note.
  private final Runnable addNote = () -> {
    this.isAdding = !this.isAdding;
    int y = this.mouseHandler.getEvent().getY();
    int time = (this.mouseHandler.getEvent().getX() / 20) - 1;
    int tone = this.model.getHighestTone() - (y / 20) + 1;
    int x = this.mouseHandler.getEvent().getX() / 20;
    if (y > 20 && x <= this.model.getDuration()
            && y <= (this.model.getHighestTone() - this.model.getLowestTone() + 2) * 20
            && this.beat < this.model.getDuration()) {
      if (this.isAdding) {
        note = new Note(time, 100, tone / 12, Pitch.getPitch(tone % 12));
      } else if (x - note.getStartTime() > 0) {

        this.model.addNote(new Note(note.getStartTime(), x - note.getStartTime(),
                note.getTone() / 12, Pitch.getPitch(note.getTone() % 12)));
      }
    }
  };

  // removes a note
  private final Runnable removeNote = () -> {
    int time = (this.remover.getEvent().getX() / 20) - 1;
    int tone = this.model.getHighestTone() - (this.remover.getEvent().getY() / 20) + 1;
    Note n = this.getNote(time, tone);
    if (n != null) {
      this.model.removeNote(n);
    }
  };

  /**
   * Controller contols the flow of the program.
   *
   * @param v the view
   * @param m the musicEditor
   */
  public Controller(GuiView v, IMusicEditor m) {
    this.view = v;
    this.model = m;
    this.keyHandler = new KeyBoardHandler();
    this.keyHandler.addEffectForKeyReleased(VK_SPACE, this.pause);
    this.keyHandler.addEffectForKeyPressed(VK_LEFT, this.left);
    this.keyHandler.addEffectForKeyPressed(VK_RIGHT, this.right);
    this.keyHandler.addEffectForKeyPressed(VK_UP, this.up);
    this.keyHandler.addEffectForKeyPressed(VK_DOWN, this.down);
    this.keyHandler.addEffectForKeyPressed(VK_HOME, this.beginning);
    this.keyHandler.addEffectForKeyPressed(VK_END, this.end);
    this.keyHandler.addEffectForKeyPressed(VK_D, this.remove);
    System.out.println("works");
    mouseHandler = new MouseHandler();
    this.mouseHandler.setOnPress(MouseEvent.BUTTON1, this.addNote);
    this.mouseHandler.setOnRelease(MouseEvent.BUTTON1, this.addNote);
    remover = new MouseHandler();
    this.remover.setOnPress(MouseEvent.BUTTON1, this.removeNote);
    this.remover.setOnRelease(MouseEvent.BUTTON1, this.removeNote);
    this.view.addKeyListener(keyHandler);
    this.view.addMouseListener(mouseHandler);
    ViewModel concealModel = new ViewModel(model);
    this.view.display(concealModel);
    this.tick();
  }

  /**
   * gets the note that has been clicked.
   */
  private Note getNote(int time, int tone) {
    for (Note n : this.model.getNotesAt(time)) {
      if (n.getTone() == tone) {
        return n;
      }
    }
    throw new IllegalArgumentException("oops");
  }

  /**
   * Tick simulates a tick of music.
   */
  private void tick() {
    this.timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        if (!isPaused) {
          view.update(beat++);
        }
        if (beat >= model.getDuration()) {
          timer.cancel();
        }
      }
    }, 0, this.model.getTempo() / 1000);
  }
}
