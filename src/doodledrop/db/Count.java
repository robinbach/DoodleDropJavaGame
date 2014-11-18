package doodledrop.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Count extends QueryResult {
  public int count;
  public void getResult(ResultSet rs) throws SQLException{
    while(rs.next()){
      count  = rs.getInt("count");
    }      
  }    
}