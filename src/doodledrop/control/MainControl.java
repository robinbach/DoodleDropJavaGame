package doodledrop.control;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.WindowConstants;

import doodledrop.Constants;
import doodledrop.GameLogic;
import doodledrop.MainMusic;
import doodledrop.db.IpManager;
import doodledrop.db.IpManager.IpInstance;
import doodledrop.db.Player;
import doodledrop.db.ScoreBoard;
import doodledrop.control.EndingWin;
import doodledrop.control.OpeningWin;

public class MainControl
{
  public static OpeningWin openingWin = new OpeningWin();
  public static EndingWin endingWin = new EndingWin();;
  public static GameLogic gameEngine;
  
  
  private static Player resigteredPlayer;
  private static Boolean playerWin;
  private static Boolean startGame = true;
  private static Boolean forceQuit = false;
  public static IpInstance ipInstance;
  public final static Lock rpLock = new ReentrantLock();
  public final static Lock sgLock = new ReentrantLock();
  public final static Condition rpNotNull  = rpLock.newCondition(); 
  public final static Condition sgNotNull = sgLock.newCondition(); 
  
  public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException
  {
    BGM.setupBgm();
    startOpeningWin();
    rpLock.lock();
    try {
      while ((openingWin.getResigteredPlayer() == null)
          ||(Constants.IsMultiPlayer && openingWin.getIpInstance().ipaddress == null)){
        if (forceQuit){
          BGM.stop();
          return;
        }
        rpNotNull.await();
      }
    } finally {
      resigteredPlayer = openingWin.getResigteredPlayer();
      ipInstance = openingWin.getIpInstance();
      rpLock.unlock();
    }
    closeOpeningWin();
    System.out.println("#in control: registered " + openingWin.getResigteredPlayer());
    while (startGame){
      runGame();
      System.out.println("#in control: end of running game");
      sgLock.lock();
      startEndingWin();
      storeCurrentPlayerWin();
      try {
        while (endingWin.ifStartGame() == null){
          sgNotNull.await();
        }
      } finally {
        startGame = endingWin.ifStartGame();
        sgLock.unlock();
      }
      closeEndingWin();
      System.out.println("#in control: player choose to " + (startGame ? "" : "not") + " play again.");      
    }
    System.out.println("#in control: Exiting game.");
    BGM.stop();
  }
  
  public static void startOpeningWin(){
    BGM.start();
    openingWin.setMinimumSize(new Dimension(600, 600));
    openingWin.pack();
    openingWin.setVisible(true);
    openingWin.setResizable(false);
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
    endingWin.setResizable(false);
    endingWin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
  
  public static void closeEndingWin(){
    endingWin.dispose();
  }
  
  public static void runGame()
  {
    BGM.start();    
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
  
  public static void setCurrentPlayerWin(boolean isWin){
    playerWin = isWin;
    if (isWin){
      endingWin.setWinLosePic(true);
    } else {
      endingWin.setWinLosePic(false);
    }
  }
  
  private static void storeCurrentPlayerWin(){
    if (playerWin){
      ScoreBoard.setWin(resigteredPlayer);
    } else {
      ScoreBoard.setLose(resigteredPlayer);
    }
  }

  public static void setForceQuit(){
    System.out.println("#in control: Force quiting¡£");
    forceQuit = true;
  }
  
  
  
  public static class BGM{
    private static Clip bgm;
    private static Boolean enableBgm = true;
    
    public static void setupBgm() throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException{
      File soundFile = new File(MainMusic.class.getClassLoader().getResource("bgm/call_cut.wav").toURI());
      AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);

      // load the sound into memory (a Clip)
      DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
      bgm = (Clip) AudioSystem.getLine(info);
      bgm.open(sound);
    }
    
    public static void stop(){
      bgm.stop();
    }
    
    public static void start(){
      if ((!bgm.isRunning()) && enableBgm){
        bgm.loop(Clip.LOOP_CONTINUOUSLY);
      }
    }
    
    public static void setVolumn(double gain){
      // gain must be a number between 0 and 1
      FloatControl gainControl = (FloatControl) bgm.getControl(FloatControl.Type.MASTER_GAIN);
      float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
      gainControl.setValue(dB);
    }
    
    public static void setEnable(boolean enable){
      if (enable){
        enableBgm = true;
        start();
      }else{
        enableBgm = false;
        stop();
      }
    }
    
    public static Boolean isEnabled(){
      return enableBgm;
    }
  }
}
