
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ServerThread extends Thread {
    public Socket socket;
    public DataInputStream dis;
    public DataOutputStream dout;
    public QueueArray queuearray;
    public int my_num;
	
    public ServerThread(Socket clientSocket,QueueArray queuearray,int num) {
        this.socket = clientSocket;
        this.queuearray=queuearray;
        my_num=num;
        try{
        dis=new DataInputStream(socket.getInputStream());
        dout=new DataOutputStream(socket.getOutputStream());
        }catch(Exception e){System.out.println(e);}
        System.out.println("thread successfully created for "+num);
    }
    
    // 1 to get value and 2 to set value
    public synchronized boolean semaphore(int type,int person,boolean bool)
    {
    	if(type==1)
    	{
    		return queuearray.eating[person];
    	}
    	else
    	{
    		queuearray.eating[person]=bool;
    		return queuearray.eating[person];
    	}
    }

    public void run() {
		System.out.println("inside run");
		while(true)
		{
			try
			{
				String data=dis.readUTF();
				if(data.equals("eat"))
				{
					if(semaphore(1,(my_num+5-1)%5,true)==false && semaphore(1,(my_num+1)%5,true)==false)
					{
						semaphore(2,my_num,true);
						dout.writeUTF("accepted");
					}
					else
					{
						dout.writeUTF("rejected");
					}
				}
				else if(data.equals("done"))
				{
					semaphore(2,my_num,false);
				}
			}catch(Exception e){System.out.println("My exception here" + e);}
		}
    }
}