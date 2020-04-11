package cs3500.animator.provider.view;

import cs3500.animator.provider.model.ReadOnlyEAOperations;
import cs3500.animator.provider.model.ShapeState;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;

/**
 * Represents a panel that is used to draw the animation. Draws the state of the animation at a
 * specific time.
 */
public class EAPanel extends JPanel {

  private final ReadOnlyEAOperations model;
  private int tick;

  /**
   * Constructs an EasyAnimator panel using the given read-only model.
   *
   * @param model the model we are reading from
   * @throws IllegalArgumentException if the given model is null
   */
  public EAPanel(ReadOnlyEAOperations model) {
    super();
    // check for null, then assign the model.
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null");
    }
    this.model = model;
    // start at tick 0
    this.tick = 0;

  }

  /**
   * Sets the tick of this panel to the given new tick.
   *
   * @param newTick the new tick we want the panel to have
   */
  public void setTick(int newTick) {
    tick = newTick;
  }


  @Override
  protected void paintComponent(Graphics g) {
    // check for null
    if (g == null) {
      throw new IllegalArgumentException("Graphics can't be null");
    }

    // always do this!!!
    super.paintComponent(g);

    // we want 2D graphics, so...
    Graphics2D g2d = (Graphics2D) g;

    // clip the drawing area to the given width and height
    g2d.setClip(0, 0, model.getWidth(), model.getHeight());

    // now we paint the thing...
    List<ShapeState> states = model.getShapeStatesAtTime(tick);


    // loop through all states
    for (ShapeState ss : states) {

      // set the color
      g2d.setColor(new Color(ss.getRed(), ss.getGreen(), ss.getBlue()));

      // draw each shape, with positions offset by the model's top+left of the canvas
      switch (ss.getType()) {
        case RECTANGLE:
          g2d.fillRect(ss.getX() - model.getLeft(),
              (ss.getY()) - model.getTop(), ss.getWidth(), ss.getHeight());
          break;
        case ELLIPSE:
          g2d.fillOval(ss.getX() - model.getLeft(), ss.getY() - model.getTop(),
              ss.getWidth(), ss.getHeight());
          break;
        default:
          return;
      }
    }
  }
}


