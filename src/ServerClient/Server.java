package ServerClient;

import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.ArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;



class Reader extends  Thread {

    Server server;
    Socket client;
    DataInputStream dis;
    int id;

    public Reader(Server server, Socket client, int id) throws IOException {
        this.server=server;
        this.client=client;
        this.id=id;
        dis = new DataInputStream(client.getInputStream());
    }
    @Override
    public void run() {
        while (true) {
            try {
                int code = dis.readInt();
                System.out.println("Im getting code: " + code);
                if(code == 0)//client wants get the actual data
                {
                    int id = dis.readInt();
                    server.sendActualData(id);
                }
                if(code == 1)//added new shape
                {
                    String shapeTR = dis.readUTF();
                    server.addShape(shapeTR);
                }
                if(code == 2)//delete shape
                {
                    int numToDel = dis.readInt();
                    server.delShape(numToDel);
                }
                if(code == 3)//added new custom shape
                {
                    String shapeTR = dis.readUTF();
                    server.addCustomShape(shapeTR);
                }
                if(code == 4)//delete custom shape
                {
                    int numToDel = dis.readInt();
                    server.delCustomShape(numToDel);
                }

            } catch (java.io.IOException ex) {
                ex.printStackTrace();
                break;
            }
        }
    }
}
class Writer {
    Server server;
    Socket client;
    DataOutputStream dos;
    int id;

    public Writer(Server server, Socket client, int id) throws IOException {
        this.server=server;
        this.client=client;
        this.id=id;
        dos = new DataOutputStream(client.getOutputStream());
    }

    public void dataIsNotActual(int code){
        try {
            dos.write(code);//code -1
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendData(int code) {
        if(code == 1){
            synchronized (server.modelsForDrawing)
            {try {
                dos.write(1);
                dos.write(server.modelsForDrawing.size());


            for(int i = 0; i<server.modelsForDrawing.size();i++) {
                dos.writeUTF(server.modelsForDrawing.get(i));
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        }
        //if(code == 2)
    }
}
class WaitClientTread extends Thread {
    Server server;
    public WaitClientTread(Server server){
        this.server = server;
    }
    @Override
    public void run() {
        try {
            while(true){
            Socket clientSocket = server.serverSocket.accept();
            synchronized (server.clientList) {
                int id = server.clientList.size();
                System.out.println("Connected: " + id + " client!");
                server.clientList.add(clientSocket);
                Reader newReader = new Reader(server, clientSocket, id);
                Writer newWriter = new Writer(server, clientSocket, id);
                synchronized (server.readers) {
                    server.readers.add(newReader);
                    synchronized (server.writers) {
                        server.writers.add(newWriter);
                    }
                }
                newReader.start();
            }
        }
        }
        catch (IOException e) {
            e.printStackTrace();

    }
    }
}
public class Server {
    //States
    int port = 1111;
    InetAddress inetAddress = null;
    ServerSocket serverSocket;
    List<Socket> clientList;

    List<String> modelsForDrawing;
    List<String> customModels;


    List<Reader> readers;
    List<Writer> writers;

    //Methods
    public void startServer() {
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            serverSocket = new ServerSocket(port, 0, inetAddress);
            System.out.println("Server started!");
            clientList = new ArrayList<>();

            WaitClientTread waitClientTread = new WaitClientTread(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addShape(String shapeTR){
        synchronized (modelsForDrawing){
            modelsForDrawing.add(shapeTR);
        }
        notifyClients(1);
    }

    public void delShape(int numOfShape){
        synchronized (modelsForDrawing){
            modelsForDrawing.remove(numOfShape);
        }
        notifyClients(1);
    }

    public void addCustomShape(String shapeTR){
        synchronized (customModels){
            customModels.add(shapeTR);
        }
        notifyClients(2);
    }

    public void delCustomShape(int numOfShape){
        synchronized (customModels){
            customModels.remove(numOfShape);
        }
        notifyClients(2);
    }

    void notifyClients(int code){// 1-changed shapes, 2-changed customSh
        for (int i = 0; i<writers.size();i++)
        {
            writers.get(i).dataIsNotActual(code);
        }
    }


    public void sendActualData(int id) {
        writers.get(id).sendData(id);
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }

}