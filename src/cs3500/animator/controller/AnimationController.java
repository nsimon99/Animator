package cs3500.animator.controller;


import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.SwingAnimationView;
import cs3500.animator.view.TextView;

/**
 * Represents a controller for animation. Implements methods from {@link IController}.
 * Can be used for all views.
 */

public class AnimationController implements IController {
  AnimationModel model;

  /**
   * constructor for the controller.
   * @param model model being implemented.
   * @throws IllegalStateException if model is null
   */
  public AnimationController(AnimationModel model) {
    if (model == null) {
      throw new IllegalStateException("null model");
    }
    this.model = model;
  }

  @Override
  public void playAnimation(String viewType) {
    AnimationView view;
    switch (viewType) {
      case "svg":
        view = new SVGView(model, "-out", 1);
        break;
      case "text":
        view = new TextView(model, System.out);
        break;
      case "visual":
        view = new SwingAnimationView(model);
        break;
      case "editor":
        //todo: implement editor controller
      default:
        throw new IllegalArgumentException("Invalid View");
    }
    System.out.println("ehy");

    view.render();
  }

}
