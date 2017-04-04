import java.io.*;
import java.net.*;
import java.util.*;  

public class Philosopher {
	public static DataOutputStream dout;
	public static DataInputStream dis;
	public static Socket socket=null;
	public static void main(String[] args) {
	try{	
		try
		{
			socket=new Socket(InetAddress.getLocalHost(),4000);
		}
		catch(UnknownHostException u)
		{
			System.err.println("I don't know host");
			System.exit(0);
		}
	
		dout=new DataOutputStream(socket.getOutputStream());
		dis=new DataInputStream(socket.getInputStream());
	
		Scanner input = new Scanner(System.in);
		while(true)
		{
			System.out.println("Enter 1 to eat");
			String data=input.nextLine();
			if(data.equals("1"))
			{
				dout.writeUTF("eat");
				System.out.println("Thinking");
				String reply=dis.readUTF();
				if(reply.equals("accepted"))
				{
					System.out.println("Eating");
					Thread.sleep(10000);
					dout.writeUTF("done");
				}
				else if(reply.equals("rejected"))
				{
					System.out.println("Couldn't eat try again");
				}
			}
		} // while ends
	}catch(Exception e){System.out.println(e);}
}
}