package cs3500.animator.model;

import java.awt.Color;
import java.util.Objects;

/**
 * A class to represent the state of a Shape in an animation. The state is the position, dimension,
 * and color of the shape at a given tick.
 */
public class ShapeState implements Comparable<ShapeState> {

  private final int tick;
  private final Position pos;
  private final Dimension dim;
  private final Color color;
  private final ShapeType type;

  protected StateType stateType;

  /**
   * Construct a new ShapeState object.
   *
   * @param tick      the tick that the state represents.
   * @param pos       the Position of the Shape at the given tick.
   * @param dim       the Dimension of the Shape at the given tick.
   * @param color     the Color of the Shape at the given tick.
   * @param stateType the type of state (keyframe or between frame).
   */
  public ShapeState(int tick, Position pos, Dimension dim, Color color,
      ShapeType type, StateType stateType) {
    this.tick = tick;
    this.pos = new Position(pos);
    this.dim = new Dimension(dim);
    this.color = new Color(color.getRGB());
    this.type = type;
    this.stateType = stateType;
  }

  @Override
  public String toString() {
    return tick + " " + pos.toString() + " " + dim.toString() + " " + color.getRed()
        + " " + color.getGreen() + " " + color.getBlue();
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
    if (!(obj instanceof ShapeState)) {
      return false;
    }
    ShapeState temp = (ShapeState) obj;
    return (temp.tick == this.tick &&
        temp.pos.equals(this.pos) && temp.dim.equals(this.dim)
        && temp.color.equals(this.color));
  }

  public int getTick() {
    return tick;
  }

  public Position getPos() {
    return pos;
  }

  public Dimension getDim() {
    return dim;
  }

  public Color getColor() {
    return color;
  }

  public ShapeType getType() {
    return type;
  }

  @Override
  public int compareTo(ShapeState state) {
    if (state == null) {
      throw new NullPointerException("object to compare to is null");
    }
    return Integer.compare(this.tick, state.tick);
  }
}
