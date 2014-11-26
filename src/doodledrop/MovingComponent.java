package doodledrop;

public class MovingComponent
{
  // The displacement of each update period
  XVec2 velocity;

  // The point corresponding to the upper left corner.
  XVec2 location;

  // The collision rectangle start from the upper left corner.
  XVec2 collision;

  public MovingComponent()
  {
    velocity = new XVec2();
    location = new XVec2();
    collision = new XVec2(0, 0);

  }

  // constructor with location parameter
  public MovingComponent(int x_in, int y_in)
  {
    velocity = new XVec2();
    location = new XVec2(x_in, y_in);
    collision = new XVec2(0, 0);

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
  public Constants.Directions checkCollision(MovingComponent another)
  {
    // Y+ == vertically downward
    // X+ == horizontally
    
    // if they have the same x region
    if( location.x > another.location.x - collision.x
        && location.x < another.location.x + another.collision.x )
    {
      //check if their y region cross on other
      if( another.location.y > location.y
          && another.location.y - location.y < collision.y )
        return Constants.Directions.DOWN;
      if( another.location.y < location.y
          && -another.location.y + location.y < another.collision.y )
        return Constants.Directions.UP;
    }
    
    // if they have the same y region
    // Hint: y increasing from top to bottom.
    if( location.y > another.location.y + another.collision.y
        && location.y < another.location.y + collision.y )
    {
      //check if their x region cross on other
      if( another.location.x > location.x
          && another.location.x - location.x < collision.x )
        return Constants.Directions.RIGHT;
      else if( another.location.x < location.x
          && -another.location.x + location.x < another.collision.x )
        return Constants.Directions.LEFT;
    }
    
    
    // System.out.println("collision find as:" + toString());
    // System.out.println("with another component" + another.toString());

    return Constants.Directions.NONE;

  }

}
