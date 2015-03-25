import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String args[]) {
        try {
            Socket skt = new Socket("localhost", 1234);
            BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
            out.print("Tran Van Tu");
            out.close();
            in.close();
        }
        catch (Exception e) {
            System.out.print("Whoops! It didn't work!\n");
        }
    }
}
