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

    //States------------------------------------------------------------

    int port = 1111;
    InetAddress inetAdress = null;
    ServerSocket serverSocket;
    List<Socket> clientSocket;
    List<DataInputStream> dataInputStreams;
    List<DataOutputStream> dataOutputStreams;

    //Methods-----------------------------------------------------------

    class MyThread extends Thread {
        @Override
        public void run() {
            //инициализация каналов общения в сокете для сервера
            try {
                //подключение к сокету общения на серверной стороне
                Socket socket = serverSocket.accept();
                synchronized (clientSocket) {
                    //номер подключаемого клиента
                    int id = clientSocket.size();
                    System.out.println("Connected: " + id + " client");
                    clientSocket.add(socket);
                    DataInputStream newDis = new DataInputStream(socket.getInputStream()); //канал чтения из сокета
                    DataOutputStream newDos = new DataOutputStream(socket.getOutputStream());//канал записи в сокета
                    synchronized (dataInputStreams) {
                        dataInputStreams.add(newDis);
                        synchronized (dataOutputStreams) {
                            dataOutputStreams.add(newDos);
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

    public void StartServer() {
        try {
            inetAdress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            serverSocket = new ServerSocket(port, 0, inetAdress);
            System.out.println("Server started!");
            clientSocket = new ArrayList<>();
            dataInputStreams = new ArrayList<>();
            dataOutputStreams = new ArrayList<>();
            MyThread firstThread = new MyThread();
            firstThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.StartServer();
    }
}
