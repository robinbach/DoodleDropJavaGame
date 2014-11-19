package doodledrop;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

import doodledrop.doodledropopen.RunEnding;


public class GameLogic extends Thread
{

  boolean gameRunning;
  int delay;

  // GuiClassMainMenu mainMenu;
  static GamePlayer player1;
  static LinkedList<GameBar> allBars;

  public static final int MaximumBarNumber = 3;
  GamingWindow gamingMenu;
  RunEnding runEnding;

  // --------------------------------------------------------------------------
  // constructor and the four master functions:
  // constructor
  // game initialize
  // game playing: will call game updating in loop;
  // game updating
  // game exiting

  public GameLogic()
  {
    allBars = new LinkedList<GameBar>();
    player1 = new GamePlayer();
    delay = 200;
  }
  
  public void setRunEnding(RunEnding _runEnding){
    this.runEnding = _runEnding;
  }

  public void gameInit()
  {
    // mainMenu.initGUI();
    // Network.initNetwork();
  }

  public void gamePlaying()
  {
    gameRunning = true;
    System.out.println("game running");

    gamingMenu = new GamingWindow();
    gamingMenu.setRunEnding(this.runEnding);

    // gamingMenu.initGUI();
    // initBars();
    // initPlayers();
    // the main updating loop:
    while( gameRunning )
    {
      gameUpdate();
    }
  }

  public void gameUpdate()
  {
    int updateNums = 0;
    updateNums++;
    System.out.println("@@@@@@---------");
    System.out.println("game updating" + updateNums);

    try
    {
      sleep(delay);
    }
    catch( InterruptedException e )
    {
      e.printStackTrace();
    }

    // update the status
    updateBarStatus();
    updatePlayerStatus();

    // getNetworkInfo();
    moveBars();
    movePlayers();
  }

  public void gameExit()
  {
    System.out.println("game exiting");
    // Database.showStatistics();
    gamingMenu.dispose();
  }

  // --------------------------------------------------------------------------
  // methods that the master function used
  public void moveBars()
  {
    System.out.println("moving bars");

    for( GameBar eachbar : allBars )
    {
      eachbar.move();
    }
  }

  public void movePlayers()
  {
    player1.move();

    // for testing
    GamingWindow.position.setText(player1.location.toString());

  }

  // function: updatePlayerStatus
  // check the player's status after this move
  private void updatePlayerStatus()
  {
    for( GameBar eachbar : allBars )
    {
      MovingComponent.Directions direction = player1.checkCollision(eachbar);
      if(  direction != MovingComponent.Directions.NONE )
      {
        System.out.println("collision find, block players as"
            + direction.toString());
        switch( direction)
        {
          case LEFT:
            if(player1.velocity.x < 0)
              player1.velocity.x = 0;
          break;
          case RIGHT:
            if(player1.velocity.x > 0)
              player1.velocity.x = 0;
          break;
          case UP:
            if(player1.velocity.y < 0)
              player1.velocity.y = 0;
          break;
          case DOWN:
            if(player1.velocity.y > 0)
              player1.velocity.y = 0;
          break;
          default:
          break;
        }
        if( eachbar.barType == GameBar.barTypeEnum.KILLLING )
        {
          System.out.println(" which kills players");
          player1.isAlive = false;
          gameRunning = false;
        }
        break;
      }
    }
    System.out.println("updating players");
  }

  private void updateBarStatus()
  {
    System.out.println("updating bars");

    if( allBars.size() < MaximumBarNumber )
    {
      allBars.add(new GameBar());
    }
    
    if( allBars.getFirst().location.y < 0 )
    {
      allBars.removeFirst();
    }

  }

  // --------------------------------------------------------------------------
  // the engine adapt these listener from the game frame:
  public static void keyPressed(int keyCode)
  {
    if( keyCode == KeyEvent.VK_RIGHT )
    {
      System.out.println("right moving");

      player1.set_velocity(1, 0);
    }
    else if( keyCode == KeyEvent.VK_LEFT )
    {
      System.out.println("left moving");

      player1.set_velocity(-1, 0);
    }
  }


  public static void keyReleased(int keyCode)
  {
    if( keyCode == KeyEvent.VK_RIGHT )
    {
      System.out.println("right moving stoped");

      player1.set_velocity(0, 0);
    }
    else if( keyCode == KeyEvent.VK_LEFT )
    {
      System.out.println("left moving stoped");

      player1.set_velocity(0, 0);
    }
  }

}
