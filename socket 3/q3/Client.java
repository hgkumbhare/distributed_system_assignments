
import java.io.*;
import java.net.*;

public class Client {
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
	DataInputStream dis=new DataInputStream(socket.getInputStream());
	System.out.println(dis.readUTF());

	String username=System.console().readLine();
	String password=System.console().readLine();
	
	dout.writeUTF(username);
	dout.writeUTF(password);

	if(dis.readUTF().equals("YES"))
	{
		System.out.println("Connection is successful extablished");
	}
	else
	{
		System.out.println("Connection failed");
	}
	dout.flush();
	dout.close();
	socket.close();

	}catch(Exception e){System.out.println(e);}
	}
}
