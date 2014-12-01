package doodledrop;

import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameKbdListener extends KeyAdapter
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
      try
      {
        ((Window) event.getSource()).dispose();
      }
      catch( ClassCastException e )
      {

      }
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
      try
      {
        ((Window) event.getSource()).dispose();
      }
      catch( ClassCastException e )
      {

      }
    }
    else if( event.getKeyCode() == KeyEvent.VK_K )
    {
      System.out.println("Player Killed");
      try
      {
        ((Window) event.getSource()).dispose();
      }
      catch( ClassCastException e )
      {

      }
    }
    else if( event.getKeyCode() == KeyEvent.VK_W )
    {
      System.out.println("Player win");
      try
      {
        ((Window) event.getSource()).dispose();
      }
      catch( ClassCastException e )
      {

      }
    }
    else if( event.getKeyCode() == KeyEvent.VK_P )
    {
      System.out.println("Player pause");
    }
  }
}
