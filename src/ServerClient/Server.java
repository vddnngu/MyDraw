package ServerClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Server {
    int port = 1111;
    InetAddress ip = null;
    ServerSocket ss;
    List<Socket> cs;
    List<DataInputStream> dis;
    List<DataOutputStream> dos;
    Socket sc;

    class MyThread extends Thread {
        @Override
        public void run() {
            //инициализация каналов общения в сокете для сервера
            try {
                //подключение к сокету общения на серверной стороне
                Socket socket = ss.accept();
                synchronized (cs) {
                    int id = cs.size();
                    System.out.println("Connected: " + id + " client");
                    cs.add(socket);
                    DataInputStream newDis = new DataInputStream(socket.getInputStream()); //канал чтения из сокета
                    DataOutputStream newDos = new DataOutputStream(socket.getOutputStream());//канал записи в сокета
                    synchronized (dis) {
                        dis.add(newDis);
                        synchronized (dos) {
                            dos.add(newDos);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            MyThread nextThread = new MyThread();
            nextThread.start();
        }
    }

    public void startServer() {
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            ss = new ServerSocket(port, 0, ip);
            System.out.println("Server started!");
            cs = new ArrayList<>();
            dis = new ArrayList<>();
            dos = new ArrayList<>();
            MyThread firstThread = new MyThread();
            firstThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }
}
