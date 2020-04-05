package cs3500.animator.model;

/**
 * An enum to represent the possible shape types.
 */
public enum ShapeType {
  TRIANGLE("triangle"), RECTANGLE("rectangle"), OVAL("oval");

  private final String name;

  ShapeType(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
