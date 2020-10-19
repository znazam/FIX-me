package fix;//package java;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Scanner;

public class Router {
//    TODO
// must accept incoming connections from multiple brokers and markets
//When a Broker or Market establishes the connection the fix.Router asigns it a unique 6 digit ID and communicates the ID to them both
//     the fix.Router will use the ID to create the routing table
//Validate the message based on the checkshum.
// Identify the destination in the routing table.
// Forward the message.
// ------------------------------------------------------------------

//    PROGRESS
//    connects the broker and market and receives messages and sends them back
//    Done!!
    Socket s;
    ArrayList<ServerConnection> MarketList = new ArrayList<>();
    ArrayList<ServerConnection> BrokerList = new ArrayList<>();
    int brokerid;
    int marketid;
    int i;
    HashMap<Integer, Integer> pair = new HashMap<Integer, Integer>();
    public static void main(String[] args) {
        new Router();
    }
    public Router(){
        while(true) {
            System.out.println("Server waiting for request\ntype quit to close fix.Router");

            try (ServerSocket broker = new ServerSocket(5000)) {
                s = broker.accept();
                System.out.println("Broker accepted");
                ServerConnection sc = new ServerConnection(s,this, "Broker");//makes using multiple brokers and markets threads at the same time//                sc.setName(i);
                sc.start();//starting thread
                BrokerList.add(sc);
                brokerid = 100000 + (int)(Math.random() * ((499999 - 100000) + 1));
            } catch (IOException e) {
                System.out.println("Broker connection to Router error: " + e.getMessage());
            }

            try (ServerSocket market = new ServerSocket(5001)) {
                s = market.accept();
                System.out.println("Market Connected");
                ServerConnection sc = new ServerConnection(s,this, "Broker");
                sc.start();
                MarketList.add(sc);
                marketid = 500000 + (int)(Math.random() * ((999999 - 500000) + 1));
            } catch (IOException e) {
                System.out.println("Broker connection to Router error: " + e.getMessage());
            }
//            if (q.toLowerCase().equals("quit"))
//                break;
        }

//        broker.close();
//        Market.close();
//        out.close();
//        in.close();
//        s.close();

    }
}