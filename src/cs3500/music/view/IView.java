package cs3500.music.view;

/**
 * Interface to render the model.
 */
public interface IView {

  /**
   * Renders the given model to the console.
   *
   * @param m model of the composition to be rendered
   */
  void render(ConcealModel m);

}
