package doodledrop.control;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.InvalidPathException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EndingWin extends JFrame {
  private static final long serialVersionUID = 1L;
  private static JPanel imagepanel;
  private static JPanel buttonpanel;
  private static JPanel rightpart;
  
  private static JButton tryagainbutton;
  private static JButton scoredatabutton;
  private static JButton settingbutton;
  private static JButton exitbutton;
  
  private static JLabel titleimagelabel;

  private static ButtonListener Buttons;
  
  private static Boolean startGame;
  
  public EndingWin(Boolean _startGame) {
    super ("Doodle Drop");
    startGame = _startGame;
    setLayout(new GridLayout(2,1));
    imagepanel = new JPanel(new BorderLayout());
    buttonpanel = new JPanel(new GridLayout(1,2));
    rightpart = new JPanel(new GridLayout(6,1));
    
    try{
      titleimagelabel = new JLabel(new 
        ImageIcon(getClass().getClassLoader().getResource("image/ending.jpg")));
    }
    catch (InvalidPathException e) 
    {
      System.out.println("Image path exception");
    }

    imagepanel.add(titleimagelabel,BorderLayout.CENTER);
    tryagainbutton = new JButton("TRY AGAIN");
    scoredatabutton = new JButton("SCORES");
    settingbutton = new JButton("SETTING");
    exitbutton = new JButton("EXIT");
    
    Buttons = new ButtonListener();
    tryagainbutton.addActionListener(Buttons);
    scoredatabutton.addActionListener(Buttons);
    settingbutton.addActionListener(Buttons);
    exitbutton.addActionListener(Buttons);
    
    rightpart.add(tryagainbutton);
    rightpart.add(scoredatabutton);
    rightpart.add(settingbutton);
    rightpart.add(exitbutton);
    //buttonpanel.add();
    buttonpanel.add(rightpart);
    add(imagepanel);
    add(buttonpanel);
    
  }
  
  public class ButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent event) {
      if (event.getSource().equals(tryagainbutton))
      {
        /*GameLogic gameEngine = new GameLogic();
        Thread gameThread = new Thread(gameEngine);
        gameThread.start();
        GameControl.runEnding.gameEnding.dispose();*/
        startGame = true;
      }
      else if (event.getSource().equals(scoredatabutton))
      {
        ScoreDialog scoredialog = new ScoreDialog("Scores", EndingWin.this);
      }
      else if (event.getSource().equals(settingbutton))
      {
        SettingDialog settingdialog = new SettingDialog("Settings", EndingWin.this);
      }
      else if (event.getSource().equals(exitbutton))
      {
        int confirm = JOptionPane.showConfirmDialog(EndingWin.this,
            "Are you sure you want to exit?",
                      "Confirmation",
                      JOptionPane.YES_NO_OPTION);
        if (confirm == 0)
        {
          startGame = false;  //make main control continue
        }
      }
    }
  }
  
  public Boolean ifStartGame(){
    return this.startGame;
  }
}