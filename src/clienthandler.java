import java.io.*;
 import java.net.*;
 import java.text.DateFormat;
 import java.text.SimpleDateFormat;
 import java.util.*;
 import java.util.logging.Level;
 import java.util.logging.Logger;

         class clienthandler extends Thread{
 //DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
 //DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
 final Socket com_tunnel;

         final DataInputStream dis_tunnel;
 final DataOutputStream dos_tunnel;
 String received = "";
 String toreturn = "";



 public clienthandler(Socket s, DataInputStream dis, DataOutputStream dos)
 {
         this.com_tunnel = s;
         this.dis_tunnel = dis;
         this.dos_tunnel = dos;

         }
 public void run(){
         while(true){
             try {
                 //dos_tunnel.writeUTF("What do you want [Date/Time]");
                 int a = dis_tunnel.readInt();
                 int b = dis_tunnel.readInt();
                 dos_tunnel.writeUTF("Addition \nMultiplication");
                 received = dis_tunnel.readUTF();
                // int sum = a+b;
                 if(received.equals("Exit")){
                     System.out.println("Client " + this.com_tunnel + " sends exits");
                     System.out.println("Closing the connection");
                     this.com_tunnel.close();
                     break;
                     }

               //  Date date = new Date();
                 switch (received) {

                     case "Addition" :
                         //toreturn = fordate.format(date);
                         //dos_tunnel.writeUTF(toreturn);
                        //int a = dis_tunnel.readInt();

                         //int b= dis_tunnel.readInt();
                         int sum = a+b;
                         dos_tunnel.writeInt(sum);

                         break;

                     case "Multiplication" :
                         //toreturn = fortime.format(date);
                         //dos_tunnel.writeUTF(toreturn);
                         int mul = a*b;
                         dos_tunnel.writeInt(mul);
                         break;

                     default:
                         dos_tunnel.writeUTF("Invalid input");
                         break;
                     }

                 } catch (IOException ex) {
                 Logger.getLogger(clienthandler.class.getName()).log(Level.SEVERE, null, ex);

                 }
             }
         try {
             this.dos_tunnel.close();
             this.dis_tunnel.close();
             } catch (IOException ex) {
             Logger.getLogger(clienthandler.class.getName()).log(Level.SEVERE, null, ex);

             }
         }
 }