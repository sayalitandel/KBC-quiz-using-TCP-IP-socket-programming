import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KBCClient01 {
    private Socket socket;
    private BufferedReader inuptFromKeyboard;
    private BufferedReader inuptFromServer; 

    private KBCClient01() throws Exception{
        this.socket = new Socket(InetAddress.getByName("localhost"), 7777);
        this.inuptFromKeyboard = new BufferedReader(new InputStreamReader(System.in));
        this.inuptFromServer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    private void readFromServer() throws IOException{
        String data = null;
        while(true){
            data = this.inuptFromServer.readLine();
            if (data == null || data.equals("."))
                break;
            if (data.equals("EXIT")){
                System.out.println("Goodbye!");
                System.exit(0);
            }
            System.out.println(data);
        }
    }

    private void start() throws Exception{
        String ans;
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);

        this.readFromServer();
        ans = inuptFromKeyboard.readLine();
        out.println(ans);

        this.readFromServer();
        ans = inuptFromKeyboard.readLine();
        out.println(ans);

        this.readFromServer();
        ans = inuptFromKeyboard.readLine();
        out.println(ans);

        this.readFromServer();
    }
    public static void main(String[] args) throws Exception {
        KBCClient01 client = new KBCClient01();
        System.out.println("\r\nConnected to Server: " + client.socket.getInetAddress());
        client.start();
    }
}