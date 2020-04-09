package cs3500.animator.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AnimatorMouseListener extends MouseAdapter {
  private final EditorView view;

  public AnimatorMouseListener(EditorView view) {
    super();
    this.view = view;
  }
  @Override
  public void mouseClicked(MouseEvent me) {
    view.onClick(me);
  }


}
