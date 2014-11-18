package doodledrop;

public class MainFile
{
  public static GameLogic gameEngine;

  public static void main(String[] args)
  {
    gameEngine = new GameLogic();

    gameEngine.gameInit();

    gameEngine.gamePlaying();

    gameEngine.gameExit();

  }
}
