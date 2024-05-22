import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String argv[]) throws Exception
    {
        String clientMsg;
        String responseMsg;
        //user is required to input the port number that the server will run at
        System.out.println("Enter the port number for the server: ");
        int portNumber = new Scanner(System.in).nextInt();

        //create the welcoming socket at port number provided by the user
        ServerSocket welcomeSocket = new ServerSocket(portNumber);

        while(true) {//server an always on-host which waits for clients to establish a connection
            //waits a contact from a client
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Connection with client "+ connectionSocket.getRemoteSocketAddress()+" is established.");
            //The server must exchange data through I/O streams on top of the socket

            //BufferedReader used to read bytes from client attached to socket
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            //DataOutputStream used to write bytes to client attached to socket
            DataOutputStream  outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            //read the msg received from client
            do{
                clientMsg = inFromClient.readLine();
                System.out.println("Message from the client "+connectionSocket.getRemoteSocketAddress() +" is: " +clientMsg);
                //create the response msg sent to the user
                responseMsg = "-------- Echo from the server: "+clientMsg.toUpperCase() + " --------\n";

                //send the msg back to client
                outToClient.writeBytes(responseMsg);
            }while(!clientMsg.toLowerCase().equals("finish"));


        }
    }


}


