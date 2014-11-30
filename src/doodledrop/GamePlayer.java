package doodledrop;

import doodledrop.Constants.Directions;

public class GamePlayer extends MovingComponent
{


  boolean isAlive;
  int isBlocked;
  XVec2 inertia;
  int healthPoint;
  // enum Directions declared in Constants
  Directions motionStatus;


  public GamePlayer()
  {
    super();
    isAlive = true;
    healthPoint = Constants.PLAYER_HEALTH;
    location.set(Constants.STAGE_WIDTH/2 , 0);
    velocity.set(0, Constants.PLAYER_DROP_SPEED);
    motionStatus = Directions.NONE;
    
    collision.set(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);

    inertia = new XVec2(0,0);
    // isBlocked = false;
  }

  public void move()
  {
    update_status();

    if( velocity.x + velocity.y != 0 )
    {
      System.out.println("Player moving to:" + location.toString());
    }

    if(inertia.y != 0)
    {
      location.y += inertia.y;
      System.out.println("Player inertia y is:" + inertia.y);
      inertia.y /= Constants.POWER_DEACY_CONSTANT;
    }
    if(inertia.x != 0)
    {
      location.x += inertia.x;
      System.out.println("Player inertia x is:" + inertia.x);
      inertia.x /= Constants.POWER_DEACY_CONSTANT;
    }
    super.move();
    if(location.x < -Constants.PLAYER_WIDTH)
    {
      location.x = Constants.STAGE_WIDTH;
    }
    if(location.x > Constants.STAGE_WIDTH)
    {
      location.x = -Constants.PLAYER_WIDTH;
    }

    // velocity.x = 0;
    velocity.y = Constants.PLAYER_DROP_SPEED;

  }

  private void update_status()
  {
    if( velocity.y > 0 )
    {
      motionStatus = Directions.DOWN;
    }
    else if( velocity.x < 0 )
    {
      motionStatus = Directions.LEFT;
    }
    else if( velocity.x > 0 )
    {
      motionStatus = Directions.RIGHT;
    }
    else
    {
    	if(velocity.y <= Constants.BAR_RISING_SPEED)
    	{
    		motionStatus = Directions.UP;
    	}
    	else
    	{
    	      motionStatus = Directions.NONE;
    	}
    }
  }

//  public Directions get_status()
//  {
//    return motionStatus;
//  }

}
