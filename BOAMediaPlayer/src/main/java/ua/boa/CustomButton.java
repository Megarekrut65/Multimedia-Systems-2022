package ua.boa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CustomButton extends NoFocusableButton {
    private final Color PRESSED_COLOR = new Color(0,0,0,1);
    private final Color RELEASED_COLOR = new Color(0,0,0,0);
    private final int width;
    private final int height;
    public CustomButton(ImageIcon imageIcon, int width, int height){
        setContentAreaFilled(false);
        setBorderPainted(false);
        this.width = width;
        this.height = height;
        setImage(imageIcon);
        setPreferredSize(new Dimension(width+5, height+5));
    }
    public void setImage(ImageIcon imageIcon){
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        setIcon(new ImageIcon(image));
    }
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(PRESSED_COLOR);
        } else if (getModel().isRollover()) {
            g.setColor(RELEASED_COLOR);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
