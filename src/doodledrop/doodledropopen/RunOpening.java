package doodledrop.doodledropopen;

import java.awt.Dimension;

import javax.swing.WindowConstants;

public class RunOpening extends Thread
{
  public RunGameEngine runGameEngine;
  public opening win;  
  
  public void setRunGameEngine(RunGameEngine _runGameEngine){
    this.runGameEngine = _runGameEngine;
  }
  
  public void run(){
    win = new opening();
    win.setRunGameEngine(runGameEngine);
    win.setMinimumSize(new Dimension(600, 600));
    win.pack();
    win.setVisible(true);
    win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

}
