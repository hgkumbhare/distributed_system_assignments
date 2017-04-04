
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class EchoThread extends Thread {
    protected Socket socket;
	static int readers=0,writers=0;
	static String data="7";
	
	String readOrWriteData(String requestType,String n){//not a synchronized method  
   
		System.out.println("I am inside critical section completing"+ requestType + "request");
		if(requestType.equals("read"))
		{
			System.out.println("reading data in critical section");
			try {
				Thread.sleep(5000);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			System.out.println("data" + data);
			readers--;
		}
		else
		{
			System.out.println("writing data in critical section");
			try {
				Thread.sleep(50000);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			data=n;
			writers--;
		}
		return data;
 	}  

	// method which is granting requests to enter critical sections
	synchronized String request(String requestType,String n){// synchronized method  
   
		if(requestType.equals("read"))
		{
			while(writers!=0)
			{
				System.out.println("waiting for critical section to read");
				try {
					Thread.sleep(1000);
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}
			readers++;
			readOrWriteData(requestType,n);
		}
		else 
		{
			while(writers!=0 || readers!=0)
			{
				System.out.println("waiting for critical section to write");
				try {
					Thread.sleep(1000);
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}
			writers++;
			readOrWriteData(requestType,n);
		}
		return data;
	}  
	
    public EchoThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
		try
		{
		// take type of request from user
        DataInputStream dis=new DataInputStream(socket.getInputStream());
		String requestType=dis.readUTF();
		String dataToWrite="";
		int number;
		// If request type is write then take data to write from user
		if(requestType.equals("write"))
		{
			dataToWrite=dis.readUTF();
		}
		String data1=request(requestType,dataToWrite);
		System.out.println("requestType" + requestType);
		
		// if request type is read send data back to client
		if(requestType.equals("read"))
		{
			DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(data1);
		}
		}catch(Exception e){System.out.println(e);}
    }
}