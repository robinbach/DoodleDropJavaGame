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
    KILLLING, NORMAL, DISAPPEAR, SPRING, TURNINGLEFT, TURNINGRIGHT;
  };
  
  public static final int BAR_TYPES =  barTypeEnum.values().length;
  
  public static final int PLAYER_HEIGHT = 60;
  public static final int PLAYER_WIDTH = 34;

  public static final int BAR_HEIGHT = 30;
  public static final int BAR_WIDTH = 80;
  
  public static final int SPRING_BAR_POWER = 35;
  public static final int TURNING_BAR_POWER = 1;
  public static final double POWER_DEACY_CONSTANT = 1.2;

  public static final int PLAYER_HORIZONTAL_SPEED = 6;

  public static int PLAYER_DROP_SPEED = 5;

  public static final int PLAYER_JUMP_SPEED = -150;

  public static final int FRAME_DELAY = 20;
  public static final int GAME_PREPARE_TIME = 2000;

  public static final int BOARD_DELAY = 20;
  public static final int SPRING_DELAY = 10;


  public static int BAR_RISING_SPEED = -5;
  public static final int BAR_VERTICAL_DISTANCE = 100;

  public static final int MAXIMUM_BAR_NUM = 100;

  public static final int STAGE_HEIGHT = 700;
  public static final int STAGE_WIDTH = 450;


}
