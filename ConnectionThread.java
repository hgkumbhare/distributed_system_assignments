import java.io.*;
import java.net.*;
import java.util.Scanner;  

public class ConnectionThread extends Thread{
	public Node_data node_data;	
	
	public int node_connected_num;
	public ServerSocket serverSocket = null;
   public Socket socket = null;
   
   public DataOutputStream dout;
	public DataInputStream dis;
    		    
	public ConnectionThread(int i, Node_data node_data){
		this.node_data=node_data;
		node_connected_num=i;
	}
	
	public void run() {
		try
		{
			// if server
			int port_num;
			if(node_connected_num>node_data.node_num)
			{
				port_num=node_data.node_num*2000+node_connected_num;

        		try {
            	serverSocket = new ServerSocket(port_num);
            	socket = serverSocket.accept();
        		
      		
      		dout=new DataOutputStream(socket.getOutputStream());
				dis=new DataInputStream(socket.getInputStream());
			
				String data=dis.readUTF();
				System.out.println(data);
				if(data.equals("token"))
				{
					this.node_data.has_token=true;
				}
				} catch (Exception e) {
					System.out.println("exception here");
            	e.printStackTrace();
      		}
			}
			else if (node_connected_num < node_data.node_num && node_connected_num!=0)
			{
				port_num=node_data.node_num+node_connected_num*2000;
				try{
					socket=new Socket(InetAddress.getLocalHost(),port_num);
				
				dout=new DataOutputStream(socket.getOutputStream());
				dis=new DataInputStream(socket.getInputStream());
				
				String data=dis.readUTF();
				System.out.println(data);
				if(data.equals("token"))
				{
					this.node_data.has_token=true;
				}			
				
				}catch(Exception e){
					
					System.out.println("exception here 3");
					System.out.println(e);				
				}
			}	
    }catch(Exception e){
					System.out.println(e);				
				}
 }
    
    public void request()
    {
    	if(node_data.has_token)
    	{
    			System.out.println("CS for "+ node_data.node_num);
    	}
    	else
    	{
    		try{
    			dout.writeUTF("request");
    		} catch (IOException e) {
    			
					System.out.println("exception here 2");
            	e.printStackTrace();
      		}
    	}
    }
}