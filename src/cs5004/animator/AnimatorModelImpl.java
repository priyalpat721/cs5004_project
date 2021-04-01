package cs5004.animator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AnimatorModelImpl implements IAnimatorModel {
  private HashMap<String, IShape> logOfShapes;
  private HashMap<String, List<IAction>> logOfActions;
  private List<IAction> shapeActions;
  private List<IAction> chronologicalOrderOfActions;

  public AnimatorModelImpl() {
    this.logOfShapes = new HashMap<>();
    this.logOfActions = new HashMap<>();
    this.shapeActions = new LinkedList<>();
    this.chronologicalOrderOfActions = new LinkedList<>();
  }

  @Override
  public void createShape(String name, Shape shape, RGB color, double width, double height,
                          double x, double y, int startTime, int endTime) {
    if (shape == Shape.CIRCLE) {
      logOfShapes.put(name, new Circle(name, color, width, width, x, y, startTime, endTime));
    } else if (shape == Shape.SQUARE) {
      logOfShapes.put(name, new Square(name, color, width, width, x, y, startTime, endTime));
    } else if (shape == Shape.RECTANGLE) {
      logOfShapes.put(name, new Rectangle(name, color, width, height, x, y, startTime, endTime));
    } else if (shape == Shape.TRIANGLE) {
      logOfShapes.put(name, new Triangle(name, color, width, height, x, y, startTime, endTime));
    } else if (shape == Shape.RHOMBUS) {
      logOfShapes.put(name, new Rhombus(name, color, width, height, x, y, startTime, endTime));
    } else if (shape == Shape.OVAL) {
      logOfShapes.put(name, new Oval(name, color, width, height, x, y, startTime, endTime));
    }
  }

  @Override
  public void move(String name, double newX, double newY, int startTime, int endTime) {
    IAction newMove = new Move(name, newX, newY, startTime, endTime);
    addActionToShape(name, newMove);
    chronologicalOrderOfActions.add(newMove);
  }

  @Override
  public void changeColor(String name, RGB newColor, int startTime, int endTime) {
    IAction newChangeColor = new ChangeColor(name, newColor, startTime, endTime);
    addActionToShape(name, newChangeColor);
    chronologicalOrderOfActions.add(newChangeColor);
  }

  @Override
  public void scale(String name, double newWidth, double newHeight, int startTime, int endTime) {
    IAction newScale = new Scale(name, newWidth, newHeight, startTime, endTime);
    addActionToShape(name, newScale);
    chronologicalOrderOfActions.add(newScale);
  }

  // adds any action
  public void addActions(String name, IAction actions) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    if (name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be empty");
    }
    if (actions == null) {
      throw new IllegalArgumentException("Actions cannot be null");
    }
    addActionToShape(name, actions);
    chronologicalOrderOfActions.add(actions);
  }

  // create, move, change Color, scale
  private void addActionToShape(String name, IAction action) {
    shapeActions.add(action);
    logOfActions.put(name, shapeActions);
  }

  @Override
  public List<IShape> getShapesAtTicks(int tick) {
    List<IShape> frameOfShapes = new LinkedList<>();

    for (Map.Entry<String, IShape> objects : logOfShapes.entrySet()) {
      IShape accumulatorShape = objects.getValue().copy();

      for (IAction actions : logOfActions.get(objects.getKey())) {
        accumulatorShape = actions.getShapeAtTick(tick, accumulatorShape);
      }

      frameOfShapes.add(accumulatorShape);
    }

    return frameOfShapes;
  }
}
