package todo;

/**
* todo/TodoListHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TodoList.idl
* Thursday, June 13, 2019 9:05:08 AM CST
*/

public final class TodoListHolder implements org.omg.CORBA.portable.Streamable
{
  public todo.TodoList value = null;

  public TodoListHolder ()
  {
  }

  public TodoListHolder (todo.TodoList initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = todo.TodoListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    todo.TodoListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return todo.TodoListHelper.type ();
  }

}
