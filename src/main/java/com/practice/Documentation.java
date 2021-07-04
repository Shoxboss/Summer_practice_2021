package com.practice;

import javax.swing.*;
import java.awt.*;

public class Documentation  extends JDialog {

    private void InitUI() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        setMinimumSize(new Dimension(600, 400));
        String offset = "        ";
        JLabel label = new JLabel(offset+"some Text");
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setBackground(Color.lightGray);
        mainPanel.add(label);
        add(mainPanel);

    }
    public Documentation() {
        InitUI();
        this.pack();
        this.setVisible(true);
    }
}
