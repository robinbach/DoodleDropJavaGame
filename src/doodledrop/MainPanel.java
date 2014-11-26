package doodledrop;

import doodledrop.Constants;
import doodledrop.Constants.barTypeEnum;

import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;


public class MainPanel {

    private static JFrame MainFrame;

    private static JPanel MainPanel;

    private static ImageIcon background;

    private static ImageIcon characterIcon;
    
    private static JLabel characterlabel;
    
    private static Constants.Directions direction[];
    
    private static String playerPicString, barPicString;
    
    private static Vector<JLabel> barVector;
    
    public static void main(String[] args) {
        new MainPanel();
    }
    
    public MainPanel() {
    	//initialize
    	direction = new Constants.Directions[3];
    	direction[0] = direction [1] = direction[2] = Constants.Directions.NONE;
    	playerPicString = "image/characterpic/stand0.png";
    	barVector = new Vector<JLabel>();
    	//get contentPane
    	MainFrame = new JFrame("");
      MainPanel = (JPanel) MainFrame.getContentPane();
      MainFrame.setContentPane(MainPanel);
      MainPanel.setOpaque(false);
      MainPanel.setLayout(null);
      //set character label
      characterIcon = new ImageIcon(getClass().getClassLoader().getResource(playerPicString));
      characterlabel = new JLabel(characterIcon);
      MainPanel.add(characterlabel);
      characterlabel.setBounds(0, 0, characterIcon.getIconWidth(),characterIcon.getIconHeight());
      characterlabel.setLocation(0, 0);
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
        
    }
    
    public static void setPlayerPictureStr()
    {
    	//leftward 0 rightward 1 downward 2 upward 3
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
    	}
    	
    }
    
    public static void setPlayerLocation(int x, int y, Constants.Directions indirection)
    {
    	direction[2] = direction[1];
    	direction[1] = direction[0];
    	direction[0] = indirection;
    	setPlayerPictureStr();
    	characterIcon = new ImageIcon(MainPanel.class.getClassLoader().getResource(playerPicString));
    	characterlabel.setIcon(characterIcon);
    	characterlabel.setLocation(x, y);
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
        barString = "image/board/normalboard.png";
      }else if(barType == Constants.barTypeEnum.KILLLING)
      {
        barString = "image/board/disappearboard";
      }
      return barString;
    }
    
    public static void creatBar(XVec2 location, Constants.barTypeEnum barType, int barID)
    {
      barPicString = getBarPicString(barType);
      ImageIcon barIcon = new ImageIcon(MainPanel.class.getClassLoader().getResource(barPicString));
      JLabel barlabel = new JLabel(barIcon);
      System.out.println(barPicString);
      barVector.addElement(barlabel);
      MainPanel.add(barVector.lastElement());
      barVector.lastElement().setBounds(0, 0, Constants.BAR_WIDTH, Constants.BAR_HEIGHT);
      barVector.lastElement().setLocation(location.x, location.y);
    }
    
    public static void updateBarLocation(Vector<XVec2> locationVector)
    {
      for(int i = 0; i < barVector.size(); ++i)
      {
        barVector.elementAt(i).setLocation(locationVector.elementAt(i).x, locationVector.elementAt(i).y);
      } 
    }
    
    public static void deleteBar()
    {
      
    }

}




/*
MainPanel.addMouseListener(new MouseListener() {

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub
    //creatBar(50, 50, Constants.barTypeEnum.NORMAL);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    setPlayerLocation(curX , curY +10, Constants.Directions.DOWN);  
  }
  });

*/

