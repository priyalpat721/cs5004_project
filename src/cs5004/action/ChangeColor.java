package cs5004.action;

import cs5004.shape.IShape;
import cs5004.utilities.RGB;

/**
 * Action class for color change.
 * This action is of type Action.CHANGECOLOR.
 */
public class ChangeColor extends AbstractAction {

  public ChangeColor(String name, IShape currentShape, RGB newColor,
                     int startTime, int endTime) {
    super(name, currentShape, newColor, startTime, endTime);
  }

//  private String name;
//  private IShape currentShape;
//  private RGB newColor;
//  private Time time;
//  private RGB oldColor;

//  public ChangeColor(String name, IShape currentShape, RGB newColor, int startTime, int endTime) {
//    if (name == null) {
//      throw new IllegalArgumentException("Name cannot be null");
//    }
//    if (name.isBlank()) {
//      throw new IllegalArgumentException("Name cannot be empty");
//    }
//    if (newColor == null) {
//      throw new IllegalArgumentException("Color cannot be null");
//    }
//    this.name = name;
//    this.currentShape = currentShape;
//    this.newColor = newColor;
//    this.time = new Time(startTime, endTime);
//    this.oldColor = new RGB(currentShape.getColor().getRed(), currentShape.getColor().getGreen(),
//        currentShape.getColor().getBlue());
//    this.currentShape.setColor(newColor);
//  }

  @Override
  public IShape getShapeAtTick(int tick, IShape shape) {
    if (tick < 0) {
      throw new IllegalArgumentException("Ticks cannot be negative");
    }
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }
    IShape copy = shape.copy();
    if (tick <= this.time.getStartTime()) {
      return shape.copy();
    } else if (tick > this.time.getEndTime()) {
      copy.setColor(newColor);
      return copy;
    } else {

      double percent = (double) (tick - this.time.getStartTime())
          / (this.time.getEndTime() - this.time.getStartTime());

      double currentR = (percent * (newColor.getRed() - oldColor.getRed())) + oldColor.getRed();
      double currentG = (percent * (newColor.getBlue() - oldColor.getBlue())) + oldColor.getBlue();
      double currentB = (percent * (newColor.getGreen() - oldColor.getGreen()))
          + oldColor.getGreen();

      copy.setColor(new RGB(currentR, currentG, currentB));
      return copy;
    }
  }

  @Override
  public String toString() {
    return name + " changes color from " + oldColor.toString()
        + " to " + newColor.toString() + " from time t= " + this.time.getStartTime()
        + " to t=" + this.time.getEndTime();
  }

}