package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeState;
import cs3500.animator.model.Timeline;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.Timer;


/**
 * a view for the animation program utilizing the Swing library.
 */
public class SwingAnimationView extends JFrame implements AnimationView {

  protected AnimationPanel panel;
  protected AnimationModel model;
  protected Timer timer;

  protected int currentTick;

  /**
   * constructor for a view using builder.
   */
  public SwingAnimationView(AnimationModel model) {
    this.model = model;
    currentTick = 0;
     timer = new Timer(100, e -> {
       currentTick++;
       updateTimeline(currentTick);
       repaint();
     });
    setSize(
        model.getBounds().getDimensions().getW(),
        model.getBounds().getDimensions().getH());
    setLocation(model.getBounds().getX(), model.getBounds().getY());
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.panel = new AnimationPanel(model.getBounds().getDimensions().getWidth(),
        model.getBounds().getDimensions().getHeight());
    this.add(panel);
    this.pack();
  }

  @Override
  public void render() {
    this.timer.start();
    this.setVisible(true);
  }

  private void updateTimeline(int currentTick) {
    ArrayList<ShapeState> states = new ArrayList<>();

    for (Map.Entry<String, Shape> entry : model.getElements().entrySet()) {
      var currentShapeLog = entry.getValue().getTimeline().getLog();
      for (ShapeState state : currentShapeLog) {
        if(state.getTick() == currentTick) {
          states.add(state);
        }
      }
    }
    panel.updateTimeline(new Timeline(states));
  }
}
