import javax.swing.*;
import java.awt.*;

public class AboutUs extends JDialog {

    private void InitUI() {

        setMinimumSize(new Dimension(600, 400));
        String offset = "        ";
        JLabel label = new JLabel(offset+"some text");
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setBackground(Color.lightGray);
        add(label);
    }

    public AboutUs() {
        InitUI();
        this.pack();
        this.setVisible(true);
    }
}
