package client;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import todo.TodoList;
import todo.TodoListHelper;
import user.UserManager;
import user.UserManagerHelper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by chenglinyu on 2019-06-13
 * Description: TodoListAPP客户端
 */
public class TodoListAppClient {


    // ORB is the intermediary between client and server
    private ORB orb;
    private NamingContextExt ncRef;

    // 用户管理者
    private UserManager userManager;

    // 当前用户的todoList
    private TodoList todoList;

    // 读取console input
    private BufferedReader bufferedReader;

    public static void main(String... args) {
        TodoListAppClient todoListAppClient = new TodoListAppClient(); // 创建一个客户端
        todoListAppClient.init();
        todoListAppClient.procedure();
    }

    public TodoListAppClient() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void init() {
        System.out.println("Client init config starts....");
        String[] args = {};
        Properties properties = new Properties();
        properties.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");  //指定ORB的ip地址
        properties.put("org.omg.CORBA.ORBInitialPort", "8888");       //指定ORB的端口

        try {
            //获取ORB实例
            orb = ORB.init(args, properties);

            // get the root naming context
            // Use NamingContextExt which is part of the Interoperable
            // Naming Service (INS) specification
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            this.ncRef = NamingContextExtHelper.narrow(objRef);

            //通过ORB拿到server实例化好的Creator类
            this.userManager = UserManagerHelper.narrow(this.ncRef.resolve_str("UserManager"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Client init config ends...");
    }

    //与用户交互
    public void procedure() {
        String choice;
        try {
            while (true) {
                System.out.println("Welcome to to_do_list_APP!Please choose:");
                System.out.println("1.Register\n2.Login\n3.Exit");
                choice = bufferedReader.readLine();
                switch (choice) {
                    case "1":
                        while (true) {
                            if (register()) {
                                break;
                            }
                        }
                        break;
                    case "2":
                        if (login()) {
                            System.out.println("Login Success!");
                            transcation();
                        } else {
                            System.out.println("Login fail!");
                        }
                        break;
                    case "3":
                        return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //注册
    private boolean register() {
        String username, password;
        try {
            System.out.println("please input username:");
            username = bufferedReader.readLine();
            System.out.println("please input password:");
            password = bufferedReader.readLine();
            if (userManager.register(username, password)) {
                System.out.println("Register Success!");
                return true;
            } else {
                System.out.println("Failure! The useName has been registered");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //登录
    private boolean login() {
        String username, password;
        try {
            System.out.println("please input username:");
            username = bufferedReader.readLine();
            System.out.println("please input password:");
            password = bufferedReader.readLine();
            if (userManager.logIn(username, password)) {

                //通过ORB拿到server实例化好的User类
                this.todoList = TodoListHelper.narrow(ncRef.resolve_str(username));
                return true;

            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Transaction
    private void transcation() {
        String choice = "6";
        do {
            System.out.println("Please choose following command:");
            System.out.println("1.Add item\n" +
                    "2.Query item\n" +
                    "3.Show items\n" +
                    "4.Delete item\n" +
                    "5.Clear items\n" +
                    "6.Logout");
            try {
                choice = this.bufferedReader.readLine();
                switch (choice) {
                    case "1":
                        addItem();
                        break;
                    case "2":
                        query();
                        break;
                    case "3":
                        System.out.println(todoList.show());
                        break;
                    case "4":
                        delete();
                        break;
                    case "5":
                        if (todoList.clear()) {
                            System.out.println("Clear items done!");
                        } else {
                            System.out.println("The to-do-list is empty,doesn't need to clear!");
                        }
                        break;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!choice.equals("6"));
    }

    // 处理添加待办事项
    private void addItem() {
        try {
            System.out.println("please input startTime (like this:2018-06-13 08:20:00):");
            String startTime = bufferedReader.readLine();
            System.out.println("please input endTime (like this:2018-06-14 08:20:00):");
            String endTime = bufferedReader.readLine();
            System.out.println("please input description:");
            String label = bufferedReader.readLine();
            if (todoList.add(startTime, endTime, label)) {
                System.out.println("Add item Success!");
            } else {
                System.out.println("Add item fail! Date format illegal");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 处理查询待办事项
    private void query() {
        try {
            System.out.println("please input startTime (like this:2018-06-13 08:20:00):");
            String startTime = bufferedReader.readLine();
            System.out.println("please input endTime (like this:2018-06-14 08:20:00):");
            String endTime = bufferedReader.readLine();
            System.out.println(todoList.query(startTime, endTime));
        } catch (Exception e) {
            System.out.println("Date Format Illegal");
        }
    }


    // 处理删除待办事项
    private void delete() {
        try {
            System.out.println("please input index:");
            String index = bufferedReader.readLine();
            if (todoList.delete(index)) {
                System.out.println("Delete item successful!");
            } else {
                System.out.println("Delete item fail!");
            }

        } catch (Exception e) {
            System.out.println("No item to delete");
        }

    }

}



