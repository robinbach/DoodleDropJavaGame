package doodledrop;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainPanel {

    private static JFrame MainFrame = new JFrame("");

    private static JPanel MainPanel;

    private static ImageIcon background;

    private static JLabel chalabel;
    public static void main(String[] args) {
        new MainPanel();
    }

    public MainPanel() {
        background = new ImageIcon(getClass().getClassLoader().getResource("image/background/background.png"));
        JLabel label = new JLabel(background);

        label.setBounds(0, 0, background.getIconWidth(),
                background.getIconHeight());
        MainPanel = (JPanel) MainFrame.getContentPane();
        MainFrame.setContentPane(MainPanel);
        MainPanel.setOpaque(false);
        MainPanel.setLayout(null);
        
        ImageIcon cha = new ImageIcon(getClass().getClassLoader().getResource("image/characterpic/left0.png"));
        chalabel = new JLabel(cha);
        MainPanel.add(chalabel);
        chalabel.setBounds(0, 0, cha.getIconWidth(),cha.getIconHeight());
        chalabel.setLocation(40, 40);
        MainFrame.getLayeredPane().setLayout(null);
      
        
        
        MainFrame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setSize(background.getIconWidth(), background.getIconHeight());
        MainFrame.setResizable(false);
        MainFrame.setVisible(true);    
    }
    public static void SetLocation(int x, int y)
    {
    	MainPanel.removeAll();
    	chalabel.setLocation(x, y);
    	MainPanel.add(chalabel);
    	MainPanel.repaint();
    }
    
}



