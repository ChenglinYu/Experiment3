package user;


/**
* user/UserManagerOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from UserManager.idl
* Thursday, June 13, 2019 8:40:00 AM CST
*/

public interface UserManagerOperations 
{
  boolean logIn (String userName, String password);
  boolean register (String userName, String password);
} // interface UserManagerOperations
