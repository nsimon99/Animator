package cs3500.animator.model;

import java.util.Objects;

/**
 * represent the position of an object on a two dimensional grid.
 */
public final class Position {

  private final int x;
  private final int y;

  public Position() {
    this.x = 0;
    this.y = 0;
  }

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Position(Position pos) {
    this.x = pos.x;
    this.y = pos.y;
  }

  /**
   * returns the x coordinate.
   * @return int, x
   */
  public int getX() {
    return this.x;
  }

  /**
   * returns the y coordinate.
   * @return int, y
   */
  public int getY() {
    return this.y;
  }

  @Override
  public String toString() {
    return x + " " + y;
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
    if (!(obj instanceof Position)) {
      return false;
    }
    Position temp = (Position) obj;
    return (temp.x == this.x && temp.y == this.y);
  }
}
