package cs3500.animator.provider.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * Interface that contains all functionality for the editor view for the EasyAnimator animation
 * studio. Contains methods that can generate all required visual components, which are called by
 * the controller at the correct times.
 */
public interface IEAEditorView extends IEasyAnimatorView {


  /**
   * Resets the focus of the program, so that the keyboard works.
   */
  void resetFocus();

  /**
   * Generates an info pane withe given message.
   *
   * @param message the message we want on the info pane
   * @param title   the title we want to info pane to have
   */
  void generateInfoPane(String message, String title);

  /**
   * Adds the given key listener to this view.
   *
   * @param kl the key listener you want to control the view
   */
  void addKeyListener(KeyListener kl);

  /**
   * Adds the given action listener as a listener for all of the buttons in the animation studio.
   *
   * @param listener the listener that will
   */
  void addButtonListeners(ActionListener listener);

  /**
   * Sets the playing label of the animation studio to playing if the given boolean is true, paused
   * otherwise.
   *
   * @param playing represents whether or not the animation is currently playing
   */
  void setPlayingLabel(boolean playing);

  /**
   * Sets the speed label of the animation studio to the given speed value.
   *
   * @param speed the speed value we want to set the label to
   */
  void setSpeedLabel(int speed);

  /**
   * Sets the loopback label of the animation studio to "loopback enabled" if the given boolean is
   * true, "loopback disabled" otherwise.
   *
   * @param loopback represents whether or not loopback for the animation is enabled.
   */
  void setLoopbackLabel(boolean loopback);

  /**
   * Generates an add shape pop-up for the animation studio, which allows a user to input the
   * parameters for adding a new shape.
   */
  void generateAddShapePopup();

  /**
   * Generates the pop-up window for removing a shape in the animation.
   */
  void generateRemoveShapePopup();

  /**
   * Generates the first pop-up window for a user to add a keyframe to an animation. Shows all of
   * the shapes in the animation to add to as radio buttons in the pop-up window.
   */
  void generateAddKeyframePopup1();

  /**
   * Generates the second pop-up window for a user to add a keyframe to an animation. Shows a text
   * field which allows the user to input the time they want to add a keyframe at.
   */
  void generateAddKeyframePopup2();

  /**
   * Generates the first pop-up window for removing a keyframe, which contains all of the shapes in
   * the animation that a user can remove a keyframe from.
   */
  void generateRemoveKeyframePopup1();

  /**
   * Generates the second pop-up window for removing a keyframe, which contains all of the times of
   * keyframes that can be removed from the shape with the given name.
   *
   * @param name the name of the shape that the user selected in the first pop-up
   */
  void generateRemoveKeyFramePopup2(String name);

  /**
   * Generates the correct first pop-up window for editing a keyframe, which has all of the shapes
   * in the animation that the user can choose to edit.
   */
  void generateEditKeyframePopup1();

  /**
   * Generates the correct second pop-up window for editing a keyframe, which has all of the correct
   * times of keyframes to choose from using the given shape name.
   *
   * @param name the name of the shape that the user selected in the prior pop-up.
   */
  void generateEditKeyframePopup2(String name);

  /**
   * Generates the correct third pop-up window for editing a key frame. Shows 7 text boxes for
   * editing all of the components of a keyframe.
   *
   * @param name the name of the shape the user is editing
   * @param time the time of the keyframe that the user is editing
   */
  void generateEditKeyframePopup3(String name, int time);

  /**
   * Generates the pop-up window for when a user wants to clear a shape.
   */
  void generateClearShapePopup();

  /**
   * Gets all of the input text which is used to edit a shape state.
   *
   * @return the shape state text fields, represented as a list
   */
  List<String> getShapeStateTextFields();

  /**
   * Gets the name of the shape that the user is adding.
   *
   * @return the name of the shape the user is adding
   */
  String getShapeName();

  /**
   * Gets the shape type of the shape that the user is adding.
   *
   * @return the type of the shape the user is adding
   */
  String getShapeType();

  /**
   * Gets the selected shape name from the list of shape name buttons.
   *
   * @return the shape name the user has selected
   */
  String getShapeNameFromButtons();

  /**
   * Gets the time that has been typed into the time text field, as a String.
   *
   * @return the time that has been typed into the time text field
   */
  String getTimeFromText();

  /**
   * Gets the correct time button that was selected by the user, or the empty string if no button
   * was selected.
   *
   * @return the time that was clicked in by the user
   */
  String getTimeFromButtons();

  /**
   * Closes the add shape popup of the animation studio..
   */
  void closePopup();

  /**
   * Shows the error message with the given message and title- used if the user input invalid
   * arguments.
   *
   * @param message message of the error pane
   * @param title   title of the error pane
   */
  void error(String message, String title);

}
