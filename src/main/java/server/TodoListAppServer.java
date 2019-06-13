package server;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import todo.TodoList;
import todo.TodoListHelper;
import user.UserManager;
import user.UserManagerHelper;

import java.util.Properties;

/**
 * Created by chenglinyu on 2019-06-13
 * Description:
 */
public class TodoListAppServer {

    // 单例模式
    private static TodoListAppServer todoListAppServer = new TodoListAppServer();

    private TodoListAppServer()
    {

    }

    //获取唯一可用的对象
    public static TodoListAppServer getInstance(){
        return todoListAppServer;
    }

    private ORB orb;
    private POA rootPOA;
    private NamingContextExt ncRef;

    public void registerService(String userName)
    {
        try {
            //创建一个UserImpl实例
            TodoListServant todoListServant = new TodoListServant();
            todoListServant.setOrb(this.orb);

            //从服务中得到对象的引用,并注册到服务中
            org.omg.CORBA.Object ref = this.rootPOA.servant_to_reference(todoListServant);
            TodoList todoListRef = TodoListHelper.narrow(ref);

            //在命名上下文中绑定这个对象
            NameComponent[] path = this.ncRef.to_name(userName);
            this.ncRef.rebind(path, todoListRef);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public  void init(String[] args, Properties properties) {
        try {
            this.orb = ORB.init(args,properties);

            // get reference to rootpoa & activate the POAManager
            this.rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            this.rootPOA.the_POAManager().activate();

            // create userManagerServant and register it with the ORB
            UserManagerServant userManagerServant = new UserManagerServant();
            userManagerServant.setOrb(orb);

            // get object reference from the servant
            org.omg.CORBA.Object userManagerServantRef = this.rootPOA.servant_to_reference(userManagerServant);
            UserManager userManager = UserManagerHelper.narrow(userManagerServantRef);

            // get the root naming context
            // Use NamingContextExt which is part of the Interoperable
            // Naming Service (INS) specification
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            this.ncRef = NamingContextExtHelper.narrow(objRef);

            // bind the Object Reference in Naming
            String name = "UserManager";
            NameComponent path[] = ncRef.to_name(name);
            this.ncRef.rebind(path, userManager);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String... args) {
        try {
            Properties properties = new Properties();
            properties.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");  //指定ORB的ip地址
            properties.put("org.omg.CORBA.ORBInitialPort", "8888");
            TodoListAppServer todoListAppServer = TodoListAppServer.getInstance();
            todoListAppServer.init(args,properties);
            System.out.println("TodoListServer ready and waiting.....");
            todoListAppServer.orb.run();
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
            e.printStackTrace();
        }
    }
}

