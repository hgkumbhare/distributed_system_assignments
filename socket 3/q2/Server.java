import java.net.*;
import java.io.*;
 
class Server
{
	public final static int FILE_SIZE = 6022386;
	public final static String FILE_TO_RECEIVED = "/home/harshada/Desktop/raksh1.txt"; 
	public static void main(String args[]) throws IOException
	{
	    int bytesRead;
        int current = 0;
		FileOutputStream fos = null;
        BufferedOutputStream bos = null;
		FileInputStream fis = null;
    	BufferedInputStream bis = null;
		ServerSocket socket=null;
		OutputStream os = null;
		try
		{
			socket=new ServerSocket(6666);
		}
		catch(IOException e)
		{
			System.err.println("Port 98 could not be found");
			System.exit(1);
		}
		Socket c=null;
		try
		{
			c=socket.accept();
			System.out.println("Connection from "+c);
		}
		catch(IOException e)
		{
			System.out.println("Accept failed");
			System.exit(1);
		}
		// receive file
      byte [] mybytearray  = new byte [FILE_SIZE];
      InputStream is = c.getInputStream();
      fos = new FileOutputStream(FILE_TO_RECEIVED);
      bos = new BufferedOutputStream(fos);
      bytesRead = is.read(mybytearray,0,mybytearray.length);
      current = bytesRead;
		System.out.println("aaa" + bytesRead);
      /*while(bytesRead > -1)
	  {
		  //if(bytesRead >= 0) current += bytesRead;
         //bytesRead =is.read(mybytearray, current, (mybytearray.length-current));
		   bytesRead = is.read(mybytearray,0,mybytearray.length);
		  fos.write(mybytearray,0,bytesRead);
		  System.out.println("here"+bytesRead);
      }*/ 
		fos.write(mybytearray,0,bytesRead);
		System.out.println("hello from server"+current);
      //bos.write(mybytearray, 0 , current);
      //bos.flush();
      System.out.println("File " + FILE_TO_RECEIVED
          + " downloaded (" + current + " bytes read)");
		
		// read text in file
		
		 FileReader fileReader = new FileReader(FILE_TO_RECEIVED);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

			String filenametosend="",line;
            while((line = bufferedReader.readLine()) != null) {
				filenametosend=line;
                System.out.println(line);
            }   
		System.out.println("FIle name to be sent " + filenametosend);
		
		
		
		try
		{
		 File myFile = new File("/home/harshada/Desktop/"+filenametosend);
			System.out.println("/home/harshada/Desktop/"+filenametosend);
          mybytearray  = new byte [(int)myFile.length()];
          fis = new FileInputStream(myFile);
          bis = new BufferedInputStream(fis);
          bis.read(mybytearray,0,mybytearray.length);
          os = c.getOutputStream();
          System.out.println("Sending " + filenametosend + "(" + mybytearray.length + " bytes)");
          os.write(mybytearray,0,mybytearray.length);
          os.flush();
          System.out.println("Done.");
        }
        finally {
          if (bis != null) bis.close();
          if (os != null) os.close();
          if (socket!=null) socket.close();
        }
	
	
		
	
		c.close();
		socket.close();
	}
}
