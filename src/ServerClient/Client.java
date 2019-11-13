package ServerClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    //States------------------------------------------------------

    private static final int port = 1111;
    private Socket clientSocket;
    InetAddress inetAddress = null;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    //Methods-----------------------------------------------------

    class MyThread extends Thread {
        @Override
        public void run(){
            try{
                try{
                    inetAddress =InetAddress.getLocalHost();
                }catch (UnknownHostException e){
                    e.printStackTrace();
                }
                clientSocket = new Socket(inetAddress, port);
                dataInputStream = new DataInputStream(clientSocket.getInputStream());
                dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public Client(){
        MyThread connect = new MyThread();
        connect.start();
    }

    public static void main(String[] args) {
        Client client = new Client();
    }
}
