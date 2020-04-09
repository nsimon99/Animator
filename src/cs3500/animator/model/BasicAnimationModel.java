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

  @Override
  public void addKeyframe(String name, int t) {
    if (elements.containsKey(name)) {
      var timelineList = this.elements.get(name).getTimeline().getLog();
      for (ShapeState state : timelineList) {
        if (state.getTick() == t) {
          state.stateType = StateType.KEYFRAME;
          break;
        }
      }
    } else {
      throw new IllegalArgumentException("No shape with ID: " + name);
    }
  }

  @Override
  public void deleteKeyframe(String name, int t) {
    if (elements.containsKey(name)) {
      var timelineList = this.elements.get(name).getTimeline().getLog();
      var indexOfLastKeyframe = 0;
      for (int i = 0; i < timelineList.size(); i++) {
        ShapeState state = timelineList.get(i);
        if (state.getTick() == t) {
          timelineList.remove(i);
          for (int j = indexOfLastKeyframe + 1; j < i; j++) {
            timelineList.remove(indexOfLastKeyframe + 1);
          }
          break;
        } else if (state.stateType == StateType.KEYFRAME) {
          indexOfLastKeyframe = i;
        }
      }
    } else {
      throw new IllegalArgumentException("No shape with ID: " + name);
    }

  }

  @Override
  public int getLastTick() {
    int lastTick = 0;
    for (Shape shape : this.getElements().values()) {
      shape.getTimeline().sort();
      int shapeLastTick = shape.getTimeline().get(shape.getTimeline().size() - 1).getTick();
      lastTick = shapeLastTick > lastTick ? shapeLastTick : lastTick;
    }
    return lastTick;
  }

  @Override
  public String getShapeAtPosition(int x, int y, int t) {
    for (Shape shape : elements.values()) {
      ShapeState state = shape.getTimeline().get(t);
      // For some reason height and width are switched...
      // leave 25 px on each side for user error
      int maxX = state.getPos().getX() + state.getDim().getHeight() + 25;
      int maxY = state.getPos().getY() + state.getDim().getWidth() + 25;
      int minX = state.getPos().getX() - 25;
      int minY = state.getPos().getY() - 25;
      if (x < maxX && x > minX && y < maxY && y > minY) {
        return shape.getId();
      }
    }
    throw new IllegalArgumentException("No shape at given position");
  }

  @Override
  public void editKeyframe(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
    Timeline timeline = elements.get(name).getTimeline();
    deleteKeyframe(name, t);
    updateElement(name, t, new Position(x, y), new Dimension(h, w), new Color(r, g, b));
  }
}
