package cs3500.animator.provider.model;

import java.util.List;
import java.util.NavigableMap;

/**
 * Interface that allows a user to read information from the animation model. However, does not
 * allow for the model to be mutated. This will likely be useful when constructing the view.
 */
public interface ReadOnlyEAOperations {

  /**
   * Gets the width of the animation.
   *
   * @return the width of the animation
   */
  int getWidth();

  /**
   * Gets the height of the animation.
   *
   * @return the height of the animation
   */
  int getHeight();

  /**
   * Gets the top of the animation.
   *
   * @return the top of the animation
   */
  int getTop();

  /**
   * Gets the left of the animation.
   *
   * @return the left of the animation
   */
  int getLeft();

  /**
   * Returns the final time of the final animation in the model, or 0 of there are no shapes in the
   * animation.
   *
   * @return - the final time of the final animation of the model
   */
  int getEndingTime();

  /**
   * Gets a list of all of the shape states at the given time- used to represent everything that
   * will be presented on the screen at the specific time in the animation.
   *
   * @param t the time we want to get the animation state at
   * @return the animation state at the given time
   * @throws IllegalArgumentException if the time is negative
   * @throws IllegalArgumentException if the time is greater than the ending time
   * @throws IllegalStateException    if the program hasn't been started
   * @throws IllegalStateException    if there are no shapes in the animation
   */
  List<ShapeState> getShapeStatesAtTime(int t);

  /**
   * Returns a list of all of the shape ids in this animation.
   *
   * @return a list of all shape ids in this animation
   */
  List<String> getIds();

  /**
   * Returns the all of the shape states of the shape with the given id.
   *
   * @param id the id of the shape we want the states of
   * @return the type of the shape with the given id
   * @throws IllegalArgumentException if no shape with the given id exists in the model
   * @throws IllegalArgumentException if the given id is null
   * @throws IllegalStateException    if there are no shapes in the animation
   */
  NavigableMap<Integer, ShapeState> getShapeStatesFromId(String id);

  /**
   * Returns the type of the shape with the given id.
   *
   * @param id the id of the shape we want the type of
   * @return the type of the shape with the given id
   * @throws IllegalArgumentException if no shape with the given id exists in the model
   * @throws IllegalArgumentException if the given id is null
   * @throws IllegalStateException    if there are no shapes in the animation
   */
  ShapeType getType(String id);
}
