package doodledrop.control;

import java.awt.Dimension;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.WindowConstants;

import doodledrop.GameLogic;
import doodledrop.db.Player;
import doodledrop.db.ScoreBoard;
import doodledrop.control.EndingWin;
import doodledrop.control.OpeningWin;

public class MainControl
{
  public static OpeningWin openingWin = new OpeningWin();
  public static EndingWin endingWin = new EndingWin();;
  public static GameLogic gameEngine;
  
  public static Player resigteredPlayer;
  public static Boolean startGame = true;
  public final static Lock rpLock = new ReentrantLock();
  public final static Lock sgLock = new ReentrantLock();
  public final static Condition rpNotNull  = rpLock.newCondition(); 
  public final static Condition sgNotNull = sgLock.newCondition(); 
  
  public static void main(String[] args) throws InterruptedException
  {
    startOpeningWin();
    rpLock.lock();
    try {
      while (openingWin.getResigteredPlayer() == null){
        rpNotNull.await();
      }
    } finally {
      resigteredPlayer = openingWin.getResigteredPlayer();
      rpLock.unlock();
    }
    /*while (resigteredPlayer == null){
      resigteredPlayer = openingWin.getResigteredPlayer();
    }*/
    closeOpeningWin();
    System.out.println("#in control: registered " + openingWin.getResigteredPlayer());
    while (startGame){
      runGame();
      System.out.println("#in control: end of running game");
      sgLock.lock();
      //startGame = null;
      startEndingWin();
      try {
        while (endingWin.ifStartGame() == null){
          sgNotNull.await();
        }
      } finally {
        startGame = endingWin.ifStartGame();
        sgLock.unlock();
      }
      /*while (startGame == null){
        startGame = endingWin.ifStartGame();
      }*/
      closeEndingWin();
      System.out.println("#in control: player choose to " + (startGame ? "" : "not") + " play again.");      
    }
    System.out.println("#in control: Exiting game.");
  }
  
  public static void startOpeningWin(){
    openingWin.setMinimumSize(new Dimension(600, 600));
    openingWin.pack();
    openingWin.setVisible(true);
    openingWin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
  
  public static void closeOpeningWin(){
    openingWin.dispose();
  }
  
  public static void startEndingWin(){
    endingWin.resetStartGame();
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
  
  public static void setCurrentPlayerWin(){
    ScoreBoard.setWin(resigteredPlayer);
  }
  
  public static void setCurrentPlayerLose(){
    ScoreBoard.setLose(resigteredPlayer);
  }
}
