package doodledrop;

public class Constants
{

  public static enum Directions
  {
    NONE, UP, DOWN, LEFT, RIGHT
  }
  
  // define the type of this bar
  public static enum barTypeEnum
  {
    KILLLING, NORMAL, DISAPPEAR, SPRING, TURNING;
  };
  
  public static final int PLAYER_HEIGHT = 60;
  public static final int PLAYER_WIDTH = 34;

  public static final int BAR_HEIGHT = 30;
  public static final int BAR_WIDTH = 80;
  


  public static final int PLAYER_HORIZONTAL_SPEED = 5;
  public static final int PLAYER_DROP_SPEED = 3;
  public static final int PLAYER_JUMP_SPEED = -150;


  public static final int FRAME_DELAY = 20;

  public static final int BAR_RISING_SPEED = -7;
  public static final int BAR_VERTICAL_DISTANCE = 100;

  public static final int MAXIMUM_BAR_NUM = 10;

  public static final int STAGE_HEIGHT = 450;
  public static final int STAGE_WIDTH = 300;


}
