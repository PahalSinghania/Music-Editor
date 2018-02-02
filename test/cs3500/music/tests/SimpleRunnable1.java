package cs3500.music.tests;

/**
 * Helps test the SimpleRunnable.
 */
class SimpleRunnable1 implements Runnable {
  String output;

  public SimpleRunnable1() {
    this.output = "";
  }

  public String toString() {
    return this.output;
  }

  @Override
  public void run() {
    this.output = "simpleRun1";
  }
}