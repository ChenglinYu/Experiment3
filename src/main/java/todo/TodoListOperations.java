package todo;


/**
* todo/TodoListOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TodoList.idl
* Thursday, June 13, 2019 9:05:08 AM CST
*/

public interface TodoListOperations 
{
  boolean add (String startTime, String endTime, String label);
  String query (String rangeStartTime, String rangeEndTime);
  boolean delete (String itemNo);
  boolean clear ();
  String show ();
} // interface TodoListOperations
