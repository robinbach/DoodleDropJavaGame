package doodledrop;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import doodledrop.Constants.Directions;

public class PlayerSocket implements Runnable
{
	private int portNum;
	private String ipAddr;
	private Socket socket;
	private DataOutputStream outData;
	private DataInputStream inData;
	
	public PlayerSocket(String inIPAddr, int inPortNum)
	{
		ipAddr = inIPAddr;
		portNum = inPortNum;
	}
	
	public void startServer()
	{
		ServerSocket serverSocket;
		try
		{
			serverSocket = new ServerSocket(portNum);
		    System.out.println("waiting for client to connect...");
		    socket = serverSocket.accept();
		    outData = new DataOutputStream(socket.getOutputStream());
		    inData = new DataInputStream(socket.getInputStream());
		}
		catch(IOException exception)
		{
			System.out.println("The corresponding port has already been used by other program");
		    System.exit(1);
		}
	}
	
	public void startClient()
	{
	    try
	    {
	      socket = new Socket(ipAddr, portNum);
	      outData = new DataOutputStream(socket.getOutputStream());
	      inData = new DataInputStream(socket.getInputStream());
	    }
	    catch( IOException exception )
	    {
	      System.out.println("unable to connect the server");
	      System.exit(3);
	    }
	}
	
	  public String recvString()
	  {
	    Vector<Byte> byteVec = new Vector<Byte>();
	    byte [] byteArray;
	    byte recByte;
	    String resultString = null;
	    try
	    {
	      recByte = inData.readByte();
	      while(recByte != 0)
	      {
	        byteVec.add(recByte);
	        recByte = inData.readByte();
	      }
	      byteArray = new byte[byteVec.size()];
	      for(int i = 0; i < byteVec.size(); ++i)
	        byteArray[i] = byteVec.elementAt(i).byteValue();
	      resultString = new String(byteArray);
	    }
	    catch( IOException exception )
	    {
	      System.out.println("Error happened when receiving string");
	      System.exit(1);
	    }
	    return resultString;
	  }
	  
	  
	  
	  public boolean sendString( String str)
	  {
	    boolean isSuccess = false;
	    try
	    {
	      outData.writeBytes(str);
	      outData.writeByte(0);
	      isSuccess = true;
	    }
	    catch(IOException exception)
	    {
	      System.out.println("Fail to send string to client");
	      System.exit(1);
	    }
	    return isSuccess;
	  }
	  
	  public boolean sendInfo(XVec2 location, Directions direction)
	  {
		  return sendString(location.x + " " + location.y + " " + direction.name());
	  }
	  
	  public void recvInfo()
	  {
		  String result = recvString();
		  int firstIndex = result.indexOf(' ');
		  int lastIndex = result.lastIndexOf(' ');
		  int x = Integer.parseInt(result.substring(0, firstIndex));
		  int y = Integer.parseInt(result.substring(firstIndex + 1, lastIndex));
		  Directions direction = Directions.valueOf(result.substring(lastIndex + 1));
		  GameLogic.updatePlayer2Info(new XVec2(x, y), direction);
	  }
	  
	  public void run()
	  {
		  while(true)
		  {
			  recvInfo();
		  }
	  }
}
