import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Tu on 26/3/2015.
 */
public class BoxNumber extends JLabel{
    public BoxNumber() {
        super();
        setBackground(new Color(204, 192, 179));
        setHorizontalAlignment(CENTER);

        setPreferredSize(new Dimension(120, 120));
        setSize(120, 120);

        setOpaque(true);
        Border padding = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        Border border = BorderFactory.createLineBorder(new Color(187, 173, 160), 5);
        setBorder(border);

        setFont(new Font("Consolas", Font.BOLD, 35));
    }

    public void setText(int value) {
        super.setText(Integer.toString(value));
        updateBackGround(value);
        updateFontColor(value);
    }

    public void updateBackGround(int value) {
        switch (value) {
            case 0: setBackground(new Color(204, 192, 179));
                break;
            case 2: setBackground(new Color(238, 228, 218));
                break;
            case 4: setBackground(new Color(237, 224, 200));
                break;
            case 8: setBackground(new Color(242, 177, 121));
                break;
            case 16: setBackground(new Color(245, 149, 99));
                break;
            case 32: setBackground(new Color(246, 124, 95));
                break;
            case 64: setBackground(new Color(246, 94, 59));
                break;
            case 128: setBackground(new Color(237, 207, 114));
                break;
            case 256: setBackground(new Color(237, 204, 97));
                break;
            case 512: setBackground(new Color(237, 200, 80));
                break;
            case 1024: setBackground(new Color(237, 197, 63));
                break;
            case 2048: setBackground(new Color(237, 193, 46));
                break;
            default: setBackground(new Color(237, 225, 63));
                break;
        }
    }

    public void updateFontColor(int value) {
        if (value<=4) {
            setForeground(new Color(119, 110, 101));
        } else {
            setForeground(new Color(255, 241, 222));
        }
    }
}
