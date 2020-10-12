import java.io.*;
import java.net.Socket;

public class Market{
//    Must have a list of instruments that can be traded
//    must try to execute orders from broker
//    if execution is successfull, it updates the internal instrument list and sends the broker an Executed message
//    If the order can’t be met, the market sends a Rejected message
//    an order can’t be executed if the instrument is not traded on the market or if the demanded quantity is not available (in case of Buy orders)
//
    public static void main(String[] args) throws IOException {
        String ip = "localhost";
        int port = 5001;
        Socket s = new Socket(ip,port);

        String str = "Message from market sent";
        OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
        PrintWriter out = new PrintWriter(os);
        out.println(str);//sending data to server
        out.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String ser = br.readLine();
        System.out.println("Data from server "+ser);
    }
}