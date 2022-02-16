package swingSocketChat;
import org.postgresql.core.Tuple;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;



public class Client {

    JFrame f = new JFrame("Client");
    static String chat = "";
    private JPanel main;
    private JButton sendB;
    private JTextField msgInput;
    private JTextPane textPane1;


    static Socket clientSocket; // socket used by client to send and recieve data from server
    static  BufferedReader in;   // object to read data from socket
    static PrintWriter out;     // object to write data into socket

    private void updatePane(){
        textPane1.setText(chat);
        f.repaint();
    }
    private void sendMessage(){
        chat += "Client: " + msgInput.getText() + '\n';
        //send data
        out.println(msgInput.getText());
        out.flush();
        msgInput.setText("");
    }
    Client(){
        f.setVisible(true);
        f.setContentPane(main);
        f.pack();

        try {
            clientSocket = new Socket("localhost", 5000);
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e){
            System.out.println("error");
        }


        sendB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendMessage();
                updatePane();
            }
        });
    }

    public static void main(String[] args) throws Exception {
        Client c = new Client();
        c.start();
    }
    public void start() throws Exception {
        String msg;
        msg = in.readLine();
        chat += "Server: " + msg + "\n";
        updatePane();

        while(msg!=null){
            System.out.println("Server : "+msg);
            msg = in.readLine();
            chat += "Server: " + msg + "\n";
            updatePane();
        }

        chat += "Server out of service. Connection closed";
        updatePane();
        System.out.println("Server out of service");

        out.close();
        clientSocket.close();
    }
}
