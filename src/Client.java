import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by jfabiano on 8/26/2016.
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("This is the client");

        try {

            //Scanner for the console
            Scanner consoleInput = new Scanner(System.in);

            // connect to the server on the target port
            Socket clientSocket = new Socket("localhost", 8005);
            //Change this string to point to the appropriate ip address, or localhost for myself, 127.0.0.1 = me, 10.0.0.139 = Ben

            // once we connect to the server, we also have an input and output stream
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //the input is the socket/inputstream, the input streamReader changes the stream into characters, and the Bufferedreader
            //turns the characters into lines to use the readline() function

            System.out.println("Please enter your name: ");
            String name = consoleInput.nextLine();
            String input = "";

            out.println("name=" + name);

            String serverResponse = in.readLine();
            System.out.println(serverResponse);
            if(serverResponse.equals("I have your name. Speak, human.")) {

                while(true)
                {
                    //if (in.ready() == false) {
//                    if (input.equals("history")) {
//                        while(!in.ready()) {
//                            if(in.ready())
//                            {
//                                System.out.println("History of messages you have sent:\n" + in.readLine());
//                                break;
//                            }
//                        }
//                        //System.out.println("History of messages you have sent:\n" + in.readLine());
//                    }

                        System.out.println("Go ahead and type messages(type \"exit\" to exit or \"history\" to get all the messages you have sent).");
                        input = consoleInput.nextLine();
                        if (input.equals("exit")) {
                            break;
                        }
                        if (input.equals("history")) {
                            out.println(input);
                            //in.skip();
                            System.out.println("History of messages you have sent: " + in.readLine());
                        }else {
                            out.println(input);
                        }
                    //}else if (in.ready() == true)
                    {
                        //System.out.println("History of messages you have sent:\n" + in.readLine());
                    }
//                    if (in.ready() == true)
//                    {
//                        System.out.println("History:\n" + in.readLine());
//                    }
                }
                //if the server/client is waiting for a input from the other the stream is blocked. always have a closed loop for the back and
                //forth between the server and the client.
            }
            else if(serverResponse == "Your initial message did not begin with \"name=\". I don't know who you are, sorry.")
            {
                System.out.println("Sorry the connection failed.");
            }
            else
            {
                while(true)
                {
                    input = consoleInput.nextLine();
                    if (input.equals("exit"))
                    {
                        break;
                    }
                    out.println(input);
                }
            }
            //use a .split on the input stream. connect the ip address/socket to the name sent?
            // close the connection
            System.out.println("Thanks for sending messages! Goodbye!");
            clientSocket.close();
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
