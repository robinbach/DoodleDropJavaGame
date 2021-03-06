package doodledrop.control;

import java.awt.Dimension;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.WindowConstants;

import doodledrop.GameLogic;
import doodledrop.db.Player;
import doodledrop.control.EndingWin;
import doodledrop.control.OpeningWin;

public class ControlTest
{
  public static OpeningWin openingWin;
  public static EndingWin endingWin;
  public static GameLogic gameEngine;
  
  public static Player resigteredPlayer;
  public static Boolean startGame = true;
  public static final Lock startGameLock = new ReentrantLock();
  public static final Condition startGameNotSet  = startGameLock.newCondition();
  
  public static void main(String[] args)
  {
    startOpeningWin();
    while (resigteredPlayer == null){
      resigteredPlayer = openingWin.getResigteredPlayer();
    }
    closeOpeningWin();
    System.out.println("#in control: registered��" + openingWin.getResigteredPlayer());
    while (startGame){
      runGame();
      System.out.println("#in control: end of running game");
      startGame = null;
      startEndingWin();
      while (startGame == null){
        startGame = endingWin.ifStartGame();
      }
      closeEndingWin();
      System.out.println("#in control: player choose to " + (startGame ? "" : "not") + " play again.");      
    }
    System.out.println("#in control: Exiting game.");
  }
  
  public static void startOpeningWin(){
    openingWin = new OpeningWin();
    openingWin.setMinimumSize(new Dimension(600, 600));
    openingWin.pack();
    openingWin.setVisible(true);
    openingWin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
  
  public static void closeOpeningWin(){
    openingWin.dispose();
  }
  
  public static void startEndingWin(){
    endingWin = new EndingWin();
    endingWin.setMinimumSize(new Dimension(600, 600));
    endingWin.pack();
    endingWin.setVisible(true);
    endingWin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
  
  public static void closeEndingWin(){
    endingWin.dispose();
  }
  
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
