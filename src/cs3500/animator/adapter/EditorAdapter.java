package cs3500.animator.adapter;

import cs3500.animator.provider.model.ReadOnlyEAOperations;
import cs3500.animator.provider.view.EAEditorView;
import cs3500.animator.view.AnimationView;

/**
 * Adapter class for Editor Animation.
 */
public class EditorAdapter extends EAEditorView implements AnimationView {

  private int tickrate;


  /**
   * Constructs the given editor view, using the given model.
   *
   * @param model the model we are given
   */
  public EditorAdapter(ReadOnlyEAOperations model, int t) {
    super(model);
    tickrate = t;
  }

  @Override
  public void render() {
    this.drawAt(tickrate);
    this.refresh();
    this.makeVisible();
  }


}