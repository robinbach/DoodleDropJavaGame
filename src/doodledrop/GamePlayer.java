package doodledrop;

public class GamePlayer extends MovingComponent
{


  boolean isAlive;
  int isBlocked;


  public GamePlayer()
  {
    super();
    isAlive = true;
    //isBlocked = false;
  }

  // public void move()
  // {
  // if(velocity.x + velocity.y != 0)
  // {
  // System.out.println("Player moving to:" + location.toString());
  // }
  // set_location(location.x + velocity.x, location.y + velocity.y);
  // // velocity.x = 0;
  // // velocity.y = 0;
  // //
  //
  // }
  //
  // public void set_velocity(int x, int y)
  // {
  // velocity.x = x;
  // velocity.y = y;
  // }
  //
  // public void set_location(int x, int y)
  // {
  // location.x = x;
  // location.y = y;
  // //GUI.changeIconPosition(location, "left");
  // }
  //
  // public XVec2 get_location()
  // {
  // return location;
  // }

  // public String toString()
  // {
  // return "velocity = " + velocity.toString() + ", location = " +
  // location.toString();
  // }

}
