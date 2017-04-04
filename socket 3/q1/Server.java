
/*
	How to run a java program
	javac filename.java
	java filename
*/

import java.io.*;
import java.net.*;
import java.util.Date;

public class Server {
	public static void main(String[] args){
		try{
			ServerSocket ss=new ServerSocket(6666);
			Socket s=ss.accept();//establishes connection 

			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
			DataInputStream dis=new DataInputStream(s.getInputStream());

			while(true)
			{
				String	num1=(String)dis.readUTF();
				if(num1.equals("return"))
				{
					break;
				}
				String	op=(String)dis.readUTF();
				String	num2=(String)dis.readUTF();
				if(op.equals("+"))
				{
					dout.writeUTF(String.valueOf(Integer.parseInt(num1)+Integer.parseInt(num2)));
				}		
				else if(op.equals("-"))
				{
					dout.writeUTF(String.valueOf(Integer.parseInt(num1)-Integer.parseInt(num2)));
				}	
				else if(op.equals("*"))
				{
					dout.writeUTF(String.valueOf(Integer.parseInt(num1)*Integer.parseInt(num2)));
				}	
				else
				{
					dout.writeUTF(String.valueOf(Integer.parseInt(num1)/Integer.parseInt(num2)));
				}	
				dout.flush();

			}
			dout.close();
		    ss.close();
		}catch(Exception e){System.out.println(e);}
	}
}
