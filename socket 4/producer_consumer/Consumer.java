import java.io.*;
import java.net.*;
import java.util.*;  

public class Consumer {
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
			System.out.println("Enter 1 to consume data");
			// to get data from Server
			String data=input.nextLine();
			if(data.equals("1"))
			{
				dout.writeUTF("datafromconsumer");
				String reply = dis.readUTF();				
				if(reply.equals("accepted"))
				{
					String data2 = dis.readUTF();
					System.out.println("data consume from server and data is " + data2);
				}
				else if(reply.equals("rejected"))
				{
					System.out.println("data cannot be consumed from server so waiting ...");
					while(true)
					{					
						String reply2=dis.readUTF();
						System.out.println("Server sends - " + reply2);
						if(reply2.equals("consumedata"))
						{
							break;
						}
					}
				}
				else
				{
					System.out.println("Invalid data received " + reply);
				}
			}
		} // while ends
	}catch(Exception e){System.out.println(e);}
}
}