package ServerClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ClientHandler implements Runnable {
    private Server server;
    private DataOutputStream outMessage;
    private DataInputStream inMessage;
    private static final String HOST = "localhost";
    private static final int PORT = 3443;
    private Socket clientSocket = null;

    private static int clients_count = 0;

    public ClientHandler(Socket socket, Server server) {
        try {
            clients_count++;
            this.server = server;
            this.clientSocket = socket;
            this.outMessage = new DataOutputStream(socket.getOutputStream());
            this.inMessage = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            while (true) {
                synchronized (inMessage){
                    Message msg = new Message();
                    Cods code = Cods.valueOf(inMessage.readInt());
                    msg.code=code;
                    switch (code){
                        case ClientCode_AddCS:
                            msg.data.add(inMessage.readUTF());
                            synchronized (server.customShapes){
                            server.addCustomShape(msg.data.get(0));
                            }
                            Logger.LogIn(msg);
                            break;
                        case ClientCode_AddSFD:
                            int customShNumber = inMessage.readInt();
                            msg.data.add(""+customShNumber);
                            msg.data.add(inMessage.readUTF());
                            synchronized (server.shapesForDrawing) {
                                server.addShapeForDrawing(msg.data.get(1), customShNumber);
                            }
                            break;
                        case ClientCode_DelCS:
                            Integer num = inMessage.readInt();
                            msg.data.add(""+num);
                            synchronized (server.customShapes){
                            server.deleteShapeInCustomSh(num);
                            }
                            Logger.LogIn(msg);
                            break;
                        case ClientCode_DelSFD:
                            int count = inMessage.readInt();
                            ArrayList<Integer> nums = new ArrayList<Integer>();
                            synchronized (server.shapesForDrawing){
                            for (int i =0; i<=count; i++) nums.add(inMessage.readInt());
                            server.deleteShapeInSFD(nums);
                            }
                            Logger.LogIn(msg);
                            break;
                        case ClientCode_GetCS:
                            synchronized (server.customShapes){
                            Logger.LogIn(msg);
                            msg.code = Cods.ServerCode_SetCS;
                            msg.data = new ArrayList<String>(server.getCustomShapes());
                            sendMsg(msg);
                            break;}
                        case ClientCode_GetSFD:
                            synchronized (server.shapesForDrawing){
                            Logger.LogIn(msg);
                            msg.code = Cods.ServerCode_SetSFD;
                            msg.data = new ArrayList<String>(server.getShapesForDrawing());
                            sendMsg(msg);
                            break;}
                        default:break;
                    }
            }}
        }
        catch ( IOException ex) {
            ex.printStackTrace();
        }
    }
    public void sendMsg(Message msg) {
        try {
            synchronized (outMessage){
            outMessage.writeInt(msg.code.getValue());
            outMessage.flush();
            switch (msg.code) {
                case ServerCode_DataNotActual:
                    break;
                case ServerCode_SetSFD:
                    synchronized (server.shapesForDrawing) {
                        outMessage.writeInt(msg.data.size());
                        outMessage.flush();
                        for (String it : msg.data) {
                            outMessage.writeUTF(it);
                            outMessage.flush();
                        }
                    }
                    break;
                case ServerCode_SetCS:
                    synchronized (server.customShapes) {
                        outMessage.writeInt(msg.data.size());
                        outMessage.flush();
                        for (String it : msg.data) {
                            outMessage.writeUTF(it);
                            outMessage.flush();
                        }
                    }
                    break;
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Logger.LogOut(msg);
    }

}