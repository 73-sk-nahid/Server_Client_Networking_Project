import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.spec.ECField;
import java.sql.Struct;
import java.util.Scanner;

public class client {
    public static void main(String args[]) throws IOException{
         try{
             Socket clientsocket = new Socket ("localhost", 5000);
             System.out.println("Connected at server Handshaking port " + clientsocket.getPort());

             System.out.println("Client is connecting at Communication Port " + clientsocket.getLocalPort());

             System.out.println("Client is Connected");
             Scanner scn = new Scanner(System.in);
             DataOutputStream dos = new DataOutputStream(clientsocket.getOutputStream());

             DataInputStream dis = new DataInputStream(clientsocket.getInputStream());
             while(true){
                // String inLine = dis.readUTF();
                // System.out.println(inLine);
                 System.out.println("Enter 1st number(0 for exit): ");
                 int outLine = scn.nextInt();
                 System.out.println("Enter 2nd number(0 for exit): ");
                 int outLine2 = scn.nextInt();
                 dos.writeInt(outLine);
                 dos.writeInt(outLine2);

                 String inLine = dis.readUTF();
                 System.out.println(inLine);
                 String outLine3 = scn.nextLine();
                 dos.writeUTF(outLine3);

                 if(outLine == 0){
                     System.out.println("Closing the connecting "+ clientsocket);
                     clientsocket.close();
                     System.out.println("Connection Closed");
                     break;
                     }
                // String received = dis.readUTF();
                // System.out.println(received);
                 int result = dis.readInt();
                 System.out.println("Result is:  " +result);
                 }
             dos.close();
             dis.close();
             clientsocket.close();
             }
         catch (Exception ex){
             System.out.println(ex);
             }
         }
}
