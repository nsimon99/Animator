package cs3500.animator.provider.model;

import cs3500.animator.model.ShapeType;
import java.util.Objects;

/**
 * Class to represent the state of a shape at a certain point in the animation. Contains information
 * about the shape's color, position, and dimensions at the specified time. Represents an
 * instantaneous value object for a shape state. Class invariants: Red, green, and blue are in the
 * interval [0,255]. Width and height are greater than 0. Shape type is not null. The Pos2D position
 * is not null.
 */
public final class ShapeState {

  // fields to represent this state's color, position, dimensions, and time
  private final double red;
  private final double green;
  private final double blue;
  private final Pos2D position;
  private final double width;
  private final double height;
  private final cs3500.animator.model.ShapeType type;

  private static final double DELTA = 0.001;

  /**
   * Constructs a ShapeState object, using the given specifiers for the shape.
   *
   * @param p      the position of the shape
   * @param width  the width of the shape
   * @param height the height of the shape
   * @param r      the redness of the shape
   * @param g      the redness of the shape
   * @param b      the redness of the shape
   * @param type   the type of the shape
   * @throws IllegalArgumentException if the given time is negative
   * @throws IllegalArgumentException if the red, green, or blue values are not within [0, 255]
   * @throws IllegalArgumentException if the width or the height are less than or equal to 0
   * @throws IllegalArgumentException if the given shape type is null
   */
  public ShapeState(Pos2D p, double width, double height, double r, double g, double b,
      cs3500.animator.model.ShapeType type) {

    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("Invalid color input");
    }
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid dimensions");
    }
    if (type == null) {
      throw new IllegalArgumentException("Shape type can't be null");
    }
    if (p == null) {
      throw new IllegalArgumentException("Pos2D cannot be null");
    }

    this.red = r;
    this.green = g;
    this.blue = b;
    this.position = new Pos2D(p);
    this.width = width;
    this.height = height;
    this.type = type;
  }

  /**
   * Constructs a default shape state: a black, 50x50 shape, at the given position, with the given
   * type.
   *
   * @param p the position of the shape
   * @param type the type of the shape
   */
  public ShapeState(Pos2D p, cs3500.animator.model.ShapeType type) {

    if (p == null) {
      throw new IllegalArgumentException("Position can't be null");
    }

    if (type == null) {
      throw new IllegalArgumentException("Type can't be null");
    }

    this.red = 0;
    this.green = 0;
    this.blue = 0;
    this.width = 50;
    this.height = 50;
    this.type = type;
    this.position = new Pos2D(p);
  }

  /**
   * Copy constructor for a ShapeState- used to easily create copies of this value object.
   *
   * @param s the given ShapeState that we want to copy
   */
  public ShapeState(ShapeState s) {
    this(s.position, s.width, s.height, s.red, s.green, s.blue, s.type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append(Math.round(position.getX())).append(" ");
    sb.append(Math.round(position.getY())).append(" ");
    sb.append(Math.round(width)).append(" ");
    sb.append(Math.round(height)).append(" ");
    sb.append(Math.round(red)).append(" ");
    sb.append(Math.round(green)).append(" ");
    sb.append(Math.round(blue));

    return sb.toString();
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }

    if (!(a instanceof ShapeState)) {
      return false;
    }

    ShapeState that = (ShapeState) a;

    return Math.abs(this.red - that.red) < DELTA
        && Math.abs(this.green - that.green) < DELTA
        && Math.abs(this.blue - that.blue) < DELTA
        && this.position.equals(that.position)
        && Math.abs(this.width - that.width) < DELTA
        && Math.abs(this.height - that.height) < DELTA
        && this.type == that.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue, position, width, height, type);
  }

  /**
   * Gets the type of this shape state.
   *
   * @return the type of this shape state
   */
  public ShapeType getType() {
    return type;
  }

  /**
   * Gets the x coordinate of this shape state, as an int.
   *
   * @return the x coordinate of this shape state
   */
  public int getX() {
    return position.getX();
  }

  /**
   * Gets the y coordinate of this shape state, as an int.
   *
   * @return the y coordinate of this shape state
   */
  public int getY() {
    return position.getY();
  }

  /**
   * Gets the width of this shape state, as an int.
   *
   * @return the width of this shape state
   */
  public int getWidth() {
    return (int) Math.round(width);
  }

  /**
   * Gets the height of this shape state, as an int.
   *
   * @return the height of this shape state
   */
  public int getHeight() {
    return (int) Math.round(height);
  }

  /**
   * Gets the redness value of this shape state, as an int.
   *
   * @return the integer representing the redness value of this shape state.
   */
  public int getRed() {
    return (int) Math.round(red);
  }

  /**
   * Gets the greenness value of this shape state, as an int.
   *
   * @return the integer representing the greenness value of this shape state.
   */
  public int getGreen() {
    return (int) Math.round(green);
  }

  /**
   * Gets the greenness value of this shape state, as an int.
   *
   * @return the integer representing the greenness value of this shape state.
   */
  public int getBlue() {
    return (int) Math.round(blue);
  }

}
