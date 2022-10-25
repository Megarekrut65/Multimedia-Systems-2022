package ua.boa.panels;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Panel that contain label with file name.
 */
public class FileNamePanel extends JPanel {
    private final JLabel fileNameLabel;

    public FileNamePanel() {
        super(new BorderLayout());
        fileNameLabel = new JLabel();
        fileNameLabel.setFont(new Font("arial", Font.BOLD, 15));
        setBackground(Color.BLACK);
        add(fileNameLabel, BorderLayout.EAST);
        add(Box.createVerticalStrut(20), BorderLayout.WEST);
    }

    /**
     * Changes text of label(file name)
     *
     * @param text - new text
     */
    public void setText(String text) {
        fileNameLabel.setVisible(!Objects.equals(text, ""));
        fileNameLabel.setText(text + " ");
    }
}
