

package cs3500.animator.controller;


import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.EditorView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.SwingAnimationView;
import cs3500.animator.view.TextView;

/**
 * Represents a controller for animation. Implements methods from {@link IController}.
 * Can be used for all views.
 */

public class Controller implements IController {

  AnimationModel model;

  /**
   * constructor for the controller.
   *
   * @param model model being implemented.
   * @throws IllegalStateException if model is null
   */
  public Controller(AnimationModel model) {
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
        view = new EditorView(model);
        break;
      default:
        throw new IllegalArgumentException("Invalid View");
    }

    view.render();
  }

  @Override
  public String getString() {
    return model.renderText();
  }


}


