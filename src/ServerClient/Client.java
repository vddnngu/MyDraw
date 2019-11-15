package ServerClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

class Client extends JFrame {

    // States
    private static final int SERVER_PORT = 1111;
    private Socket clientSocket;
    InetAddress inetAddress = null;
    private DataInputStream inMessage;
    private DataOutputStream outMessage;
    private JTextField textField;

    //Methods
    class  MyThread extends Thread {
        @Override
        public void run () {
            try {
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                clientSocket = new Socket(inetAddress, SERVER_PORT);
                inMessage = new DataInputStream(clientSocket.getInputStream());
                outMessage  = new DataOutputStream(clientSocket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Client() {
        MyThread connect = new MyThread();
        connect.start();
        setBounds(300, 150, 300, 250);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.SOUTH);
        JButton ready = new JButton("Enter");
        bottomPanel.add(ready, BorderLayout.EAST);
        textField = new JTextField("text field: ");
        bottomPanel.add(textField, BorderLayout.CENTER);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setText("");
            }
        });
        ready.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println(textField.getText());
                    outMessage.writeUTF(textField.getText());
                    outMessage.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String answer = inMessage.readUTF();
                        outMessage.writeUTF(answer);
                    }
                } catch (Exception e) { }
            }
        }).start();
        setVisible(true);
    }

    //TODO
    public void RepaintHandler(){

    }

    public static void main(String[] args) {
        Client client = new Client();
    }

}