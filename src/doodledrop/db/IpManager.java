package doodledrop.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IpManager
{
    public static String getServerIp() throws Exception {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String ip = in.readLine();
            return ip;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static IpInstance storeIP(String ip){
      IpInstance serverIp = new IpInstance();
      String query = "insert into ip (ipaddress, connected) values ('"+ip+"', 0);";
      RunDb.runQuery(query, null, false);
      String query2 = "select serverid, ipaddress from ip where ipaddress='"+ip+"';"; 
      RunDb.runQuery(query2, serverIp, true);
      return serverIp;
    }
    
    public static void getIP(IpInstance ipInstance) throws ServerNotAvailableException{
      String query = "select serverid, ipaddress from ip where connected=0 limit 1;";
      RunDb.runQuery(query, ipInstance, true);
      if (ipInstance.serverId == null){
        throw new ServerNotAvailableException("No server is currently available.");
      }
    }
    
    public static void setConnected(IpInstance ipInstance){
      String query = "update ip set connected=1 where serverid="+ipInstance.serverId+";";
      RunDb.runQuery(query, null, false);
    }
    
    public static void setDisconnected(IpInstance ipInstance){
      String query = "update ip set connected=0 where serverid="+ipInstance.serverId+";";
      RunDb.runQuery(query, null, false);
    }
    
    public static void deleteIp(IpInstance ipInstance){
      String query = "delete from ip where serverid="+ipInstance.serverId+";";
      RunDb.runQuery(query, null, false);
    }
    
    public static void deleteAllIps(){
      String query = "delete from ip;";
      RunDb.runQuery(query, null, false);
    }
    
    public static class IpInstance extends QueryResult{
      public Integer serverId;
      public String ipaddress;
      
      @Override
      void getResult(ResultSet rs) throws SQLException
      {
        while(rs.next()){
          serverId  = rs.getInt("serverid");
          ipaddress  = rs.getString("ipaddress");
        }
      }
      
      @Override
      public String toString(){
        return "server id: " + this.serverId + " ipaddress " + this.ipaddress;
      }
    }
}
