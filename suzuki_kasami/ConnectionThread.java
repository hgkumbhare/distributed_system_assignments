import java.io.*;
import java.net.*;
import java.util.*;

public class ConnectionThread extends Thread{
	public Node_data node_data;	
	
	public int node_connected_num;
	public ServerSocket serverSocket = null;
   public Socket socket = null;
   
   public DataOutputStream dout;
	public DataInputStream dis;
    		    
	public ConnectionThread(int i, Node_data node_data){
		// keeping token initially with node 1
		this.node_data=node_data;
		this.node_connected_num=i;
		if(node_data.node_num==1)
		{
			node_data.has_token=true;
		}
		else
		{
			node_data.has_token=false;
		}
	}
	
	public void run() {
		try
		{
			// if server
			int port_num;
			if(node_connected_num>node_data.node_num)
			{
				// assigning port number for every connection
				port_num=node_data.node_num*2000+node_connected_num;

        		try {
        			System.out.println("Port_number for connecting = "+port_num);
            	serverSocket = new ServerSocket(port_num);
            	socket = serverSocket.accept();
        		
      		
      		dout=new DataOutputStream(socket.getOutputStream());
				dis=new DataInputStream(socket.getInputStream());
			
			while(true)
			{
				String data=dis.readUTF();
				System.out.println("data received "+data);
				// data has request for token
				if(data.indexOf("+")!=-1)
				{
					// return him token
					String[] parts = data.split("\\+");
					System.out.println(parts[0] + "   " + parts[1]);
					int part1 = Integer.parseInt(parts[0]); // node_num
					int part2 = Integer.parseInt(parts[1]); // rn
					node_data.rn[part1]=Math.max(node_data.rn[part1],part2);
					if(node_data.has_token==true && node_data.rn[part1]==node_data.ln[part1]+1)
					{
						sendToken(part1);
					}
				}
				else
				{
					String data2=dis.readUTF();
					System.out.println("data2 received  "+data2);
					node_data.has_token=true;
					String arr = data;
					String[] items = arr.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

					int[] results = new int[items.length];

					for (int i = 0; i < items.length; i++) {
    				try {
        				node_data.ln[i] = Integer.parseInt(items[i]);
    				} catch (NumberFormatException nfe) {
        			//NOTE: write something here if you need to recover from formatting errors
    				};
    				// take data of queue
				}
				node_data.queue.fromString(data2);
				if(node_data.want_to_use==true)
				{
					request(node_data.rn[node_data.node_num]);
				}
				}
			}
				} catch (Exception e) {
					System.out.println("exception1 here");
            	e.printStackTrace();
      		}
			}
			else if (node_connected_num < node_data.node_num && node_connected_num!=0)
			{
				port_num=node_data.node_num+node_connected_num*2000;
				try{
					System.out.println("Port_number for connecting = "+port_num);
					socket=new Socket(InetAddress.getLocalHost(),port_num);
				
				dout=new DataOutputStream(socket.getOutputStream());
				dis=new DataInputStream(socket.getInputStream());
				
				while(true)
				{
				String data=dis.readUTF();
				System.out.println("data received " + data);
				// data has request for token
				if(data.indexOf("+")!=-1)
				{
						// return him token
					String[] parts = data.split("\\+");
					System.out.println(parts[0] + "   " + parts[1]);
					int part1 = Integer.parseInt(parts[0]); // node_num
					int part2 = Integer.parseInt(parts[1]); // rn
					node_data.rn[part1]=Math.max(node_data.rn[part1],part2);
					if(node_data.has_token==true && node_data.rn[part1]==node_data.ln[part1]+1)
					{
						sendToken(part1);
					}
				}
				else
				{
					String data2=dis.readUTF();
					System.out.println("data2 received  "+data2);
					this.node_data.has_token=true;
					String arr = data;
					String[] items = arr.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

					int[] results = new int[items.length];

					for (int i = 0; i < items.length; i++) {
    				try {
        				node_data.ln[i] = Integer.parseInt(items[i]);
    				} catch (NumberFormatException nfe) {
        			//NOTE: write something here if you need to recover from formatting errors
    				};
    				// take data of queue
				}
				node_data.queue.fromString(data2);
				if(node_data.want_to_use==true)
				{
					request(node_data.rn[node_data.node_num]);
				}
				}
			}
				}catch(Exception e){
					
					System.out.println("exception2 here");
					System.out.println(e);				
				}
			}	
    }catch(Exception e){
					System.out.println(e);				
				}
 }
    
    public void request(int num)
    {
    	if(node_data.has_token)
    	{
    			System.out.println("Executing CS for "+ node_data.node_num);
    			node_data.want_to_use=false;
    			// updating ln of token after execution
    			node_data.ln[node_data.node_num]=node_data.rn[node_data.node_num];
    			// for every process k not in the token queue Q, it appends k to Q if RN_{i}[k]=LN[k]+1. 
    			// This indicates that process k has an outstanding request
    			for (int i=1;i<=node_data.total_num;i++)
    			{
    				if(node_data.queue.checkinqueue(i)==false && node_data.rn[i]==node_data.ln[i]+1)
    				{
    					System.out.println("Adding to queue "+ i);
    					node_data.queue.enqueue(i);
    				}
    			}
    			int number;
    			if(node_data.queue.isEmpty()==false)
    			{
    				number=node_data.queue.dequeue();
    				sendToken(number);
    			}
    	}
    	else
    	{
    		try{
    			// request format is node_num+rn[node_num]
    			System.out.println("writing request for token from "+node_data.node_num);
    			dout.writeUTF(node_data.node_num+"+"+num);
    		} catch (IOException e) {
    			
					System.out.println("exception3 here");
            	e.printStackTrace();
      		}
    	}
    }
    public void sendToken(int num)
    {
    		if(num==node_connected_num)
			{
				try{
				// send token to it
				System.out.println("Sending token to "+ num);
				dout.writeUTF(Arrays.toString(node_data.ln));
				dout.writeUTF(node_data.queue.toString());
				node_data.has_token=false;
				node_data.want_to_use=true;
				} catch (IOException e) {
    			
					System.out.println("exception4 here");
            	e.printStackTrace();
      		}
			}
    }
}