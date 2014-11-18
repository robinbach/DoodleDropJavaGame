package doodledrop;

public class MovingComponent
{
  public enum Directions {NONE ,UP, DOWN, LEFT, RIGHT}
  // The displacement of each update period
  XVec2 velocity;

  // The point corresponding to the upper left conner.
  XVec2 location;

  // The collision rectangle start from the upper left corner.
  XVec2 collision;

  public MovingComponent()
  {
    velocity = new XVec2();
    location = new XVec2();
    collision = new XVec2(25, 25);

  }

  public MovingComponent(int x_in, int y_in)
  {
    velocity = new XVec2();
    location = new XVec2(x_in, y_in);
    collision = new XVec2(25, 25);

  }

  public void set_velocity(int x, int y)
  {
    velocity.x = x;
    velocity.y = y;
  }

  public void set_location(int x, int y)
  {
    location.x = x;
    location.y = y;
    // GUI.changeIconPosition(location, "left");
  }

  public XVec2 get_location()
  {
    return location;
  }


  // --------------------------------------------------------------------------
  // high level methods:

  // Function: move
  // move the components to next location according to their current velocity
  public void move()
  {
    // if(velocity.x + velocity.y != 0)
    // {
    // System.out.println("components moving to:" + location.toString());
    // }
    set_location(location.x + velocity.x, location.y + velocity.y);
  }

  // out put information of this component
  public String toString()
  {
    return "velocity = " + velocity.toString() + ", location = "
        + location.toString() + ", collision = " + collision.toString();
  }

  // check collision of two component
  public Directions checkCollision(MovingComponent another)
  {
    if( another.location.x - location.x < collision.x)
      return Directions.RIGHT;
    if (another.location.y - location.y < collision.y )
      return Directions.DOWN;
    
    if( location.x - another.location.x < another.collision.x)
      return Directions.LEFT;
    if( location.y - another.location.y  < another.collision.y )
      return Directions.RIGHT;

    System.out.println("collision find as:" + toString());
    System.out.println("with another component" + another.toString());

    return Directions.NONE;

  }

}
