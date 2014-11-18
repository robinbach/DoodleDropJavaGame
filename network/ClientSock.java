package test;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Vector;

public class ClientSock
{
  private String targetIP;
  private int targetPort;
  private Socket clientSocket;
  private static DataOutputStream outData;
  private DataInputStream inData;
  
  
  
  public ClientSock(String ip, int port)
  {
    targetIP = ip;
    targetPort = port;
  }
  
  public void startClient()
  {
    try
    {
      clientSocket = new Socket(targetIP, targetPort);
      outData = new DataOutputStream(clientSocket.getOutputStream());
      inData = new DataInputStream(clientSocket.getInputStream());
    }
    catch( IOException exception )
    {
      System.out.println("unable to connect the server");
      System.exit(3);
    }
  }
  
  public boolean sendByte(byte outByte)
  {
    boolean isSuccess = false;
    try
    {
      outData.writeByte(outByte);
      isSuccess = true;
    }
    catch(IOException exception)
    {
      System.out.println("Can not send Byte");
    }
    return isSuccess;
  }
  
  
  public byte recvByte()
  {
    byte recByte = 0; 
    try
    {
      recByte= inData.readByte();
    }
    catch(IOException exception)
    {
      System.out.println("Server can't receive byte");
    }
    return recByte;
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
  
}
