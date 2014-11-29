package doodledrop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScoreBoard
{
  //JDBC driver name and database URL
  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
  private static final String DB_URL = "jdbc:mysql://"
      + "eecs285-p4.ckaip7e5k60r.us-west-2.rds.amazonaws.com:3306/eecs285";
  
  //  Database credentials
  private static final String USER = "dianazh";
  private static final String PASS = "dianazh17";
  
  //default player
  public static final String DEFAULT_PLAYER = "player0";
  
  public static void createPlayer(String name) throws UserExistException{
    String query = null;
    query = "select count(*) as count from players where name = '"+name+"';";
    Count count = new Count();
    runQuery(query, count, true);
    if (count.count != 0){
      throw new UserExistException(name);
      //throw new UserExistException("User name: "+name+" already exist!");
    }
    query = "insert into players (name) values ('"+name+"');";
    runQuery(query, null, false);
  }
  
  // player name must be unique and not null
  // check in UI
  public static Player startPlayer(String name) throws UserExistException{
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
  }
  
  public static Player getPlayerInfo(String name){
    String query = "select * from players where name = '"+name+"';";
    Player player = new Player();
    runQuery(query, player, true);
    return player;
  }
  
  public static Top10Scores getTop10(){
    String query = "select players.name, score.score from players, score "
        + "where players.id = score.player_id order by score DESC limit 10;";
    Top10Scores top10 = new Top10Scores();
    runQuery(query, top10, true);
    return top10;
  }
  
  public static void storeScore (Player player, int score){
    String query = "insert into score (player_id, score) values ("+player.id+", "+score+");";
    runQuery(query, null, false);
  }
  
  public static void setWin(Player player){
    int curr_win = ++player.win;
    String query = "update players set win="+curr_win+" where id="+player.id+";";
    runQuery(query, null, false);
  }
  
  public static void setLose(Player player){
    int curr_lose = ++player.lose;
    String query = "update players set lose="+curr_lose+" where id="+player.id+";";
    runQuery(query, null, false);
  }
  
  public static void deletePlayer(Player player){
    String query = "delete from players where id="+player.id+";";
    runQuery(query, null, false);
  }
  
  private static void runQuery(String query, QueryResult result, boolean isSelect){
    Connection conn = null;
    Statement stmt = null;
    try{
        Class.forName(JDBC_DRIVER); 
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();
        if (isSelect){
          ResultSet rs = stmt.executeQuery(query);
          result.getResult(rs);
          rs.close();
        } else {
          System.out.println("exec: "+query);
          stmt.executeUpdate(query);
        }
        stmt.close();
        conn.close();
    }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
    }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
    }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
    }
  }
}
