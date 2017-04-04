
import java.io.*;
import java.net.*;
import java.util.Date;

public class EchoThread extends Thread {
    protected Socket socket;
	static int lock=0;
	
	 void printTable(int n){//synchronized method  
   for(int i=1;i<=5;i++){  
     System.out.println(n*i);  
     try{  
      Thread.sleep(4000);  
     }catch(Exception e){System.out.println(e);}  
   }  
		lock--;
 }  

	 synchronized void print(int n){//synchronized method  
   System.out.println("Printing table for " + n);
		while(lock!=0)
		{
			try{  
			  Thread.sleep(4000);  
			 }catch(Exception e){System.out.println(e);}  
				}
		lock++;
		printTable(n);
	}  
	
    public EchoThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
		try
		{
        DataInputStream dis=new DataInputStream(socket.getInputStream());
		String str=dis.readUTF();
		System.out.println(str);
		int result = Integer.parseInt(str);
		print(result);
		}catch(Exception e){System.out.println(e);}
    }
}