package fix;

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
        boolean idAssigned = false;
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
            String mID = "";
            String bID = "";
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            idAssigned = false;
            while (true) {
            if (!idAssigned)
            {
//              id assined by route
                bID = in.readLine();
                mID = in.readLine();
                System.out.println(mID);
                System.out.println(bID);
                idAssigned = true;
            }
//            out.println("Market sent first");
//            out.flush();
            String id = null;
//            while(id == null){
                id = in.readLine();
            if(id.equals("quit")){
                out.println(id);//sending data to server
                out.flush();
                in.close();
                os.close();
                out.close();
                s.close();
                System.exit(1);
            }

            int begin = id.indexOf("49=")+3;
            int end = begin + 6;
            String tid = id.substring(begin, end);
            int bid = Integer.parseInt(tid);
            begin = id.indexOf("56=")+3;
            end = begin+6;
            tid = id.substring(begin, end);
            int mid = Integer.parseInt(tid);
            System.out.println(mid+"   "+mID);
            if (mid == Integer.parseInt(mID)) {
//                while (true) {
//                    str = scan.nextLine();
                    in = new BufferedReader(new InputStreamReader(s.getInputStream()));
//                    if (str.toLowerCase().equals("quit"))
//                        break;
                    out.println(id);//sending data to server
                    out.flush();

//            data given by the Router
//                    String ser = in.readLine();
                    System.out.println("Data from Router " + id);
                    System.out.println(" Market ID in Market is: "+mid);
                }
            }
//            out.close();
//            in.close();
//            s.close();
        }
}