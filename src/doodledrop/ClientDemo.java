package doodledrop;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import doodledrop.Constants.Directions;

public class ClientDemo
{
  public static ClientSock firstClient;
  public static String recvString;
  
  public static class KeyListen extends KeyAdapter
  {
    public void keyPressed(KeyEvent e)
    {
      if(e.getKeyChar() == 'w')
      {  
        System.out.println("press W");
        firstClient.sendString("w");
      }
      else if(e.getKeyChar() == 's')
        firstClient.sendString("s");
      else if(e.getKeyChar() == 'a')
        firstClient.sendString("a");
      else if(e.getKeyChar() == 'd')
        firstClient.sendString("d");
    }
  }
  
  public static void main(String [] argv)
  {
    
  /*  JFrame windows;
    JButton leftButton;
    JButton rightButton;
    windows = new JFrame("testFrame");
    windows.setSize(220, 150);
    windows.setLayout(new BorderLayout());
    leftButton = new JButton("left");
    rightButton = new JButton("right");
    windows.add(leftButton, BorderLayout.WEST);
    windows.add(rightButton, BorderLayout.EAST);
    windows.setVisible(true);
    windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
    /*leftButton.addKeyListener(new KeyListen());*/
    firstClient = new ClientSock("192.168.146.140", 45000);
    firstClient.startClient();
    firstClient.sendString("Hello to the server");
    firstClient.sendInfo(new XVec2(12, 23), Directions.LEFT);
    //recvString = firstClient.recvString();
    //System.out.println("Received this message from server: " + recvString);
  }
}
