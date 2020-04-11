package cs3500.animator.provider.model;

/**
 * Represents the type of a shape. This will be used to draw the correct shape in the animation.
 * Class invariants: The string is not null.
 */
public enum ShapeType {

  ELLIPSE("ellipse"), RECTANGLE("rectangle");

  private final String s;

  /**
   * Constructs a ShapeType with the given string.
   *
   * @param s the string of the shape type
   */
  ShapeType(String s) {
    if (s == null) {
      throw new IllegalArgumentException("String can't be null");
    }
    this.s = s;
  }

  @Override
  public String toString() {
    return s;
  }

  /**
   * Returns the correct ShapeType using the given string.
   *
   * @param s the string we are using to find the shape type
   * @return the shape type of the string
   * @throws IllegalArgumentException if the string is null or invalid
   */
  public static ShapeType findType(String s) {

    if (s == null) {
      throw new IllegalArgumentException("String can't be null");
    }

    switch (s) {
      case "rectangle":
        return RECTANGLE;
      case "ellipse":
        return ELLIPSE;
      default:
        throw new IllegalArgumentException("Not a shape type");
    }
  }
}
