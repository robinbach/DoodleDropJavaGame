package doodledrop.doodledropopen;

import doodledrop.GameLogic;
import doodledrop.doodledropopen.opening;
import doodledrop.doodledropopen.ending;



import javax.swing.*;

import java.awt.*;


public class main {
	public static RunOpening runOpening;
	public static RunGameEngine runGameEngine;
	public static RunEnding runEnding;
	
	
  public static void main(String[] args)
  {
    runOpening = new RunOpening();
    runGameEngine = new RunGameEngine();
    runEnding = new RunEnding();
    runOpening.setRunGameEngine(runGameEngine);
    runGameEngine.setRunEnding(runEnding);
    runOpening.run();
  }
}
