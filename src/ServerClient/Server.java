package ServerClient;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.ArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class Server {
    //States
    int port = 1111;
    InetAddress inetAddress = null;
    ServerSocket serverSocket;
    List<Socket> socketList;
    List<DataInputStream> dataInputStreams;
    List<DataOutputStream> dataOutputStreams;
    List<ReaderThread> readers;

    //Methods
    class ReaderThread extends  Thread {
        public  int id;
        @Override
        public void run() {
            while (true) {
                try {
                    String answer = dataInputStreams.get(id).readUTF();
                    System.out.println(id);
                    System.out.println(answer);
                } catch (java.io.IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Socket socket = serverSocket.accept();
                synchronized (socketList) {
                    int id = socketList.size();
                    System.out.println("Connected: " + id + " client!");
                    socketList.add(socket);
                    DataInputStream newDis = new DataInputStream(socket.getInputStream());
                    DataOutputStream newDos = new DataOutputStream(socket.getOutputStream());
                    ReaderThread reader = new ReaderThread();
                    reader.id = id;
                    synchronized (dataInputStreams) {
                        dataInputStreams.add(newDis);
                        synchronized (dataOutputStreams) {
                            dataOutputStreams.add(newDos);
                            synchronized (readers) {
                                readers.add(reader);
                            }
                        }
                    }
                    reader.start();
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
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            serverSocket = new ServerSocket(port, 0, inetAddress);
            System.out.println("Server started!");
            socketList = new ArrayList<>();
            dataInputStreams = new ArrayList<>();
            dataOutputStreams = new ArrayList<>();
            readers = new ArrayList<>();

            MyThread firstThread = new MyThread();
            firstThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //TODO
    public void EventHandler(){

    }
    public void ExitHandler(){

    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }
}