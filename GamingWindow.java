package doodledrop;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class GamingWindow extends JFrame
{

  
  
  public class KeyboardListener extends KeyAdapter
  {
    public void keyPressed(KeyEvent event)
    {
      System.out.println("pressed");

      GameLogic.keyPressed(event.getKeyCode());
      if( event.getKeyCode() == KeyEvent.VK_RIGHT )
      {
        System.out.println("right pressed");
//        player1.set_velocity(1, 0);
      }
      else if( event.getKeyCode() == KeyEvent.VK_LEFT )
      {
        System.out.println("left pressed");
//        player1.set_velocity(-1, 0);
      }
      else if( event.getKeyCode() == KeyEvent.VK_Q )
      {
        System.out.println("Quit pressed");
        GamingWindow.this.dispose();
      }
    }
  }
  
  
  public GamingWindow()
  {
    super("Doodle _(:3Z)_ Drop");
    
    setMinimumSize(new Dimension(120, 60));
    pack();
    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
    addKeyListener(new KeyboardListener());
    
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    


  }
}
