import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Broker {
//will send market message buy, sell and will receive from market Executed or rejected
//    messages should contain Instrument Quantity Market Price

    String str = "";
    String ip = "localhost";
    int port = 5000;
    BufferedReader in;
    OutputStreamWriter os;
    PrintWriter out;
    Socket s;
    public static void main(String[] args) throws IOException {
        new Broker();
    }

    public Broker() throws IOException {
       s = new Socket(ip, port);
        os = new OutputStreamWriter(s.getOutputStream());
        out = new PrintWriter(os);
        listenForInput();
    }
    public void listenForInput() throws IOException {
            Scanner scan = new Scanner(System.in);

            while (true) {
                str = scan.nextLine();
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                if (str.toLowerCase().equals("quit"))
                    break;
                out.println(str);//sending data to server
                out.flush();

//            data given by the Router
                String ser = in.readLine();
                System.out.println("Data from Router " + ser);
            }
            out.close();
            in.close();
            s.close();
    }
}
