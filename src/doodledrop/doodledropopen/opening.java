package doodledrop.doodledropopen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.InvalidPathException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import doodledrop.db.Player;
import doodledrop.db.ScoreBoard;
import doodledrop.db.UserExistException;
import doodledrop.db.UserNotExistException;

public class opening extends JFrame {

  private static final long serialVersionUID = 1L;

	public static RunGameEngine runGameEngine;
  
  private static JPanel imagepanel;
	private static JPanel buttonpanel;
	private static JPanel rightpart;
	
	private static JButton startbutton;
	private static JButton scoredatabutton;
	private static JButton settingbutton;
	private static JButton exitbutton;
	
	private static JLabel titleimagelabel;

	ButtonListener Buttons;
	
	public opening() {
		super ("Doodle Drop");
		setLayout(new GridLayout(2,1));
		imagepanel = new JPanel(new BorderLayout());
		buttonpanel = new JPanel(new GridLayout(1,2));
		rightpart = new JPanel(new GridLayout(6,1));
		
		try{
		  titleimagelabel = new JLabel(new 
			ImageIcon(getClass().getClassLoader().getResource("image/title.jpg")));
		}
		catch (InvalidPathException e) 
		{
			System.out.println("Image path exception");
		}
		  
		imagepanel.add(titleimagelabel,BorderLayout.CENTER);
		startbutton = new JButton("START");
		scoredatabutton = new JButton("SCORES");
		settingbutton = new JButton("SETTING");
		exitbutton = new JButton("EXIT");
		
		Buttons = new ButtonListener();
    startbutton.addActionListener(Buttons);
    scoredatabutton.addActionListener(Buttons);
    settingbutton.addActionListener(Buttons);
    exitbutton.addActionListener(Buttons);
		
		rightpart.add(startbutton);
		rightpart.add(scoredatabutton);
		rightpart.add(settingbutton);
		rightpart.add(exitbutton);
		//buttonpanel.add();
		buttonpanel.add(rightpart);
		add(imagepanel);
		add(buttonpanel);
		
	}
	
	public void setRunGameEngine(RunGameEngine _runGameEngine){
	  this.runGameEngine = _runGameEngine;
	}
	
