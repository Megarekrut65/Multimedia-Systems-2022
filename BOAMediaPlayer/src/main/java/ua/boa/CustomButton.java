package ua.boa;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    private final Color PRESSED_COLOR = new Color(0,0,0,1);
    private final Color RELEASED_COLOR = new Color(0,0,0,0);

    public CustomButton(ImageIcon imageIcon, int width, int height){
        setBorder(BorderFactory.createEmptyBorder());
        setContentAreaFilled(false);
        setBorderPainted(false);
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        setIcon(new ImageIcon(image));
    }
    public void setImage(ImageIcon imageIcon){
        Image image = imageIcon.getImage().getScaledInstance(getPreferredSize().width,
                getPreferredSize().height, Image.SCALE_DEFAULT);
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
