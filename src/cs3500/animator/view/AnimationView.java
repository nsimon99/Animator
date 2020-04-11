package cs3500.animator.view;

/**
 * the view interface of the animation program.
 */
public interface AnimationView {

  /**
   * render the model to reflect changes in object states.
   *
   * @throws IllegalStateException if the view cannot be rendered.
   */
  void render();
}
