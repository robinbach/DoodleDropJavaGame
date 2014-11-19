package doodledrop.doodledropopen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.InvalidPathException;

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




public class opening extends JFrame {

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
			ImageIcon(getClass().getClassLoader().getResource("image/images/title.jpg")));
		}
		catch (InvalidPathException e) 
		  {
			// TODO Auto-generated catch block
			//e.printStackTrace();
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
	
	public class ButtonListener implements ActionListener{
		public ButtonListener()
		{
			
		}


		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			if (event.getSource().equals(startbutton))
			{
				
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
				int confirm = JOptionPane.showConfirmDialog(main.win,
					  "Are you sure you want to exit?",
                      "Confirmation",
                      JOptionPane.YES_NO_OPTION);
				if (confirm == 0)
				{
					main.win.dispose();
				}
			}
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
			super(main.win,title,true);
			
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
					int confirm = JOptionPane.showConfirmDialog(main.win,
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
			super(main.win,title,true);
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