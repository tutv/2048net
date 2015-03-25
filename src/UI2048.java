import javax.swing.*;
import java.awt.*;

/**
 * Created by Tu on 26/3/2015.
 */

public class UI2048 {
    public final int XX = 16;
    private JPanel gridNumber;
    private BoxNumber [] boxs;


    public UI2048() {
        gridNumber = new JPanel();
        gridNumber.setSize(480, 480);
        gridNumber.setPreferredSize(new Dimension(480, 480));
        gridNumber.setLayout(new GridLayout(4,4));

        boxs = new BoxNumber[XX];
        for (int i=0; i<XX; i++) {
            boxs[i] = new BoxNumber();
            gridNumber.add(boxs[i]);
        }
    }

    public JFrame createFrame() {
        JFrame frame = new JFrame("Game 2048");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(gridNumber, BorderLayout.WEST);

        JPanel control = new JPanel();
        JLabel score = new JLabel("Điểm: 1000");
        JButton start = new JButton("Start");
        control.add(score);
        control.add(start);

        frame.add(control, BorderLayout.EAST);
        frame.setVisible(true);

        int [] nums = {0, 2, 4, 8, 512, 16, 64, 1024, 32, 4, 4, 8, 4, 1024, 4, 2048};
        printGridNumber(nums);

        return frame;
    }

    private void printGridNumber(int [] nums) {
        for (int i=0; i<nums.length; i++) {
            boxs[i].setText(nums[i]);
        }
    }


    public static void main(String [] args) {
        UI2048 ui = new UI2048();
        JFrame frame = ui.createFrame();
    }
}
