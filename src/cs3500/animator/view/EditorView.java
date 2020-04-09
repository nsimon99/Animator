package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeState;
import java.awt.event.ActionEvent;
import java.util.Map;
import javax.swing.JButton;


/**
 * View that allows user to pause,start, restart, and resume animations.
 */
public class EditorView extends SwingAnimationView {

  boolean canLoop;
  /**
   * constructor for a view using builder.
   *
   * @param model given model
   */
  public EditorView(AnimationModel model) {
    super(model);
    canLoop = false;


    JButton start = new JButton("Start");
    start.addActionListener((ActionEvent e) -> {
      timer.start();
    });
    panel.add(start);


    JButton pause = new JButton("Pause");
    pause.addActionListener((ActionEvent e) -> {
      timer.stop();
    });
    panel.add(pause);

    JButton restart = new JButton("Restart");
    restart.addActionListener((ActionEvent e) -> {
      timer.stop();
      EditorView newView = new EditorView(this.model);
      newView.render();
    });
    panel.add(restart);

    JButton resume = new JButton("Resume");
    resume.addActionListener((ActionEvent e) -> {
      System.currentTimeMillis();
      timer.start();
    });
    panel.add(resume);

    JButton speedUp = new JButton("Increase Speed");
    speedUp.addActionListener((ActionEvent e) -> {
      timer.setDelay(50);
    });
    panel.add(speedUp);


    JButton decreaseSpeed = new JButton("Decrease Speed");
    decreaseSpeed.addActionListener((ActionEvent e) -> {
      timer.setDelay(200);
    });
    panel.add(decreaseSpeed);

    JButton enableLoop = new JButton("Enable Loop");
    enableLoop.addActionListener((ActionEvent e) -> {
          canLoop = true;
        });
    panel.add(enableLoop);

      JButton disableLoop = new JButton("Disable Loop");
      disableLoop.addActionListener((ActionEvent e) -> {
        canLoop = false;
    });
    panel.add(disableLoop);

//call method that loops if looping is enabled
//lastState();


  }

  @Override
  protected void updateTimeline() {
    // if looping and if it's the last tick, set tick to 0.
    this.currentTick = canLoop && currentTick >= model.getLastTick() ? 0 : currentTick;

    super.updateTimeline();
  }

  /**
   * Checks if looping is enabled and loops animation is allowed.
   */
  /*private void lastState() {
//    var timelineList = panel.getTimeLineAsList();
//    Collections.sort(timelineList);
    while (canLoop) {
      for (Map.Entry<String, Shape> entry : model.getElements().entrySet()) {
        var currentShapeLog = entry.getValue().getTimeline().getLog();
        for (ShapeState state : currentShapeLog) {
          while (state.getTick() == currentTick
              && currentShapeLog.get(currentShapeLog.size() - 1) == state) {

////    if (timelineList.get(currentTick) == timelineList.get(timelineList.size() - 1)) {
            timer.stop();
            EditorView newView = new EditorView(this.model);
            newView.render();
          }
        }
      }
    }
  }*/

}
