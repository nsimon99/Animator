package cs3500.animator.adapter;

import cs3500.animator.provider.controller.IEasyAnimatorController;

/**
 * Controller that connects new view and model.
 *
 */
public class ControllerAdapter implements IEasyAnimatorController {
  private ModelAdapter model;
  private EditorAdapter view;


  /**
   * Constructor for the controller.
   * @param model given model with information
   * @param view type of view for user
   */
  public ControllerAdapter(ModelAdapter model, EditorAdapter view) {
    this.model = model;
    this.view = view;
  }


  @Override
  public void animate() {


  }
}
