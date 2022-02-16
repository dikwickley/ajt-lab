package socketChat;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws  Exception{
        final ServerSocket serverSocket ;
        final Socket clientSocket ;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc=new Scanner(System.in);

        serverSocket = new ServerSocket(5000);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream());
        in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));

        //message sending thread
        Thread sender= new Thread(new Runnable() {
            String msg;
            @Override
            public void run() {
                while(true){
                    msg = sc.nextLine(); //reads data
                    out.println(msg);
                    out.flush();
                }
            }
        });
        sender.start();

        //message recieve thread
        Thread receive= new Thread(new Runnable() {
            String msg ;
            @Override
            public void run() {
                try {
                    msg = in.readLine();

                    while(msg!=null){
                        System.out.println("Client : "+msg);
                        msg = in.readLine();
                    }

                    System.out.println("Client disconnect");

                    out.close();
                    clientSocket.close();
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        receive.start();
    }
}
