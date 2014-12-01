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
    MainControl.BGM.start();
    scoredata = new JTextArea(15,30);
    scoredata.setEditable(false);
    Top10Winners top10 = ScoreBoard.getTop10W();
    Integer i = 0;
    scoredata.append("Place\tPlayer\tWin\tLose\n");
    for (PlayerWinlose player : top10.list){
      i++;
      scoredata.append(i+"\t"+ player.name+"\t"+player.win+"\t"+player.lose+"\n");      
    }    
    JScrollPane jspOutput = new JScrollPane(scoredata);
    add(jspOutput);
    pack();
    setVisible(true);
  }  
}