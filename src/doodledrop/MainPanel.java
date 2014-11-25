package doodledrop;

import doodledrop.Constants;

import java.awt.event.*;

import javax.swing.*;

public class MainPanel {

    private static JFrame MainFrame;

    private static JPanel MainPanel;

    private static ImageIcon background;

    private static ImageIcon characterIcon;
    
    private static JLabel characterlabel;
    
    private static Constants.Directions direction[];
    
    private static String picString;
    
    private static int curX, curY;
    
    
    public static void main(String[] args) {
        new MainPanel();
    }

    public MainPanel() {
    	//initialize
      curX = 0;
      curY = 0;
    	direction = new Constants.Directions[3];
    	direction[0] = direction [1] = direction[2] = Constants.Directions.NONE;
    	picString = "image/characterpic/stand0.png";
    	//get contentPane
    	MainFrame = new JFrame("");
        MainPanel = (JPanel) MainFrame.getContentPane();
        MainFrame.setContentPane(MainPanel);
        MainPanel.setOpaque(false);
        MainPanel.setLayout(null);
        //set character label
        characterIcon = new ImageIcon(getClass().getClassLoader().getResource(picString));
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
        //test
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
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				setLocation(curX + 10, curY , Constants.Directions.RIGHT);	
			}
		});
        
        //mainframe settings
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setSize(background.getIconWidth(), background.getIconHeight());
        MainFrame.setResizable(false);
        MainFrame.setVisible(true); 
        
    }
    
    public static void setPictureStr()
    {
    	//leftward 0 rightward 1 downward 2 upward 3
    	if(direction[0] == Constants.Directions.LEFT)
    	{
    		if(direction[1] == Constants.Directions.LEFT)
    		{
    			if(direction[2] == Constants.Directions.LEFT)
    			{
    				if(picString == "image/characterpic/left0.png")
    				{
    					picString = "image/characterpic/left1.png";
    				}else if(picString == "image/characterpic/left1.png")
    				{
    					picString = "image/characterpic/left2.png";
    				}else
    				{
    					picString = "image/characterpic/left0.png";
    				}
    			}else 
    			{
    				picString = "image/characterpic/left1.png";
				}
    		}else 
    		{
    			picString = "image/characterpic/left0.png";
			}
    	}else if(direction[0] == Constants.Directions.RIGHT)
    	{
    		if(direction[1] == Constants.Directions.RIGHT)
    		{
    			if(direction[2] == Constants.Directions.RIGHT)
    			{
    				if(picString == "image/characterpic/right0.png")
    				{
    					picString = "image/characterpic/right1.png";
    				}else if(picString == "image/characterpic/right1.png")
    				{
    					picString = "image/characterpic/right2.png";
    				}else
    				{
    					picString = "image/characterpic/right0.png";
    				}
    			}else 
    			{
    				picString = "image/characterpic/right1.png";
				}
    		}else 
    		{
    			picString = "image/characterpic/right0.png";
			}
    	}else if(direction[0] == Constants.Directions.DOWN)
    	{
    		if(direction[1] == Constants.Directions.DOWN)
    		{
    			picString = "image/characterpic/jump0.png";
    		}else 
    		{
				picString = "image/characterpic/jump1.png";
			}	
    	}else if(direction[0] == Constants.Directions.UP)
    	{
    		picString = "image/characterpic/stand0.png";
    	}
    	
    }
    
    public static void setLocation(int x, int y, Constants.Directions indirection)
    {
      curX = x;
      curY = y;
    	direction[2] = direction[1];
    	direction[1] = direction[0];
    	direction[0] = indirection;
    	setPictureStr();
    	MainPanel.removeAll();
    	characterIcon = new ImageIcon(MainPanel.class.getClassLoader().getResource(picString));
    	characterlabel.setIcon(characterIcon);
    	characterlabel.setLocation(x, y);
    	MainPanel.add(characterlabel);
    	MainPanel.repaint();
    }
    
}



