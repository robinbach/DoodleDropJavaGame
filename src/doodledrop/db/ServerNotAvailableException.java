package doodledrop.db;

public class ServerNotAvailableException extends Exception
{
  ServerNotAvailableException(String str){
    super(str);
  }  
}