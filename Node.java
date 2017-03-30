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
			for(int i=0;i<=node_data.total_num;i++)
			{
				node_data.rn[i]=0;
				node_data.ln[i]=0;
			}

			Scanner sc = new Scanner(System.in);
			
			while(true){			
				System.out.println("polling");
				int input = sc.nextInt();
				if(input==1){
					System.out.println("input ok");
					node_data.rn[node_data.node_num]++;
						for(int i=1;i<=node_data.total_num;i++){
								if(i!=node_data.node_num){
									threads.get(i).request(node_data.rn[node_data.node_num]);								
								}					
						}
					}
				}				
			}
}