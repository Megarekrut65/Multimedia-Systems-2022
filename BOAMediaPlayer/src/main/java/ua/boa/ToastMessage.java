package ua.boa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;

public class ToastMessage extends JFrame {
    private final int timeToShow;

    public ToastMessage(ImageIcon imageIcon, String message, int timeToShow, Component parent) {
        this.timeToShow = timeToShow;
        setUndecorated(true);
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240, 250));
        setSize(300, 50);
        setLocationRelativeTo(parent);
        setIconImage(imageIcon.getImage());
        add(new JLabel(message));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new RoundRectangle2D.Double(0, 0, getWidth(),
                        getHeight(), 20, 20));
            }
        });
    }

    public void display() {
        try {
            setOpacity(1);
            setVisible(true);
            Thread.sleep(timeToShow);
            for (double d = 1.0; d > 0.2; d -= 0.1) {
                Thread.sleep(100);
                setOpacity((float) d);
            }
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}