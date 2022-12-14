package ua.boa.panels;

import ua.boa.listeners.MediaPlayerListener;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for displaying current time and media duration
 */
public class TimePanel extends JPanel {
    public TimePanel(MediaPlayerListener mediaPlayerListener) {
        super(new BorderLayout());
        JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.HORIZONTAL);
        label.setFont(new Font("arial", Font.PLAIN, 20));
        mediaPlayerListener.setTimeLabel(label);
        add(label, BorderLayout.CENTER);
    }
}
