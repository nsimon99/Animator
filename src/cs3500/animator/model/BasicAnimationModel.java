package cs3500.animator.model;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * A basic implementation of the AnimationModel Interface. Represents the data of an animation with
 * a series of shape objects.
 */
public class BasicAnimationModel implements AnimationModel {

  private Bounds bounds;

  private final HashMap<String, Shape> elements;

  /**
   * Constructs a new empty Animation.
   */
  public BasicAnimationModel() {
    this.elements = new HashMap<>();
  }

  @Override
  public void addElement(Shape s) {
    elements.put(s.getId(), s);
  }

  @Override
  public void updateElement(String id, int tick, Position pos, Dimension dim, Color c) {
    Shape s = elements.get(id);
    if (s == null) {
      throw new IllegalArgumentException("Invalid Shape ID!");
    }
    s.addState(tick, pos, dim, c);
  }

  @Override
  public void removeElement(String id) {
    elements.remove(id);
  }

  @Override
  public void removeAll() {
    elements.clear();
  }

  @Override
  public Shape getShape(String name) {
    if (!this.elements.containsKey(name)) {
      throw new IllegalArgumentException("Shape is not in the ami");
    }
    return elements.get(name);
  }

  @Override
  public Map<String, Shape> getElements() {
    return elements;
  }

  @Override
  public Bounds getBounds() {
    return this.bounds;
  }

  @Override
  public void setBounds(int x, int y, Dimension d) {
    this.bounds = new Bounds(x, y, d);
  }

  @Override
  public String renderText() {
    StringBuilder result = new StringBuilder();
    for (Map.Entry<String, Shape> element : elements.entrySet()) {
      result.append("\nshape ").append(element.getKey()).append(" ")
          .append(element.getValue().getType().toString()).append("\n\n");

      int size = element.getValue().getTimeline().size();

      for (int i = 0; i < size - 1; i++) {
        result.append("motion ").append(element.getKey()).append(" ")
            .append(element.getValue().getTimeline().get(i).toString());
        result.append("   motion ").append(element.getKey()).append(" ").append(element.getValue()
            .getTimeline().get(i + 1).toString());
        result.append("\n");
      }

    }
    return result.toString();
  }
}
