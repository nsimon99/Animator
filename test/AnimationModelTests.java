import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.BasicAnimationModel;
import cs3500.animator.model.Dimension;
import cs3500.animator.model.Position;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeType;
import java.awt.Color;
import org.junit.Test;

/**
 * Unit Tests for AnimationModel.
 */
public class AnimationModelTests {

  @Test
  public void testAddElement() {
    AnimationModel testModel = new BasicAnimationModel();
    String initialStatus = "";

    assertEquals(initialStatus, testModel.renderText());

    testModel.addElement(new Shape("t1", 1, new Position(10, 10),
        new Dimension(2, 2), Color.cyan, ShapeType.RECTANGLE));
    String endStatus = "\nshape t1 rectangle\n\n";
    String out = testModel.renderText();

    assertEquals(endStatus, out);
  }

  @Test
  public void testUpdateElement() {
    AnimationModel testModel = new BasicAnimationModel();
    String initialStatus = "";

    assertEquals(initialStatus, testModel.renderText());

    testModel.addElement(new Shape("t1", 1, new Position(10, 10),
        new Dimension(3, 1), Color.cyan, ShapeType.RECTANGLE));
    String unupdatedStatus = "\nshape t1 rectangle\n\n";
    String out = testModel.renderText();

    assertEquals(unupdatedStatus, out);

    testModel.updateElement("t1", 2, new Position(15, 15),
        new Dimension(2, 2), Color.magenta);

    out = testModel.renderText();

    String updatedStatus = "\nshape t1 rectangle\n"
        + "\n"
        + "motion t1 1 10 10 1 3 0 255 255   motion t1 2 15 15 2 2 255 0 255\n";

    assertEquals(updatedStatus, out);
  }

  @Test
  public void testRemoveOnlyElement() {
    AnimationModel testModel = new BasicAnimationModel();
    String initialStatus = "";

    assertEquals(initialStatus, testModel.renderText());

    testModel.addElement(new Shape("t1", 1, new Position(10, 10),
        new Dimension(3, 1), Color.cyan, ShapeType.RECTANGLE));
    String unupdatedStatus = "\nshape t1 rectangle\n\n";
    String out = testModel.renderText();

    assertEquals(unupdatedStatus, out);

    testModel.updateElement("t1", 2, new Position(15, 15),
        new Dimension(2, 2), Color.magenta);

    out = testModel.renderText();

    String updatedStatus = "\nshape t1 rectangle\n"
        + "\n"
        + "motion t1 1 10 10 1 3 0 255 255   motion t1 2 15 15 2 2 255 0 255\n";

    assertEquals(updatedStatus, out);

    String removedStatus = "";

    testModel.removeElement("t1");

    out = testModel.renderText();

    assertEquals(removedStatus, out);
  }

  @Test
  public void testAddMultipleElements() {
    AnimationModel testModel = new BasicAnimationModel();
    String initialStatus = "";

    assertEquals(initialStatus, testModel.renderText());

    testModel.addElement(new Shape("t1", 1, new Position(10, 10),
        new Dimension(3, 1), Color.cyan, ShapeType.RECTANGLE));
    String unupdatedStatus = "\nshape t1 rectangle\n\n";
    String out = testModel.renderText();

    assertEquals(unupdatedStatus, out);

    testModel.updateElement("t1", 2, new Position(15, 15),
        new Dimension(2, 2), Color.magenta);

    out = testModel.renderText();

    String updatedStatus = "\nshape t1 rectangle\n"
        + "\n"
        + "motion t1 1 10 10 1 3 0 255 255   motion t1 2 15 15 2 2 255 0 255\n";

    assertEquals(updatedStatus, out);

    testModel.addElement(new Shape("t2", 1, new Position(1, 1),
        new Dimension(1, 1), Color.BLACK, ShapeType.OVAL));

    String finalStatus = "\nshape t1 rectangle\n"
        + "\n"
        + "motion t1 1 10 10 1 3 0 255 255   motion t1 2 15 15 2 2 255 0 255\n"
        + "\nshape t2 oval\n\n";

    out = testModel.renderText();

    assertEquals(finalStatus, out);
  }

