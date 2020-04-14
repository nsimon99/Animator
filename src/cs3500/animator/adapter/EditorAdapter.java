package cs3500.animator.adapter;

import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeState;
import cs3500.animator.model.Timeline;
import cs3500.animator.provider.model.ReadOnlyEAOperations;
import cs3500.animator.provider.view.EAEditorView;
import cs3500.animator.provider.view.EAPanel;
import cs3500.animator.view.AnimationView;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * Adopter class for Editor Animation
 */
public class EditorAdapter extends EAEditorView implements AnimationView {

  private int tickrate;


  /**
   * Constructs the given editor view, using the given model.
   *
   * @param model the model we are given
   */
  public EditorAdapter(ReadOnlyEAOperations model, int t) {
    super(model);
    tickrate = t;


    // add the panel, assign its size using the width and height from the model

  }

  @Override
  public void render() {
    this.drawAt(tickrate);
    this.refresh();
    this.makeVisible();
  }


//  AnimationPanel panel;
//  protected EditorView view;
//  AnimationModel model;
//  private JButton playPauseButton;
//  private JButton restartButton;
//  private JButton speedUpButton;
//  private JButton slowDownButton;
//  private JButton loopbackButton;
//  private JButton keyCommandsButton;
//
//  // temporary
//  private JButton textViewButton;
//
//  private JLabel speedLabel;
//  private JLabel loopbackLabel;
//  private JLabel playingLabel;
//
//  private JPanel buttonsPanel;
//
//  // pop-up
//  private JPanel popupPanel;
//  private JScrollPane popupScrollPane;
//
//  // add shape
//  private JButton addShapeButton;
//  private JButton okAddButton;
//  private JTextField shapeName;
//  private List<JRadioButton> shapeTypes;
//
//  // remove shape
//  private JButton removeShapeButton;
//  private JButton okRemoveButton;
//  private List<JRadioButton> shapeNames;
//
//  // add key frame
//  private JButton addKeyframeButton;
//  private JButton okAddKeyframeButton;
//  private JButton okAddKeyframeTimeButton;
//  private JTextField time;
//
//  // remove key frame
//  private JButton removeKeyframeButton;
//  private JButton okRemoveKeyframeButton;
//  private JButton okRemoveKeyframeTimeButton;
//  private List<JRadioButton> shapeTimes;
//
//  // edit key frame
//  private JButton editKeyframeButton;
//  private JButton okEditKeyframeButton;
//  private JButton okEditKeyframeTimeButton;
//  private JButton okEditKeyframeFinalButton;
//  private JTextField width;
//  private JTextField height;
//  private JTextField x;
//  private JTextField y;
//  private JTextField r;
//  private JTextField g;
//  private JTextField b;
//
//  // clear animation
//  private JButton clearAnimationButton;
//
//  // clear shape
//  private JButton clearShapeButton;
//  private JButton okClearShapeButton;
//
//  /**
//   * Constructs the given editor view, using the given model.
//   *
//   * @param model the model we are given
//   */
//  public EditorAdapter(AnimationModel model,  EditorView view, AnimationPanel panel) {
//    this.model = model;
//    this.view = view;
//    this.panel = panel;
//  }
//
//  @Override
//  public void makeVisible() {
//    view.render();
//  }
//
//  @Override
//  public void drawAt(int tick) {
//
//
//  }
//
//  @Override
//  public String generateTextView() {
//    throw new UnsupportedOperationException("Can't generate text for an editor view");
//  }
//
//  @Override
//  public void refresh() {
//    view.updateTimeline();
//  }
//
//  @Override
//  public void resetFocus() {
//
//
//  }
//
//  @Override
//  public void generateInfoPane(String message, String title) {
//    JTextArea text = new JTextArea(message);
//    text.setEditable(false);
//    JScrollPane pane = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//    pane.setPreferredSize(new Dimension(500, 300));
//
//    JOptionPane.showMessageDialog(null, pane, title,
//        JOptionPane.INFORMATION_MESSAGE);
//
//  }
//
//  @Override
//  public void addKeyListener(KeyListener kl) {
//
//
//  }
//
//  @Override
//  public void addButtonListeners(ActionListener listener) {
//    playPauseButton.addActionListener(listener);
//    restartButton.addActionListener(listener);
//    speedUpButton.addActionListener(listener);
//    slowDownButton.addActionListener(listener);
//    loopbackButton.addActionListener(listener);
//    keyCommandsButton.addActionListener(listener);
//    textViewButton.addActionListener(listener);
//    addShapeButton.addActionListener(listener);
//    okAddButton.addActionListener(listener);
//    removeShapeButton.addActionListener(listener);
//    okRemoveButton.addActionListener(listener);
//    addKeyframeButton.addActionListener(listener);
//    okAddKeyframeButton.addActionListener(listener);
//    okAddKeyframeTimeButton.addActionListener(listener);
//    removeKeyframeButton.addActionListener(listener);
//    okRemoveKeyframeButton.addActionListener(listener);
//    okRemoveKeyframeTimeButton.addActionListener(listener);
//    editKeyframeButton.addActionListener(listener);
//    okEditKeyframeButton.addActionListener(listener);
//    okEditKeyframeTimeButton.addActionListener(listener);
//    okEditKeyframeFinalButton.addActionListener(listener);
//    clearAnimationButton.addActionListener(listener);
//    clearShapeButton.addActionListener(listener);
//    okClearShapeButton.addActionListener(listener);
//  }
//
//  @Override
//  public void setPlayingLabel(boolean playing) {
//
//  }
//
//  @Override
//  public void setSpeedLabel(int speed) {
//
//  }
//
//  @Override
//  public void setLoopbackLabel(boolean loopback) {
//
//  }
//
//  @Override
//  public void generateAddShapePopup() {
//
//  }
//
//  @Override
//  public void generateRemoveShapePopup() {
//
//  }
//
//  @Override
//  public void generateAddKeyframePopup1() {
//
//  }
//
//  @Override
//  public void generateAddKeyframePopup2() {
//
//  }
//
//  @Override
//  public void generateRemoveKeyframePopup1() {
//
//  }
//
//  @Override
//  public void generateRemoveKeyFramePopup2(String name) {
//
//  }
//
//  @Override
//  public void generateEditKeyframePopup1() {
//
//  }
//
//  @Override
//  public void generateEditKeyframePopup2(String name) {
//
//  }
//
//  @Override
//  public void generateEditKeyframePopup3(String name, int time) {
//
//  }
//
//  @Override
//  public void generateClearShapePopup() {
//
//  }
//
//  @Override
//  public List<String> getShapeStateTextFields() {
//    return null;
//  }
//
//  @Override
//  public String getShapeName() {
//    return null;
//  }
//
//  @Override
//  public String getShapeType() {
//    return null;
//  }
//
//  @Override
//  public String getShapeNameFromButtons() {
//    return null;
//  }
//
//  @Override
//  public String getTimeFromText() {
//    return null;
//  }
//
//  @Override
//  public String getTimeFromButtons() {
//    return null;
//  }
//
//  @Override
//  public void closePopup() {
//
//  }
//
//  @Override
//  public void error(String message, String title) {
//
//  }
}