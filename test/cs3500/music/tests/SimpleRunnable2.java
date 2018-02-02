package cs3500.music.tests;

/**
 * Created by Pahal on 11/21/16.
 */
class SimpleRunnable2 implements Runnable {
  String output;

  public SimpleRunnable2() {
    this.output = "";
  }

  public String toString() {
    return this.output;
  }

  @Override
  public void run() {
    this.output = "simpleRun2";
  }
}
