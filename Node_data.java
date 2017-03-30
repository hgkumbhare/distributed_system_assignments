import java.io.*;
import java.net.*;
import java.util.*;  
public class Node_data{
	public boolean want_to_use;
	public boolean has_token;
	public int node_num;
	public int total_num;
	public int[] rn;
	
	// token structure has only ln and queue
	public int[] ln;
	public QueueArray queue;
}