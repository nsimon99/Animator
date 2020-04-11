package cs3500.animator.provider.model;

import java.util.Objects;

/**
 * Class that is a value object, which represents a 2D position. Contains information about the x
 * and y coordinates of the position. Class invariants: X and Y are non-negative.
 */
public final class Pos2D {

  private final double x;
  private final double y;
  private static final double DELTA = 0.001;

  /**
   * Gets the x value of this position.
   *
   * @return the x value of this position
   */
  public int getX() {
    return (int) Math.round(x);
  }

  /**
   * Gets the y value of this position.
   *
   * @return the y value of this position
   */
  public int getY() {
    return (int) Math.round(y);
  }

  /**
   * Initialize this object to the specified position.
   *
   * @throws IllegalArgumentException if the given x or y values are less than 0
   */
  public Pos2D(double x, double y) {

    this.x = x;
    this.y = y;
  }

  /**
   * Copy constructor.
   */
  public Pos2D(Pos2D v) {
    this(v.x, v.y);
  }

  @Override
  public String toString() {
    return String.format("(%d, %d)", Math.round(this.x), Math.round(this.y));
  }

  @Override
  public boolean equals(Object a) {

    if (this == a) {
      return true;
    }

    if (!(a instanceof Pos2D)) {
      return false;
    }

    Pos2D that = (Pos2D) a;
    return Math.abs(this.x - that.x) < DELTA
        && Math.abs(this.y - that.y) < DELTA;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }

}
