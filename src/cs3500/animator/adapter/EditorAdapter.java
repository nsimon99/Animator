package cs3500.animator.adapter;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.provider.view.IEAEditorView;
import cs3500.animator.view.AnimationPanel;
import cs3500.animator.view.EditorView;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * Adopter class for Editor Animation
 */
public class EditorAdapter implements IEAEditorView {
  AnimationPanel panel;
  protected EditorView view;
  AnimationModel model;

  /**
   * Constructs the given editor view, using the given model.
   *
   * @param model the model we are given
   */
  public EditorAdapter(AnimationModel model,  EditorView view, AnimationPanel panel) {
    this.model = model;
    this.view = view;
    this.panel = panel;
  }

  @Override
  public void makeVisible() {
    view.render();
  }

  @Override
  public void drawAt(int tick) {


  }

  @Override
  public String generateTextView() {
    throw new UnsupportedOperationException("Can't generate text for an editor view");
  }

  @Override
  public void refresh() {
    view.updateTimeline();
  }

  @Override
  public void resetFocus() {

  }

  @Override
  public void generateInfoPane(String message, String title) {

  }

  @Override
  public void addKeyListener(KeyListener kl) {

  }

  @Override
  public void addButtonListeners(ActionListener listener) {

  }

  @Override
  public void setPlayingLabel(boolean playing) {

  }

  @Override
  public void setSpeedLabel(int speed) {

  }

  @Override
  public void setLoopbackLabel(boolean loopback) {

  }

  @Override
  public void generateAddShapePopup() {

  }

  @Override
  public void generateRemoveShapePopup() {

  }

  @Override
  public void generateAddKeyframePopup1() {

  }

  @Override
  public void generateAddKeyframePopup2() {

  }

  @Override
  public void generateRemoveKeyframePopup1() {

  }

  @Override
  public void generateRemoveKeyFramePopup2(String name) {

  }

  @Override
  public void generateEditKeyframePopup1() {

  }

  @Override
  public void generateEditKeyframePopup2(String name) {

  }

  @Override
  public void generateEditKeyframePopup3(String name, int time) {

  }

  @Override
  public void generateClearShapePopup() {

  }

  @Override
  public List<String> getShapeStateTextFields() {
    return null;
  }

  @Override
  public String getShapeName() {
    return null;
  }

  @Override
  public String getShapeType() {
    return null;
  }

  @Override
  public String getShapeNameFromButtons() {
    return null;
  }

  @Override
  public String getTimeFromText() {
    return null;
  }

  @Override
  public String getTimeFromButtons() {
    return null;
  }

  @Override
  public void closePopup() {

  }

  @Override
  public void error(String message, String title) {

  }
}
