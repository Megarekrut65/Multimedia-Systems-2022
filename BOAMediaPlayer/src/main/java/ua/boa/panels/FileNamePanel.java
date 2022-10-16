package ua.boa.panels;

import javax.swing.*;
import java.awt.*;

public class FileNamePanel extends JPanel {
    private final JLabel fileNameLabel;

    public FileNamePanel(){
        super(new BorderLayout());
        fileNameLabel = new JLabel();
        fileNameLabel.setFont(new Font("arial", Font.BOLD, 15));
        setBackground(new Color(0,0,0,0));
        add(fileNameLabel, BorderLayout.EAST);
    }
    public void setText(String text){
        fileNameLabel.setVisible(!text.equals(""));
        fileNameLabel.setText(text + " ");
    }
}
