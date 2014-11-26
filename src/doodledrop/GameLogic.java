package doodledrop;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Vector;

import doodledrop.Constants.Directions;


public class GameLogic extends Thread implements Runnable
{

  boolean gameRunning;
  int delay;
  static int updateNums;

  // GuiClassMainMenu mainMenu;
  static GamePlayer player1, player2;
  static LinkedList<GameBar> allBars;

  DebugWindow debugMenu;
  MainPanel mainPanel;

  // --------------------------------------------------------------------------
  // constructor and the four master functions:
  // constructor
  // game initialize
  // game playing: will call game updating in loop;
  // game updating
  // game exiting

  public GameLogic()
  {
	  
  }

  public void run()
  {
    gameInit();

    gamePlaying();

    gameExit();
  }


  public void gameInit()
  {
    // mainMenu.initGUI();

    // @GUI_API
    // initialize/swap to gaming menu
    mainPanel = new MainPanel();

    // initiate Bars:
    allBars = new LinkedList<GameBar>();

    // initiate Players:
    player1 = new GamePlayer();
    player2 = new GamePlayer();

    // initiate thread delay time
    delay = Constants.FRAME_DELAY;

    // initialize the frame counter, for debug.
    updateNums = 0;

    // @Network_API
    // initialize the network connection with another player
    // established a thread for updating info of player2.
    // if( multiplayer )
    // Network.initNetworkConnection();
    // should return when the connection is established.
    //
  }

  // @Network_API
  // call this function when updating in Networking thread
  static public void updatePlayer2Info(XVec2 location, Directions motionStatus)
  {
    player2.location = location;
    player2.motionStatus = motionStatus;
  }

  public void gamePlaying()
  {
    gameRunning = true;
    System.out.println("game running");

    debugMenu = new DebugWindow();

    allBars.add(new GameBar());

    // gamingMenu.initGUI();


    // the main updating loop:
    while( gameRunning )
    {
      gameUpdate();
    }
  }

  public void gameUpdate()
  {
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

  // exit the game.
  public void gameExit()
  {
    System.out.println("game exiting");
    // @ GUI_API
    // create a showResultMenu(),
    // Should contains game statistics (database), resume button, etc.
    // GUI.showResultMenu();

    debugMenu.dispose();
  }

  // --------------------------------------------------------------------------
  // methods that the master function used
  public void moveBars()
  {
    System.out.println("moving bars");

    Vector<XVec2> barLocationList = new Vector<XVec2>();

    // for each existing bar on the panel
    
    for( GameBar eachbar : allBars )
    {
      int barID = eachbar.barID;
      XVec2 location = eachbar.location;
      barLocationList.add(new XVec2(eachbar.location.x, eachbar.location.y));
      eachbar.move();
    }

    // @GUI_API movebarB: set the image location of each bar,
    // or:
    // GUI.drawAllBarOnCanvas(GameBar[] allBars)
    // where each has member attribute as barID and location
    
    // updateBarLocation(Vector<XVec2> barLocationList);
  }

  public void movePlayers()
  {
    player1.move();

    // for testing
    DebugWindow.position.setText(player1.location.toString());

    XVec2 location = player1.location;
    Directions motionStatus = player1.motionStatus;

    // @GUI_API setPlayerLocationOnGUI: set the image location of the player
    // Parameters:
    // @param XVec2 Location
    // @param enum Directions motionStatus: the current moving direction of
    // player:
    // LEFT, RIGHT, UP, DOWN, NONE.
    // GameingWindow.setPlayerLocationOnGUI(location.x, location.y,
    // motionStatus);
    // or GameingWindow.setPlayerLocationOnGUI(location, motionStatus);
    MainPanel.setPlayerLocation(location.x, location.y, motionStatus);

    // @GUI_API setPlayerLocation: set the image location of the player
    // Parameters:
    // @param XVec2 Location.
    // @param enum Directions motionStatus.
    // GameingWindow.setPlayer2OnGUI(location, motionStatus);

  }

  // function: updatePlayerStatus
  // check the player's status after this move
  private void updatePlayerStatus()
  {

    for( GameBar eachbar : allBars )
    {
      Constants.Directions direction = player1.checkCollision(eachbar);

      if( direction != Constants.Directions.NONE )
      {
        System.out.println("collision find with: " + eachbar.toString());
        System.out.println(", block players in " + direction.toString());
        System.out.println(" in " + direction.toString());

        switch ( direction )
        {
          case LEFT:
            if( player1.velocity.x < 0 )
              player1.velocity.x = 0;
            break;
          case RIGHT:
            if( player1.velocity.x > 0 )
              player1.velocity.x = 0;
            break;
          case UP:
            if( player1.velocity.y < 0 )
              player1.velocity.y = 0;
            break;
          case DOWN:
            if( player1.velocity.y > 0 )
              player1.velocity.y = eachbar.velocity.y;
            break;
          default:
            System.err.println("error");
            break;
        }
        if( eachbar.barType == Constants.barTypeEnum.KILLLING )
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
    if( allBars.isEmpty() == true )
    {
      allBars.add(new GameBar());
      return;
    }

    DebugWindow.bar1.setText(allBars.getFirst().location.toString());

    if( allBars.size() < Constants.MAXIMUM_BAR_NUM
        && allBars.getLast().location.y < Constants.STAGE_HEIGHT
            - Constants.BAR_VERTICAL_DISTANCE )
    {
      allBars.add(new GameBar());
    }

    if( allBars.getFirst().location.y < 0 )
    {
      allBars.removeFirst();
      // @ GUI_API
      // deleteBar();
      
    }

  }

  // --------------------------------------------------------------------------
  // the engine adapt these listener from the game frame:
  // callback functions, change player velocity
  public static void keyPressed(int keyCode)
  {
    if( keyCode == KeyEvent.VK_RIGHT )
    {
      System.out.println("right moving");

      player1.velocity.x = Constants.PLAYER_HORIZONTAL_SPEED;
    }
    else if( keyCode == KeyEvent.VK_LEFT )
    {
      System.out.println("left moving");

      player1.velocity.x = -Constants.PLAYER_HORIZONTAL_SPEED;
    }
    else if( keyCode == KeyEvent.VK_UP )
    {
      System.out.println("upward moving");

      player1.velocity.y = Constants.PLAYER_JUMP_SPEED;
    }
  }


  public static void keyReleased(int keyCode)
  {
    if( keyCode == KeyEvent.VK_RIGHT )
    {
      System.out.println("right moving stoped");

      player1.velocity.x = 0;
    }
    else if( keyCode == KeyEvent.VK_LEFT )
    {
      System.out.println("left moving stoped");

      player1.velocity.x = 0;
    }
    else if( keyCode == KeyEvent.VK_UP )
    {
      System.out.println("upward moving stoped");

      player1.velocity.y = Constants.PLAYER_DROP_SPEED;
    }
  }

}
