package cs3500.animator.provider.view;

/**
 * Represents a visualization for an EasyAnimatorOperations model. Allows users to see the data that
 * is represented within an animation in various contexts.
 */
public interface IEasyAnimatorView {

  /**
   * Refresh the view to reflect any changes in the swing state.
   *
   * @throws UnsupportedOperationException if the view cannot do this task.
   */
  void refresh();

  /**
   * Make the view visible to start the swing session.
   *
   * @throws UnsupportedOperationException if the view cannot do this task.
   */
  void makeVisible();

  /**
   * Draws the given animation at the given tick.
   *
   * @param tick the tick you want to draw the animation at.
   * @throws UnsupportedOperationException if the view cannot do this task.
   */
  void drawAt(int tick);

  /**
   * Generates the appropriate text for the view as a String.
   *
   * @return the textual view of the animation.
   */
  String generateTextView();

}
