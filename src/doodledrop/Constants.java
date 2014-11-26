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
    KILLLING, NORMAL
  };
  
  public static final int PLAYER_HEIGHT = 25;
  public static final int PLAYER_WIDTH = 25;

  public static final int BAR_HEIGHT = 15;
  public static final int BAR_WIDTH = 50;


  public static final int PLAYER_HORIZONTAL_SPEED = 5;
  public static final int PLAYER_DROP_SPEED = 1;
  public static final int PLAYER_JUMP_SPEED = -10;

  public static final int FRAME_DELAY = 200;

  public static final int BAR_RISING_SPEED = -1;
  public static final int BAR_VERTICAL_DISTANCE = 100;

  public static final int MAXIMUM_BAR_NUM = 1;

  public static final int STAGE_HEIGHT = 150;
  public static final int STAGE_WIDTH = 150;


}
