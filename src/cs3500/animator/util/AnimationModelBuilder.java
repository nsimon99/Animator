package cs3500.animator.util;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.Dimension;
import cs3500.animator.model.Position;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeType;
import java.awt.Color;

/**
 * Builder Class for an AnimationModel.
 */
public class AnimationModelBuilder implements AnimationBuilder<AnimationModel> {

  private final AnimationModel model;
  private int tickrate;

  public AnimationModelBuilder() {
    model = new BasicAnimationModel();
  }

  public AnimationModelBuilder(AnimationModel model) {
    this.model = model;
  }

  @Override
  public AnimationModel build() {
    return model;
  }

  @Override
  public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
    model.setBounds(x, y, new Dimension(width, height));
    return new AnimationModelBuilder(model);
  }

  @Override
  public AnimationBuilder<AnimationModel> setTickrate(int tickrate) {
    this.tickrate = tickrate;
    return this;
  }

  @Override
  public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
    ShapeType shapeType = ShapeType.RECTANGLE;
    switch (type) {
      case "rectangle":
        break;
      case "ellipse":
        shapeType = ShapeType.OVAL;
        break;
      case "triangle":
        shapeType = ShapeType.TRIANGLE;
        break;
      default:
        throw new IllegalArgumentException("Invalid shape type!");
    }
    model.addElement(new Shape(name, shapeType));
    return new AnimationModelBuilder(model);
  }

  @Override
  public AnimationBuilder<AnimationModel> addMotion(String name, int t1, int x1, int y1, int w1,
      int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
      int b2) {
    AnimationBuilder builder = addKeyframe(name, t1, x1, y1, w1, h1, r1, g1, b1);
    return builder.addKeyframe(name, t1 + t2, x2, y2, w2, h2, r2, g2, b2);
  }

  @Override
  public AnimationBuilder<AnimationModel> addKeyframe(String name, int t, int x, int y, int w,
      int h, int r, int g, int b) {

    model.getShape(name).addState(t, new Position(x, y), new Dimension(w, h), new Color(r, g, b));
    return new AnimationModelBuilder(model);
  }

  public int getTickrate() {
    return tickrate;
  }
}
