package cs3500.animator.model;

import java.util.Objects;

/**
 * represent the height and width of a two dimension object.
 */
public final class Dimension {

  private final int height;
  private final int width;

  /**
   * Construct a new Dimension with height and width 0.
   */
  public Dimension() {
    this.height = 0;
    this.width = 0;
  }

  /**
   * Construct a new Dimension with the given height and width.
   *
   * @param height the height of the Dimension.
   * @param width  the width of the Dimension.
   */
  public Dimension(int height, int width) {
    this.height = height;
    this.width = width;
  }

  /**
   * Construct a new Dimension with the same height and width as the given Dimension.
   *
   * @param dim the Dimesnsion to copy.
   */
  public Dimension(Dimension dim) {
    this.height = dim.height;
    this.width = dim.width;
  }

  /**
   * returns the height of the object.
   *
   * @return int, height
   */
  public int getH() {
    return this.height;
  }

  /**
   * returns the width of the object.
   *
   * @return int, w
   */
  public int getW() {
    return this.width;
  }

  @Override
  public String toString() {
    return width + " " + height;
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
    if (!(obj instanceof Dimension)) {
      return false;
    }
    Dimension temp = (Dimension) obj;
    return (temp.height == this.height && temp.width == this.width);
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }
}