package doodledrop.db;

public class UserNotExistException extends Exception
{
  UserNotExistException(String str){
    super(str);
  }  
}
