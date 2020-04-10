package cs3500.animator.provider.view;

import cs3500.animator.model.ShapeState;
import cs3500.animator.model.ShapeType;
import cs3500.animator.provider.model.ReadOnlyEAOperations;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

/**
 * View which allows a user to adjust playback of an animation- by starting, pausing, resuming, and
 * restarting. Also allows for editing by adding/deleting shapes, and adding/deleting/editing
 * keyframes.
 */
public class EAEditorView extends JFrame implements IEAEditorView {

  private ReadOnlyEAOperations model;

  // main components
  private EAPanel animationPanel;
  private JPanel mainPanel;

  // playback control buttons
  private JButton playPauseButton;
  private JButton restartButton;
  private JButton speedUpButton;
  private JButton slowDownButton;
  private JButton loopbackButton;
  private JButton keyCommandsButton;

  // temporary
  private JButton textViewButton;

  private JLabel speedLabel;
  private JLabel loopbackLabel;
  private JLabel playingLabel;

  private JPanel buttonsPanel;

  // pop-up
  private JPanel popupPanel;
  private JScrollPane popupScrollPane;

  // add shape
  private JButton addShapeButton;
  private JButton okAddButton;
  private JTextField shapeName;
  private List<JRadioButton> shapeTypes;

  // remove shape
  private JButton removeShapeButton;
  private JButton okRemoveButton;
  private List<JRadioButton> shapeNames;

  // add key frame
  private JButton addKeyframeButton;
  private JButton okAddKeyframeButton;
  private JButton okAddKeyframeTimeButton;
  private JTextField time;

  // remove key frame
  private JButton removeKeyframeButton;
  private JButton okRemoveKeyframeButton;
  private JButton okRemoveKeyframeTimeButton;
  private List<JRadioButton> shapeTimes;

  // edit key frame
  private JButton editKeyframeButton;
  private JButton okEditKeyframeButton;
  private JButton okEditKeyframeTimeButton;
  private JButton okEditKeyframeFinalButton;
  private JTextField width;
  private JTextField height;
  private JTextField x;
  private JTextField y;
  private JTextField r;
  private JTextField g;
  private JTextField b;

  // clear animation
  private JButton clearAnimationButton;

  // clear shape
  private JButton clearShapeButton;
  private JButton okClearShapeButton;

  // constant labels
  private static final JLabel SHAPE_NAME_LABEL = new JLabel("Name: ");
  private static final JLabel TIME_LABEL = new JLabel("Time: ");

  /**
   * Constructs the given editor view, using the given model.
   *
   * @param model the model we are given
   */
  public EAEditorView(ReadOnlyEAOperations model) {

    super();
    setTitle("The Easy Animator");

    // check for null
    if (model == null) {
      throw new IllegalArgumentException("Model can't be null");
    }
    this.model = model;

    // initialize the main panel
    constructMainPanel();
    constructAnimationPanel();
    generateLabelPanel();
    generateButtonPanel();
    constructPopup();
    createOKButtons();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
  }

