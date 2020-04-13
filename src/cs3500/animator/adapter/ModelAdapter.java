package cs3500.animator.adapter;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.Shape;
import cs3500.animator.provider.model.Pos2D;
import cs3500.animator.provider.model.ShapeState;
import cs3500.animator.provider.model.ShapeType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;


/**
 * Class that wraps our model to adapt to provider's view.
 */

public class ModelAdapter extends BasicAnimationModel implements IModelAdapter {

  /**
   * Constructs a new Model Adapter from an existing BasicAnimationModel.
   * @param model the model to copy.
   */
  public ModelAdapter(AnimationModel model) {
    super(model);
  }

  @Override
  public int getWidth() {
    return super.getBounds().getDimensions().getWidth();
  }

  @Override
  public int getHeight() {
    return super.getBounds().getDimensions().getHeight();
  }

  @Override
  public int getTop() {
    return super.getBounds().getY();
  }

  @Override
  public int getLeft() {
    return super.getBounds().getX();
  }

  @Override
  public int getEndingTime() {
    return super.getLastTick();
  }

  @Override
  public List<ShapeState> getShapeStatesAtTime(int t) {
    Collection<Shape> shapes = super.getElements().values();
    List<ShapeState> states = new ArrayList<>();
    for (Shape s : shapes) {
      try {
        states.add(convertShapeState(s.getTimeline().getTick(t)));
      } catch (IllegalArgumentException e) {
        // s does not have a state at time T, don't add it to list.
      }

    }
    return states;
  }

  @Override
  public List<String> getIds() {
    return new ArrayList<>(super.getElements().keySet());
  }

  @Override
  public NavigableMap<Integer, ShapeState> getShapeStatesFromId(String id) {
    NavigableMap<Integer, ShapeState> map = new TreeMap<Integer, ShapeState>();
    for (cs3500.animator.model.ShapeState state : super.getElements().get(id).getTimeline()
        .getLog()) {
      map.put(state.getTick(), convertShapeState(state));
    }
    return map;
  }

  @Override
  public ShapeType getType(String id) {
    return convertShapeType(super.getElements().get(id).getType());
  }

  private ShapeState convertShapeState(cs3500.animator.model.ShapeState state) {
    ShapeType type = convertShapeType(state.getType());

    ShapeState convertedState = new ShapeState(
        new Pos2D(state.getPos().getX(), state.getPos().getY()),
        state.getDim().getWidth(),
        state.getDim().getHeight(),
        state.getColor().getRed(),
        state.getColor().getGreen(),
        state.getColor().getBlue(), type);
    return convertedState;
  }

  private ShapeType convertShapeType(cs3500.animator.model.ShapeType type) {
    switch (type) {
      case RECTANGLE:
        return ShapeType.RECTANGLE;
      case OVAL:
        return ShapeType.ELLIPSE;
      case TRIANGLE:
        throw new IllegalArgumentException("Triangles not supported");
      default:
        throw new IllegalArgumentException("Invalid Shape");
    }
  }

//  private Bounds bounds;
//
//  private final HashMap<String, Shape> elements;
//
//
//  /**
//   * Constructer for class.
//   */
//  public ModelAdapter()  {
//    this.elements = new HashMap<>();
//  }
//
//  @Override
//  public int getWidth() {
//    return bounds.getDimensions().getWidth();
//  }
//
//  @Override
//  public int getHeight() {
//    return bounds.getDimensions().getHeight();
//  }
//
//  @Override
//  public int getTop() {
//    return bounds.getY();
//  }
//
//  @Override
//  public int getLeft() {
//    return bounds.getX();
//  }
//
//  @Override
//  public int getEndingTime() {
//    int lastTick = 0;
//    for (Shape shape : this.getElements().values()) {
//      shape.getTimeline().sort();
//      int shapeLastTick = shape.getTimeline().get(shape.getTimeline().size() - 1).getTick();
//      lastTick = shapeLastTick > lastTick ? shapeLastTick : lastTick;
//    }
//    return lastTick;
//  }
//
//  @Override
//  public List<ShapeState> getShapeStatesAtTime(int t) {
//    if (t < 0 || t > getEndingTime()) {
//      throw new IllegalArgumentException("invalid time");
//    } else if (elements.size() == 0) {
//      throw new IllegalStateException("No shaped in animation");
//    } else {
//      List<ShapeState> result = new ArrayList<>();
//      for (Shape shape : elements.values()) {
//        cs3500.animator.model.ShapeState state = shape.getTimeline().getTick(t);
//        result.add(state);
//      }
//      return result;
//    }
//  }
//
//  @Override
//  public List<String> getIds() {
//    List<String> result = new ArrayList<>();
//    for (Shape shape : elements.values()) {
//      String id = shape.getId();
//      result.add(id);
//    }
//    return result;
//  }
//
//  @Override
//  public Timeline getShapeStatesFromId(String id) {
//    Shape s = elements.get(id);
//    Timeline t = s.getTimeline();
//    t.sort();
//    return t;
//
//  }
//
//  @Override
//  public ShapeType getType(String id) {
//    if (id.equals(null) || (!elements.containsKey(id))) {
//      throw new IllegalArgumentException("invalid id");
//    } else if (elements.size() == 0) {
//      throw new IllegalStateException("no shapes in animations");
//    } else {
//      Shape s = elements.get(id);
//      return s.getType();
//    }
//  }
//
//  /**
//   * Get all of the elements of the model.
//   *
//   * @return a Map of all the Shapes in the model.
//   */
//  private Map<String, Shape> getElements() {
//    return elements;
//  }
}
