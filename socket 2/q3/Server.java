import java.io.*;
import java.net.*;
import java.util.Date;

public class Server {
public static void main(String[] args){
try{
		ServerSocket socket=null;
		try
		{
			socket=new ServerSocket(6667);
		}
		catch(IOException e)
		{
			System.err.println("Port 98 could not be found");
			System.exit(1);
		}
		Socket c=null;
		try
		{
			c=socket.accept();
			System.out.println("Connection from "+c);
		}
		catch(IOException e)
		{
			System.out.println("Accept failed");
			System.exit(1);
		}
	
	DataOutputStream dout=new DataOutputStream(c.getOutputStream());
	dout.writeUTF("Enter Username and password in two seperate lines");

	DataInputStream dis=new DataInputStream(c.getInputStream());
	String	username=(String)dis.readUTF();
	String	password=(String)dis.readUTF();
	if(username.equals("username") && password.equals("password"))
	{
		System.out.println("you entered correct username and password");
		dout.writeUTF("YES");
	}
	else
	{
		System.out.println("you entered wrong username and password");
		dout.writeUTF("NO");
	}

	dout.flush();
	dout.close();
	socket.close();
	c.close();

	}catch(Exception e){System.out.println(e);}
	}
}
