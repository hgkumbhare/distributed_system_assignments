
import java.io.*;
import java.net.*;

public class Client {
public static void main(String[] args) {
try{	
Socket s=new Socket("localhost",6666);
DataInputStream dis=new DataInputStream(s.getInputStream());
String	str=(String)dis.readUTF();
System.out.println("message from server stating time on server side = "+str);
s.close();

}catch(Exception e){System.out.println(e);}
}
}
