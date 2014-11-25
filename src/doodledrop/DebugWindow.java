package doodledrop;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class DebugWindow extends JFrame
{
  /**
   * for safety
   */
  private static final long serialVersionUID = 1L;

  static JLabel position, bar1;

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
        DebugWindow.this.dispose();
      }
    }

    public void keyReleased(KeyEvent event)
    {
      System.out.println("released");

      GameLogic.keyReleased(event.getKeyCode());

      if( event.getKeyCode() == KeyEvent.VK_RIGHT )
      {
        System.out.println("right released");
        // player1.set_velocity(1, 0);
      }
      else if( event.getKeyCode() == KeyEvent.VK_LEFT )
      {
        System.out.println("left released");
        // player1.set_velocity(-1, 0);
      }
      else if( event.getKeyCode() == KeyEvent.VK_Q )
      {
        System.out.println("Quit released");
        DebugWindow.this.dispose();
      }
    }
  }


  public DebugWindow()
  {
    super("Doodle _(:3Z)_ Drop");

    setMinimumSize(new Dimension(400, 120));

    setLayout(new FlowLayout());
    JLabel positionLabel = new JLabel("player's location: ");
    add(positionLabel);
    position = new JLabel();
    add(position);

    JLabel barLabel = new JLabel("Top bar's location: ");
    add(barLabel);

    bar1 = new JLabel();
    add(bar1);

    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    addKeyListener(new KeyboardListener());

    setDefaultCloseOperation(EXIT_ON_CLOSE);

  }
}
