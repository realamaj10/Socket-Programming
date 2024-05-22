import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client {
    public static void main(String argv[]) throws Exception
    {

        //from the command line user should provide the hostname of the server
        System.out.print("Enter the hostname/ IP address of the server: ");
        String host = new Scanner(System.in).nextLine();
        //from the command line user should provide the port number of the server
        System.out.print("Enter the port number of the server: ");
        int portNumber = new Scanner(System.in).nextInt();
        // use the hostname and the port number to create a client socket to connect to server
        Socket clientSocket = new Socket(host, portNumber);


        //The client must exchange data through I/O streams on top of the socket
        //DataOutputStream used to write bytes to server attached to socket
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        //BufferedReader to read bytes from server attached to socket
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inFromUser;
        do{
            // get the message that will be sent from the user from command line
            System.out.print("Enter the message that want to send to server. Type Finish to close connection: ");
            inFromUser = new Scanner(System.in).nextLine();
            //send the message to server
            outToServer.writeBytes(inFromUser + '\n');
            //read the message from the server
            String response = inFromServer.readLine();
            // output the msg in command line
            System.out.println("Message received from server: \n" + response);
        }while(!inFromUser.toLowerCase().equals("finish"));


        //close the connection to server
        System.out.println("Connection with the server is closed.");
        clientSocket.close();

    }


}
