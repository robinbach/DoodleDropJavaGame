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
    
    private static String player1PicString, player2PicString;
    
    private static Vector<ibar> barVector;
    
    /**
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
    
    @SuppressWarnings("static-access")
    public static void main(String[] args)
    {
      MainPanel x= new MainPanel();
      XVec2 location = new XVec2(100, 100);
      x.creatBar(location, barTypeEnum.KILLLING , 1);
      Vector<XVec2> locationvec = new Vector<XVec2>();
      locationvec.addElement(new XVec2(100,300));
      x.updateBarLocation(locationvec);
      x.refreshPanel();
    }
    
    
    /**
     * Destrucor of MainPanel, call at the end of game.
     */
    public static void gameEnding()
    {
      MainFrame.dispose();
    }
    
    /**
     * refresh the MainPanel
     */
    public static void refreshPanel()
    {
      MainPanel.repaint();
    }
    
    
    /**
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
    
    /**
     * set the location of player1 or player2
     * 
     * @param x, y
     *      the location of player
     * @param player1or2
     * 
     * @param indrection
     *      the location of the player
     */
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
    
    /**
     * set player1PicStr
     */
    public static void setPlayerPictureStr()
    {
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
    
    /**
     * set player2PicStr
     */
    public static void setPlayer2PictureStr()
    {
      if(direction2[0] == Constants.Directions.LEFT)
      {
        if(direction2[1] == Constants.Directions.LEFT)
        {
          if(direction2[2] == Constants.Directions.LEFT)
          {
            if(player2PicString == "image/characterpic2/left0.png")
            {
              player2PicString = "image/characterpic2/left1.png";
            }else if(player2PicString == "image/characterpic2/left1.png")
            {
              player2PicString = "image/characterpic2/left2.png";
            }else
            {
              player2PicString = "image/characterpic2/left0.png";
            }
          }else 
          {
            player2PicString = "image/characterpic2/left1.png";
        }
        }else 
        {
          player2PicString = "image/characterpic2/left0.png";
      }
      }else if(direction2[0] == Constants.Directions.RIGHT)
      {
        if(direction2[1] == Constants.Directions.RIGHT)
        {
          if(direction2[2] == Constants.Directions.RIGHT)
          {
            if(player2PicString == "image/characterpic2/right0.png")
            {
              player2PicString = "image/characterpic2/right1.png";
            }else if(player2PicString == "image/characterpic2/right1.png")
            {
              player2PicString = "image/characterpic2/right2.png";
            }else
            {
              player2PicString = "image/characterpic2/right0.png";
            }
          }else 
          {
            player2PicString = "image/characterpic2/right1.png";
        }
        }else 
        {
          player2PicString = "image/characterpic2/right0.png";
      }
      }else if(direction2[0] == Constants.Directions.DOWN)
      {
        if(direction2[1] == Constants.Directions.DOWN)
        {
          player2PicString = "image/characterpic2/jump0.png";
        }else 
        {
        player2PicString = "image/characterpic2/jump1.png";
      } 
      }else if(direction2[0] == Constants.Directions.UP)
      {
        player2PicString = "image/characterpic2/stand0.png";
      }else if(direction2[0] == Constants.Directions.NONE)
      {
        player2PicString = "image/characterpic2/stand0.png";
      }
    }
    
    
    /**
     * class ibar
     * @member barLabel
     *      the JLabel for this bar
     * @member barID
     *      specific ID for this bar
     */
    public static class ibar
    {
      public Constants.barTypeEnum barType;
      public JLabel barLabel;
      public int barID, timestamps;
      public String picString;
      public ibar(Constants.barTypeEnum inbarType, ImageIcon barimage, int inbarID, String inPicString)
      {
        timestamps = 0;
        barType = inbarType;
        barLabel = new JLabel(barimage);
        barID = inbarID;
        picString = inPicString;
      }
    }
    
    /**
     * 
     * @param location
     *      initial location of bar
     * @param barType
     *      type of bar
     * @param barID
     *      specific ID of bar
     */
    public static void creatBar(XVec2 location, Constants.barTypeEnum barType, int barID)
    {
      ibar inbar = new ibar(barType, null, barID, null);
      inbar.picString = setBarPictureStr(barType);
      ImageIcon barIcon = new ImageIcon(MainPanel.class.getClassLoader().getResource(inbar.picString));
      inbar.barLabel = new JLabel(barIcon);
      barVector.addElement(inbar);
      MainPanel.add(barVector.lastElement().barLabel);
      System.out.println("after add label");
      barVector.lastElement().barLabel.setBounds(0, 0, barIcon.getIconWidth(), barIcon.getIconHeight());
      barVector.lastElement().barLabel.setLocation(location.x, location.y);
    }
    
    /**
     * 
     * @param locationVector
     *      location to update
     */
    public static void updateBarLocation(Vector<XVec2> locationVector)
    {
      for(int i = 0; i < barVector.size(); ++i)
      {
        if(barVector.elementAt(i).barType == Constants.barTypeEnum.TURNINGLEFT )
        {
          barVector.elementAt(i).timestamps = barVector.elementAt(i).timestamps % (Constants.BOARD_DELAY * 4);
          if(//barVector.elementAt(i).picString == "image/board/turning0.png" && 
              barVector.elementAt(i).timestamps / Constants.BOARD_DELAY == 1)
          {
            barVector.elementAt(i).picString = "image/board/turning1.png";
          }else if(//barVector.elementAt(i).picString == "image/board/turning1.png" && 
              barVector.elementAt(i).timestamps / Constants.BOARD_DELAY == 2)
          {
            barVector.elementAt(i).picString = "image/board/turning2.png";
          }else if(//barVector.elementAt(i).picString == "image/board/turning2.png" && 
              barVector.elementAt(i).timestamps / Constants.BOARD_DELAY ==3)
          {
            barVector.elementAt(i).picString = "image/board/turning3.png";
          }else if(//barVector.elementAt(i).picString == "image/board/turning3.png" && 
              barVector.elementAt(i).timestamps / Constants.BOARD_DELAY ==0)
          {
            barVector.elementAt(i).picString = "image/board/turning0.png";
          }
        }else if(barVector.elementAt(i).barType == Constants.barTypeEnum.TURNINGRIGHT )
        {
          barVector.elementAt(i).timestamps = barVector.elementAt(i).timestamps % (Constants.BOARD_DELAY * 4);
          if(//barVector.elementAt(i).picString == "image/board/turning0.png" && 
              barVector.elementAt(i).timestamps / Constants.BOARD_DELAY == 1)
          {
            barVector.elementAt(i).picString = "image/board/turning3.png";
          }else if(//barVector.elementAt(i).picString == "image/board/turning3.png" && 
              barVector.elementAt(i).timestamps / Constants.BOARD_DELAY == 2)
          {
            barVector.elementAt(i).picString = "image/board/turning2.png";
          }else if(//barVector.elementAt(i).picString == "image/board/turning2.png" && 
              barVector.elementAt(i).timestamps / Constants.BOARD_DELAY == 3)
          {
            barVector.elementAt(i).picString = "image/board/turning1.png";
          }else if(//barVector.elementAt(i).picString == "image/board/turning1.png" && 
              barVector.elementAt(i).timestamps / Constants.BOARD_DELAY == 0)
          {
            barVector.elementAt(i).picString = "image/board/turning0.png";
          }
          
        }else if(barVector.elementAt(i).barType == Constants.barTypeEnum.DISAPPEAR )
        {
          barVector.elementAt(i).timestamps = barVector.elementAt(i).timestamps % (Constants.BOARD_DELAY *2);
          if(barVector.elementAt(i).picString == "image/board/disappear1.png"
              && barVector.elementAt(i).timestamps / Constants.BOARD_DELAY == 0)
          {
            barVector.elementAt(i).picString = "image/board/disappear2.png";
          }else if(barVector.elementAt(i).picString == "image/board/disappear2.png"
              && barVector.elementAt(i).timestamps / Constants.BOARD_DELAY == 1)
          {
            barVector.elementAt(i).picString = "image/board/disappear3.png";
          }
        }else if(barVector.elementAt(i).barType == Constants.barTypeEnum.SPRING )
        {
          barVector.elementAt(i).timestamps = barVector.elementAt(i).timestamps % (Constants.SPRING_DELAY *3);
          if(barVector.elementAt(i).picString == "image/board/spring1.png"
              && barVector.elementAt(i).timestamps / Constants.SPRING_DELAY == 0)
          {
            barVector.elementAt(i).picString = "image/board/spring2.png";
          }else if(barVector.elementAt(i).picString == "image/board/spring2.png"
              && barVector.elementAt(i).timestamps / Constants.SPRING_DELAY == 1)
          {
            barVector.elementAt(i).picString = "image/board/spring1.png";
          }else if(barVector.elementAt(i).picString == "image/board/spring1.png"
              && barVector.elementAt(i).timestamps / Constants.SPRING_DELAY == 2)
          {
            barVector.elementAt(i).picString = "image/board/spring0.png";
          }
        }
        
        barVector.elementAt(i).timestamps ++;
        ImageIcon barIcon = new ImageIcon(MainPanel.class.getClassLoader().getResource(barVector.elementAt(i).picString));
        barVector.elementAt(i).barLabel.setIcon(barIcon);
        barVector.elementAt(i).barLabel.setLocation(locationVector.elementAt(i).x, locationVector.elementAt(i).y);
      }
    }
    
    /**
     * delete the first bar in the bar vector
     */
    public static void deleteBar()
    {
      barVector.elementAt(0).barLabel.setLocation(-100, -100);
      barVector.remove(0);
    }

    /**
     * 
     * @param index
     *      the index of the collision bar in barvector
     */
    public static void barCollision(int index)
    {
      if(barVector.elementAt(index).barType == barTypeEnum.SPRING )
      {
        barVector.elementAt(index).picString = "image/board/spring1.png";
      }else if(barVector.elementAt(index).barType == barTypeEnum.DISAPPEAR)
      {
        barVector.elementAt(index).picString = "image/board/disappear1.png";
      }
    }
    
    /**
     * set BarPictureStr
     * @param barType
     *      the type of bar
     * @return barPictureStr
     *      the string route of the bar pic 
     */
    public static String setBarPictureStr(barTypeEnum barType)
    {
      String barString = null;
      if(barType == Constants.barTypeEnum.NORMAL)
      {
        barString = "image/board/normal.png";
      }else if(barType == Constants.barTypeEnum.KILLLING)
      {
        barString = "image/board/killing.png";
      }else if(barType == Constants.barTypeEnum.SPRING)
      {
        barString = "image/board/spring0.png";
      }else if(barType == Constants.barTypeEnum.DISAPPEAR)
      {
        barString = "image/board/disappear0.png";
      }else if(barType == Constants.barTypeEnum.TURNINGLEFT)
      {
        barString = "image/board/turning0.png";
      }else if(barType == Constants.barTypeEnum.TURNINGRIGHT)
      {
        barString = "image/board/turning0.png";
      }
      return barString;
    }
    
}

