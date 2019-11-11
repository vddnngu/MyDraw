package ServerClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static final int port = 1111;
    private Socket clientSocket;
    InetAddress ip = null;
    private DataOutputStream dos;
    private DataInputStream dis;
    class MyThread extends Thread {
        @Override
        public void run(){
            try{
                try{
                    ip=InetAddress.getLocalHost();
                }catch (UnknownHostException e){
                    e.printStackTrace();
                }
                clientSocket = new Socket(ip, port);
                dis = new DataInputStream(clientSocket.getInputStream());
                dos = new DataOutputStream(clientSocket.getOutputStream());
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
