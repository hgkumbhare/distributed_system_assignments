
import java.io.*;
import java.net.*;

public class Client {
public static void main(String[] args) {
try{	
Socket s=new Socket("localhost",6668);
	
DataOutputStream dout=new DataOutputStream(s.getOutputStream());

dout.writeUTF("Hello Server");

DataInputStream dis=new DataInputStream(s.getInputStream());
String	str=(String)dis.readUTF();
System.out.println("encrpted message received from server= "+str);


dout.flush();

dout.close();

s.close();

}catch(Exception e){System.out.println(e);}
}
}
