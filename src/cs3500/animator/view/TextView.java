package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import java.io.IOException;

/**
 * A text based view for the Animator. Renders the timeline of the animation as text.
 */
public class TextView implements AnimationView {

  private final Appendable out;
  private AnimationModel model;

  /**
   * Construct a new TextView.
   *
   * @param model the AnimationModel to render.
   * @param out   the output destination.
   * @throws IllegalArgumentException if either the AnimationModel or Appendable is null.
   */
  public TextView(AnimationModel model, Appendable out) {
    if (model == null || out == null) {
      throw new IllegalArgumentException("Model and Appendable can't be null");
    }
    this.out = out;
    this.model = model;
  }

  @Override
  public void render() {
    try {
      out.append(model.renderText());
    } catch (IOException e) {
      throw new IllegalStateException("Failed to append", e);
    }
  }
}
