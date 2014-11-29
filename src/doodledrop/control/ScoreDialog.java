package doodledrop.control;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ScoreDialog extends JDialog{
  JTextArea scoredata;
  
  public ScoreDialog(String title, JFrame win)
  {
    super(win,title,true);
    scoredata = new JTextArea(10,20);
    scoredata.setEditable(false);
    JScrollPane jspOutput = new JScrollPane(scoredata);
    scoredata.append("first   abc\n second    dce");
    add(scoredata);
    pack();
      setVisible(true);
  }
  
}