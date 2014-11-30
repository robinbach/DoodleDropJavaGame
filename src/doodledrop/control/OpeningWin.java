package doodledrop.control;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.InvalidPathException;

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
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import doodledrop.db.Player;
import doodledrop.db.ScoreBoard;
import doodledrop.db.UserExistException;
import doodledrop.db.UserNotExistException;

public class OpeningWin extends JFrame {

  private static final long serialVersionUID = 1L;

  private static JPanel imagepanel;
  private static JPanel buttonpanel;
  private static JPanel rightpart;
  
  private static JButton startbutton;
  private static JButton scoredatabutton;
  private static JButton settingbutton;
  private static JButton exitbutton;
  
  private static JLabel titleimagelabel;

  private static ButtonListener Buttons;
  
  private static Player registeredPlayer;
  
  public OpeningWin() {
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
    buttonpanel.add(rightpart);
    add(imagepanel);
    add(buttonpanel);
    
  }
  
  public class ButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent event) {
      if (event.getSource().equals(startbutton))
      {
        MainControl.rpLock.lock();
        RegisterDialog registerDialog = new RegisterDialog(OpeningWin.this);
        registeredPlayer = registerDialog.getResigteredPlayer();
        MainControl.rpNotNull.signal();
        MainControl.rpLock.unlock();
      }
      else if (event.getSource().equals(scoredatabutton))
      {
        ScoreDialog scoredialog = new ScoreDialog("Score Board", OpeningWin.this);
      }
      else if (event.getSource().equals(settingbutton))
      {
        SettingDialog settingdialog = new SettingDialog("Settings", OpeningWin.this);
      }
      else if (event.getSource().equals(exitbutton))
      {
        int confirm = JOptionPane.showConfirmDialog(OpeningWin.this,
            "Are you sure you want to exit?",
                      "Confirmation",
                      JOptionPane.YES_NO_OPTION);
        if (confirm == 0)
        {
          OpeningWin.this.dispose();
          MainControl.rpLock.lock();
          MainControl.setForceQuit();
          MainControl.rpNotNull.signal();
          MainControl.rpLock.unlock();
        }
      }
    }
  }
  
  public class RegisterDialog extends JDialog{
    private JLabel welcomMsg, newPlayerMsg, oldPlayerMsg;
    private JTextField playerName;
    private JButton newBtn, oldBtn, okBtn, clsBtn;
    private JPanel top, middle, bottom;
    private JPanel welcomePanel, choosePanel, newPlayer, oldPlayer, pnPanel;
    
    private boolean isNew = false;
    private Player registeredPlayer = null;
    
    public RegisterDialog(JFrame openingWin){
      super(openingWin,"Please Register",true);
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
      okBtn.setEnabled(false);
      okBtn.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          if (playerName.getText().isEmpty()){
            JOptionPane.showMessageDialog(RegisterDialog.this, 
                "Name can't be empty!", "Error!", JOptionPane.ERROR_MESSAGE);
          } else {
            if (isNew){
              try {
                ScoreBoard.createPlayer(playerName.getText());
                registeredPlayer = ScoreBoard.getPlayerInfo(playerName.getText());
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
                registeredPlayer = ScoreBoard.getPlayerInfo(playerName.getText());
              } catch (UserNotExistException ex){
                JOptionPane.showMessageDialog(RegisterDialog.this, 
                    ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
              }
              System.out.println("old player: "+getResigteredPlayer());
            }
            RegisterDialog.this.dispose();
          }
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
      okBtn.setEnabled(true);
    }
    
    public void setOldPlayer(){
      isNew = false;
      newPlayer.setVisible(false);
      oldPlayer.setVisible(true);
      pnPanel.setVisible(true);
      oldBtn.setEnabled(false);
      newBtn.setEnabled(true);
      okBtn.setEnabled(true);
    }
    
    public Player getResigteredPlayer(){
      return this.registeredPlayer;
    }   
  }
  
  public Player getResigteredPlayer(){
    return this.registeredPlayer;
  }
}