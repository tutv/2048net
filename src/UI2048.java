import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
/**
 * Created by Tu on 26/3/2015.
 */

public class UI2048 {
    public final int XX = 16;
    private JPanel gridNumber;
    private BoxNumber [] boxs;
    private int score;

    public int [] nums;


    public UI2048() {
        score = 0;

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
    }

    public JFrame createFrame() {
        JFrame frame = new JFrame("Game 2048");
        frame.setSize(700, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(gridNumber, BorderLayout.WEST);

        JPanel control = new JPanel();
        JLabel score = new JLabel("Điểm: 1000");
        JButton start = new JButton("Start");
        control.add(score);
        control.add(start);

        frame.add(control, BorderLayout.EAST);
        frame.setVisible(true);

        frame.setFocusable(true);
        frame.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        System.out.println(e.getKeyCode());

                        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
                            printGridNumber();
                        }
                    }
                }
        );

        return frame;
    }

    private void printGridNumber() {

        for (int i=0; i<XX; i++) {
            nums[i] = (int) Math.pow(2, Math.abs((new Random()).nextInt() % 10) + 1);
            boxs[i].setText(nums[i]);
        }
    }


    public static void main(String [] args) {
        UI2048 ui = new UI2048();
        JFrame frame = ui.createFrame();
    }
}
