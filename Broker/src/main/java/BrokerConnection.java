import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BrokerConnection extends Thread{
    String ip = "localhost";
    int port = 5000;
    BufferedReader in;
    OutputStreamWriter os;
    PrintWriter out;
    Socket s;
    public BrokerConnection(Socket socket, Broker broker) throws IOException {
        s = socket;
    }

    public void SendStringToRouter(String text){
        out.println(text);//sending data to server
        out.flush();
    }

    @lombok.SneakyThrows
    public void run(){
        try {
            os = new OutputStreamWriter(s.getOutputStream());
            out = new PrintWriter(os);
            while (true) {
                try {
                    in = new BufferedReader(new InputStreamReader(s.getInputStream()));
//            data given by the Router
                    String ser = in.readLine();
                    System.out.println("Data from Router " + ser);
                }
                catch (IOException e){
                    e.printStackTrace();
                    close();
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
            close();
        }
    }

    public void close() throws IOException {
        out.close();
        in.close();
        s.close();
    }
}
