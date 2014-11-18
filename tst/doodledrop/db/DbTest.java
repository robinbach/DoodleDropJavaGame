package doodledrop.db;

import doodledrop.db.Top10Scores.PlayerScore;
import doodledrop.db.ScoreBoard;

public class DbTest
{
  public static void main(String[] args) {
      
    for (Integer i = 0; i < 5; i++){
      try {
        ScoreBoard.createPlayer("player"+i);
      } catch( UserExistException e ) {
        System.out.println(e.getMessage());
      }
    }
    
    Player testPlayer = ScoreBoard.getPlayerInfo("player0");
    System.out.println(testPlayer.toString());
    ScoreBoard.setWin(testPlayer);
    ScoreBoard.setLose(testPlayer);
    Player testPlayer2 = ScoreBoard.getPlayerInfo("player0");
    System.out.println(testPlayer2);
    for (int i = 10; i < 50; i+= 5){
      ScoreBoard.storeScore(testPlayer, i);
    }
    System.out.println("get top 10: ");
    Top10Scores top10 = ScoreBoard.getTop10();
    for (PlayerScore player : top10.list){
      System.out.println(player.toString());
    }
  }
}
