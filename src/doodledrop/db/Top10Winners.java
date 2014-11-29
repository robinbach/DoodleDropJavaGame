package doodledrop.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Top10Winners extends QueryResult {
  public ArrayList<PlayerWinlose> list = new ArrayList<>();
  
  public void getResult(ResultSet rs) throws SQLException{
    while(rs.next()){
      list.add(new PlayerWinlose(rs.getString("name"), rs.getInt("win"), rs.getInt("lose")));
    }
  }
  
  public class PlayerWinlose{
    public String name;
    public int win;
    public int lose;
    PlayerWinlose(String _name, int _win, int _lose){
      this.name = _name;
      this.win = _win;
      this.lose = _lose;
    }
    
    public String toString(){
      return "name: "+this.name + " win: "+ this.win + " lose: " + this.lose;
    }
  }
}