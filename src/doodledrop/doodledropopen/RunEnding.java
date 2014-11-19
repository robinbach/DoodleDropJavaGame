package doodledrop.doodledropopen;

import java.awt.Dimension;

import javax.swing.WindowConstants;

import doodledrop.GameLogic;

public class RunEnding extends Thread
{
  public static ending gameEnding;
  
  public void run(){
    System.out.println("here");
    gameEnding = new ending();
    System.out.println("here1");
    gameEnding.setMinimumSize(new Dimension(600, 600));
    gameEnding.pack();
    gameEnding.setVisible(true);
    gameEnding.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    System.out.println("here2");
  }
}
