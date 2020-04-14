package cs3500.animator.adapter;

import cs3500.animator.provider.controller.IEasyAnimatorController;
import cs3500.animator.view.AnimationView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Controller that connects new view and model.
 */
public class ControllerAdapter implements IEasyAnimatorController {

  private ModelAdapter model;
  private EditorAdapter view;
  private Timer timer;
  private int currentTick;
  private boolean loop;

  private String shapeName = "";


  /**
   * Constructor for the controller.
   *
   * @param model given model with information
   * @param view  type of view for user
   */
  public ControllerAdapter(ModelAdapter model, EditorAdapter view) {
    this.model = model;
    this.view = view;
    currentTick = 0;
    this.timer = new Timer(100, e -> {
      currentTick++;
      view.drawAt(currentTick);
      getLoop();
      view.refresh();
      view.repaint();

    });
    loop = true;

  }


  @Override
  public void animate() {
    timer.start();
    view.render();

    ActionListener listener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        getAction(e);
      }
    };
    view.addButtonListeners(listener);


  }


  /**
   * assigns action command to whatever button is being pressed.
   *
   * @param e Action event to be performed.
   */
  private void getAction(ActionEvent e) {

    switch (e.getActionCommand()) {
      case "Play/Pause":
        if (timer.isRunning()) {
          timer.stop();
        } else {
          timer.start();
        }
        break;
      case "Restart":
        currentTick = 0;
        view.drawAt(currentTick);
        break;
      case "Speed Up":
        timer.setDelay(50);
        break;
      case "Slow Down":
        timer.setDelay(200);
        break;
      case "Loopback":
        if (loop) {
          loop = false;
        } else {
          loop = true;
        }
      case "Add Keyframe":
        view.generateAddKeyframePopup1();
        view.generateAddKeyframePopup2();
        break;
      case "Remove Keyframe":
        view.generateRemoveKeyframePopup1();
        view.generateRemoveKeyFramePopup2(view.getShapeNameFromButtons());
        break;
      case "Edit Keyframe":
        view.generateEditKeyframePopup1();
        view.generateEditKeyframePopup2(view.getShapeNameFromButtons());
        view.generateEditKeyframePopup3(view.getShapeNameFromButtons(), Integer.parseInt(
            view.getTimeFromText()
        ));
        break;
      case "OK Remove Keyframe":
      case "OK Edit Keyframe":
      case "OK Add Keyframe":
        shapeName = view.getShapeNameFromButtons();
        view.closePopup();
        break;
      case "OK Add Keyframe Time":
        int time = Integer.parseInt(view.getTimeFromText());
        view.closePopup();
        model.addKeyframe(shapeName, time);
        break;
      case "OK Edit Keyframe Time":
        int editTime = Integer.parseInt(view.getTimeFromButtons());
        view.closePopup();
        break;
      case "OK Remove Keyframe Time":
        int removeTime = Integer.parseInt(view.getTimeFromButtons());
        view.closePopup();
        model.deleteKeyframe(shapeName, removeTime);
        break;

      default:
        System.out.println(e.getActionCommand());
        throw new IllegalArgumentException("Can't perform action.");
    }
  }


  /**
   * if looping and if it's the last tick, set tick to 0 and restart animation
   */
  private void getLoop() {
    this.currentTick = loop && currentTick >= model.getEndingTime() ? 0 : currentTick;
    view.drawAt(currentTick);
  }


}
