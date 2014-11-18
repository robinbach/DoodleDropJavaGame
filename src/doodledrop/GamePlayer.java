package doodledrop;

public class GamePlayer
{
  double[] velocity;
  double[] location;

  public GamePlayer()
  {
    velocity = new double[2];
    location = new double[2];

    velocity[0] = 0.0;
    velocity[1] = 0.0;
    location[0] = 0.0;
    location[1] = 0.0;
  }

  public void move()
  {
    if(velocity[1] + velocity[0] != 0)
    {
      System.out.println("Player moving");
    }
    set_location(location[0] + velocity[0], location[1] + velocity[1]);
    velocity[0] = 0;
    velocity[1] = 0;
    //

  }

  public void set_velocity(double x, double y)
  {
    velocity[0] = x;
    velocity[1] = y;
  }

  public void set_location(double x, double y)
  {
    location[0] = x;
    location[1] = y;
    //GUI.changeIconPosition(location, "left");
  }

  public double[] get_location()
  {
    return location;
  }

  public String toString()
  {
    return "velocity = (" + velocity[0] + "," + velocity[1] + ")";
  }
}
