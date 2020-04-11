package cs3500.animator.model;

/**
 * The Bounding Box of the Animation.
 */
public class Bounds {

  private final int x;
  private final int y;
  private final Dimension dimension;

  /**
   * Construct a new bounding box.
   *
   * @param x         the leftmost X value.
   * @param y         the topmost Y Value
   * @param dimension the dimensions.
   */
  public Bounds(int x, int y, Dimension dimension) {
    this.x = x;
    this.y = y;
    this.dimension = dimension;
  }

  /**
   * Get the leftmost X value.
   *
   * @return the leftmost X value.
   */
  public int getX() {
    return x;
  }

  /**
   * Get the topmost Y value.
   *
   * @return the leftmost Y value.
   */
  public int getY() {
    return y;
  }

  /**
   * Get the Dimensions of the bounding box.
   *
   * @return a copy of the Dimensions of the bounding box.
   */
  public Dimension getDimensions() {
    return new Dimension(dimension);
  }
}
