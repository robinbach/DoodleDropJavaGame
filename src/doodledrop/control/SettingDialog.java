package doodledrop.control;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import doodledrop.Constants;

public class SettingDialog extends JDialog{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private static JLabel enableSound;
  private static JRadioButton doenable;
  private static JRadioButton notenable;
  
  private static JLabel mode;
  private static JRadioButton easy;
  private static JRadioButton normal;
  private static JRadioButton hard;
  
  private static JLabel volume;
  private static JSlider volumeslider;
  
  private static JLabel playernum;
  private static JRadioButton oneplayer;
  private static JRadioButton twoplayers;
  
  private static JLabel youwanttobe;
  private static JRadioButton server;
  private static JRadioButton client;
  
  private static JButton okbutton;
  private static JButton cancelbutton;
  
  private static JPanel soundpanel1;
  private static JPanel soundpanel2;
  private static JPanel modepanel;
  private static JPanel playerpanel1;
  private static JPanel playerpanel2;
  private static JPanel buttonpanel;
  
  private static JFrame win;
  
  private static Boolean enableBgm = true;
  private static Boolean isMulti = false;
  private static Boolean isServer = false;
  
  private static int bgmVolumn = 100;
  
  public SettingDialog(String title, JFrame _win)
  {
    super(_win,title,true);
    win = _win;
    setLayout(new GridLayout(7,1));
    
    soundpanel1 = new JPanel(new FlowLayout());
    soundpanel2 = new JPanel(new FlowLayout());
    modepanel = new JPanel(new FlowLayout());
    playerpanel1 = new JPanel(new FlowLayout());
    playerpanel2 = new JPanel(new FlowLayout());
    buttonpanel = new JPanel(new FlowLayout());
    
    enableSound = new JLabel("Sound: ");
    doenable = new JRadioButton("ON");
    notenable = new JRadioButton("OFF");
    ButtonGroup group1 = new ButtonGroup();
    group1.add(doenable);
    group1.add(notenable);

    if (enableBgm){
      doenable.setSelected(true);
    } else {
      notenable.setSelected(true);
    }
    
    doenable.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        System.out.println("Sound is enabled.");
        enableBgm = true;
      }
    }
    );
    
    notenable.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        System.out.println("Sound is disabled.");
        enableBgm = false;
      }
    }
    );
    
    soundpanel1.add(enableSound);
    soundpanel1.add(doenable);
    soundpanel1.add(notenable);
    
    volume = new JLabel("Volume: ");
    volumeslider = new JSlider(JSlider.HORIZONTAL,0,100,bgmVolumn);
    volumeslider.setPaintTicks(true);
    volumeslider.setPaintLabels(true);
    volumeslider.setSnapToTicks(true);
    
    volumeslider.addChangeListener(new ChangeListener(){
      @Override
      public void stateChanged(ChangeEvent e)
      {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()){
          Integer value = source.getValue();
          System.out.println("volume: " + value);
          bgmVolumn = value;          
        }        
      }    
    });
    
    soundpanel2.add(volume);
    soundpanel2.add(volumeslider);
    
    mode = new JLabel("Mode: ");
    easy = new JRadioButton("Easy");
    normal = new JRadioButton("Normal", true);
    hard = new JRadioButton("Hard");
    
    ButtonGroup group2 = new ButtonGroup();
    group2.add(easy);
    group2.add(normal);
    group2.add(hard);
    
    playernum = new JLabel("Player: ");
    oneplayer = new JRadioButton("One Player");
    twoplayers = new JRadioButton("Two Players");
    
    ButtonGroup group3 = new ButtonGroup();
    group3.add(oneplayer);
    group3.add(twoplayers);
    
    youwanttobe = new JLabel("You want to be: ");
    server = new JRadioButton("Server");
    client = new JRadioButton("Client");
    
    ButtonGroup group4 = new ButtonGroup();
    group4.add(server);
    group4.add(client);
    
    server.setEnabled(false);
    client.setEnabled(false);
    
    playerpanel1.add(playernum);
    playerpanel1.add(oneplayer);
    playerpanel1.add(twoplayers);
    
    playerpanel2.add(youwanttobe);
    playerpanel2.add(client);
    playerpanel2.add(server);
    
    easy.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e)
    	{
    		System.out.println("easy is selected");
    		Constants.BAR_RISING_SPEED = -3;
    		Constants.PLAYER_DROP_SPEED = 3;
    	}
    }
    );
    
    
    normal.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e)
    	{
    		System.out.println("normal is selected");
    		Constants.BAR_RISING_SPEED = -6;
    		Constants.PLAYER_DROP_SPEED = 6;
    	}
    }
    );
    
    hard.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e)
    	{
    		System.out.println("hard is selected");
    		Constants.BAR_RISING_SPEED = -10;
    		Constants.PLAYER_DROP_SPEED = 10;
    	}
    }
    );
    
    oneplayer.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e)
    	{
    		System.out.println("one players mode is selected");
    		isMulti = false;
    	}
    }
    );
    
    twoplayers.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e)
    	{
    		System.out.println("two players mode is selected");
    		isMulti = true;
    		server.setEnabled(true);
    		client.setEnabled(true);
    	}
    }
    );
    
    server.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e)
    	{
    		System.out.println("server mode is selected");
    		isMulti = true;
    		isServer = true;
    	}
    }
    );
    
    client.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e)
    	{
    		System.out.println("server mode is selected");
    		isMulti = true;
    		isServer = false;
    	}
    }
    );
    
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
    add(playerpanel1);
    add(playerpanel2);
    add(buttonpanel);
    
    //add(new JLabel("         !!!more setting stuff"));
    
    pack();
    setVisible(true);
  }
  
  public class ScoreButtonListener implements ActionListener{

    public void actionPerformed(ActionEvent event) {
      if (event.getSource().equals(okbutton)){
        int confirm = JOptionPane.showConfirmDialog(win,
              "Are you sure you want to save the settings?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);
        if (confirm == 0)
        {
          if (enableBgm){
            MainControl.startBgm();
          } else {
            MainControl.stopBgm();
          }
          
          if (isMulti){
        	  Constants.IsMultiPlayer = true;
          }
          else{
        	  Constants.IsMultiPlayer = false;
          }
          
          if (isServer){
        	  Constants.IsServer = true;
          }
          else{
        	  Constants.IsServer = false;
          }
          
          MainControl.setBgmVolumn((double)bgmVolumn/100);
          setVisible(false);
        }
        else
        {
          Constants.BAR_RISING_SPEED = -4;
          Constants.PLAYER_DROP_SPEED = 4;
          Constants.IsMultiPlayer = false;
        }
      }
      else if (event.getSource().equals(cancelbutton)){
        Constants.BAR_RISING_SPEED = -4;
        Constants.PLAYER_DROP_SPEED = 4;
        Constants.IsMultiPlayer = false;
        setVisible(false);
      }
    }
  }
}
