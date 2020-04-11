package cs3500.animator.provider.view;


import cs3500.animator.provider.model.ReadOnlyEAOperations;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Visual view for an animation. Draws and plays the animation inside of a window.
 */
public class EAVisualView extends JFrame implements IEasyAnimatorView {

  private final EAPanel panel;

  /**
   * Constructs the visual view for an animation using the given read-only model.
   *
   * @param model the model that we want to read from
   * @throws IllegalArgumentException if the given model is null
   */
  public EAVisualView(ReadOnlyEAOperations model) {
    super();

    // check for null
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null");
    }

    // set the layout
    this.setLayout(new BorderLayout());

    // add the panel, assign its size using the width and height from the model

    panel = new EAPanel(model);
    panel.setPreferredSize(new Dimension(model.getWidth(), model.getHeight()));

    // create a scroll pane for the panel, add it to the frame
    JScrollPane pane = new JScrollPane(panel);
    this.add(pane);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();

  }


  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void drawAt(int tick) {
    panel.setTick(tick);
  }

  @Override
  public String generateTextView() {
    throw new UnsupportedOperationException("Can't generate the text of a visual view");
  }

}
