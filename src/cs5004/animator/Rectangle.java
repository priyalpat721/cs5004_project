package cs5004.animator;

public class Rectangle extends AbstractShape {

  public Rectangle(String name, RGB color, double width, double height,
                   int x, int y, int startTime, int endTime) {
    super(name, color, width, height, x, y, startTime, endTime);

    this.type = Shape.RECTANGLE;
  }

  @Override
  public IShape copy() {
    return new Rectangle(this.name, this.color, this.getWidth(), this.getHeight(),
            this.getPosition().getX(), this.getPosition().getY(),
            this.getTotalTime().getStartTime(), this.getTotalTime().getEndTime());
  }

}
