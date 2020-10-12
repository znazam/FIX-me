import java.io.*;
import java.net.Socket;

public class Broker {
//will send market message buy, sell and will receive from market Executed or rejected
//    messages should contain Instrument Quantity Market Price
    public static void main(String[] args) throws IOException {

        String ip = "localhost";
        int port = 5000;
        Socket s = new Socket(ip,port);

        String str = "Message from Broker sent";
        OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
        PrintWriter out = new PrintWriter(os);
        out.println(str);//sending data to server
        out.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String ser = br.readLine();
        System.out.println("Data from server "+ser);
    }
}
