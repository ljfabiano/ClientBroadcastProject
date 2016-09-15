import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jfabiano on 9/14/2016.
 */
public class ClientServer implements Runnable{
    Socket connection;
    BufferedReader inputFromClient;
    PrintWriter outputToClient;
    int portNumber = 8006;
    public void run()
    {
        setConnection();
    }
    public void setConnection()
    {
        //this.portNumber = portNumber;
        try {
            System.out.println("Client Server called");
            ServerSocket serverListener = new ServerSocket(portNumber);

//            while(true) {
                System.out.println("Waiting for connection ...");
                Socket clientSocket = serverListener.accept();
                System.out.println("Accepted a connection from " + clientSocket.toString());
                //create new connection handler just accepted, and create the connection handler object, then create the thread, and then
                //pass it the thread
//                ConnectionHandler myHandler = new ConnectionHandler(clientSocket);
//                Thread handlingThread = new Thread(myHandler);
//                handlingThread.start();
                //this.connection = clientSocket;
                    this.connection = clientSocket;
                inputFromClient = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
                outputToClient = new PrintWriter(this.connection.getOutputStream(), true);
                connectionHandler();
//            }


//                    if (inputLine.equals("history")) {
//                        //aList.add(inputLine);
//                        //add a sys out to see what is actually happening here in the aList
//                        System.out.println("Before sending the list I have: " + aList);
//                        outputToClient.println(aList);//working on the 1st extra for the weekend assignment.
//                        //Not working quite right. The array of strings is not showing the latest addition to it in the printout.
//                    }
//                    else {
//                        System.out.println("Before adding the new piece I have: " + aList);
//                        aList.add(inputLine);
//                        System.out.println("After adding the new piece I have: " + aList);
//                        System.out.println(inputStringArray[1] + " says: " + inputLine);
//                    }

        }catch(IOException e)
        {
            e.printStackTrace();
        }

    }
    public void connectionHandler() {
        while (true) {
            String name;
            String inputLine;
            String[] inputStringArray;
            System.out.println("connection from serverclient is made");
            try {
                //BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
                // this is how we write back to the client
                //PrintWriter outputToClient = new PrintWriter(this.connection.getOutputStream(), true);

                // read from the input until the client disconnects
                //On the server side, make sure the first message that each client sends is their name using a message structure
                //like this: name=name-of-client
                name = inputFromClient.readLine();
                System.out.println(name);
                inputStringArray = name.split("=");
                if (inputStringArray[0].equals("name")) {
                    outputToClient.println("I have your name. Speak, human.");
                    inputLine = inputFromClient.readLine();
                    System.out.println("input from server client = " + inputLine);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
