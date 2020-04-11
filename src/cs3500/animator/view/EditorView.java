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
  private boolean waitingToEdit = false;

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

    JButton editKeyframe = new JButton("Edit Keyframe");
    editKeyframe.addActionListener((ActionEvent e) -> {
      timer.stop();
      JOptionPane.showMessageDialog(this,
          "Click on the shape you want edit");

      this.waitForClick();
      waitingToEdit = true;
    });
    panel.add(editKeyframe);
  }

  @Override
  public void updateTimeline() {
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
      if (waitingToEdit) {
        editKeyFrameOnClick(me.getX(), me.getY(), currentTick);
      }
      timer.start();
    }
  }

  private void waitForClick() {
    timer.stop();
    waitingForClick = true;
  }

  /**
   * Add a keyframe at the given time/position. If there is no shape at the given position, alert
   * the user.
   *
   * @param y the y coord.
   * @param t the tick.
   */
  private void addKeyframeOnClick(int x, int y, int t) {
    try {
      String shapeId = model.getShapeAtPosition(x, y, t);
      model.addKeyframe(shapeId, t);
    } catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this,
          "No shape at given position!");
    }
    waitingToAdd = false;
  }

  /**
   * Delete a keyframe at the given time/position. If there is no shape at the given position, alert
   * the user.
   *
   * @param x the x coord.
   * @param y the y coord.
   * @param t the tick.
   */
  private void removeKeyframeOnClick(int x, int y, int t) {
    try {
      String shapeId = model.getShapeAtPosition(x, y, t);
      model.deleteKeyframe(shapeId, t);
    } catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this,
          "No shape at given position!");
    }
    waitingToDelete = false;
  }

  /**
   * Get user input and modify a keyframe at the given position. If the selected frame is not a
   * keyframe, make it one.
   *
   * @param x the x coord.
   * @param y the y coord.
   * @param t the tick.
   */
  private void editKeyFrameOnClick(int x, int y, int t) {
    try {
      String shapeId = model.getShapeAtPosition(x, y, t);
      int newX = getIntegerInput("Input a new X value", 0, Integer.MAX_VALUE);
      int newY = getIntegerInput("Input a new Y value", 0, Integer.MAX_VALUE);
      int newH = getIntegerInput("Input a new height value", 0, Integer.MAX_VALUE);
      int newW = getIntegerInput("Input a new width value", 0, Integer.MAX_VALUE);
      int newR = getIntegerInput("Input a new red value", 0, 255);
      int newG = getIntegerInput("Input a new green value", 0, 255);
      int newB = getIntegerInput("Input a new blue value", 0, 255);
      model.editKeyframe(shapeId, t, newX, newY, newW, newH, newR, newG, newB);
    } catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(this,
          "No shape at given position!");
    }
    waitingToEdit = false;
  }

  private int getIntegerInput(String msg, int min, int max) {
    boolean waiting = true;
    int val = 0;
    while (waiting) {
      try {
        val = Integer.parseInt(JOptionPane.showInputDialog(msg));
        waiting = false;
      } catch (NumberFormatException e) {
        val = Integer.parseInt(
            JOptionPane
                .showInputDialog(msg + "\nInput MUST be a numeric value."));
      }
      if (val < min || val > max) {
        JOptionPane.showMessageDialog(this,
            "Value must be between " + min + " and " + max);
        waiting = true;
      }
    }
    return val;
  }
}
