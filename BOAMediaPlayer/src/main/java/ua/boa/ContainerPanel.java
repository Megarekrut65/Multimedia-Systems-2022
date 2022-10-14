package ua.boa;

import javax.swing.*;
import java.awt.*;

public class ContainerPanel extends JPanel {
    public ContainerPanel(JPanel child){
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setPreferredSize(child.getPreferredSize());
        setBackground(Color.BLACK);
        add(child);
    }
}
