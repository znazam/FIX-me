package fix;

import java.io.*;
import java.net.Socket;

public class ServerConnection extends Thread{
    Socket socket;
    Router router;
    BufferedReader in;
    OutputStreamWriter os;
    PrintWriter out;
    String str;
    String tid;
    int mid;
    int bid;
    String run;
    boolean idSent = false;
    boolean introductions = false;
    public ServerConnection(Socket socket, Router router, String run){
        super("ServerConnectionThread");
        this.socket = socket;
        this.router = router;
        this.run = run;
    }

    public void SendID(int brID, int mrID){
        System.out.println(brID);
        out.println(brID);
        out.flush();
        System.out.println(mrID);
        out.println(mrID);
        out.flush();
    }
    public void SendString(String reply){
        System.out.println(reply);
        out.println(reply);
        out.flush();
    }

    public void SendStringToAll() throws IOException {
            for(int i = 0; i < router.BrokerList.size(); i++)
            {//issue is here at the moment
            if (router.pair.containsValue(i)) {
                System.out.println("1");
                ServerConnection mr = router.MarketList.get(i);
                System.out.println("2");
                ServerConnection br = router.BrokerList.get(i);
                System.out.println("3");
                String msg;
                if (!idSent){
                    System.out.println("4");
                    br.SendID(router.brokerid, router.marketid);
                    System.out.println("5");
                    mr.SendID(router.brokerid, router.marketid);
                    System.out.println("6");
                    System.out.println("7");
                    msg = in.readLine();
                    System.out.println("8");
                    mr.SendString(msg);//loops through array of connections and sends the string to them all
                    System.out.println("9");
                    msg = in.readLine();
                    System.out.println("10");
                    br.SendString(msg);
                    System.out.println("11");
                    idSent = true;
                }
                else{
                    System.out.println("12");
                    msg = in.readLine();
                    System.out.println("13");
                    int begin = msg.indexOf("49="+1);
                    System.out.println("14");
                    int end = msg.indexOf("49="+6);
                    System.out.println("15");
                    tid = msg.substring(begin, end);
                    System.out.println("16");
                    bid = Integer.parseInt(tid);
                    System.out.println("17");
                    begin = msg.indexOf("56="+1);
                    System.out.println("18");
                    end = msg.indexOf("56="+6);
                    System.out.println("19");
                    tid = msg.substring(begin, end);
                    System.out.println("20");
                    mid = Integer.parseInt(tid);
                    System.out.println("21");
                    System.out.println("Broker ID in Server is: "+bid+" Market ID inn server is: "+mid);
                    if(router.pair.containsKey(bid)){
                        System.out.println("22");
                        mr.SendString(msg);//loops through array of connections and sends the string to them all
                        System.out.println("23");
                        msg = in.readLine();
                        System.out.println("24");
                        br.SendString(msg);
                        System.out.println("25");
                    }
                }
//                System.out.println("7");
//
//                System.out.println("8");
//                mr.SendString(msg);//loops through array of connections and sends the string to them all
//                System.out.println("9");
//                msg = in.readLine();
//                System.out.println("10");
//                br.SendString(msg);
//                System.out.println("11");
            }
        }
    }

    @lombok.SneakyThrows
    public void run(){//overwriting the run() function in thread
        //Reading data from broker

//                String str2 = str.substring(0, 3);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        os = new OutputStreamWriter(socket.getOutputStream());
        out = new PrintWriter(os);
        System.out.println("do you even enter");
        if (router.marketid > 99999){
            router.pair.put(router.brokerid, router.i);
            router.i++;
            while (true) {
                System.out.println("apparently you do");
                SendStringToAll();
            }
        }
//        in.close();
//        os.close();
//        out.close();
//        socket.close();
    }

}
