package todo;


/**
* todo/TodoListHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TodoList.idl
* Thursday, June 13, 2019 9:05:08 AM CST
*/

abstract public class TodoListHelper
{
  private static String  _id = "IDL:todo/TodoList:1.0";

  public static void insert (org.omg.CORBA.Any a, todo.TodoList that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static todo.TodoList extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (todo.TodoListHelper.id (), "TodoList");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static todo.TodoList read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_TodoListStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, todo.TodoList value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static todo.TodoList narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof todo.TodoList)
      return (todo.TodoList)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      todo._TodoListStub stub = new todo._TodoListStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static todo.TodoList unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof todo.TodoList)
      return (todo.TodoList)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      todo._TodoListStub stub = new todo._TodoListStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
