import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;

public class KBCServer01 {
    private ServerSocket server;

    public KBCServer01() throws Exception {
        this.server = new ServerSocket(7777, 1, InetAddress.getByName("localhost"));
    }

    private boolean checkAns(BufferedReader in, String ans, PrintWriter out) throws Exception{
        String data = in.readLine();
        System.out.println(data);
        if(data.equals(ans)){
            out.println("\r\nRight Answer!");
            return true;
        }else{
            out.println("\r\nSorry Your Answer is Wrong! Try Next Time.");
            return false;
        }
    }

    private void listen() throws Exception {
        String data = null;
        Socket client = this.server.accept();
        String clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("\r\nNew connection from " + clientAddress);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        out.println("Hello From KBC Server!\r\nLets play KBC.");
        out.println("Question 1 for Rs.10,000:\r\nHow many players of a Kho-Kho team can play on the field during the match?");
        out.println("a. 10 b. 9 c. 7 d. 8");
        out.println(".");

        if(checkAns(in, "b", out)){
            out.println("Wow,You have won Rs.10,000. Be ready for the second question !");
            out.println("Question 2 for Rs.10,00,000:\r\nWhat is the common name for surgery conducted on coronary arteries that supply blood to the heart?");
            out.println("a. Cataract b. Gastric c. Bypass d. Debridement");
            out.println(".");
        }else{
            out.println("EXIT");
            System.exit(0);
        }

        if(checkAns(in, "c", out)){
            out.println("Wow,You have won Rs.10,00,000. Be ready for the third and final question !");
            out.println("Question 3 for Rs.10Cr.:\r\nWhich of these Indian cities is closest to the Pakistani city of Lahore?");
            out.println("a. Srinagar b. Jaisalmer c. amritsar d. Udhampur");
            out.println(".");
        }else{
            out.println("EXIT");
            System.exit(0);
        }

        if(checkAns(in, "c", out)){
            out.println("YOU HAVE WON THE KBC!!! CONGRATULATIONS ");
            out.println("EXIT");
        }else{
            out.println("EXIT");
            System.exit(0);
        }
    }
    public static void main(String[] args) throws Exception {
        KBCServer01 app = new KBCServer01();
        app.listen();
    }
}