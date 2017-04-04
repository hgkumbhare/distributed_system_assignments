
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ServerThread extends Thread {
    public Socket socket;
    public DataInputStream dis;
    public DataOutputStream dos;
    public QueueArray queuearray;
    public String type;
	
    public ServerThread(Socket clientSocket,QueueArray queuearray) {
        this.socket = clientSocket;
        this.queuearray=queuearray;
        type="nothing";
        try{
        dis=new DataInputStream(socket.getInputStream());
        dos=new DataOutputStream(socket.getOutputStream());
        }catch(Exception e){System.out.println(e);}
        System.out.println("thread successfully created");
    }
    
    public void produceData()
    {
    	System.out.println("produceData called");
    	try{
    	String data = dis.readUTF();
    	System.out.println("Data received from producer " + data);
    	if(queuearray.isFull())
    	{
    		System.out.println("Data received from producer is rejected " + data);
    		dos.writeUTF("rejected");
    		queuearray.produceriswaiting=true;
    	}
    	else
    	{
    		System.out.println("Data received from producer is accepted " + data);
    		queuearray.enqueue(Integer.valueOf(data));
    		dos.writeUTF("accepted");
    	}
    	}catch(Exception e){System.out.println(e);}
    }
    
    public void consumeData()
    {
    	System.out.println("consumeData called");
    	try{
    	if(queuearray.isEmpty())
    	{
    		System.out.println("data cannot be given to consumer no data");
    		dos.writeUTF("rejected");
    		queuearray.consumeriswaiting=true;
    	}
    	else
    	{
    		System.out.println("data given to consumer");
    		dos.writeUTF("accepted");
    		int data = queuearray.dequeue();
    		dos.writeUTF(Integer.toString(data));
    	}
    	}catch(Exception e){System.out.println(e);}
    }

    public void run() {
		System.out.println("inside run");
		while(true)
		{
			try
			{
				String in=null;
							if(queuearray.consumeriswaiting && queuearray.isEmpty()==false && type.equals("consumer"))
							{
								dos.writeUTF("consumedata");
								queuearray.consumeriswaiting=false;
							}
							if(queuearray.produceriswaiting && queuearray.isFull()==false && type.equals("producer"))
							{
								dos.writeUTF("producedata");
								queuearray.produceriswaiting=false;
							}
							if(dis.available()!=0)
							{
									in=dis.readUTF();
									System.out.println("received from client " + in);
									if(in.equals("datafromproducer"))
									{
									type="producer";
									produceData();
									}
									else if(in.equals("datafromconsumer"))
									{
									type="consumer";
									consumeData();
								}
							}
					this.sleep(1000);
					//System.out.println("inside type "+type);
			}catch(Exception e){System.out.println("My exception here" + e);}
		}
    }
}