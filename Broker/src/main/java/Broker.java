import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Broker {
//will send market message buy, sell and will receive from market Executed or rejected
//    messages should contain Instrument Quantity Market Price
    String ip = "localhost";
    int port = 5000;
    BrokerConnection bc;
    String str = "";
    public static void main(String[] args) throws IOException {
        new Broker();
    }

    public Broker() throws IOException {
       Socket s = new Socket(ip, port);
       bc = new BrokerConnection(s, this);
       bc.start();
        listenForInput();
    }
    public void listenForInput() throws IOException {
        Scanner scan = new Scanner(System.in);
        while(true) {
            str = scan.nextLine();
            if (str.toLowerCase().equals("quit"))
                break;
            bc.SendStringToRouter(str);
        }
        bc.close();
    }
}
