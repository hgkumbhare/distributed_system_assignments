import java.net.*;
import java.io.*;
 
class Client
{
	public final static int FILE_SIZE = 6022386;
    public final static String FILE_TO_SEND = "/home/harshada/Desktop/raksh.txt"; 
	public final static String FILE_TO_RECEIVED = "/home/harshada/Desktop/recieved.txt"; 
	public static void main(String args[]) throws IOException
	{
		Socket socket=null;
		BufferedReader b=null;
		FileOutputStream fos = null;
    BufferedOutputStream bos = null;
		 int bytesRead;
    int current = 0;
		
		FileInputStream fis = null;
    	BufferedInputStream bis = null;
		OutputStream os = null;
 
		try
		{
			socket=new Socket(InetAddress.getLocalHost(),6666);
			b=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
 
		catch(UnknownHostException u)
		{
			System.err.println("I don't know host");
			System.exit(0);
		}
		try {

          File myFile = new File(FILE_TO_SEND);
          byte [] mybytearray  = new byte [(int)myFile.length()];
          fis = new FileInputStream(myFile);
          bis = new BufferedInputStream(fis);
          bis.read(mybytearray,0,mybytearray.length);
          os = socket.getOutputStream();
          System.out.println("Sending " + FILE_TO_SEND + "(" + mybytearray.length + " bytes)");
          os.write(mybytearray,0,mybytearray.length);
          os.flush();
          System.out.println("Done.");
        }
		finally{}
		
		
		// receive file
      byte [] mybytearray  = new byte [FILE_SIZE];
      InputStream is = socket.getInputStream();
      fos = new FileOutputStream(FILE_TO_RECEIVED);
      bos = new BufferedOutputStream(fos);
      bytesRead = is.read(mybytearray,0,mybytearray.length);
      current = bytesRead;

      do {
         bytesRead =
            is.read(mybytearray, current, (mybytearray.length-current));
         if(bytesRead >= 0) current += bytesRead;
      } while(bytesRead > -1);

      bos.write(mybytearray, 0 , current);
      bos.flush();
      System.out.println("File " + FILE_TO_RECEIVED
          + " downloaded (" + current + " bytes read)");
		
		
		
		b.close();
		socket.close();
		
          if (bis != null) bis.close();
          if (os != null) os.close();
          if (socket!=null) socket.close();
       
	}
}
