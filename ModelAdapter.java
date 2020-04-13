package cs3500.animator.adapter;

import cs3500.animator.model.Bounds;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeState;
import cs3500.animator.model.ShapeType;
import cs3500.animator.model.Timeline;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class that wraps our model to adapt to provider's view.
 */

public class ModelAdapter implements IModelAdapter{

  private Bounds bounds;

  private final HashMap<String, Shape> elements;


  /**
   * Constructer for class.
   */
  public ModelAdapter()  {
    this.elements = new HashMap<>();
  }

  @Override
  public int getWidth() {
    return bounds.getDimensions().getWidth();
  }

  @Override
  public int getHeight() {
    return bounds.getDimensions().getHeight();
  }

  @Override
  public int getTop() {
    return bounds.getY();
  }

  @Override
  public int getLeft() {
    return bounds.getX();
  }

  @Override
  public int getEndingTime() {
    int lastTick = 0;
    for (Shape shape : this.getElements().values()) {
      shape.getTimeline().sort();
      int shapeLastTick = shape.getTimeline().get(shape.getTimeline().size() - 1).getTick();
      lastTick = shapeLastTick > lastTick ? shapeLastTick : lastTick;
    }
    return lastTick;
  }

  @Override
  public List<ShapeState> getShapeStatesAtTime(int t) {
    if (t < 0 || t > getEndingTime()) {
      throw new IllegalArgumentException("invalid time");
    } else if (elements.size() == 0) {
      throw new IllegalStateException("No shaped in animation");
    } else {
      List<ShapeState> result = new ArrayList<>();
      for (Shape shape : elements.values()) {
        cs3500.animator.model.ShapeState state = shape.getTimeline().getTick(t);
        result.add(state);
      }
      return result;
    }
  }

  @Override
  public List<String> getIds() {
    List<String> result = new ArrayList<>();
    for (Shape shape : elements.values()) {
      String id = shape.getId();
      result.add(id);
    }
    return result;
  }

  @Override
  public Timeline getShapeStatesFromId(String id) {
    Shape s = elements.get(id);
    Timeline t = s.getTimeline();
    t.sort();
    return t;

  }

  @Override
  public ShapeType getType(String id) {
    if (id.equals(null) || (!elements.containsKey(id))) {
      throw new IllegalArgumentException("invalid id");
    } else if (elements.size() == 0) {
      throw new IllegalStateException("no shapes in animations");
    } else {
      Shape s = elements.get(id);
      return s.getType();
    }
  }

  /**
   * Get all of the elements of the model.
   *
   * @return a Map of all the Shapes in the model.
   */
  private Map<String, Shape> getElements() {
    return elements;
  }
}
