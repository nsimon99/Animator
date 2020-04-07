package cs3500.animator.controller;

/**
 * Controller interface for animation.
 */
public interface IController {


  /**
   * Plays animation depending on the type of view.
   *
   * @param viewType type of view being implemented
   */
  void playAnimation(String viewType);

  /**
   * Returns text version of given view and model
   */

  String getString();

}
