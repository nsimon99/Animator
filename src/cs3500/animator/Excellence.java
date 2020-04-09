package cs3500.animator;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationModelBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AnimationView;
import cs3500.animator.view.EditorView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.SwingAnimationView;
import cs3500.animator.view.TextView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Main Class to run the animator from the command line.
 */
public final class Excellence {

  /**
   * Run the animator from the command line.
   *
   * @param args The command line arguments in the form: -in "name-of-animation-file" -view
   *             "type-of-view" -out "where-output-show-go" -speed "integer-ticks-per-second"
   */
  public static void main(String[] args) {
    String inputFile = "smalldemo.txt";
    String viewType = "editor";
    String outputDest = "-out";
    int ticksPerSecond = 1;
    for (int i = 0; i < args.length - 1; i++) {
      if (args[i].equals("-in")) {
        inputFile = args[i + 1];
      } else if (args[i].equals("-view")) {
        viewType = args[i + 1];
      } else if (args[i].equals("-out")) {
        outputDest = args[i + 1];
      } else if (args[i].equals("-speed")) {
        try {
          ticksPerSecond = Integer.parseInt(args[i + 1]);
        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Illegal Speed", e);
        }
      }
    }
    AnimationBuilder<AnimationModel> modelBuilder = new AnimationModelBuilder();
    Readable reader = null;
    try {
      reader = new FileReader(new File(inputFile));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Input File Not Found", e);
    }
    AnimationReader animationReader = new AnimationReader();

    AnimationModel model = animationReader.parseFile(reader, modelBuilder);
    AnimationView view;
    switch (viewType) {
      case "svg":
        view = new SVGView(model, outputDest, ticksPerSecond);
        break;
      case "text":
        view = new TextView(model, System.out);
        break;
      case "visual":
        view = new SwingAnimationView(model);
        break;
      case "editor":
        view = new EditorView(model);
        break;
      default:
        throw new IllegalArgumentException("Invalid View Type");
    }

    view.render();
  }
}
