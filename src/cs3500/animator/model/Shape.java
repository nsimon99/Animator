package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * represent a two dimensional object in an animation.
 */
public class Shape {

  private final Timeline timeline;
  private final String id;
  private final ShapeType type;

  /**
   * Constructs a new Shape object.
   *
   * @param id   the ID of the Shape.
   * @param tick the initial tick of the Shape.
   * @param pos  the initial Position of the Shape.
   * @param dim  the initial Dimension of the Shape.
   * @param c    the initial Color of the Shape.
   * @param t    the type of Shape.
   */
  public Shape(String id, int tick, Position pos, Dimension dim, Color c, ShapeType t) {
    this.id = id;
    this.type = t;
    this.timeline = new Timeline(new ShapeState(tick, pos, dim, c, t));
  }

  /**
   * Constructs a new Shape Object.
   * @param id the ID of the Shape.
   * @param t the type of the Shape.
   */
  public Shape(String id, ShapeType t) {
    this.id = id;
    this.type = t;
    this.timeline = new Timeline(new ArrayList<>());
  }

  /**
   * add a new state to the shape's timeline.
   *
   * @param tick the position on the timeline in tick in the new state
   * @param pos  the position on the cavas
   * @param dim  the dimensions of the shape
   * @param c    the color of the shape
   */
  public void addState(int tick, Position pos, Dimension dim, Color c) {
    timeline.append(new ShapeState(tick, pos, dim, c, this.type));
  }

  /**
   * Get the Timeline of this Shape.
   *
   * @return the Timeline of all changes to this Shape.
   */
  public Timeline getTimeline() {
    return new Timeline(timeline);
  }

  /**
   * Get the ID of this Shape.
   *
   * @return the ID of this Shape.
   */
  public String getId() {
    return id;
  }

  /**
   * Get the ShapeType of this Shape.
   *
   * @return the ShapeType of this Shape.
   */
  public ShapeType getType() {
    return type;
  }
}
