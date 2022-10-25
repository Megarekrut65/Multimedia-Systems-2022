package ua.boa.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Panel to cover other panel. Using for mouse listener.
 * When original panel is hidden listener doesn't call events.
 * But if there is container with background color as app background, listener works
 */
public class ContainerPanel extends JPanel {
    /**
     * @param child - panel that need cover
     */
    public ContainerPanel(JPanel child) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setPreferredSize(child.getPreferredSize());
        setBackground(Color.BLACK);
        add(child);
    }
}
