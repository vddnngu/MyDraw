package ServerClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ServerHandler implements Runnable {

    private DataOutputStream outMessage;
    private DataInputStream inMessage;
    private static final String HOST = "localhost";
    private static final int PORT = 3443;
    private Client client = null;
    private Socket mySocket;


    // конструктор, который принимает клиентский сокет и сервер
    public ServerHandler(Client client, Socket socket) {
        try {
            this.client = client;
            this.mySocket = socket;
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
                synchronized (inMessage) {
                    Message msg = new Message();
                    Cods code = Cods.valueOf(inMessage.readInt());
                    msg.code = code;
                    switch (code) {
                        case ServerCode_SetCS:
                            int count = inMessage.readInt();
                            for (int i = 0; i < count; i++)
                                msg.data.add(inMessage.readUTF());
                            client.updateCustomShape(msg.data);
                            Logger.LogIn(msg);
                            break;
                        case ServerCode_SetSFD:
                            count = inMessage.readInt();
                            for (int i = 0; i < count; i++) {
                                msg.data.add(inMessage.readUTF());
                            }
                            client.updateShapesForDrawing(msg.data);
                            Logger.LogIn(msg);
                            break;
                        case ServerCode_DataNotActual:
                            Logger.LogIn(msg);
                            client.dataIsNotActual();
                            break;
                    }
                }
            }
        }
        catch ( IOException ex) {
            ex.printStackTrace();
        }
    }
    // отправляем сообщение
    public void sendMsg(Message msg) {
        try {
            synchronized (outMessage){
            outMessage.writeInt(msg.code.getValue());
            outMessage.flush();
            switch (msg.code) {
                case ClientCode_AddCS:
                    outMessage.writeUTF(msg.data.get(0));
                    outMessage.flush();
                    break;
                case ClientCode_AddSFD:
                    outMessage.writeInt(Integer.parseInt(msg.data.get(0)));
                    outMessage.flush();
                    outMessage.writeUTF(msg.data.get(1));
                    outMessage.flush();
                    break;
                case ClientCode_DelCS:
                    outMessage.writeInt(Integer.parseInt(msg.data.get(0)));
                    outMessage.flush();
                    break;
                case ClientCode_DelSFD:
                    outMessage.writeInt(msg.data.size());
                    outMessage.flush();
                    for(String it: msg.data)
                    {
                        outMessage.writeInt(Integer.parseInt(it));
                        outMessage.flush();
                    }
                    break;
                case ClientCode_GetCS:

                case ClientCode_GetSFD:
                    break;

            }}
        } catch (IOException e) {
            e.printStackTrace();
        }
        Logger.LogOut(msg);
    }
}