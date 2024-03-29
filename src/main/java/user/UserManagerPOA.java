package user;


/**
* user/UserManagerPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from UserManager.idl
* Thursday, June 13, 2019 8:40:00 AM CST
*/

public abstract class UserManagerPOA extends org.omg.PortableServer.Servant
 implements user.UserManagerOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("logIn", new java.lang.Integer (0));
    _methods.put ("register", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // user/UserManager/logIn
       {
         String userName = in.read_string ();
         String password = in.read_string ();
         boolean $result = false;
         $result = this.logIn (userName, password);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 1:  // user/UserManager/register
       {
         String userName = in.read_string ();
         String password = in.read_string ();
         boolean $result = false;
         $result = this.register (userName, password);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:user/UserManager:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public UserManager _this() 
  {
    return UserManagerHelper.narrow(
    super._this_object());
  }

  public UserManager _this(org.omg.CORBA.ORB orb) 
  {
    return UserManagerHelper.narrow(
    super._this_object(orb));
  }


} // class UserManagerPOA
