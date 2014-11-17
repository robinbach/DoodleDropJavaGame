package doodledrop;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;


public class GameLogic extends Thread
{

  boolean gameRunning;

  // GuiClassMainMenu mainMenu;
  static GamePlayer player1;

  //

  public GameLogic()
  {
    player1 = new GamePlayer();
  }

  public void gameInit()
  {
    // mainMenu.initGUI();

    // Network.initNetwork();

  }


  // have a listener like this:
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

  public void gamePlaying()
  {
    gameRunning = true;
    System.out.println("game running");

    GamingWindow gamingMenu = new GamingWindow();

    // gamingMenu.initGUI();
    // initBars();
    // initPlayers();
    int updateNums = 0;
    while( gameRunning )
    {
      updateNums++;
      gameUpdate();
      System.out.println("@@@@@@---------");
      System.out.println("game updating" + updateNums);
      try
      {
        sleep(3000);
      }
      catch( InterruptedException e )
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

  }

  public void moveBars()
  {
    System.out.println("moving bars");

    // for( eachbar: existingBars)
    // {
    // eachbar.set_location(eachbar.velocity);
    // }
  }

  private void updatePlayers()
  {
    System.out.println("updating players");

  }

  private void updateBarStatus()
  {
    System.out.println("updating bars");

  }

  public void movePlayers()
  {
    player1.move();
  }

  public void gameUpdate()
  {

    // getNetworkInfo();
    moveBars();
    movePlayers();

    updateBarStatus();
    updatePlayers();
  }

  public void gameExit()
  {
    System.out.println("game exiting");
    // Database.showStatistics();
  }

}
