package cs3500.animator.view;

import cs3500.animator.model.ShapeState;
import java.awt.Graphics2D;

/**
 * Render a Triangle.
 */
public class TriangleRenderer implements ShapeRenderer {

  private ShapeState state;

  public TriangleRenderer(ShapeState state) {
    this.state = state;
  }

  @Override
  public void renderShape(Graphics2D shape) {
    shape.setColor(state.getColor());
    shape.fillPolygon(
        new int[]{
            (state.getPos().getX() - (state.getDim().getW() / 2)),
            state.getPos().getX(),
            (state.getPos().getX() + (state.getDim().getW() / 2))},
        new int[]{
            (state.getPos().getY() - (state.getDim().getH() / 2)),
            state.getPos().getY(),
            (state.getPos().getY() + (state.getDim().getH() / 2))},
        3
    );
  }
}
