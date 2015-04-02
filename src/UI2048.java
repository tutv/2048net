import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Tu on 26/3/2015.
 */

public class UI2048 {
    public final int XX = 16;
    private JPanel gridNumber;
    private BoxNumber [] boxs;

    public int [] nums;

    public static Socket socket;
    public static BufferedReader in;
    public static PrintWriter out;


    public UI2048() {
        try {
            socket = new Socket("128.199.68.73", 8080);
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        gridNumber = new JPanel();
        gridNumber.setSize(480, 480);
        gridNumber.setPreferredSize(new Dimension(480, 480));
        gridNumber.setLayout(new GridLayout(4,4));

        boxs = new BoxNumber[XX];
        nums = new int [XX];
        for (int i=0; i<XX; i++) {
            boxs[i] = new BoxNumber();
            nums[i] = 0;
            gridNumber.add(boxs[i]);
        }

        gridNumber.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                send(e.getKeyCode());
                String re = receive();
                System.out.println(re);
                nums = cvtSring2Arr(re);
                updateGrid();
            }
        });
        gridNumber.setFocusable(true);
    }

    public JFrame createFrame() {
        JFrame frame = new JFrame("Game 2048");
        frame.setSize(650, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        frame.add(gridNumber);
        Dimension sizeGrid = gridNumber.getPreferredSize();
        gridNumber.setBounds(0, 0, sizeGrid.width, sizeGrid.height);

        JPanel control = createControl();
        frame.add(control);
        control.setBounds(sizeGrid.width + 20, 20, 100, 500);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeGame();
                super.windowClosing(e);
            }
        });

        return frame;
    }

    public JPanel createControl() {
        JPanel control = new JPanel();
        JLabel score = new JLabel("Điểm: 0");
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopGame();
            }
        });

        control.add(score);
        control.add(start);
        control.add(stop);
        start.setFocusable(false);
        stop.setFocusable(false);
        return control;
    }

    public void startGame() {
        System.out.println("Start Game!");
    }

    public void stopGame() {
        System.out.println("Stop Game");
        closeGame();
    }

    public void send(int charCode) {
        out.println(charCode);
        out.flush();
    }

    public String receive() {
        String m = "";
        try {
            m = in.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return m;
    }

    public int [] cvtSring2Arr(String m) {
        String [] arrStr = m.split(" ");
        int [] arr = new int[XX];
        for (int i=0; i<XX; i++) {
            arr[i] = Integer.parseInt(arrStr[i]);
        }
        return arr;
    }

    private void updateGrid() {
        for (int i=0; i<XX; i++) {
            boxs[i].setText(nums[i]);
        }
    }

    private void closeGame() {
        try {
            out.close();
            in.close();
            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String [] args) {
        UI2048 ui = new UI2048();
        JFrame frame = ui.createFrame();
    }
}