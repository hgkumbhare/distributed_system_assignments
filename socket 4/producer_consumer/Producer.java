import java.io.*;
import java.net.*;
import java.util.*;  

public class Producer {
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
			System.out.println("Enter 1 to input data as producer");
			String data=input.nextLine();
			// send data to Server
			if(data.equals("1"))
			{
				dout.writeUTF("datafromproducer");
				String data2=input.nextLine();
				dout.writeUTF(data2);
				String reply = dis.readUTF();				
				if(reply.equals("accepted"))
				{
					System.out.println("data successfully sent and accepted by server");
				}
				else if(reply.equals("rejected"))
				{
					System.out.println("data not accepted by server so waiting ...");
					while(true)
					{					
						String reply2=dis.readUTF();
						System.out.println("Server sends - " + reply2);
						if(reply2.equals("producedata"))
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