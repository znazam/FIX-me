import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Router {
//    TODO
// must accept incoming connections from multiple brokers and markets
//When a Broker or Market establishes the connection the Router asigns it a unique 6 digit ID and communicates the ID to them both
//     the Router will use the ID to create the routing table
//Validate the message based on the checkshum.
// Identify the destination in the routing table.
// Forward the message.
// ------------------------------------------------------------------

//    PROGRESS
//    connects the broker and receives messages and sends them back
    public static void main(String[] args) throws IOException {
        System.out.println("Server started");
        ServerSocket broker = new ServerSocket(5000);
        System.out.println("Server waiting for request");
        Socket s = broker.accept();
        System.out.println("Broker Connected");


        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str = br.readLine();//Reading data from client

        System.out.println("Client data: "+str);

        String str2 = str.substring(0,3);

        OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
        PrintWriter out = new PrintWriter(os);
        out.println(str2);
        out.flush();//Sending data to client



        ServerSocket market = new ServerSocket(5001);
        System.out.println("Server waiting for request");
        s = market.accept();
        System.out.println("Market Connected");


        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        str = br.readLine();//Reading data from client

        System.out.println("Client data: "+str);

        str2 = str.substring(0,3);

        os = new OutputStreamWriter(s.getOutputStream());
        out = new PrintWriter(os);
        out.println(str2);
        out.flush();//Sending data to client
    }
}
