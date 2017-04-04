
import java.io.*;
import java.net.*;
import java.util.Scanner;  

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
	DataInputStream dis=new DataInputStream(socket.getInputStream());
	
	
	Scanner input = new Scanner(System.in);
	String data=input.nextLine();
	dout.writeUTF(data);
	if(data.equals("write"))
	{
		String data2=input.nextLine();
		dout.writeUTF(data2);
	}
	else
	{
		String data2=dis.readUTF();
		System.out.print("data read is "+data2);
	}
	System.out.print("done");

	dout.flush();
	dout.close();
	socket.close();

	}catch(Exception e){System.out.println(e);}
	}
}
