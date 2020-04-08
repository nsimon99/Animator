package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import java.awt.event.ActionEvent;
import java.util.Collections;
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
    canLoop = true;

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
          lastState();
        });
    panel.add(enableLoop);

      JButton disableLoop = new JButton("Disable Loop");
      disableLoop.addActionListener((ActionEvent e) -> {
        canLoop = false;
    });
    panel.add(disableLoop);




  }
  public void lastState() {
    var timelineList = panel.getTimeLineAsList();
    Collections.sort(timelineList);
    if (timelineList.get(currentTick) == timelineList.get(timelineList.size() - 1)) {
      timer.stop();
      EditorView newView = new EditorView(this.model);
      newView.render();
    }

  }
}
