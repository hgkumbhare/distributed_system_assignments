
import java.io.*;
import java.net.*;
import java.util.Date;

public class EchoThread extends Thread {
    protected Socket socket;

    public EchoThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
		try
		{
        DataInputStream dis=new DataInputStream(socket.getInputStream());
		System.out.println(dis.readUTF());
		}catch(Exception e){System.out.println(e);}
    }
}