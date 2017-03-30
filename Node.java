import java.io.*;
import java.net.*;
import java.util.*;  

public class Node
{
	public static Node_data node_data;
	
	
	public static void main(String args[]){
		  node_data = new Node_data();
			System.out.println("hello " + args[0] +  "    " +args[1] + "   ");
			String node_str = new String(args[0]);
			//System.out.println("13");
			node_data.node_num = Integer.parseInt(args[0]);
			String total_str = new String(args[1]);
			node_data.total_num = Integer.parseInt(args[1]);
			ArrayList<ConnectionThread> threads = new ArrayList<ConnectionThread>(node_data.total_num+1);					
			for(int i=0;i<=node_data.total_num;i++){
						threads.add(new ConnectionThread(i,node_data));
						threads.get(i).start();	
					}
			node_data.rn = new int[node_data.total_num+1];
			node_data.ln = new int[node_data.total_num+1];
			node_data.want_to_use=false;
			for(int i=0;i<=node_data.total_num;i++)
			{
				node_data.rn[i]=0;
				node_data.ln[i]=0;
			}
			node_data.queue = new QueueArray();

			Scanner sc = new Scanner(System.in);
			
			// Asking the user if it wants to enter critical section
			while(true){			
				System.out.println("polling and enter 1 if you want to enter critical section");
				int input = sc.nextInt();
				// Enter 1 if you want to enter critical section
				if(input==1){
					node_data.rn[node_data.node_num]++;
					// Send request to all nodes to get token
						for(int i=1;i<=node_data.total_num;i++){
								if(i!=node_data.node_num){
									threads.get(i).request(node_data.rn[node_data.node_num]);								
								}					
						}
					}
				}				
			}
}