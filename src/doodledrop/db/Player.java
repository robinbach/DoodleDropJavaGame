package doodledrop.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Player extends QueryResult {
  public String name;
  public Integer id, win, lose;
  public void getResult(ResultSet rs) throws SQLException{
    while(rs.next()){
      id  = rs.getInt("id");
      name  = rs.getString("name");
      win = rs.getInt("win");
      lose = rs.getInt("lose");
    }
  }
  
  public String toString(){
    return "Player name: "+name + " id: "+id + " win: "+win+" lose: "+lose;
  }
}