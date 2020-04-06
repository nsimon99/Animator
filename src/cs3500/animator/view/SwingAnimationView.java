package cs3500.animator.view;

import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeState;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.Timeline;
import cs3500.animator.util.AnimationModelBuilder;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import cs3500.animator.model.AnimationModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;


/**
 * a view for the animation program utilizing the Swing library.
 */
public class SwingAnimationView extends JFrame implements AnimationView {

  private AnimationPanel panel;
  private AnimationModel model;
  private Timer timer;

  /**
   * constructor for a view using builder.
   */
  public SwingAnimationView(AnimationModel model, int tickrate) {
    this.model = model;
     timer = new Timer(1000/ 1, new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
         repaint();
       }
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
    int currentTick = 0;
    while (true) {

      ArrayList<ShapeState> states = new ArrayList<>();
      this.setVisible(true);

      for (Map.Entry<String, Shape> entry : model.getElements().entrySet()) {
        var currentShapeLog = entry.getValue().getTimeline().getLog();
        if (currentTick < currentShapeLog.size()) {
          ShapeState currentState = currentShapeLog.get(currentTick);
          if (currentState != null) {
            states.add(currentState);
          }
        }
      }
      panel.updateTimeline(new Timeline(states));




      this.timer.start();
      currentTick++;
    }
  }
}
