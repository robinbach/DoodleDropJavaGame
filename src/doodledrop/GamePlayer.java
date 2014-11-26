package doodledrop;

import doodledrop.Constants.Directions;

public class GamePlayer extends MovingComponent
{


  boolean isAlive;
  int isBlocked;
  // enum Directions declared in Constants
  Directions motionStatus;


  public GamePlayer()
  {
    super();
    isAlive = true;
    velocity.set(0, Constants.PLAYER_DROP_SPEED);
    motionStatus = Directions.NONE;

    collision.set(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);


    // isBlocked = false;
  }

  public void move()
  {
    update_status();

    if( velocity.x + velocity.y != 0 )
    {
      System.out.println("Player moving to:" + location.toString());
    }

    super.move();

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

  public Directions get_status()
  {
    return motionStatus;
  }

}
