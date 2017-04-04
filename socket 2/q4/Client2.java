
import java.io.*;
import java.net.*;

public class Client2 {
public static void main(String[] args) {
try{	
	
	Socket socket=null;
		try
		{
			socket=new Socket(InetAddress.getLocalHost(),6667);
		}
 
		catch(UnknownHostException u)
		{
			System.err.println("I don't know host");
			System.exit(0);
		}
	

	
	DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
	//DataInputStream dis=new DataInputStream(socket.getInputStream());
	System.out.println("Sent");
	
	dout.writeUTF("hello2");

	dout.flush();
	dout.close();
	socket.close();

	}catch(Exception e){System.out.println(e);}
	}
}
