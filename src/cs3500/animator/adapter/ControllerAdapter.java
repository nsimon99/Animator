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

  private IModelAdapter model;
  private EditorAdapter view;
  private Timer timer;
  private int currentTick;
  private boolean loop;


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
        break;
      default:
        throw new IllegalArgumentException("Can't preform action.");
    }
  }

  public void getLoop() {
    // if looping and if it's the last tick, set tick to 0.
    this.currentTick = loop && currentTick >= model.getEndingTime() ? 0 : currentTick;
    view.drawAt(currentTick);
  }


}
