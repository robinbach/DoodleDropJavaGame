package doodledrop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScoreBoard
{
  
  
  public static void createPlayer(String name) throws UserExistException{
    String query = null;
    query = "select count(*) as count from players where name = '"+name+"';";
    Count count = new Count();
    RunDb.runQuery(query, count, true);
    if (count.count != 0){
      throw new UserExistException("User name: "+name+" already exist!");
    }
    query = "insert into players (name) values ('"+name+"');";
    RunDb.runQuery(query, null, false);
  }
  
  // player name must be unique and not null
  // check in UI
  /*public static Player startPlayer(String name) throws UserExistException{
    Player player = null;
    try {
      createPlayer(name);
    } catch (UserExistException ex){
      if (ex.getMessage() == DEFAULT_PLAYER){
        player = getPlayerInfo(name);
      } else {
        throw new UserExistException("User name: "+name+" already exist!");
      }
    }
    player = getPlayerInfo(name);
    return player;
  }*/
  
  public static Player getPlayerInfo(String name) throws UserNotExistException{
    String query = "select * from players where name = '"+name+"';";
    Player player = new Player();
    RunDb.runQuery(query, player, true);
    if (player.name == null){
      throw new UserNotExistException("User name: "+name+" doesn't exist!");
    }
    return player;
  }
  
  public static Top10Scores getTop10(){
    String query = "select players.name, score.score from players, score "
        + "where players.id = score.player_id order by score DESC limit 10;";
    Top10Scores top10 = new Top10Scores();
    RunDb.runQuery(query, top10, true);
    return top10;
  }
  
  public static Top10Winners getTop10W(){
    String query = "select name, win, lose from players order by win DESC, (win-0.8*lose) DESC limit 10;";
    Top10Winners top10 = new Top10Winners();
    RunDb.runQuery(query, top10, true);
    return top10;
  }
  
  public static void storeScore (Player player, int score){
    String query = "insert into score (player_id, score) values ("+player.id+", "+score+");";
    RunDb.runQuery(query, null, false);
  }
  
  public static void setWin(Player player){
    int curr_win = ++player.win;
    String query = "update players set win="+curr_win+" where id="+player.id+";";
    RunDb.runQuery(query, null, false);
  }
  
  public static void setLose(Player player){
    int curr_lose = ++player.lose;
    String query = "update players set lose="+curr_lose+" where id="+player.id+";";
    RunDb.runQuery(query, null, false);
  }
  
  public static void deletePlayer(Player player){
    String query = "delete from players where id="+player.id+";";
    RunDb.runQuery(query, null, false);
  }
  
  
}