  @Test
  public void testRemoveOneOfElements() {
    AnimationModel testModel = new BasicAnimationModel();
    String initialStatus = "";

    assertEquals(initialStatus, testModel.renderText());

    testModel.addElement(new Shape("t1", 1, new Position(10, 10),
        new Dimension(3, 1), Color.cyan, ShapeType.RECTANGLE));
    String unupdatedStatus = "\nshape t1 rectangle\n\n";
    String out = testModel.renderText();

    assertEquals(unupdatedStatus, out);

    testModel.updateElement("t1", 2, new Position(15, 15),
        new Dimension(2, 2), Color.magenta);

    out = testModel.renderText();

    String updatedStatus = "\nshape t1 rectangle\n"
        + "\n"
        + "motion t1 1 10 10 1 3 0 255 255   motion t1 2 15 15 2 2 255 0 255\n";

    assertEquals(updatedStatus, out);

    testModel.addElement(new Shape("t2", 1, new Position(1, 1),
        new Dimension(1, 1), Color.BLACK, ShapeType.OVAL));

    String finalStatus = "\nshape t1 rectangle\n"
        + "\n"
        + "motion t1 1 10 10 1 3 0 255 255   motion t1 2 15 15 2 2 255 0 255\n";

    testModel.removeElement("t2");
    out = testModel.renderText();

    assertEquals(finalStatus, out);
  }

  @Test
  public void testRemoveAllElements() {
    AnimationModel testModel = new BasicAnimationModel();
    String initialStatus = "";

    assertEquals(initialStatus, testModel.renderText());

    testModel.addElement(new Shape("t1", 1, new Position(10, 10),
        new Dimension(3, 1), Color.cyan, ShapeType.RECTANGLE));
    String unupdatedStatus = "\nshape t1 rectangle\n\n";
    String out = testModel.renderText();

    assertEquals(unupdatedStatus, out);

    testModel.updateElement("t1", 2, new Position(15, 15),
        new Dimension(2, 2), Color.magenta);

    out = testModel.renderText();

    String updatedStatus = "\nshape t1 rectangle\n"
        + "\n"
        + "motion t1 1 10 10 1 3 0 255 255   motion t1 2 15 15 2 2 255 0 255\n";

    assertEquals(updatedStatus, out);

    testModel.addElement(new Shape("t2", 1, new Position(1, 1),
        new Dimension(1, 1), Color.BLACK, ShapeType.OVAL));

    String finalStatus = "";

    testModel.removeAll();
    out = testModel.renderText();

    assertEquals(finalStatus, out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalUpdate() {
    AnimationModel testModel = new BasicAnimationModel();
    String initialStatus = "";

    assertEquals(initialStatus, testModel.renderText());

    testModel.addElement(new Shape("t1", 1, new Position(10, 10),
        new Dimension(3, 1), Color.cyan, ShapeType.RECTANGLE));
    String unupdatedStatus = "\nshape t1 rectangle\n\n";
    String out = testModel.renderText();

    assertEquals(unupdatedStatus, out);

    testModel.updateElement("t1", 2, new Position(15, 15),
        new Dimension(2, 2), Color.magenta);

    out = testModel.renderText();

    String updatedStatus = "\nshape t1 rectangle\n"
        + "\n"
        + "motion t1 1 10 10 1 3 0 255 255   motion t1 2 15 15 2 2 255 0 255\n";

    assertEquals(updatedStatus, out);

    // Should throw IllegalArgumentException
    testModel.updateElement("FAKE", 2, new Position(1, 2),
        new Dimension(1, 1), Color.RED);
  }

  @Test
  public void testRemoveFromEmptyModel() {
    AnimationModel testModel = new BasicAnimationModel();
    assertEquals("", testModel.renderText());

    testModel.removeElement("t1");
    assertEquals("", testModel.renderText());

    testModel.removeAll();
    assertEquals("", testModel.renderText());
  }

  @Test
  public void testMultipleUpdates() {
    AnimationModel testModel = new BasicAnimationModel();
    String initialStatus = "";

    assertEquals(initialStatus, testModel.renderText());

    testModel.addElement(new Shape("t1", 1, new Position(10, 10),
        new Dimension(3, 1), Color.cyan, ShapeType.RECTANGLE));
    String unupdatedStatus = "\nshape t1 rectangle\n\n";
    String out = testModel.renderText();

    assertEquals(unupdatedStatus, out);

    testModel.updateElement("t1", 2, new Position(15, 15),
        new Dimension(2, 2), Color.magenta);

    testModel.updateElement("t1", 4, new Position(1, 1),
        new Dimension(7, 3), Color.red);

    out = testModel.renderText();

    String updatedStatus = "\nshape t1 rectangle\n"
        + "\n"
        + "motion t1 1 10 10 1 3 0 255 255   motion t1 2 15 15 2 2 255 0 255\n"
        + "motion t1 2 15 15 2 2 255 0 255   motion t1 4 1 1 3 7 255 0 0\n";

    assertEquals(updatedStatus, out);
  }
}
