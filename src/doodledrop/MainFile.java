package doodledrop;

public class MainFile
{
  public static GameLogic gameEngine;

  public static void main(String[] args)
  {
    // for testing
    runGame();

  }

  // @GUI.API
  // when the "Start/Restart Game" button clicked,
  // This runGame() function should be called
  // it will evoke a new thread for gaming,
  // and create gameWindow (see gameLogic for more API),
  // TO-DO: move runGame() and corresponding declaration
  // to the corresponding GUI button listener

  public static void runGame()
  {
    gameEngine = new GameLogic();
    Thread gameThread = new Thread(gameEngine);
    gameThread.start();

    try
    {
      gameThread.join();
    }
    catch( InterruptedException e )
    {
      e.printStackTrace();
    }

  }
}
