//fuser -n tcp -k 6667
//sudo kill -9 PID
import java.io.*;
import java.net.*;
import java.util.Date;

public class Server {

    static final int PORT = 4000;
    public static QueueArray queuearray=null;

    public static void main(String args[]) {
    	queuearray = new QueueArray();
    	//System.out.println(queuearray);
        
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();

        }
        int i=0;
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new threa for a client
            new ServerThread(socket,queuearray,i).start();
            i++;
        }
    }
}