package doodledrop;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

import doodledrop.Constants.Directions;
import doodledrop.control.MainControl;

public class GameLogic extends Thread implements Runnable
{

  // static boolean gameRunning;
  private int delay;
  static int updateNums;

  // GuiClassMainMenu mainMenu;
  static GamePlayer player1, player2;
  static LinkedList<GameBar> allBars;

  private DebugWindow debugMenu;
  MainPanel mainPanel;
  MainMusic mainMusic;
  PlayerSocket playerSocket;
  
  public static boolean paused;
  static boolean isMulti;
  private static boolean isWinner;
  
  static private int score;
  static private int clientFrameNum;

  

  // --------------------------------------------------------------------------
  // constructor and the four master functions:
  // constructor
  // game initialize
  // game playing: will call game updating in loop;
  // game updating
  // game exiting

  public GameLogic()
  {
    isMulti = Constants.IsMultiPlayer;
    score = 0;
    paused = false;
  }
  
  public GameLogic(boolean isMulti_in)
  {
    isMulti = isMulti_in;
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
    mainMusic = new MainMusic();
    MainPanel.setBloodBar(3);

    isWinner = false;
    

    // initiate Bars:
    allBars = new LinkedList<GameBar>();
    GameBar.barIDCounter = 0;
    GameBar.rand = new Random(1);

    // initiate Players:
    player1 = new GamePlayer();
    player2 = new GamePlayer();

    if(isMulti)
    {
      if(Constants.IsServer)
      {
        MainPanel.playerInitial(1, player1.location);
        MainPanel.playerInitial(2, player1.location);

      }
      else
      {
        MainPanel.playerInitial(2, player1.location);
        MainPanel.playerInitial(1, player2.location);
      }
    }
    else
    {
      MainPanel.playerInitial(1, player1.location);
    }


    // initiate thread delay time
    delay = Constants.FRAME_DELAY;

    // initialize the frame counter, for debug.
    updateNums = 0;
    
    if( isMulti )
    {
      // initialize the network connection with another player
      // established a thread for updating info of player2.
      playerSocket = new PlayerSocket(MainControl.ipInstance.ipaddress,Constants.SERVER_PORT); //Constants.SERVER_IP
      if(Constants.IsServer)
      {
        playerSocket.startServer();
      }
      else
      {
        playerSocket.startClient();
      }
      Thread networkThread = new Thread(playerSocket);
      networkThread.start();
    }    
    // should block until when the connection is established.
  }

  // @Network_API
  // call this function when updating in Networking thread
  static public void updatePlayer2Info(XVec2 location, Directions motionStatus, int frameNumIN)
  {
    System.out.println("receiving player info and updating" + location.toString());
    player2.location = location;
    player2.motionStatus = motionStatus;
    clientFrameNum = frameNumIN;
    if(location.y == 0 && motionStatus == Directions.NONE)
    {
      isWinner = true;
    }
  }

//  // @Network_API
//  // call this function when updating in Networking thread
//  static public void player2Lose()
//  {
//    isWinner = true;
//  }
  
  public void gamePlaying()
  {
    player1.isAlive = true;
    System.out.println("game running");

    debugMenu = new DebugWindow();
    allBars.add(new GameBar());


    try
    {
      sleep(Constants.GAME_PREPARE_TIME);
    }
    catch( InterruptedException e )
    {
      e.printStackTrace();
    }


    // the main updating loop:
    while( (!isMulti && player1.isAlive) || //single player
        (isMulti && player1.isAlive && isWinner == false) ) //multiple player
    {
      while(paused)
      {
        try
        {
          sleep(Constants.GAME_PAUSE_TIME);
          //where the listener occur
        }
        catch( InterruptedException e )
        {
          e.printStackTrace();
        }
      }
      
      updateNums++;
      gameUpdate();
      

      
      while(player1.isAlive && isWinner == false 
          && isMulti && updateNums - clientFrameNum > Constants.DELAY_CONTROL)
      {
        try
        {
          sleep(Constants.OFFSET_FRAME * delay);
          //where the listener occur
        }
        catch( InterruptedException e )
        {
          e.printStackTrace();
        }
      }
      
    }
  }

  public void gameUpdate()
  {
    System.out.println("@@@@@@---------");
    System.out.println("game updating" + updateNums);

    
    try
    {
      sleep(delay);
      //where the listener occur
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

    // dispose windows
    debugMenu.dispose();
    MainPanel.gameEnding();
    
    // set winners
    // whoever ends first is loser
    // end either current player died or the other player died
    // if died send signal to the other player to stop
    // send timestamp as well in case of network delay, i.e. died after the other already died
    
    MainControl.setCurrentPlayerWin(isWinner);
    if (isWinner){  // if (player1.isAlive || otherDeathTimestamp_earlier_than_currtimestamp)
      MainControl.BGM.stop();
      mainMusic.playWin();
    } else {
      MainControl.BGM.stop();
      mainMusic.playLose();
    }
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
      // int barID = eachbar.barID;
      // XVec2 location = eachbar.location;
      barLocationList.add(new XVec2(eachbar.location.x, eachbar.location.y));
      eachbar.move();
    }

    // @GUI_API movebarB: set the image location of each bar,
    // or:
    // GUI.drawAllBarOnCanvas(GameBar[] allBars)
    // where each has member attribute as barID and location

    MainPanel.updateBarLocation(barLocationList);
    // updateBarLocation(Vector<XVec2> barLocationList);
  }

