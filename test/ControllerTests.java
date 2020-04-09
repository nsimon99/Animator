import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.Controller;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.Dimension;
import cs3500.animator.model.Position;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeType;
import java.awt.Color;
import org.junit.Test;

/**
 * Unit tests for controller.
 */
public class ControllerTests {

  @Test
  public void testController() {
    AnimationModel testModel = new BasicAnimationModel();
    String initialStatus = "";

    testModel.addElement(new Shape("t1", 1, new Position(10, 10),
        new Dimension(3, 1), Color.cyan, ShapeType.RECTANGLE));

    testModel.updateElement("t1", 2, new Position(15, 15),
        new Dimension(2, 2), Color.magenta);

    testModel.updateElement("t1", 4, new Position(1, 1),
        new Dimension(7, 3), Color.red);
    String updatedStatus = "\nshape t1 rectangle\n"
        + "\n"
        + "motion t1 1 10 10 1 3 0 255 255   motion t1 2 15 15 2 2 255 0 255\n"
        + "motion t1 2 15 15 2 2 255 0 255   motion t1 4 1 1 3 7 255 0 0\n";
    Controller con = new Controller(testModel);
    con.playAnimation("text");

    assertEquals(updatedStatus, con.getString());

  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidController() {
    AnimationModel testModel = new BasicAnimationModel();
    String initialStatus = "";

    testModel.addElement(new Shape("t1", 1, new Position(10, 10),
        new Dimension(3, 1), Color.cyan, ShapeType.RECTANGLE));

    testModel.updateElement("t1", 2, new Position(15, 15),
        new Dimension(2, 2), Color.magenta);

    testModel.updateElement("t1", 4, new Position(1, 1),
        new Dimension(7, 3), Color.red);
    String updatedStatus = "\nshape t1 rectangle\n"
        + "\n"
        + "motion t1 1 10 10 1 3 0 255 255   motion t1 2 15 15 2 2 255 0 255\n"
        + "motion t1 2 15 15 2 2 255 0 255   motion t1 4 1 1 3 7 255 0 0\n";
    Controller con = new Controller(testModel);
    con.playAnimation("String");


  }

  @Test
  public void testControllerEmpty() {
    AnimationModel testModel = new BasicAnimationModel();


    Controller con = new Controller(testModel);
    con.playAnimation("text");

    assertEquals("", con.getString());

  }

}
