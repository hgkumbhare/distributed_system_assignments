import java.io.*;
import java.net.*;
import java.util.Date;

public class Server {
public static void main(String[] args){
try{
ServerSocket ss=new ServerSocket(6666);
Socket s=ss.accept();//establishes connection 
Date d1 = new Date();
String formattedDate = d1.toString();

DataOutputStream dout=new DataOutputStream(s.getOutputStream());

dout.writeUTF(formattedDate);
dout.flush();

dout.close();

ss.close();

}catch(Exception e){System.out.println(e);}
}
}
