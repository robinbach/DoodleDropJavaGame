package doodledrop.db;

public class UserExistException extends Exception
{
  UserExistException(String str){
    super(str);
  }  
}
