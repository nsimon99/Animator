package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;


/**
 * View that allows user to pause,start, restart, and resume animations.
 */
public class EditorView extends SwingAnimationView {

  boolean canLoop;

  private boolean waitingForClick = false;
  private boolean waitingToAdd = false;
  private boolean waitingToDelete = false;

  /**
   * constructor for a view using builder.
   *
   * @param model given model
   */
  public EditorView(AnimationModel model) {
    super(model);
    addMouseListener(new AnimatorMouseListener(this));

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
      currentTick = 0;
      super.updateTimeline();
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

    JButton addKeyframe = new JButton("Add Keyframe");
    addKeyframe.addActionListener((ActionEvent e) -> {
      timer.stop();
      JOptionPane.showMessageDialog(this,
          "Click on the shape you want to add a keyframe to");

      this.waitForClick();
      waitingToAdd = true;
    });
    panel.add(addKeyframe);

    JButton removeKeyframe = new JButton("Remove Keyframe");
    removeKeyframe.addActionListener((ActionEvent e) -> {
      timer.stop();
      JOptionPane.showMessageDialog(this,
          "Click on the shape you want to remove a keyframe from");

      this.waitForClick();
      waitingToDelete = true;
    });
    panel.add(removeKeyframe);
  }

  @Override
  protected void updateTimeline() {
    // if looping and if it's the last tick, set tick to 0.
    this.currentTick = canLoop && currentTick >= model.getLastTick() ? 0 : currentTick;

    super.updateTimeline();
  }

  protected void onClick(MouseEvent me) {
    if (waitingForClick) {
      this.waitingForClick = false;

      if (waitingToAdd) {
        addKeyframeOnClick(me.getX(), me.getY(), currentTick);
      }
      if (waitingToDelete) {
        removeKeyframeOnClick(me.getX(), me.getY(), currentTick);
      }
      timer.start();
    }
  }

  private void waitForClick() {
    timer.stop();
    waitingForClick = true;
  }

  private void addKeyframeOnClick(int x, int y, int t) {
    try {
      String shapeId = model.getShapeAtPosition(x, y, t);
      model.addKeyframe(shapeId, t);
    }
    catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this,
          "No shape at given position!");
    }
  }

  private void removeKeyframeOnClick(int x, int y, int t) {
    try {
      String shapeId = model.getShapeAtPosition(x, y, t);
      model.deleteKeyframe(shapeId, t);
    }
    catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this,
          "No shape at given position!");
    }
  }
}
