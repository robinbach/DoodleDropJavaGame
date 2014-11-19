package doodledrop;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import doodledrop.doodledropopen.RunEnding;

public class GamingWindow extends JFrame
{
  /**
   * for safety
   */
  private static final long serialVersionUID = 1L;
  
  public static final int stageLength = 100;
  public static final int stageWidth = 100;

  public RunEnding runEnding;
  
  static JLabel position;
  
  public void setRunEnding(RunEnding _runEnding){
    this.runEnding = _runEnding;
  }

  public class WindowOnDispose extends WindowAdapter{
    public void windowClosed(WindowEvent e){
      System.out.println("hey");
      //open the ending window
      runEnding = new RunEnding();
      runEnding.start();
      //runEnding.run();
    }
  }
  
  public class KeyboardListener extends KeyAdapter
  {
    public void keyPressed(KeyEvent event)
    {
      System.out.println("pressed");

      GameLogic.keyPressed(event.getKeyCode());
      
      if( event.getKeyCode() == KeyEvent.VK_RIGHT )
      {
        System.out.println("right pressed");
      }
      else if( event.getKeyCode() == KeyEvent.VK_LEFT )
      {
        System.out.println("left pressed");
      }
      else if( event.getKeyCode() == KeyEvent.VK_Q )
      {
        System.out.println("Quit pressed");
        GamingWindow.this.dispose();
      }
    }
    
    public void keyReleased(KeyEvent event)
    {
      System.out.println("released");

      GameLogic.keyReleased(event.getKeyCode());
      
      if( event.getKeyCode() == KeyEvent.VK_RIGHT )
      {
        System.out.println("right released");
//        player1.set_velocity(1, 0);
      }
      else if( event.getKeyCode() == KeyEvent.VK_LEFT )
      {
        System.out.println("left released");
//        player1.set_velocity(-1, 0);
      }
      else if( event.getKeyCode() == KeyEvent.VK_Q )
      {
        System.out.println("Quit released");
        GamingWindow.this.dispose();
      }
    }  }
  
  
  public GamingWindow()
  {
    super("Doodle _(:3Z)_ Drop");
    
    setMinimumSize(new Dimension(300, 160));
    position = new JLabel();
    add(position);
    setVisible(true);
    //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
    addKeyListener(new KeyboardListener());
    addWindowListener(new WindowOnDispose());
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

  }
}
