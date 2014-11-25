package doodledrop.doodledropopen;

import doodledrop.GameLogic;

public class RunGameEngine extends Thread{
  public RunEnding runEnding;
  public GameLogic gameEngine;
  
  public void run(){
    gameEngine = new GameLogic();
    //gameEngine.setRunEnding(this.runEnding);
    gameEngine.gameInit();
    gameEngine.gamePlaying();
    gameEngine.gameExit();
  }
  
  public void setRunEnding(RunEnding _runEnding){
    this.runEnding = _runEnding;
  }
}