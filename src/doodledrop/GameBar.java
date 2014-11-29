package doodledrop;

import java.util.Random;

public class GameBar extends MovingComponent
{
  // XVec2 location;
  // XVec2 velocity;
  private static int barIDCounter = 0;
  int barID;


  Constants.barTypeEnum barType;

  public GameBar()
  {
    super(0, Constants.STAGE_HEIGHT);
    initBar();
    
    
    // @ GUI_API
    // create a bar panel in GUI bar panel array
    // GUI.createBar(location, barType, barID);
    MainPanel.creatBar(location, barType, barID);
    
  }

  private void initBar()
  {
    Random rand = new Random();
    // int offset = 10;
    location.x = rand.nextInt(Constants.STAGE_WIDTH - Constants.BAR_WIDTH);
    location.y = Constants.STAGE_HEIGHT;

    velocity.y = Constants.BAR_RISING_SPEED;

    collision.set(Constants.BAR_WIDTH, Constants.BAR_HEIGHT);
    barType = Constants.barTypeEnum.SPRING;

//    if(barID % 3 != 0)
//      barType = Constants.barTypeEnum.NORMAL;
//    else
//    {
//      int rand_in = barID;// % 7;
////      switch(rand_in)
//        barType = Constants.barTypeEnum.values()[rand_in % Constants.BAR_TYPES];
//    }

    barID = barIDCounter;
    ++barIDCounter;
  }

  public GameBar(Constants.barTypeEnum type_in)
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
