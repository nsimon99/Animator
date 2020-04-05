package cs3500.animator.view;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.Dimension;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeState;
import cs3500.animator.model.ShapeType;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;

/**
 * Render the animation as an SVG.
 */
public class SVGView implements AnimationView {

  private AnimationModel model;
  private String filename;

  private final int secondsPerTick;

  /**
   * Construct a new SVG View.
   *
   * @param model    the AnimationModel to render
   * @param filename the output filename.
   * @param spt      the number of seconds per model tick.
   */
  public SVGView(AnimationModel model, String filename, int spt) {
    this.model = model;
    this.filename = filename;
    secondsPerTick = spt;
  }

  @Override
  public void render() {
    Dimension modelDimension = model.getBounds().getDimensions();
    StringBuilder svgBuilder = new StringBuilder();
    svgBuilder.append(String.format("<svg width=\"%d\" height=\"%d\" version=\"1.1\""
            + "xmlns=\"http://www.w3.org/2000/svg\">",
        modelDimension.getWidth(), modelDimension.getHeight()));

    for (Shape shape : model.getElements().values()) {
      svgBuilder.append(makeShape(shape));
    }

    svgBuilder.append("</svg>");

    try {
      FileWriter fw = new FileWriter(new File(filename));
      fw.append(svgBuilder);
      fw.close();
    } catch (IOException e) {
      throw new IllegalStateException("Unable to write to file");
    }
  }

  private String makeShape(Shape shape) {
    StringBuilder shapeBuilder = new StringBuilder();
    if (shape.getTimeline().getLog().size() > 0) {

      ArrayList<ShapeState> log = shape.getTimeline().getLog();
      for (int i = 0; i < log.size(); i++) {
        ShapeState state = log.get(i);
        String end = "";
        boolean initialVisibility = state.getTick() < 1;
        String visible = initialVisibility ? "visible" : "hidden";
        if (i == 0) {
          switch (shape.getType()) {
            case RECTANGLE:
              shapeBuilder
                  .append(String.format("<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" "
                          + "fill=\"%s\" visibility=\"%s\" >\n",
                      state.getPos().getX(),
                      state.getPos().getY(),
                      state.getDim().getWidth(),
                      state.getDim().getHeight(),
                      state.getColor().toString(),
                      visible));
              end = "</rect>";
              break;
            case OVAL:
              shapeBuilder.append("<ellipse ");
              shapeBuilder.append(String.format("cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\" "
                      + "fill=\"%s\" visibility=\"%s\" >\n",
                  state.getPos().getX(),
                  state.getPos().getY(),
                  state.getDim().getWidth() / 2,
                  state.getDim().getHeight() / 2,
                  state.getColor().toString(),
                  visible));
              end = "</ellipse>";
              break;
            case TRIANGLE:
              shapeBuilder.append("<polygon ");
              end = "</polygon>";
              // Only Rectangles and Ellipses required for hw6.
              throw new IllegalArgumentException("Invalid ShapeType",
                  new NotImplementedException("Triangles not yet implemented!"));
            default:
              throw new IllegalArgumentException("Invalid ShapeType");
          }
        } else {
          shapeBuilder.append(makeAnimation(shape.getType(), state, log.get(i - 1)));
        }
        if (i == log.size() - 1) {
          shapeBuilder.append(end);
        }
      }
      shapeBuilder.append("\n");
      return shapeBuilder.toString();
    }
    return "";
  }

