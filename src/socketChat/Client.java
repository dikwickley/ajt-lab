package socketChat;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    public static void main(String[] args)  throws Exception{
        final Socket clientSocket; // socket used by client to send and recieve data from server
        final BufferedReader in;   // object to read data from socket
        final PrintWriter out;     // object to write data into socket
        final Scanner sc = new Scanner(System.in); // object to read data from user's keybord

        clientSocket = new Socket("localhost", 5000);
        out = new PrintWriter(clientSocket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        //sending thread
        Thread sender = new Thread(new Runnable() {
            String msg;
            @Override
            public void run() {
                while(true){
                    msg = sc.nextLine();
                    out.println(msg);
                    out.flush();
                }
            }
        });
        sender.start();

        //message recieving thread
        Thread receiver = new Thread(new Runnable() {
            String msg;
            @Override
            public void run() {
                try {
                    msg = in.readLine();
                    while(msg!=null){
                        System.out.println("Server : "+msg);
                        msg = in.readLine();
                    }
                    System.out.println("Server out of service");
                    out.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        receiver .start();
    }
}
