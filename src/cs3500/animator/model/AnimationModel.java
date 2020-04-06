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
   * @param name the name of the Shape.
   * @return the Shape.
   */
  Shape getShape(String name);

  /**
   * Get all of the elements of the model.
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


}