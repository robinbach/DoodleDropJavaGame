package doodledrop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RunDb
{
  //JDBC driver name and database URL
  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
  private static final String DB_URL = "jdbc:mysql://"
      + "eecs285-p4.ckaip7e5k60r.us-west-2.rds.amazonaws.com:3306/eecs285";
  
  //  Database credentials
  private static final String USER = "dianazh";
  private static final String PASS = "dianazh17";
  
  public static void runQuery(String query, QueryResult result, boolean isSelect){
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
