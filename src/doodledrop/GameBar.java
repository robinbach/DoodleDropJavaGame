package doodledrop;

import java.util.Random;

public class GameBar extends MovingComponent
{
  // XVec2 location;
  // XVec2 velocity;
  static int barIDCounter = 0;
  int barID;


  // define the type of this bar
  public enum barTypeEnum
  {
    KILLLING, NORMAL
  };

  barTypeEnum barType;

  public GameBar()
  {
    super(0, Constants.STAGE_HEIGHT);
    initBar();
  }

  private void initBar()
  {
    Random rand = new Random();
    // int offset = 10;
    location.x = rand.nextInt(Constants.STAGE_WIDTH - Constants.BAR_WIDTH);
    location.y = Constants.STAGE_HEIGHT;

    velocity.y = Constants.BAR_RISING_SPEED;

    collision.set(Constants.BAR_WIDTH, Constants.BAR_HEIGHT);

    barType = barTypeEnum.NORMAL;

    barID = barIDCounter;
    ++barIDCounter;
  }

  public GameBar(barTypeEnum type_in)
  {
    super();
    initBar();
    barType = type_in;
  }

  public String toString()
  {
    return "bar: id = " + barID + super.toString();
  }


}