  /**
   * Constructs the main panel that is used in the animation, complete with scrollbar
   * functionality.
   */
  private void constructMainPanel() {
    // initialize the main panel
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setPreferredSize(new Dimension(model.getWidth() + 50,
        model.getHeight() + 240));
    JScrollPane mainPane = new JScrollPane(mainPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.add(mainPane);

  }

  /**
   * Constructs the panel in the animation studio which shows the animation itself.
   */
  private void constructAnimationPanel() {
    animationPanel = new EAPanel(model);
    animationPanel.setLayout(new FlowLayout());
    animationPanel.setPreferredSize(new Dimension(model.getWidth(), model.getHeight()));
    mainPanel.add(animationPanel);
  }

  /**
   * Generates all of the OK buttons in the animation studio, which are used to submit user input
   * from the pop-up panels to the controller, so that the model can be appropriately updated.
   */
  private void createOKButtons() {
    okAddButton = addOKButton("Add Shape");
    okRemoveButton = addOKButton("Remove Shape");
    okAddKeyframeButton = addOKButton("Add Keyframe");
    okAddKeyframeTimeButton = addOKButton("Add Keyframe Time");
    okRemoveKeyframeButton = addOKButton("Remove Keyframe");
    okRemoveKeyframeTimeButton = addOKButton("Remove Keyframe Time");
    okEditKeyframeButton = addOKButton("Edit Keyframe");
    okEditKeyframeTimeButton = addOKButton("Edit Keyframe Time");
    okEditKeyframeFinalButton = addOKButton("Edit Keyframe Final");
    okClearShapeButton = addOKButton("Clear Shape");
  }

  /**
   * Returns a new JButton, with the text "OK", and the given action command, which is used by the
   * controller.
   *
   * @param actionCommand the desired action command of the ok button
   * @return an "OK" button with the given action command
   */
  private JButton addOKButton(String actionCommand) {
    JButton button = new JButton("OK");
    button.setActionCommand("OK " + actionCommand);
    return button;
  }


  /**
   * Generates the label panel for the animation studio, which provides the user with information
   * about the current speed of the animation, whether of not it is currently playing, and whether
   * loopback is currently enabled or disabled.
   */
  private void generateLabelPanel() {
    speedLabel = new JLabel("");
    loopbackLabel = new JLabel("");
    playingLabel = new JLabel("");

    JPanel labelPanel = new JPanel();
    labelPanel.setLayout(new FlowLayout());
    labelPanel.add(playingLabel);
    labelPanel.add(Box.createHorizontalStrut(50));
    labelPanel.add(speedLabel);
    labelPanel.add(Box.createHorizontalStrut(50));
    labelPanel.add(loopbackLabel);
    mainPanel.add(labelPanel);
  }

  /**
   * Generates the button panel for th animation studio, which provides the user with the ability to
   * control playback of the animation (ex. playing and pausing), as well as the ability to edit the
   * animation.
   */
  private void generateButtonPanel() {
    buttonsPanel = new JPanel();
    playPauseButton = addButton("Play/Pause");
    restartButton = addButton("Restart");
    speedUpButton = addButton("Speed Up");
    slowDownButton = addButton("Slow Down");
    loopbackButton = addButton("Loopback");
    keyCommandsButton = addButton("Key Commands");
    textViewButton = addButton("Animation Text");

    addShapeButton = addButton("Add Shape");
    removeShapeButton = addButton("Remove Shape");
    addKeyframeButton = addButton("Add Keyframe");
    removeKeyframeButton = addButton("Remove Keyframe");
    editKeyframeButton = addButton("Edit Keyframe");
    clearAnimationButton = addButton("Clear Animation");
    clearShapeButton = addButton("Clear Shape");
    buttonsPanel.setLayout(new FlowLayout());

    mainPanel.add(buttonsPanel);
  }


  /**
   * Adds the given button with the given name to this JFrame.
   *
   * @param name the name of the button
   */
  private JButton addButton(String name) {
    JButton button = new JButton(name);
    button.setActionCommand(name);
    buttonsPanel.add(button);
    return button;
  }

  /**
   * Constructs all of the components necessary for the pop-up windows in the animation studios.
   */
  private void constructPopup() {
    popupPanel = new JPanel();
    popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.Y_AXIS));
    popupScrollPane = new JScrollPane(popupPanel);
    popupScrollPane.setMaximumSize(new Dimension(200, 300));

    shapeName = new JTextField(15);
    time = new JTextField(5);
    shapeTypes = new ArrayList<>();
    shapeTimes = new ArrayList<>();
    shapeNames = new ArrayList<>();

