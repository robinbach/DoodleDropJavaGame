package doodledrop;

import doodledrop.Constants;
import doodledrop.Constants.barTypeEnum;

import java.util.Vector;

import javax.swing.*;

public class MainPanel {

    private static JFrame MainFrame;

    private static JPanel MainPanel;

    private static ImageIcon background;

    private static ImageIcon player1Icon, player2Icon;
    
    private static JLabel player1Label, player2Label;
    
    private static Constants.Directions direction1[], direction2[];
    
    private static String player1PicString, player2PicString, barPicString;
    
    private static Vector<ibar> barVector;
    
    public static void main(String[] args) {
        new MainPanel();
    }
    
    /*
     * Constructor of MainPanel
     */
    public MainPanel() {
    	//initialize
    	barVector = new Vector<ibar>();
    	//get contentPane
    	MainFrame = new JFrame("");
      MainPanel = (JPanel) MainFrame.getContentPane();
      MainFrame.setContentPane(MainPanel);
      MainPanel.setOpaque(false);
      MainPanel.setLayout(null);
      
      //System.out.println(playerIcon.getIconWidth() + "," + playerIcon.getIconHeight());

      MainFrame.getLayeredPane().setLayout(null);
      //set background picture
      background = new ImageIcon(getClass().getClassLoader().getResource("image/background/background.png"));
      JLabel label = new JLabel(background);
      label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
      MainFrame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
      //mainframe settings
      MainFrame.addKeyListener(new GameKbdListener());
      MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      MainFrame.setSize(background.getIconWidth(), background.getIconHeight());
      MainFrame.setResizable(false);
      MainFrame.setVisible(true); 
    }
    
    /*
     * Destrucor of MainPanel, call at the end of game.
     */
    public static void gameEnding()
    {
      MainFrame.dispose();
    }
    
    /*
     * Player initialization
     * @param palyer1or2 
     *      Choose player1 or player2
     * @param iniLocation 
     *      the initial location of the player
     */
    public static void playerInitial(int player1or2, XVec2 iniLocation)
    {
      if(player1or2 == 1)
      {
        direction1 = new Constants.Directions[3];
        direction1[0] = direction1[1] = direction1[2] = Constants.Directions.NONE;
        player1PicString = "image/characterpic/stand0.png";
        player1Icon = new ImageIcon(MainPanel.class.getClassLoader().getResource(player1PicString));
        player1Label = new JLabel(player1Icon);
        MainPanel.add(player1Label);
        player1Label.setBounds(0, 0, player1Icon.getIconWidth(),player1Icon.getIconHeight());
        player1Label.setLocation(iniLocation.x, iniLocation.y);  
      }else 
      {
        direction2 = new Constants.Directions[3];
        direction2[0] = direction2[1] = direction2[2] = Constants.Directions.NONE;
        player2PicString = "image/characterpic2/stand0.png";
        player2Icon = new ImageIcon(MainPanel.class.getClassLoader().getResource(player2PicString));
        player2Label = new JLabel(player2Icon);
        MainPanel.add(player2Label);
        player2Label.setBounds(0, 0, player2Icon.getIconWidth(),player2Icon.getIconHeight());
        player2Label.setLocation(iniLocation.x, iniLocation.y);  
      }
      
    }
    
    
    public static class ibar
    {
      JLabel barLabel;
      int barID;
      public ibar(ImageIcon barimage, int inbarID)
      {
        barLabel = new JLabel(barimage);
        barID = inbarID;
      }
    }
    
    public static void setPlayerLocation(int x, int y, int player1or2, Constants.Directions indirection)
    {
      if(player1or2 == 1)
      {
        direction1[2] = direction1[1];
        direction1[1] = direction1[0];
        direction1[0] = indirection;
        setPlayerPictureStr();
        player1Icon = new ImageIcon(MainPanel.class.getClassLoader().getResource(player1PicString));
        player1Label.setIcon(player1Icon);
        player1Label.setLocation(x, y);
      }else
      {
        direction2[2] = direction2[1];
        direction2[1] = direction2[0];
        direction2[0] = indirection;
        setPlayer2PictureStr();
        player2Icon = new ImageIcon(MainPanel.class.getClassLoader().getResource(player2PicString));
        player2Label.setIcon(player2Icon);
        player2Label.setLocation(x, y);
      }
    }
    
