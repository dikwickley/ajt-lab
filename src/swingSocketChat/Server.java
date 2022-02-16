package swingSocketChat;
import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    JFrame f = new JFrame("Server");
    static String chat = "";

    private JPanel main;
    private JButton sendB;
    private JTextField msgInput;
    private JTextPane textPane1;

    static  ServerSocket serverSocket ;
    static Socket clientSocket ;
    static BufferedReader in;
    static PrintWriter out;

    private void updatePane(){
        textPane1.setText(chat);
        f.repaint();
    }

    private void sendMessage(){
        chat += "Server: " + msgInput.getText() + '\n';
        //send data
        out.println(msgInput.getText());
        out.flush();
        msgInput.setText("");
    }

    Server(){
        f.setVisible(true);
        f.setContentPane(main);
        f.pack();

        try{
            serverSocket = new ServerSocket(5000);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
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

    public static void main(String[] args) throws  Exception {
        Server s = new Server();
        s.start();

    }

    public void  start() throws Exception{
        String msg;

        msg = in.readLine();
        chat += "Client: " + msg + "\n";
        updatePane();
        while(msg!=null){
            System.out.println("Client : "+msg);
            msg = in.readLine();
            chat += "Client: " + msg + '\n';
            updatePane();
        }
        chat += "Client Disconnect. Connection closed";
        updatePane();
        System.out.println("Client disconnect");

        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