	public class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if (event.getSource().equals(startbutton))
			{
			  RegisterDialog registerDialog = new RegisterDialog();
			}
			else if (event.getSource().equals(scoredatabutton))
			{
				ScoreDialog scoredialog = new ScoreDialog("Scores");
			}
			else if (event.getSource().equals(settingbutton))
			{
				SettingDialog settingdialog = new SettingDialog("Settings");
			}
			else if (event.getSource().equals(exitbutton))
			{
				int confirm = JOptionPane.showConfirmDialog(main.runOpening.win,
					  "Are you sure you want to exit?",
                      "Confirmation",
                      JOptionPane.YES_NO_OPTION);
				if (confirm == 0)
				{
				  main.runOpening.win.dispose();
				}
			}
		}
	}
	
	public static class RegisterDialog extends JDialog{
	  private static JLabel welcomMsg, newPlayerMsg, oldPlayerMsg;
	  private static JTextField playerName;
	  private static JButton newBtn, oldBtn, okBtn, clsBtn;
	  private static JPanel top, middle, bottom;
	  private static JPanel welcomePanel, choosePanel, newPlayer, oldPlayer, pnPanel;
	  
	  public static Player registedPlayer = null;
	  public static boolean isNew = false;
	  
	  public RegisterDialog(){
	    super(main.runOpening.win,"Please Register",true);
	    setLayout(new GridLayout(3,1));
	    top = new JPanel(new GridLayout(2,1));
	    middle = new JPanel();
	    middle.setLayout(new BoxLayout(middle,BoxLayout.PAGE_AXIS));
	    bottom = new JPanel(new FlowLayout());
	    add(top);
	    add(middle);
	    add(bottom);
	    
	    welcomMsg = new JLabel("Welcome to DoodelDrop!");
	    newBtn = new JButton("I want to create a new character.");
	    oldBtn = new JButton("I have my character.");
	    welcomePanel = new JPanel(new FlowLayout());
	    welcomePanel.add(welcomMsg);
	    choosePanel = new JPanel(new FlowLayout());
	    choosePanel.add(newBtn);
	    choosePanel.add(oldBtn);
	    
	    newBtn.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          RegisterDialog.this.setNewPlayer();
        }
      });
	    
	    oldBtn.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          RegisterDialog.this.setOldPlayer();
        }
      });
	    
	    newPlayerMsg = new JLabel("Please type in a name for your new character.");
	    oldPlayerMsg = new JLabel("Please type in your character's name.");	    
	    playerName = new JTextField(20);
	    newPlayer = new JPanel();
	    newPlayer.add(newPlayerMsg);
	    newPlayer.setVisible(false);
	    oldPlayer = new JPanel();
	    oldPlayer.add(oldPlayerMsg);
	    oldPlayer.setVisible(false);
	    pnPanel = new JPanel(new FlowLayout());
	    pnPanel.add(playerName);
	    pnPanel.setVisible(false);
	    
	    okBtn = new JButton("OK");
	    okBtn.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){
	        if (playerName.getText().isEmpty()){
	          JOptionPane.showMessageDialog(RegisterDialog.this, 
                "Name can't be empty!", "Error!", JOptionPane.ERROR_MESSAGE);
	        } else {
	          if (isNew){
	            try {
	              ScoreBoard.createPlayer(playerName.getText());
	              registedPlayer = ScoreBoard.getPlayerInfo(playerName.getText());
	            } catch (UserExistException ex){
	              JOptionPane.showMessageDialog(RegisterDialog.this, 
	                  ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
	            } catch (UserNotExistException ex2){
	              JOptionPane.showMessageDialog(RegisterDialog.this, 
	                  ex2.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
	            }
	            System.out.println("new player: "+getResigteredPlayer());
	          } else {
	            try {
	              registedPlayer = ScoreBoard.getPlayerInfo(playerName.getText());
	            } catch (UserNotExistException ex){
	              JOptionPane.showMessageDialog(RegisterDialog.this, 
	                  ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
	            }
	            System.out.println("old player: "+getResigteredPlayer());
	          }
	        }	        
	        //TODO: memorize player
	        runGameEngine.start();
          main.runOpening.win.dispose();
	      }
	    });
	    clsBtn = new JButton("Cancel");
	    clsBtn.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          RegisterDialog.this.dispose();
        }
      });
	    
	    top.add(welcomePanel);
	    top.add(choosePanel);
	    middle.add(newPlayer);
	    middle.add(oldPlayer);
	    middle.add(pnPanel);
	    bottom.add(okBtn);
	    bottom.add(clsBtn);
	    
	    pack();
	    setVisible(true);
      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	  }
	  
	  public void setNewPlayer(){
	    isNew = true;
	    newPlayer.setVisible(true);
	    oldPlayer.setVisible(false);
	    pnPanel.setVisible(true);
	    newBtn.setEnabled(false);
	    oldBtn.setEnabled(true);
	  }
	  
	  public void setOldPlayer(){
	    isNew = false;
	    newPlayer.setVisible(false);
      oldPlayer.setVisible(true);
      pnPanel.setVisible(true);
	    oldBtn.setEnabled(false);
	    newBtn.setEnabled(true);
	  }
	  
	  public Player getResigteredPlayer(){
	    return this.registedPlayer;
	  }	  
	}
	
	public static class SettingDialog extends JDialog{
		private static JLabel enableSound;
		private static JRadioButton doenable;
		private static JRadioButton notenable;
		
		private static JLabel mode;
		private static JRadioButton easy;
		private static JRadioButton normal;
		private static JRadioButton hard;
		
		private static JLabel volume;
		private static JSlider volumeslider;
		
		private static JButton okbutton;
		private static JButton cancelbutton;
		
		private static JPanel soundpanel1;
		private static JPanel soundpanel2;
		private static JPanel modepanel;
		private static JPanel buttonpanel;
		
		public SettingDialog(String title)
		{
			super(main.runOpening.win,title,true);
			
			setLayout(new GridLayout(6,1));
			
			soundpanel1 = new JPanel(new FlowLayout());
			soundpanel2 = new JPanel(new FlowLayout());
			modepanel = new JPanel(new FlowLayout());
			buttonpanel = new JPanel(new FlowLayout());
			
			enableSound = new JLabel("Sound: ");
			doenable = new JRadioButton("ON");
			notenable = new JRadioButton("OFF");
			ButtonGroup group1 = new ButtonGroup();
			group1.add(doenable);
			group1.add(notenable);
			
			
			
			soundpanel1.add(enableSound);
			soundpanel1.add(doenable);
			soundpanel1.add(notenable);
			
			volume = new JLabel("Volume: ");
			volumeslider = new JSlider(JSlider.HORIZONTAL,0,100,50);
			volumeslider.setPaintTicks(true);
			volumeslider.setPaintLabels(true);
			volumeslider.setSnapToTicks(true);
			
			soundpanel2.add(volume);
			soundpanel2.add(volumeslider);
			
			mode = new JLabel("Mode: ");
			easy = new JRadioButton("Easy");
			normal = new JRadioButton("Normal");
			hard = new JRadioButton("Hard");
			ButtonGroup group2 = new ButtonGroup();
			group2.add(easy);
			group2.add(normal);
			group2.add(hard);
			modepanel.add(mode);
			modepanel.add(easy);
			modepanel.add(normal);
			modepanel.add(hard);
			
			
			okbutton = new JButton("OK");
			okbutton.addActionListener(new ScoreButtonListener());
			cancelbutton = new JButton("Cancel");
			cancelbutton.addActionListener(new ScoreButtonListener());
			buttonpanel.add(okbutton);
			buttonpanel.add(cancelbutton);
			
			add(soundpanel1);
			add(soundpanel2);
			add(modepanel);
			add(buttonpanel);
			
			add(new JLabel("         !!!more setting stuff"));
			
			pack();
		    setVisible(true);
		}
		public class ScoreButtonListener implements ActionListener{

			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				if (event.getSource().equals(okbutton)){
					int confirm = JOptionPane.showConfirmDialog(main.runOpening.win,
							  "Are you sure you want to save the settings?",
		                      "Confirmation",
		                      JOptionPane.YES_NO_OPTION);
					if (confirm == 0)
					{
						setVisible(false);
					}
				}
				else if (event.getSource().equals(cancelbutton)){
					setVisible(false);
				}
				
			}
			
		}
		
	}
	
	public static class ScoreDialog extends JDialog{
		JTextArea scoredata;
		
		public ScoreDialog(String title)
		{
			super(main.runOpening.win,title,true);
			scoredata = new JTextArea(10,20);
			scoredata.setEditable(false);
			JScrollPane jspOutput = new JScrollPane(scoredata);
			scoredata.append("first   abc\n second    dce");
			add(scoredata);
			pack();
		    setVisible(true);
		}
		
	}
}