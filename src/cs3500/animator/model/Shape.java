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
    this.timeline = new Timeline(new ShapeState(tick, pos, dim, c, t, StateType.KEYFRAME));
  }

  /**
   * Constructs a new Shape Object.
   *
   * @param id the ID of the Shape.
   * @param t  the type of the Shape.
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
    var state = new ShapeState(tick, pos, dim, c, this.type, StateType.KEYFRAME);
    timeline.append(state);
    this.addBetweenStates(state);
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

  /**
   * Add the in between states for each tick between the given tick and previous tick in the
   * timeline. 𝑓(𝑡)=𝑎((𝑡𝑏−𝑡)/(𝑡𝑏−𝑡𝑎))+𝑏((𝑡−𝑡𝑎)/(𝑡𝑏−𝑡𝑎))
   *
   * @param state the state to add.
   */
  private void addBetweenStates(ShapeState state) {
    timeline.sort();
    int stateIndx = timeline.getLog().lastIndexOf(state) - 1;
    if(stateIndx < 0) {
      return;
    }
    ShapeState prevState = timeline.get(stateIndx);

    int ta = prevState.getTick();
    int tb = state.getTick();

    Dimension da = prevState.getDim();
    Dimension db = state.getDim();

    Position pa = prevState.getPos();
    Position pb = state.getPos();

    Color ca = prevState.getColor();
    Color cb = state.getColor();

    for (int t = ta + 1; t < tb; t++) {
      int ht = calculateBetweenValue(t, ta, tb, da.getHeight(), db.getHeight());
      int wt = calculateBetweenValue(t, ta, tb, da.getWidth(), db.getWidth());
      Dimension dt = new Dimension(ht, wt);

      int xt = calculateBetweenValue(t, ta, tb, pa.getX(), pb.getX());
      int yt = calculateBetweenValue(t, ta, tb, pa.getY(), pb.getY());
      Position pt = new Position(xt, yt);

      int rt = calculateBetweenValue(t, ta, tb, ca.getRed(), cb.getRed());
      int gt = calculateBetweenValue(t, ta, tb, ca.getGreen(), cb.getGreen());
      int bt = calculateBetweenValue(t, ta, tb, ca.getBlue(), cb.getBlue());
      Color ct = new Color(rt, gt, bt);

      ShapeState stateT = new ShapeState(t, pt, dt, ct, state.getType(), StateType.BETWEENFRAME);
      timeline.append(stateT);
    }
  }

  /**
   * 𝑓(𝑡)=𝑎((𝑡𝑏−𝑡)/(𝑡𝑏−𝑡𝑎))+𝑏((𝑡−𝑡𝑎)/(𝑡𝑏−𝑡𝑎)).
   * @param ta = ta
   * @param tb = tb
   * @param t = t
   * @param a = a
   * @param b = b
   * @return the value at time T (f(t))
   */
  private int calculateBetweenValue(int t, int ta, int tb, int a, int b) {
    return (int)((a * ((double)(tb - t)/(double)(tb - ta)))
        + (b * ((double)(t - ta)/(double)(tb - ta))));
  }
}
