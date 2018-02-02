package cs3500.music.model;

/**
 * Represent the different pitches of music.
 * - Added function get  Pitch
 */
public enum Pitch {
  C("C"), CSHARP("C#"), D("D"), DSHARP("D#"), E("E"),
  F("F"), FSHARP("F#"), G("G"), GSHARP("G#"), A("A"),
  ASHARP("A#"), B("B");

  private final String pitch;

  /**
   * Constructs a {@code Pitch} object.
   *
   * @param p the pitch as a string
   */
  Pitch(String p) {
    this.pitch = p;
  }

  /**
   * Gets the Pitch i as a string.
   *
   * @param i the index of the Pitch
   * @return the corresponding pitch as a string
   */
  public static String get(int i) {
    String s = "";
    switch (i) {
      case 0:
        s = C.toString();
        break;
      case 1:
        s = CSHARP.toString();
        break;
      case 2:
        s = D.toString();
        break;
      case 3:
        s = DSHARP.toString();
        break;
      case 4:
        s = E.toString();
        break;
      case 5:
        s = F.toString();
        break;
      case 6:
        s = FSHARP.toString();
        break;
      case 7:
        s = G.toString();
        break;
      case 8:
        s = GSHARP.toString();
        break;
      case 9:
        s = A.toString();
        break;
      case 10:
        s = ASHARP.toString();
        break;
      case 11:
        s = B.toString();
        break;
      default:
        throw new IllegalArgumentException("No such pitch exists");
    }
    return s;
  }

  /**
   * Returns the pitch of the current index.
   *
   * @param i the index
   * @return returns the current index of the card
   */
  public static Pitch getPitch(int i) {
    Pitch s;
    switch (i) {
      case 0:
        s = C;
        break;
      case 1:
        s = CSHARP;
        break;
      case 2:
        s = D;
        break;
      case 3:
        s = DSHARP;
        break;
      case 4:
        s = E;
        break;
      case 5:
        s = F;
        break;
      case 6:
        s = FSHARP;
        break;
      case 7:
        s = G;
        break;
      case 8:
        s = GSHARP;
        break;
      case 9:
        s = A;
        break;
      case 10:
        s = ASHARP;
        break;
      case 11:
        s = B;
        break;
      default:
        throw new IllegalArgumentException("No such pitch exists");
    }
    return s;
  }

  @Override
  public String toString() {
    return this.pitch;
  }
}
