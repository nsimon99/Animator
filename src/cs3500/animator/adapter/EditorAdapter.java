package cs3500.animator.adapter;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.provider.view.EAEditorView;
import cs3500.animator.view.EditorView;

/**
 * Adopter class for Editor Animation
 */
public class EditorAdapter extends EditorView {
  EAEditorView view;

  /**
   * constructor for a view using builder.
   *
   * @param model given model
   */
  public EditorAdapter(AnimationModel model, EAEditorView view) {
    super(model);
    this.view = view;
  }

  @Override
  public void render() {
    view.makeVisible();
  }

  @Override
  protected void updateTimeline() {
    view.refresh();
  }
}