    x = new JTextField(5);
    y = new JTextField(5);
    width = new JTextField(5);
    height = new JTextField(5);
    r = new JTextField(3);
    g = new JTextField(3);
    b = new JTextField(3);

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
    animationPanel.setTick(tick);
  }


  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void generateInfoPane(String message, String title) {
    JTextArea text = new JTextArea(message);
    text.setEditable(false);
    JScrollPane pane = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    pane.setPreferredSize(new Dimension(500, 300));

    JOptionPane.showMessageDialog(null, pane, title,
        JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void addButtonListeners(ActionListener listener) {
    playPauseButton.addActionListener(listener);
    restartButton.addActionListener(listener);
    speedUpButton.addActionListener(listener);
    slowDownButton.addActionListener(listener);
    loopbackButton.addActionListener(listener);
    keyCommandsButton.addActionListener(listener);
    textViewButton.addActionListener(listener);
    addShapeButton.addActionListener(listener);
    okAddButton.addActionListener(listener);
    removeShapeButton.addActionListener(listener);
    okRemoveButton.addActionListener(listener);
    addKeyframeButton.addActionListener(listener);
    okAddKeyframeButton.addActionListener(listener);
    okAddKeyframeTimeButton.addActionListener(listener);
    removeKeyframeButton.addActionListener(listener);
    okRemoveKeyframeButton.addActionListener(listener);
    okRemoveKeyframeTimeButton.addActionListener(listener);
    editKeyframeButton.addActionListener(listener);
    okEditKeyframeButton.addActionListener(listener);
    okEditKeyframeTimeButton.addActionListener(listener);
    okEditKeyframeFinalButton.addActionListener(listener);
    clearAnimationButton.addActionListener(listener);
    clearShapeButton.addActionListener(listener);
    okClearShapeButton.addActionListener(listener);
  }


  @Override
  public void setPlayingLabel(boolean playing) {
    if (playing) {
      playingLabel.setText("Playing");
    } else {
      playingLabel.setText("Paused");
    }
    this.refresh();
  }

  @Override
  public void setSpeedLabel(int speed) {
    speedLabel.setText("Speed: " + speed + " ticks/sec");
    this.refresh();
  }

  @Override
  public void setLoopbackLabel(boolean loopback) {
    if (loopback) {
      loopbackLabel.setText("Looping Enabled");
    } else {
      loopbackLabel.setText("Looping Disabled");
    }
    this.refresh();
  }


  @Override
  public void generateAddShapePopup() {

    clearPopup();
    ButtonGroup bg = new ButtonGroup();

    // add the name text field
    popupPanel.add(SHAPE_NAME_LABEL);
    popupPanel.add(shapeName);

    for (ShapeType st : ShapeType.values()) {
      JRadioButton jb = new JRadioButton(st.toString());
      popupPanel.add(jb);
      bg.add(jb);
      shapeTypes.add(jb);

    }
    popupPanel.add(okAddButton);
    popupScrollPane.setPreferredSize(new Dimension(250, 150));

    // show the new window
    JOptionPane.showOptionDialog(this, popupScrollPane, "Add Shape",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
        new Object[]{}, null);
  }


  @Override
  public void generateRemoveShapePopup() {

    clearPopup();
    ButtonGroup bg = new ButtonGroup();

    if (model.getIds().size() == 0) {
      error("No shapes in the animation", "Can't remove shape");
      return;
    }

    popupPanel.add(SHAPE_NAME_LABEL);
    for (String name : model.getIds()) {
      JRadioButton jb = new JRadioButton(name);
      bg.add(jb);
      shapeNames.add(jb);
      popupPanel.add(jb);
    }
    popupPanel.add(okRemoveButton);

    popupScrollPane.setPreferredSize(new Dimension(250, 300));

    JOptionPane.showOptionDialog(this, popupScrollPane, "Remove Shape",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
        new Object[]{}, null);
  }


  @Override
  public void generateAddKeyframePopup1() {

    clearPopup();

    addShapeNameButtons();

    popupPanel.add(Box.createHorizontalStrut(30));
    popupPanel.add(okAddKeyframeButton);

    popupScrollPane.setPreferredSize(new Dimension(250, 300));

    JOptionPane.showOptionDialog(this, popupScrollPane, "Add Keyframe",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
        new Object[]{}, null);
  }

  @Override
  public void generateAddKeyframePopup2() {

    clearPopup();

    popupPanel.add(TIME_LABEL);
    popupPanel.add(time);

    popupPanel.add(okAddKeyframeTimeButton);

    popupScrollPane.setPreferredSize(new Dimension(250, 100));

    JOptionPane.showOptionDialog(this, popupScrollPane, "Add Keyframe",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
        new Object[]{}, null);
  }


  @Override
  public void generateRemoveKeyframePopup1() {

    clearPopup();
    addShapeNameButtons();

    popupPanel.add(Box.createHorizontalStrut(30));
    popupPanel.add(okRemoveKeyframeButton);

    popupScrollPane.setPreferredSize(new Dimension(250, 300));

    JOptionPane.showOptionDialog(this, popupScrollPane, "Remove Keyframe",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
        new Object[]{}, null);

  }

  @Override
  public void generateRemoveKeyFramePopup2(String name) {

    clearPopup();
    addTimeButtons(name);

    popupPanel.add(okRemoveKeyframeTimeButton);
    popupScrollPane.setPreferredSize(new Dimension(250, 300));

    JOptionPane.showOptionDialog(this, popupScrollPane, "Remove Keyframe",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
        new Object[]{}, null);

  }

  @Override
  public void generateEditKeyframePopup1() {
    clearPopup();

    addShapeNameButtons();

    popupPanel.add(Box.createHorizontalStrut(30));
    popupPanel.add(okEditKeyframeButton);

    popupScrollPane.setPreferredSize(new Dimension(250, 300));

    JOptionPane.showOptionDialog(this, popupScrollPane, "Edit Keyframe",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
        new Object[]{}, null);

  }

  @Override
  public void generateEditKeyframePopup2(String name) {
    clearPopup();

    addTimeButtons(name);

    popupPanel.add(Box.createHorizontalStrut(30));
    popupPanel.add(okEditKeyframeTimeButton);

    popupScrollPane.setPreferredSize(new Dimension(250, 300));

    JOptionPane.showOptionDialog(this, popupScrollPane, "Edit Keyframe",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
        new Object[]{}, null);

  }

  @Override
  public void generateEditKeyframePopup3(String name, int time) {
    clearPopup();
    ShapeState ss = model.getShapeStatesFromId(name).get(time);

    x.setText(Integer.toString(ss.getX()));
    y.setText(Integer.toString(ss.getY()));
    width.setText(Integer.toString(ss.getWidth()));
    height.setText(Integer.toString(ss.getHeight()));
    r.setText(Integer.toString(ss.getRed()));
    g.setText(Integer.toString(ss.getGreen()));
    b.setText(Integer.toString(ss.getBlue()));

    popupPanel.add(new JLabel("X: "));
    popupPanel.add(x);
    popupPanel.add(new JLabel("Y: "));
    popupPanel.add(y);
    popupPanel.add(new JLabel("Width: "));
    popupPanel.add(width);
    popupPanel.add(new JLabel("Height: "));
    popupPanel.add(height);
    popupPanel.add(new JLabel("R: "));
    popupPanel.add(r);
    popupPanel.add(new JLabel("G: "));
    popupPanel.add(g);
    popupPanel.add(new JLabel("B: "));
    popupPanel.add(b);

    popupPanel.add(okEditKeyframeFinalButton);

    popupScrollPane.setPreferredSize(new Dimension(250, 400));

    JOptionPane.showOptionDialog(this, popupScrollPane, "Edit Keyframe",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
        new Object[]{}, null);

  }

  @Override
  public void generateClearShapePopup() {

    clearPopup();
    addShapeNameButtons();

    popupPanel.add(okClearShapeButton);

    popupScrollPane.setPreferredSize(new Dimension(250, 400));

    JOptionPane.showOptionDialog(this, popupScrollPane, "Clear Shape",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
        new Object[]{}, null);

  }

  @Override
  public List<String> getShapeStateTextFields() {

    List<String> textFields = new ArrayList<>();
    textFields.add(x.getText());
    textFields.add(y.getText());
    textFields.add(width.getText());
    textFields.add(height.getText());
    textFields.add(r.getText());
    textFields.add(g.getText());
    textFields.add(b.getText());

    return textFields;
  }

  @Override
  public String getShapeName() {
    return shapeName.getText();
  }

  @Override
  public String getShapeType() {
    return getSelected(shapeTypes);
  }

  @Override
  public String getShapeNameFromButtons() {
    return getSelected(shapeNames);
  }

  @Override
  public String getTimeFromText() {
    return time.getText();
  }

  @Override
  public String getTimeFromButtons() {
    return getSelected(shapeTimes);

  }

  @Override
  public void closePopup() {
    // exit the window
    Window w = SwingUtilities.getWindowAncestor(popupScrollPane);
    if (w != null) {
      w.setVisible(false);
    }
    this.refresh();
  }

  @Override
  public void error(String message, String title) {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
  }


  @Override
  public String generateTextView() {
    throw new UnsupportedOperationException("Can't generate text for an editor view");
  }

  /**
   * Given a list of JRadioButtons, will correctly output the text of the button in the list that
   * has been selected, or the empty string if no button has been selected.
   *
   * @param buttons the list of buttons we are reading from
   * @return the text of the button that is selected, or the empty string if nothing is selected
   */
  private String getSelected(List<JRadioButton> buttons) {
    for (JRadioButton button : buttons) {
      if (button.isSelected()) {
        return button.getText();
      }
    }
    return "";
  }

  /**
   * Adds the names of all of the shapes in the animation as radio buttons to the pop-up panel.
   */
  private void addShapeNameButtons() {
    popupPanel.add(SHAPE_NAME_LABEL);
    ButtonGroup bg = new ButtonGroup();
    for (String name : model.getIds()) {
      JRadioButton jb = new JRadioButton(name);
      bg.add(jb);
      shapeNames.add(jb);
      popupPanel.add(jb);
    }

  }

  /**
   * Adds all of the times of keyframes of the shape with the given name as radio buttons to the
   * pop-up panel.
   *
   * @param shapeName the name of the shape that the user is trying to edit
   */
  private void addTimeButtons(String shapeName) {
    popupPanel.add(TIME_LABEL);
    ButtonGroup bg = new ButtonGroup();
    for (Integer i : model.getShapeStatesFromId(shapeName).keySet()) {
      JRadioButton jb = new JRadioButton(i.toString());
      popupPanel.add(jb);
      bg.add(jb);
      shapeTimes.add(jb);
    }
  }


  /**
   * Clears all fields of the pop-up window.
   */
  private void clearPopup() {
    popupPanel.removeAll();
    shapeName.setText("");
    shapeTypes.clear();
    time.setText("");
    shapeTimes.clear();
    shapeNames.clear();
  }
}
