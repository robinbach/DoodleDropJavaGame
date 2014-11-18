package doodledrop;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class GamingWindow extends JFrame
{
  /**
   * for safety
   */
  private static final long serialVersionUID = 1L;
  
  public static final int stageLength = 100;
  public static final int stageWidth = 100;


  
  static JLabel position;

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
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
    addKeyListener(new KeyboardListener());
    
    setDefaultCloseOperation(EXIT_ON_CLOSE);

  }
}
