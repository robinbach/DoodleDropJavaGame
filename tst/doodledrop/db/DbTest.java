package doodledrop.db;

import doodledrop.db.Top10Scores.PlayerScore;
import doodledrop.db.ScoreBoard;
import doodledrop.db.Top10Winners.PlayerWinlose;

public class DbTest
{
  public static void main(String[] args) throws UserNotExistException {
      
    for (Integer i = 0; i < 5; i++){
      try {
        ScoreBoard.createPlayer("player"+i);
      } catch( UserExistException e ) {
        System.out.println(e.getMessage());
      }
    }
    
    Player testPlayer;
    testPlayer = ScoreBoard.getPlayerInfo("player0");
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
    ScoreBoard.deletePlayer(testPlayer);
    Top10Scores top10_2 = ScoreBoard.getTop10();
    if (!top10_2.list.isEmpty()){
      throw new RuntimeException("Player not deleted!");
    } else {
      System.out.println("Player deleted!");
    }
    Top10Winners top10W = ScoreBoard.getTop10W();
    for (PlayerWinlose player : top10W.list){
      System.out.println(player.toString());
    }
  }
}
