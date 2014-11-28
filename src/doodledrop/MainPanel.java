package doodledrop;

import doodledrop.Constants;
import doodledrop.Constants.barTypeEnum;

import java.util.Vector;

import javax.swing.*;

public class MainPanel {

    private static JFrame MainFrame;

    private static JPanel MainPanel;

    private static ImageIcon background;

    private static ImageIcon playerIcon;
    
    private static JLabel playerlabel;
    
    private static Constants.Directions direction[];
    
    private static String playerPicString, barPicString;
    
    private static Vector<ibar> barVector;
    
    public static void main(String[] args) {
        new MainPanel();
    }
    
    public MainPanel() {
    	//initialize
    	direction = new Constants.Directions[3];
    	direction[0] = direction [1] = direction[2] = Constants.Directions.NONE;
    	barVector = new Vector<ibar>();
    	//get contentPane
    	MainFrame = new JFrame("");
      MainPanel = (JPanel) MainFrame.getContentPane();
      MainFrame.setContentPane(MainPanel);
      MainPanel.setOpaque(false);
      MainPanel.setLayout(null);
      
      //System.out.println(playerIcon.getIconWidth() + "," + playerIcon.getIconHeight());
      playerInitial(1);
      
      MainFrame.getLayeredPane().setLayout(null);
      //set background picture
      background = new ImageIcon(getClass().getClassLoader().getResource("image/background/background.png"));
      JLabel label = new JLabel(background);
      label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
      MainFrame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
      //mainframe settings
      MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      MainFrame.setSize(background.getIconWidth(), background.getIconHeight());
      MainFrame.setResizable(false);
      MainFrame.setVisible(true); 
      
      MainFrame.addKeyListener(new GameKbdListener());

        
    }
    
    public static void gameEnding()
    {
      MainFrame.dispose();
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
    
    public static void setPlayerPictureStr()
    {

    	if(direction[0] == Constants.Directions.LEFT)
    	{
    		if(direction[1] == Constants.Directions.LEFT)
    		{
    			if(direction[2] == Constants.Directions.LEFT)
    			{
    				if(playerPicString == "image/characterpic/left0.png")
    				{
    					playerPicString = "image/characterpic/left1.png";
    				}else if(playerPicString == "image/characterpic/left1.png")
    				{
    					playerPicString = "image/characterpic/left2.png";
    				}else
    				{
    					playerPicString = "image/characterpic/left0.png";
    				}
    			}else 
    			{
    				playerPicString = "image/characterpic/left1.png";
				}
    		}else 
    		{
    			playerPicString = "image/characterpic/left0.png";
			}
    	}else if(direction[0] == Constants.Directions.RIGHT)
    	{
    		if(direction[1] == Constants.Directions.RIGHT)
    		{
    			if(direction[2] == Constants.Directions.RIGHT)
    			{
    				if(playerPicString == "image/characterpic/right0.png")
    				{
    					playerPicString = "image/characterpic/right1.png";
    				}else if(playerPicString == "image/characterpic/right1.png")
    				{
    					playerPicString = "image/characterpic/right2.png";
    				}else
    				{
    					playerPicString = "image/characterpic/right0.png";
    				}
    			}else 
    			{
    				playerPicString = "image/characterpic/right1.png";
				}
    		}else 
    		{
    			playerPicString = "image/characterpic/right0.png";
			}
    	}else if(direction[0] == Constants.Directions.DOWN)
    	{
    		if(direction[1] == Constants.Directions.DOWN)
    		{
    			playerPicString = "image/characterpic/jump0.png";
    		}else 
    		{
				playerPicString = "image/characterpic/jump1.png";
			}	
    	}else if(direction[0] == Constants.Directions.UP)
    	{
    		playerPicString = "image/characterpic/stand0.png";
    	}else if(direction[0] == Constants.Directions.NONE)
    	{
    	  playerPicString = "image/characterpic/stand0.png";
    	}
    	
    }
    
    
    public static void playerInitial(int player1or2)
    {
      //set character label
      playerPicString = "image/characterpic/stand0.png";
      playerIcon = new ImageIcon(MainPanel.class.getClassLoader().getResource(playerPicString));
      playerlabel = new JLabel(playerIcon);
      MainPanel.add(playerlabel);
      playerlabel.setBounds(0, 0, playerIcon.getIconWidth(),playerIcon.getIconHeight());
      playerlabel.setLocation(0, 0);
    }
    
    public static void setPlayerLocation(int x, int y, Constants.Directions indirection)
    {
    	direction[2] = direction[1];
    	direction[1] = direction[0];
    	direction[0] = indirection;
    	setPlayerPictureStr();
    	playerIcon = new ImageIcon(MainPanel.class.getClassLoader().getResource(playerPicString));
    	playerlabel.setIcon(playerIcon);
    	playerlabel.setLocation(x, y);
    }
    
    public static void refreshPanel()
    {
      MainPanel.repaint();
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

