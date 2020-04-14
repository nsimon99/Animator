package cs3500.animator.provider.model;

/**
 * The interface of our easy animator model. It supports all necessary operations for the user of an
 * animation studio.
 */
public interface EasyAnimatorOperations extends ReadOnlyEAOperations {

  /**
   * Starts the animation studio with the given screen width and height.
   *
   * @param screenWidth  the width of the canvas
   * @param screenHeight the height of the canvas
   * @param top          the top of the canvas
   * @param left         the left of the canvas
   * @throws IllegalArgumentException if the given screen width or height are <= 0
   */
  void startAnimation(int screenWidth, int screenHeight, int top, int left);

  /**
   * Adds a shape to the animation model with the given id and type.
   *
   * @param id   - A string representing an ID for the shape you are trying to add.
   * @param type - the type of shape you are trying to add.
   * @throws IllegalArgumentException if the id already exists within the animation model/is null
   * @throws IllegalArgumentException if the ShapeType is null.
   */
  void addShape(String id, ShapeType type);

  /**
   * Removes the Shape from the Animation Model with the given ID.
   *
   * @param id - the id of the shape you are trying to remove from the state.
   * @throws IllegalArgumentException if the id is null
   * @throws IllegalArgumentException if the given ID does not exist within the model
   * @throws IllegalStateException    if there are no shapes in the model
   */
  void removeShape(String id);

  /**
   * Removes allShapes within the given Animation.
   */
  void clearShapes();

  /**
   * Adds the given motion to the shape represented by the given id.
   *
   * @param id        the id of the shape we want to add to
   * @param start     the starting shape state of the motion you are adding
   * @param end       the ending shape state of the motion you are adding
   * @param startTime the starting time of the motion
   * @param endTime   the ending time of the motion
   * @throws IllegalStateException    if there are no shapes in the model
   * @throws IllegalArgumentException if any parameters are null
   * @throws IllegalArgumentException if the given ID does not exist within the model
   * @throws IllegalArgumentException if the given motion is invalid
   */
  void addMotion(String id, cs3500.animator.provider.model.ShapeState start,
      cs3500.animator.provider.model.ShapeState end, int startTime, int endTime);


  /**
   * Removes the motion defined by the given starting and ending times from the shape with the given
   * id.
   *
   * @param id        the id of the shape we want to remove from
   * @param startTime the start time of the motion we want to remove
   * @param endTime   the end time of the motion we want to remove
   * @throws IllegalStateException    if there are no shapes in the model
   * @throws IllegalArgumentException if any parameters are null
   * @throws IllegalArgumentException if the given ID does not exist within the model
   * @throws IllegalArgumentException if the given motion is invalid
   * @throws IllegalStateException    if there are no states in the given shape
   * @throws IllegalArgumentException if the given motion can't be removed
   */
  void removeMotion(String id, int startTime, int endTime);

  /**
   * Removes all of the motions associated with the given ID.
   *
   * @param id - the id of the shape you are trying to remove the motions from.
   * @throws IllegalStateException    if there are no shapes in the model
   * @throws IllegalStateException    if there are no states in the shape with the given id
   * @throws IllegalArgumentException if any parameters are null
   * @throws IllegalArgumentException if there are no shapes with the given ID
   */
  void clearMotions(String id);


  /**
   * Adds the given shape state as a new "endpoint" to the motions of a shape represented by the
   * given id.
   *
   * @param id the id of the shape we want to add to
   * @param ss the state we want to add
   * @param t  the time of the endpoint we want to add
   * @throws IllegalStateException    if there are no shapes in the model
   * @throws IllegalArgumentException if any parameters are null
   * @throws IllegalArgumentException if there are no shapes with the given ID
   * @throws IllegalArgumentException if the time of the given shapestate has already been assigned
   * @throws IllegalArgumentException if the type given shapestate is different from that of the
   *                                  shape you are trying to add to
   */
  void addEndpoint(String id, cs3500.animator.model.ShapeState ss, int t);

  /**
   * Removes the given shape state as an endpoint from the shape represented by the given id.
   *
   * @param id the id of the shape we want to remove from
   * @param t  the time of the endpoint we want to remove
   * @throws IllegalStateException    if there are no shapes in the model
   * @throws IllegalStateException    if there are no states in the shape with the given id
   * @throws IllegalArgumentException if any parameters are null
   * @throws IllegalArgumentException if the given ID does not exist within the model
   * @throws IllegalArgumentException if the given state is not within the given shape's states
   */
  void removeEndpoint(String id, int t);

  /**
   * Edits the endpoint at the specified time by replacing it with the given shape state.
   *
   * @param id the id of the shape we want to edit
   * @param t  the time we want to edit at
   * @param ss the new ShapeState we want to assign to the time
   * @throws IllegalStateException    if there are no shapes in the model
   * @throws IllegalStateException    if there are no states in the shape with the given id
   * @throws IllegalArgumentException if any of the parameters are null
   * @throws IllegalArgumentException if the time has not been assigned to any state
   * @throws IllegalArgumentException if there are no shapes with the given id
   * @throws IllegalArgumentException if the type is a different type
   */
  void editEndpoint(String id, int t, ShapeState ss);


  /**
   * At the given time, adds a new "keyframe" to the motions of a shape represented by the
   * given id.
   *
   * @param id the id of the shape we want to add to
   * @param t  the time of the endpoint we want to add
   * @throws IllegalStateException    if there are no shapes in the model
   * @throws IllegalArgumentException if any parameters are null
   * @throws IllegalArgumentException if there are no shapes with the given ID
   * @throws IllegalArgumentException if the time has already been assigned
   */
  void addKeyframe(String id, int t);
}