    public static void refreshPanel()
    {
      MainPanel.repaint();
    }
    
    
    public static void setPlayerPictureStr()
    {
      // set player1PicStr
      if(direction1[0] == Constants.Directions.LEFT)
      {
        if(direction1[1] == Constants.Directions.LEFT)
        {
          if(direction1[2] == Constants.Directions.LEFT)
          {
            if(player1PicString == "image/characterpic/left0.png")
            {
              player1PicString = "image/characterpic/left1.png";
            }else if(player1PicString == "image/characterpic/left1.png")
            {
              player1PicString = "image/characterpic/left2.png";
            }else
            {
              player1PicString = "image/characterpic/left0.png";
            }
          }else 
          {
            player1PicString = "image/characterpic/left1.png";
        }
        }else 
        {
          player1PicString = "image/characterpic/left0.png";
      }
      }else if(direction1[0] == Constants.Directions.RIGHT)
      {
        if(direction1[1] == Constants.Directions.RIGHT)
        {
          if(direction1[2] == Constants.Directions.RIGHT)
          {
            if(player1PicString == "image/characterpic/right0.png")
            {
              player1PicString = "image/characterpic/right1.png";
            }else if(player1PicString == "image/characterpic/right1.png")
            {
              player1PicString = "image/characterpic/right2.png";
            }else
            {
              player1PicString = "image/characterpic/right0.png";
            }
          }else 
          {
            player1PicString = "image/characterpic/right1.png";
        }
        }else 
        {
          player1PicString = "image/characterpic/right0.png";
      }
      }else if(direction1[0] == Constants.Directions.DOWN)
      {
        if(direction1[1] == Constants.Directions.DOWN)
        {
          player1PicString = "image/characterpic/jump0.png";
        }else 
        {
        player1PicString = "image/characterpic/jump1.png";
      } 
      }else if(direction1[0] == Constants.Directions.UP)
      {
        player1PicString = "image/characterpic/stand0.png";
      }else if(direction1[0] == Constants.Directions.NONE)
      {
        player1PicString = "image/characterpic/stand0.png";
      }  
    }
    
    public static void setPlayer2PictureStr()
    {
      //set player2PicStr
      if(direction2[0] == Constants.Directions.LEFT)
      {
        if(direction2[1] == Constants.Directions.LEFT)
        {
          if(direction2[2] == Constants.Directions.LEFT)
          {
            if(player1PicString == "image/characterpic2/left0.png")
            {
              player1PicString = "image/characterpic2/left1.png";
            }else if(player1PicString == "image/characterpic2/left1.png")
            {
              player1PicString = "image/characterpic2/left2.png";
            }else
            {
              player1PicString = "image/characterpic2/left0.png";
            }
          }else 
          {
            player1PicString = "image/characterpic2/left1.png";
        }
        }else 
        {
          player1PicString = "image/characterpic2/left0.png";
      }
      }else if(direction2[0] == Constants.Directions.RIGHT)
      {
        if(direction2[1] == Constants.Directions.RIGHT)
        {
          if(direction2[2] == Constants.Directions.RIGHT)
          {
            if(player1PicString == "image/characterpic2/right0.png")
            {
              player1PicString = "image/characterpic2/right1.png";
            }else if(player1PicString == "image/characterpic2/right1.png")
            {
              player1PicString = "image/characterpic2/right2.png";
            }else
            {
              player1PicString = "image/characterpic2/right0.png";
            }
          }else 
          {
            player1PicString = "image/characterpic2/right1.png";
        }
        }else 
        {
          player1PicString = "image/characterpic2/right0.png";
      }
      }else if(direction2[0] == Constants.Directions.DOWN)
      {
        if(direction2[1] == Constants.Directions.DOWN)
        {
          player1PicString = "image/characterpic2/jump0.png";
        }else 
        {
        player1PicString = "image/characterpic2/jump1.png";
      } 
      }else if(direction2[0] == Constants.Directions.UP)
      {
        player1PicString = "image/characterpic2/stand0.png";
      }else if(direction2[0] == Constants.Directions.NONE)
      {
        player1PicString = "image/characterpic2/stand0.png";
      }
    }
    
    public static String getBarPicString(barTypeEnum barType)
    {
      String barString = null;
      if(barType == Constants.barTypeEnum.NORMAL)
      {
        barString = "image/board/normal.png";
      }else if(barType == Constants.barTypeEnum.KILLLING)
      {
        barString = "image/board/killig.png";
      }else if(barType == Constants.barTypeEnum.SPRING)
      {
        barString = "image/board/spring0.png";
      }else if(barType == Constants.barTypeEnum.DISAPPEAR)
      {
        barString = "image/board/disappear0.png";
      }else if(barType == Constants.barTypeEnum.TURNING)
      {
        barString = "image/board/turning0.png";
      }
      
      return barString;
    }
    
    public static void creatBar(XVec2 location, Constants.barTypeEnum barType, int barID)
    {
      barPicString = getBarPicString(barType);
      ImageIcon barIcon = new ImageIcon(MainPanel.class.getClassLoader().getResource(barPicString));
      ibar inbar = new ibar(barIcon, barID);
      barVector.addElement(inbar);
      MainPanel.add(barVector.lastElement().barLabel);
      barVector.lastElement().barLabel.setBounds(0, 0, barIcon.getIconWidth(), barIcon.getIconHeight());
//      System.out.println(barIcon.getIconWidth() + ","+ barIcon.getIconHeight());

      barVector.lastElement().barLabel.setLocation(location.x, location.y);
    }
    
    public static void updateBarLocation(Vector<XVec2> locationVector)
    {
      for(int i = 0; i < barVector.size(); ++i)
      {
        barVector.elementAt(i).barLabel.setLocation(locationVector.elementAt(i).x, locationVector.elementAt(i).y);
      } 
    }
    
    public static void deleteBar()
    {
      barVector.elementAt(0).barLabel.setLocation(-100, -100);
      barVector.remove(0);
    }

}

