package cs3500.animator.model;

import java.awt.Color;
import java.util.Map;

/**
 * An interface to represent the data of an animation with a series of shape objects.
 */
public interface AnimationModel {

  /**
   * Add a Shape to the animation.
   *
   * @param s the shape to be added.
   */
  void addElement(Shape s);

  /**
   * Update the state of a Shape in the animation. The state is the position, dimension, and color
   * of the shape at a given tick.
   *
   * @param id   the id of the Shape to update.
   * @param tick the tick of the update.
   * @param p    the updated Position of the Shape.
   * @param d    the updated Dimension of the Shape.
   * @param c    the updated Color of the Shape.
   * @throws IllegalArgumentException if there is no shape with the given ID.
   */
  void updateElement(String id, int tick, Position p, Dimension d, Color c);

  /**
   * Remove the Shape with the given ID from the animation.
   *
   * @param id the id of the Shape to be removed.
   */
  void removeElement(String id);

  /**
   * Remove all Shapes from the Animation.
   */
  void removeAll();

  /**
   * Get the Shape with the given name.
   *
   * @param name the name of the Shape.
   * @return the Shape.
   */
  Shape getShape(String name);

  /**
   * Get all of the elements of the model.
   *
   * @return a Map of all the Shapes in the model.
   */
  Map<String, Shape> getElements();

  /**
   * Get the boundaries of the canvas. **Added in Assignment 6 in order to store bounding box.
   *
   * @return the boundaries of the canvas.
   */
  Bounds getBounds();

  /**
   * Set the dimensions of the bounding box. **Added in Assignment 6 in order to store bounding
   * box.
   *
   * @param x the leftmost x.
   * @param y the topmost y.
   * @param d the dimensions.
   */
  void setBounds(int x, int y, Dimension d);

  /**
   * Generate a text representation of the animation.
   *
   * @return a text representation of the animation.
   */
  String renderText();

  /**
   * Add a keyframe to the given shape at the given tick.
   *
   * @param name the name of the shape.
   * @param t    the value of the tick.
   * @throws IllegalArgumentException if there is no shape with the given ID.
   */
  void addKeyframe(String name, int t);

  /**
   * Delete the keyframe of the given shape at the given tick.
   *
   * @param name the name of the shape.
   * @param t    the value of the tick.
   * @throws IllegalArgumentException if there is no shape with the given ID.
   */
  void deleteKeyframe(String name, int t);

  /**
   * Gets the last tick value of this model.
   *
   * @return the last tick value.
   */
  int getLastTick();

  /**
   * Get the ID of the Shape at the given coordinates at the given tick.
   *
   * @param x the x coordinate.
   * @param y the y coordinate.
   * @param t the tick.
   * @return the ID of the Shape.
   * @throws IllegalArgumentException if there is no shape at the given position.
   */
  String getShapeAtPosition(int x, int y, int t);

  /**
   * Edit the given keyframe with the given parameters. If the frame is not currently a keyframe,
   * make it one.
   *
   * @param name the ID of the shape.
   * @param t the tick.
   * @param x the new x coord.
   * @param y the new y coord.
   * @param w the new width.
   * @param h the new height.
   * @param r the new red value.
   * @param g the new green value.
   * @param b the new blue value.
   */
  void editKeyframe(String name, int t, int x, int y, int w, int h, int r, int g, int b);

}
