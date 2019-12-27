package ServerClient;

import java.util.ArrayList;

public class Logger {
    public static void LogIn(Message msg){
        System.out.println("//-----------------------------------------------\n" +
                "GET:" + msg+"\n//-----------------------------------------------\n");
    }
    public static void LogOut(Message msg){
        System.out.println("//-----------------------------------------------\n" +
                "SEND:" + msg+"\n//-----------------------------------------------\n");
    }


}
