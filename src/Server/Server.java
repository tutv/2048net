import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String args[]) {
        final int XX = 16;
        ServerSocket serverSocket;
        final Socket socket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc = new Scanner(System.in);
        try {
            serverSocket = new ServerSocket(8080);
            socket = serverSocket.accept();
            System.out.println("Connected!");
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Matrix matrix = new Matrix();
            while (socket.isConnected()) {
                try {
                    String action = in.readLine();
                    if (action == null) {
                        break;
                    }
                    int move = Integer.parseInt(action);
                    matrix.move(move);
                    out.println(matrix.toString());
                    out.flush();
                    System.out.println(move + "\t" + cvtMove(move));
                    matrix.printMatrix();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("Stop Server!");
            out.close();
            in.close();
            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String cvtMove(int move) {
        switch (move) {
            case 39: return "RIGHT";
            case 40: return "DOWN";
            case 37: return "LEFT";
            case 38: return "UP";
            default: return "NO";
        }
    }
}