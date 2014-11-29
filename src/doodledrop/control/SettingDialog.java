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

public class SettingDialog extends JDialog{
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
  
  private static JFrame win;
  
  public SettingDialog(String title, JFrame _win)
  {
    super(_win,title,true);
    win = _win;
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
      if (event.getSource().equals(okbutton)){
        int confirm = JOptionPane.showConfirmDialog(win,
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
