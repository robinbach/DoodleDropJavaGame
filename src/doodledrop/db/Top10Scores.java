package doodledrop.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Top10Scores extends QueryResult {
  public ArrayList<PlayerScore> list = new ArrayList<>();
  public void getResult(ResultSet rs) throws SQLException{
    while(rs.next()){
      list.add(new PlayerScore(rs.getString("name"), rs.getInt("score")));
    }
  }
  
  public class PlayerScore{
    public String name;
    public int score;
    PlayerScore(String _name, int _score){
      this.name = _name;
      this.score = _score;
    }
    
    public String toString(){
      return "name: "+this.name + " score: "+ this.score;
    }
  }
}
