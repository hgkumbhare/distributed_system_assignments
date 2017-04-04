import java.net.*;
import java.io.*;
 
class Server
{
	public static void main(String args[]) throws IOException
	{
		ServerSocket n1=null;
		try
		{
			n1=new ServerSocket(6666);
		}
		catch(IOException e)
		{
			System.err.println("Port 98 could not be found");
			System.exit(1);
		}
		Socket c=null;
		try
		{
			c=n1.accept();
			System.out.println("Connection from "+c);
		}
		catch(IOException e)
		{
			System.out.println("Accept failed");
			System.exit(1);
		}
		PrintWriter out=new PrintWriter(c.getOutputStream(),true);
		BufferedReader in=new BufferedReader(new InputStreamReader(c.getInputStream()));
		String n;
		BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Ready to type now");
		while((n=sin.readLine())!=null)
		{
			out.println(n);
		}
		out.close();
		c.close();
		n1.close();
	}
}
