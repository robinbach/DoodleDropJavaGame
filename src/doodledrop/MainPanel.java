package doodledrop;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainPanel {

    private static JFrame MainFrame;

    private static JPanel MainPanel;

    private static ImageIcon background;

    private static ImageIcon characterIcon;
    
    private static JLabel characterlabel;
    
    private static Integer direction[];
    
    private static String picString;
    
    
    public static void main(String[] args) {
        new MainPanel();
    }

    public MainPanel() {
    	//initialize
    	direction = new Integer[3];
    	direction[0] = direction [1] = direction[2] = 0;
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
        characterlabel.setLocation(40, 40);
        MainFrame.getLayeredPane().setLayout(null);
        //set background picture
        background = new ImageIcon(getClass().getClassLoader().getResource("image/background/background.png"));
        JLabel label = new JLabel(background);
        label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
        MainFrame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        //test
//        MainPanel.addMouseListener(new MouseListener() {
//			
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mousePressed(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				setLocation(curX, curY + 10, 2);	
//			}
//		});
        
        //mainframe settings
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setSize(background.getIconWidth(), background.getIconHeight());
        MainFrame.setResizable(false);
        MainFrame.setVisible(true); 
        
    }
    
    public static void setPictureStr()
    {
    	//leftward 0 rightward 1 downward 2 upward 3
    	if(direction[0] == 0)
    	{
    		if(direction[1] == 0)
    		{
    			if(direction[2] == 0)
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
    	}else if(direction[0] == 1)
    	{
    		if(direction[1] == 1)
    		{
    			if(direction[2] == 1)
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
    	}else if(direction[0] == 2)
    	{
    		if(direction[1] == 2)
    		{
    			picString = "image/characterpic/jump0.png";
    		}else 
    		{
				picString = "image/characterpic/jump1.png";
			}	
    	}else if(direction[0] == 3)
    	{
    		picString = "image/characterpic/stand0.png";
    	}
    	
    }
    
    public static void setLocation(int x, int y, int indirection)
    {
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



