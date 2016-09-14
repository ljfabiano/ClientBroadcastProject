
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jfabiano on 8/26/2016.
 */
public class Server {

    public void setConnection()
    {
        try {
            System.out.println("Server called");
            ServerSocket serverListener = new ServerSocket(8005);
            while(true) {
                Socket clientSocket = serverListener.accept();
                //create new connection handler just accepted, and create the connection handler object, then create the thread, and then
                //pass it the thread
                ConnectionHandler myHandler = new ConnectionHandler(clientSocket);
                Thread handlingThread = new Thread(myHandler);
                handlingThread.start();
            }

        }catch(IOException e)
        {
            e.printStackTrace();
        }

    }

}
