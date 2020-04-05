package cs3500.animator.view;

import java.awt.Graphics2D;
import cs3500.animator.model.ShapeState;

/**
 * Renders an Oval.
 */
public class OvalRenderer implements ShapeRenderer {

  private ShapeState state;

  public OvalRenderer(ShapeState state) {
    this.state = state;
  }

  @Override
  public void renderShape(Graphics2D shape) {
    shape.setColor(state.getColor());
    shape.fillOval(
        state.getPos().getX(), state.getPos().getY(),
        state.getDim().getW(), state.getDim().getH());
  }
}
