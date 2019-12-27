package ServerClient;

import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static final int PORT = 3443;
     ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
     ArrayList<String> shapesForDrawing;
     ArrayList<String> customShapes;
    HashMap<Integer, ArrayList<Integer>> shapesMap;

    public Server() {

        shapesForDrawing = new ArrayList<String>();
        customShapes = new ArrayList<String>();
        shapesMap = new HashMap<Integer, ArrayList<Integer>>();
    }
    public void start(){
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен!");
            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("Клиент подключен!");
                ClientHandler client = new ClientHandler(clientSocket, this);
                clients.add(client);
                new Thread(client).start();
                Message msg = new Message();
                msg.code = Cods.ServerCode_SetCS;
                msg.data = new ArrayList<String>(customShapes);
                client.sendMsg(msg);
                msg.code = Cods.ServerCode_SetSFD;
                msg.data = new ArrayList<String>(shapesForDrawing);
                client.sendMsg(msg);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                clientSocket.close();
                System.out.println("Сервер остановлен");
                serverSocket.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendMessageToAllClients(Message msg) {
        for (ClientHandler it : clients) {
            it.sendMsg(msg);
        }

    }
    void dataIsUpdate(){
        Message msg = new Message();
        msg.code = Cods.ServerCode_SetCS;
        msg.data = new ArrayList<String>(customShapes);
        sendMessageToAllClients(msg);
        msg.code = Cods.ServerCode_SetSFD;
        msg.data = new ArrayList<String>(shapesForDrawing);
        sendMessageToAllClients(msg);
    }
    public void addShapeForDrawing(String sh, int customShNumber){
        shapesForDrawing.add(sh);
        shapesMap.get(customShNumber).add(shapesForDrawing.size()-1);
        dataIsUpdate();
    }
    public void addCustomShape(String sh){
        customShapes.add(sh);
        shapesMap.put(shapesMap.size(), new ArrayList<Integer>());
        dataIsUpdate();
    }
    public void deleteShapeInSFD(int num){
        shapesForDrawing.remove(num);
        dataIsUpdate();
    }
    public void deleteShapeInSFD(ArrayList<Integer> nums){
        Collections.sort(nums, Collections.reverseOrder());
        for (Integer it : nums) {
            shapesForDrawing.remove(it);
        }
        dataIsUpdate();
    }
    public void deleteShapeInCustomSh(int num){
        customShapes.remove(num);
        deleteShapesMatchCustom(num);
        dataIsUpdate();
    }

    private void deleteShapesMatchCustom(int num){
        ArrayList<Integer> shapesToDelete = shapesMap.get(num);
        Collections.sort(shapesToDelete, Collections.reverseOrder());
        for (int it : shapesToDelete) {
            shapesForDrawing.remove(it);
        }
    }

    public ArrayList<String> getShapesForDrawing() {
        return shapesForDrawing;
    }

    public ArrayList<String> getCustomShapes() {
        return customShapes;
    }

    public static void main(String[] args) {
        Server sr = new Server();
        sr.addCustomShape("W!1!1!2!0!1!5!6");
        sr.addCustomShape("W!1!1!3!0!1!5!6!3!4");
        sr.addCustomShape("W!1!1!2!0!0!4!6");
        sr.addShapeForDrawing("W!2!2!2!0!1!5!6", 0);
        sr.addShapeForDrawing("W!3!2!2!0!1!5!6", 0);
        sr.addShapeForDrawing("W!2!2!3!0!1!5!6!3!4", 1);
        sr.addShapeForDrawing("W!8!2!3!0!1!5!6!3!4", 1);
        sr.addShapeForDrawing("W!2!1!3!0!1!5!6!3!4", 1);
        sr.addShapeForDrawing("W!1!1!2!0!0!4!6", 2);
        sr.addShapeForDrawing("W!2!1!2!0!0!4!6", 2);
        sr.addShapeForDrawing("W!1!3!2!0!0!4!6", 2);
        sr.start();
    }
}