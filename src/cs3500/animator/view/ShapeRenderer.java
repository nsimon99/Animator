package cs3500.animator.view;

import java.awt.Graphics2D;

/**
 * an interface that represent how different shapes will be rendered in different views.
 */
public interface ShapeRenderer {

  /**
   * render the shape.
   */
  void renderShape(Graphics2D g2d);
}
