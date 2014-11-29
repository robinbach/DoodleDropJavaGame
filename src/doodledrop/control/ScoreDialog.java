package doodledrop.control;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import doodledrop.db.ScoreBoard;
import doodledrop.db.Top10Winners;
import doodledrop.db.Top10Winners.PlayerWinlose;

public class ScoreDialog extends JDialog{
  
  private static JTextArea scoredata;
  
  public ScoreDialog(String title, JFrame win)
  {
    super(win,title,true);
    scoredata = new JTextArea(10,20);
    scoredata.setEditable(false);
    Top10Winners top10 = ScoreBoard.getTop10W();
    for (PlayerWinlose player : top10.list){
      scoredata.append(player.toString()+"\n");
    }    
    JScrollPane jspOutput = new JScrollPane(scoredata);
    add(jspOutput);
    pack();
    setVisible(true);
  }  
}