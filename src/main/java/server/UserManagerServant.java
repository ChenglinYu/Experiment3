package server;

import org.omg.CORBA.ORB;
import user.UserManagerPOA;

import java.util.HashMap;

/**
 * Created by chenglinyu on 2019-06-13
 * Description:
 */
public class UserManagerServant extends UserManagerPOA {

    private TodoListAppServer todoListAppServer;

    private ORB orb;

    public void setOrb(ORB orb) {
        this.orb = orb;
    }

    // userName:password
    private HashMap<String, String> userMap;

    public UserManagerServant() {
        this.userMap = new HashMap<>();
        todoListAppServer = TodoListAppServer.getInstance();
    }

    public boolean logIn(String userName, String password) {
        if (password.equals(userMap.get(userName))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean register(String userName, String password) {
        if (userMap.get(userName) == null) {
            userMap.put(userName, password);
            todoListAppServer.registerService(userName);
            return true;
        } else {
            return false;
        }

    }
}