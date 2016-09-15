import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by jfabiano on 9/14/2016.
 */
public class ServerClient {//implements Runnable{
    Socket clientSocket;
    PrintWriter out;
    BufferedReader in;

//    public void run()
//    {
//        StartServerClient();
//    }
    public void StartServerClient() {
        System.out.println("This is the server client");
        try {
            //Scanner for the console
            Scanner consoleInput = new Scanner(System.in);
            // connect to the server on the target port
//            Thread.sleep(5000);
            clientSocket = new Socket("localhost", 8006);
            System.out.println("connection over 8006 available on the serverclient");
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //Change this string to point to the appropriate ip address, or localhost for myself, 127.0.0.1 = me, 10.0.0.139 = Ben
            // once we connect to the server, we also have an input and output stream
            //PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            //BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    System.out.println("The server client is ready to send messages");

            //System.out.println("Thanks for sending messages! Goodbye!");
            //clientSocket.close();
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    public void sendMessage(String message)
    {
        System.out.println("message to be sent = " + message);
            out.println(message);
            //out.println("name=" + name);

    }
    public void closeConnection()
    {
        try {
            clientSocket.close();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
