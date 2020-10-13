import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Market{
//    Must have a list of instruments that can be traded
//    must try to execute orders from broker
//    if execution is successfull, it updates the internal instrument list and sends the broker an Executed message
//    If the order can’t be met, the market sends a Rejected message
//    an order can’t be executed if the instrument is not traded on the market or if the demanded quantity is not available (in case of Buy orders)

        String str = "";
        String ip = "localhost";
        int port = 5001;
        BufferedReader in;
        OutputStreamWriter os;
        PrintWriter out;
        Socket s;
        public static void main(String[] args) throws IOException {
            new Market();
        }

    public Market() throws IOException {
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