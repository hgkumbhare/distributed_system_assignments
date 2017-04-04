import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) {
		try{	
			Socket s=new Socket("localhost",6666);

			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
			DataInputStream dis=new DataInputStream(s.getInputStream());


			while(true)
			{
				System.out.println("Do you want to continue operations Y/N");
				String flag=System.console().readLine();
				if(flag.equals("N"))
				{
					dout.writeUTF("return");
					break;
				}
				
				// taking inputs from user
				System.out.println("Enter your first number");
				String num1 = System.console().readLine();
				System.out.println("Your first number is: " + num1);
				
				System.out.println("Enter operator");
				String op = System.console().readLine();
				System.out.println("Your op is: " + op);
				
				System.out.println("Enter your second number");
				String num2 = System.console().readLine();
				System.out.println("Your second number is: " + num2);
				
				// sending to server
				dout.writeUTF(num1);
				dout.writeUTF(op);
				dout.writeUTF(num2);
				
				// receiving data from server
				String	str=(String)dis.readUTF();
				System.out.println("message from server stating result of calculations = "+str);
			}
			s.close();
		}catch(Exception e){System.out.println(e);}
	}
}
