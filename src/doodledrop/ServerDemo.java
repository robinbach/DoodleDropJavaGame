package doodledrop;

public class ServerDemo
{
  public static void main(String [] argv)
  {
    ServerSock firstServer;
    String recvString;
    firstServer = new ServerSock(45000);
    firstServer.startServer();
    recvString = firstServer.recvString();
    System.out.println("Received message from client " + recvString);
    firstServer.recvInfo();
    //firstServer.sendString("Back at ya client");
  }
}
