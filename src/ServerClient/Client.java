package ServerClient;

import TopoGeomAPI.HotFix.Shape;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import TopoGeomAPI.HotFix.*;

class Client {

    // States
    static final int PORT = 3443;
    private ServerHandler server;
    private boolean dataIsActual;
    private ArrayList<Shape> shapesForDrawing;
    private ArrayList<Shape> customShapes;
    private static final String SERVER_HOST = "localhost";
    private Socket mySocket;


    //Methods
    public Client() {
        try {
            dataIsActual = false;
            mySocket = new Socket(SERVER_HOST, PORT);
            server = new ServerHandler(this, mySocket);
            new Thread(server).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void addShapeForDrawing(Shape sh, int customShNumber){
        Message msg = new Message();
        msg.code = Cods.ClientCode_AddSFD;
        msg.data.add(""+customShNumber);
        msg.data.add(DataTransferType.transfer(sh));
        server.sendMsg(msg);
    }
    public void addCustomShape(Shape sh){
        Message msg = new Message();
        msg.code = Cods.ClientCode_AddCS;
        msg.data.add(DataTransferType.transfer(sh));
        server.sendMsg(msg);
    }
    public void deleteCustomShape(int customShNumber){
        Message msg = new Message();
        msg.code = Cods.ClientCode_DelCS;
        msg.data.add(""+customShNumber);
        server.sendMsg(msg);
    }
    public void getActualDrawList(){
        Message msg = new Message();
        msg.code = Cods.ClientCode_GetSFD;
        server.sendMsg(msg);
    }
    public void getActualCustomShapeList(){
        Message msg = new Message();
        msg.code = Cods.ClientCode_GetCS;
        server.sendMsg(msg);
    }
    public  boolean myDataIsActual(){
        return  dataIsActual;
    }

    public void dataIsNotActual() {

        dataIsActual = false;
    }

    public void updateCustomShape(ArrayList<String> data) {
        customShapes = new ArrayList<Shape>();
        for (String it : data)
            customShapes.add(DataTransferType.transfer(it));;
    }

    public void updateShapesForDrawing(ArrayList<String> data) {
        shapesForDrawing = new ArrayList<Shape>();
        for (String it : data)
            shapesForDrawing.add(DataTransferType.transfer(it));
    }

    public ArrayList<Shape> getCustomShapes() {
        return customShapes;
    }

    public ArrayList<Shape> getShapesForDrawing() {
        return shapesForDrawing;
    }

    public static void main(String[] args) {
        Client cl = new Client();
        cl.getActualDrawList();
        ArrayList<Shape> sh = cl.getShapesForDrawing();
        cl.getActualCustomShapeList();
        cl.getCustomShapes();
        cl.addCustomShape(DataTransferType.transfer("W!1!1!2!0!0!4!0"));
        cl.addShapeForDrawing(DataTransferType.transfer("W!1!1!2!0!0!4!0"), cl.customShapes.size());
        cl.addShapeForDrawing(DataTransferType.transfer("W!1!1!2!0!0!4!0"), cl.customShapes.size());
        cl.addShapeForDrawing(DataTransferType.transfer("W!1!1!2!0!0!4!0"), cl.customShapes.size());
        cl.addShapeForDrawing(DataTransferType.transfer("W!1!1!2!0!0!4!6"), cl.customShapes.size());
        cl.getActualDrawList();
        sh = cl.getShapesForDrawing();
        cl.getActualCustomShapeList();
        sh = cl.getCustomShapes();
        cl.deleteCustomShape(2);
        cl.getActualDrawList();
        sh = cl.getShapesForDrawing();
        cl.getActualCustomShapeList();
        sh = cl.getCustomShapes();
    }
}