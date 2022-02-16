package socket;
import java.io.*;
import java.util.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket cs = new Socket("localhost", 1234);
        DataOutputStream out = new DataOutputStream(cs.getOutputStream());
        DataInputStream din = new DataInputStream(cs.getInputStream());



        Thread sender  =  new Thread(new Runnable() {
            @Override
            public void run()  {
                String kin = "";
                Scanner scan = new Scanner(System.in);
                try {
                    while (true) {
                        System.out.print("Enter message: ");
                        kin = scan.nextLine();
                        if (kin.equals("exit")) break;
                        out.writeUTF(kin);
                    }
                } catch (IOException e){
                    System.out.print("error");
                }
            }
        });
        sender.start();

//        while(true){
//            //send message
//            System.out.print("Enter message: ");
//            kin = scan.nextLine();
//            if(kin.equals("exit")) break;
//            out.writeUTF(kin);
//
//            //recieve message
//            System.out.println("message: " + (String)din.readUTF());
//        }


        out.flush();
        out.close();

    }
}