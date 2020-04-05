package cs3500.animator.view;

import cs3500.animator.model.ShapeState;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.Timeline;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import javax.swing.JPanel;

/**
 * a custom JPanel that updates as shapestate changes.
 */
public class AnimationPanel extends JPanel {

  private Timeline timeline;
  private final Map<ShapeType, Function<ShapeState, ShapeRenderer>> renderers;

  /**
   * consturcts a animation panel.
   */
  public AnimationPanel() {
    this.timeline = new Timeline(new ArrayList<>());
    this.renderers = Map.of(
        ShapeType.OVAL, OvalRenderer::new,
        ShapeType.RECTANGLE, RectangleRenderer::new,
        ShapeType.TRIANGLE, TriangleRenderer::new);
  }

  /**
   * update the panel to any changes.
   * @param timeline the new changed timeline.
   */
  public void updateTimeline(Timeline timeline) {
    this.timeline = new Timeline(timeline);
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D g2d = (Graphics2D) graphics;
    ArrayList<ShapeState> shapes = timeline.getLog();
    Collections.sort(shapes);

    for (ShapeState state : shapes) {
      if (state != null) {
        Function<ShapeState, ShapeRenderer> rendererFunction = renderers.get(state.getType());
        ShapeRenderer renderer = rendererFunction.apply(state);
        renderer.renderShape(g2d);
      }
    }
  }
}
