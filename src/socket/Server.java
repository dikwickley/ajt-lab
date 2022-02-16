package socket;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args)  throws  Exception{
        ServerSocket ss = new ServerSocket(1234);
        System.out.println("Started Listening");
        Socket s = ss.accept();
        DataInputStream in = new DataInputStream(s.getInputStream());
//        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        String res = "";
        String kin = "";
        Scanner scan = new Scanner(System.in);
        while(true) {
            //recieve message
            res = (String) in.readUTF();
            if(res.equals("exit")) break;
            System.out.println("message: " + res);

            //send message
//            System.out.print("Enter message: ");
//            kin = scan.nextLine();
//            dout.writeUTF(kin);
        }
        ss.close();
    }
}
