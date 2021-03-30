package cs5004.animator;

import java.awt.Color;

public interface IShape {

  String getName();

  Shape getType();

  Time getTotalTime();

  Position getPosition();

  void setPosition(int newX, int newY);

  Color getColor();

  void setColor(Color newColor);

  // For circles and squares (radius and length)
  double getSize();

  void setSize(double newSize);

  // For the rest
  double getWidth();

  double getHeight();

  void setWidth(double newWidth);

  void setHeight(double newHeight);

  // For all
  IShape copy();

}