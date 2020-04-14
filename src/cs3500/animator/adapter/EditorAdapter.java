package cs3500.animator.adapter;

import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeState;
import cs3500.animator.model.Timeline;
import cs3500.animator.provider.model.ReadOnlyEAOperations;
import cs3500.animator.provider.view.EAEditorView;
import cs3500.animator.provider.view.EAPanel;
import cs3500.animator.view.AnimationView;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Adopter class for Editor Animation
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