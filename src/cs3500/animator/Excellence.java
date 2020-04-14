package cs3500.animator;

import cs3500.animator.adapter.ControllerAdapter;
import cs3500.animator.adapter.EditorAdapter;
import cs3500.animator.adapter.IModelAdapter;
import cs3500.animator.adapter.ModelAdapter;
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
    String viewType = "provider";
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
    if (viewType.equals("svg")) {
      view = new SVGView(model, outputDest, ticksPerSecond);
      view.render();
    } else if (viewType.equals("text")) {
      view = new TextView(model, System.out);
      view.render();
    } else if (viewType.equals("visual")) {
      view = new SwingAnimationView(model);
      view.render();
    } else if (viewType.equals("editor")) {
      view = new EditorView(model);
      view.render();
    } else if (viewType.equals("provider")) {
      IModelAdapter modelAdapter = new ModelAdapter(model);
      EditorAdapter edit = new EditorAdapter(modelAdapter, ticksPerSecond);
      ControllerAdapter cont = new ControllerAdapter((ModelAdapter) modelAdapter, edit);
      cont.animate();
    }


  }
}