  public void movePlayers()
  {
    player1.move();

    // for testing
    DebugWindow.position.setText(player1.location.toString());
    DebugWindow.health.setText("health: " + player1.healthPoint);
    DebugWindow.score.setText("score: " + score);


//    XVec2 location = player1.location;
//    Directions motionStatus = player1.motionStatus;

    // @GUI_API setPlayerLocationOnGUI: set the image location of the player
    // Parameters:
    // @param XVec2 Location
    // @param enum Directions motionStatus: the current moving direction of
    // player:
    // LEFT, RIGHT, UP, DOWN, NONE.
    // GameingWindow.setPlayerLocationOnGUI(location.x, location.y,
    // motionStatus);
    // or GameingWindow.setPlayerLocationOnGUI(location, motionStatus);  
    MainPanel.setBloodBar(player1.healthPoint*3/Constants.PLAYER_HEALTH);
    MainPanel.setScoreLabel(score);
    // @GUI_API setPlayerLocation: set the image location of the player
    // Parameters:
    // @param XVec2 Location.
    // @param enum Directions motionStatus.

    if(isMulti)
    {
      if(Constants.IsServer)
      {
        MainPanel.setPlayerLocation(player1.location.x, player1.location.y, 1,
            player1.motionStatus);
        MainPanel.setPlayerLocation(player2.location.x, player2.location.y, 2,
            player2.motionStatus);
      }
      else
      {
        MainPanel.setPlayerLocation(player1.location.x, player1.location.y, 2,
            player1.motionStatus);
        MainPanel.setPlayerLocation(player2.location.x, player2.location.y, 1,
            player2.motionStatus);
      }
      System.out.println("sending player information");
      playerSocket.sendInfo(player1.location, player1.motionStatus, updateNums);//@Network_API#
    }
    else
    {
      MainPanel.setPlayerLocation(player1.location.x, player1.location.y, 1,
          player1.motionStatus);
    }

  }

  // function: updatePlayerStatus
  // check the player's status after this move
  private void updatePlayerStatus()
  {
    int barIndexFromTop = 0;
    score ++;

    if(player1.location.y < 0) 
    {
      player1.healthPoint -= 10; 
    }
    
    if(player1.location.y > Constants.STAGE_HEIGHT )
    {
      player1.healthPoint = 0; 
    }
    // @_DIE, only place that player die.
    if( player1.healthPoint < 5 )
    {
      System.out.println(" which kills players");
      player1.isAlive = false;
      
      // setPlayerLocation: set the image location of the player
      if(isMulti)
        playerSocket.sendInfo(new XVec2(0 , 0), Constants.Directions.NONE, updateNums);//@Network_API#

      return;
    }
    
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
          case DOWN:
            if( player1.velocity.y > 0 )
            {
              player1.velocity.y = eachbar.velocity.y;
            }
            switch(eachbar.barType)
            {
              case DISAPPEAR:
                // animation(int barIndexFromTop);
                if(eachbar.heat <= 0)
                {
                  eachbar.collision.set(0, 0);
                  MainPanel.barCollision(barIndexFromTop);
                  mainMusic.playDisappear();
                }
                eachbar.heat--;
                break;
              case KILLLING:
                System.out.println(" which kills players");
                if(!eachbar.musicPlayed)
                  mainMusic.playKilling();
                eachbar.musicPlayed = true;

                player1.healthPoint--;
//                player1.isAlive = false;
                break;
              case SPRING:
                  mainMusic.playSprint();
                // animation(int barIndexFromTop);
                // eachbar.collision.set(0, 0);
                System.out.println("spring jumping");
                player1.inertia.y += -Constants.SPRING_BAR_POWER;
                MainPanel.barCollision(barIndexFromTop);
                break;
              case TURNINGRIGHT:
                if(!eachbar.musicPlayed)
                  mainMusic.playNormal();
                eachbar.musicPlayed = true;
                player1.inertia.x -= Constants.TURNING_BAR_POWER;
                break;
              case TURNINGLEFT:
                if(!eachbar.musicPlayed)
                  mainMusic.playNormal();
                eachbar.musicPlayed = true;
                player1.inertia.x += Constants.TURNING_BAR_POWER;
                break;
              default:
                if(!eachbar.musicPlayed)
                  mainMusic.playNormal();
                eachbar.musicPlayed = true;
                break;
            }
            //@ GUI_API step on a specific bar
            // call animation method in GUI
            break;
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
          default:
            System.err.println("error");
            break;
        }
      }
      barIndexFromTop++;
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
      // remove bar from GUI window
      MainPanel.deleteBar();
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
//    else if( keyCode == KeyEvent.VK_UP )
//    {
//      System.out.println("upward moving");
//
//      player1.velocity.y = Constants.PLAYER_JUMP_SPEED;
//    }
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
    else if( keyCode == KeyEvent.VK_K )
    {
      player1.healthPoint = 0;
      System.out.println("player killed in gamelogic");
    }
    else if( keyCode == KeyEvent.VK_W )
    {
      isWinner = true;
      System.out.println("player wined in gamelogic");
    }
    else if( keyCode == KeyEvent.VK_P )
    {
      if(!GameLogic.paused)
      {
        paused = true;
      }
      else
      {
        GameLogic.paused = false;
      }

    }
  }

}