  private String makeAnimation(ShapeType type, ShapeState state, ShapeState prevState) {
    List<String> changes = new ArrayList<String>();
    if (state.getPos() != prevState.getPos()) {
      if (state.getPos().getX() != prevState.getPos().getX()) {
        changes.add("x");
      }
      if (state.getPos().getY() != prevState.getPos().getY()) {
        changes.add("y");
      }
    }
    if (state.getDim() != prevState.getDim()) {
      if (state.getDim().getHeight() != prevState.getDim().getHeight()) {
        changes.add("height");
      }
      if (state.getDim().getWidth() != prevState.getDim().getWidth()) {
        changes.add("width");
      }
    }
    if (state.getColor() != prevState.getColor()) {
      changes.add("color");
    }

    StringBuilder animationBuilder = new StringBuilder();

    switch (type) {
      case RECTANGLE:
        for (String change : changes) {
          switch (change) {
            case "x":
              animationBuilder.append(String.format("<animate attributeType=\"xml\" begin=\"%dms\""
                      + "dur=\"%dms\" attributeName=\"x\" from=\"%d\" to=\"%d\" fill=\"freeze\" "
                      + "/>\n",
                  prevState.getTick() * secondsPerTick,
                  (state.getTick() * secondsPerTick - prevState.getTick() * secondsPerTick),
                  prevState.getPos().getX(),
                  state.getPos().getX()));
              break;
            case "y":
              animationBuilder.append(String.format("<animate attributeType=\"xml\" begin=\"%dms\""
                      + "dur=\"%dms\" attributeName=\"y\" from=\"%d\" to=\"%d\" fill=\"freeze\" "
                      + "/>\n",
                  prevState.getTick() * secondsPerTick,
                  (state.getTick() * secondsPerTick - prevState.getTick() * secondsPerTick),
                  prevState.getPos().getY(),
                  state.getPos().getY()));
              break;
            case "height":
              animationBuilder.append(String.format("<animate attributeType=\"xml\" begin=\"%dms\""
                      + "dur=\"%dms\" attributeName=\"height\" from=\"%d\" to=\"%d\" "
                      + "fill=\"freeze\" "
                      + "/>\n",
                  prevState.getTick() * secondsPerTick,
                  (state.getTick() * secondsPerTick - prevState.getTick() * secondsPerTick),
                  prevState.getDim().getHeight(),
                  state.getDim().getHeight()));
              break;
            case "width":
              animationBuilder.append(String.format("<animate attributeType=\"xml\" begin=\"%fms\""
                      + "dur=\"%fms\" attributeName=\"width\" from=\"%d\" to=\"%d\" "
                      + "fill=\"freeze\" "
                      + "/>\n",
                  prevState.getTick() * secondsPerTick,
                  (state.getTick() * secondsPerTick - prevState.getTick() * secondsPerTick),
                  prevState.getDim().getWidth(),
                  state.getDim().getWidth()));
              break;
            case "color":
              animationBuilder.append(String.format("<animate attributeType=\"xml\" begin=\"%dms\""
                      + "dur=\"%dms\" attributeName=\"fill\" from=\"%s\" to=\"%s\" fill=\"freeze\" "
                      + "/>\n",
                  prevState.getTick() * secondsPerTick,
                  (state.getTick() * secondsPerTick - prevState.getTick() * secondsPerTick),
                  prevState.getColor().toString(),
                  state.getColor().toString()));
              break;
            default:
              throw new IllegalStateException("invalid change");
          }
        }
        return animationBuilder.toString();
      case OVAL:
        for (String change : changes) {
          switch (change) {
            case "x":
              animationBuilder.append(String.format("<animate attributeType=\"xml\" begin=\"%dms\""
                      + "dur=\"%dms\" attributeName=\"cx\" from=\"%d\" to=\"%d\" fill=\"freeze\" "
                      + "/>\n",
                  prevState.getTick() * secondsPerTick,
                  (state.getTick() * secondsPerTick - prevState.getTick() * secondsPerTick),
                  prevState.getPos().getX(),
                  state.getPos().getX()));
              break;
            case "y":
              animationBuilder.append(String.format("<animate attributeType=\"xml\" begin=\"%dms\""
                      + "dur=\"%dms\" attributeName=\"cy\" from=\"%d\" to=\"%d\" fill=\"freeze\" "
                      + "/>\n",
                  prevState.getTick() * secondsPerTick,
                  (state.getTick() * secondsPerTick - prevState.getTick() * secondsPerTick),
                  prevState.getPos().getY(),
                  state.getPos().getY()));
              break;
            case "height":
              animationBuilder.append(String.format("<animate attributeType=\"xml\" begin=\"%dms\""
                      + "dur=\"%dms\" attributeName=\"ry\" from=\"%d\" to=\"%d\" fill=\"freeze\" "
                      + "/>\n",
                  prevState.getTick() * secondsPerTick,
                  (state.getTick() * secondsPerTick - prevState.getTick() * secondsPerTick),
                  prevState.getDim().getHeight() / 2,
                  state.getDim().getHeight() / 2));
              break;
            case "width":
              animationBuilder.append(String.format("<animate attributeType=\"xml\" begin=\"%dms\""
                      + "dur=\"%dms\" attributeName=\"rx\" from=\"%d\" to=\"%d\" fill=\"freeze\" "
                      + "/>\n",
                  prevState.getTick() * secondsPerTick,
                  (state.getTick() * secondsPerTick - prevState.getTick() * secondsPerTick),
                  prevState.getDim().getWidth() / 2,
                  state.getDim().getWidth() / 2));
              break;
            case "color":
              animationBuilder.append(String.format("<animate attributeType=\"xml\" begin=\"%dms\""
                      + "dur=\"%dms\" attributeName=\"fill\" from=\"%s\" to=\"%s\" fill=\"freeze\" "
                      + "/>\n",
                  prevState.getTick() * secondsPerTick,
                  (state.getTick() * secondsPerTick - prevState.getTick() * secondsPerTick),
                  prevState.getColor().toString(),
                  state.getColor().toString()));
              break;
            default:
              throw new IllegalStateException("invalid change");
          }
        }

        return animationBuilder.toString();
      case TRIANGLE:
        // Only Rectangles and Ellipses required for hw6.
        throw new IllegalArgumentException("Invalid Shape",
            new NotImplementedException("Triangles not yet Implemented"));
      default:
        throw new IllegalArgumentException("Invalid Shape Type");
    }
  }
}
