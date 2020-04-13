package cs3500.animator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;


/**
 * A class to represent the changes to the state of a Shape.
 */
public class Timeline {

  private final ArrayList<ShapeState> log;

  /**
   * Construct a new Shape Timeline from an initial State.
   *
   * @param init the initial Shape.
   */
  public Timeline(ShapeState init) {
    this.log = new ArrayList<>();
    log.add(init);
  }

  /**
   * Construct a new Shape Timeline from a list of ShapeState.
   *
   * @param log a List of ShapeState representing the changes to a Shapes state.
   */
  public Timeline(ArrayList<ShapeState> log) {
    this.log = log;
  }

  /**
   * A copy constructor for Timeline.
   *
   * @param timeline the Timeline to copy.
   */
  public Timeline(Timeline timeline) {
    this.log = (ArrayList<ShapeState>) timeline.getLog().clone();
  }

  /**
   * Append the given ShapeState to this Timeline.
   *
   * @param state the ShapeState to append.
   */
  public void append(ShapeState state) {
    log.add(state);
  }

  @Override
  public String toString() {
    return log.toString();
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Timeline)) {
      return false;
    }
    return this.log.toString().equals(((Timeline) obj).log.toString());
  }

  /**
   * Get the list of changes associated with this Timeline.
   *
   * @return this Timeline's list of changes.
   */
  public ArrayList<ShapeState> getLog() {
    return log;
  }

  public int size() {
    return log.size();
  }

  public ShapeState get(int i) {
    return log.get(i);
  }

  /**
   * Sort the elements of the timeline by tick.
   */
  public void sort() {
    Collections.sort(this.log);
  }

  /**
   * Get the state with the given tick.
   *
   * @param t the tick.
   * @return the state at that tick.
   * @throws IllegalArgumentException if no such state exists
   */
  public ShapeState getTick(int t) {
    for (ShapeState state : this.log) {
      if (state.getTick() == t) {
        return state;
      }
    }
    throw new IllegalArgumentException("no state at tick " + t);
  }
}
