package cs3500.animator.view;

import cs3500.animator.model.ShapeState;
import java.awt.Graphics2D;

/**
 * Renders a Rectangle.
 */
public class RectangleRenderer implements ShapeRenderer {

  private ShapeState state;

  public RectangleRenderer(ShapeState state) {
    this.state = state;
  }

  @Override
  public void renderShape(Graphics2D shape) {
    shape.setColor(state.getColor());
    shape.fillRect(
        state.getPos().getX(), state.getPos().getY(),
        state.getDim().getH(), state.getDim().getW());
  }
}
