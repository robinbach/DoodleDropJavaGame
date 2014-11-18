package doodledrop;

import java.util.Random;

public class GameBar extends MovingComponent
{
  // XVec2 location;
  // XVec2 velocity;
  static int barIDCounter = 0;
  final int barID;
  final int riseSpeed = 3;
  
  

  // define the type of this bar
  public enum barTypeEnum
  {
    KILLLING, NORMAL
  };

  barTypeEnum barType;

  public GameBar()
  {
    super(0, GamingWindow.stageLength);
    
    Random rand = new Random(); 
    int offset = 10;
    location.x = offset + rand.nextInt(GamingWindow.stageWidth - offset);

    velocity.y = riseSpeed;

    barType = barTypeEnum.NORMAL;

    barID = barIDCounter;
    ++barIDCounter;

  }

  public GameBar(barTypeEnum type_in)
  {
    super();

    velocity.y = riseSpeed;

    barType = type_in;

    barID = barIDCounter;
    ++barIDCounter;
  }


}
