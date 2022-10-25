package ua.boa;

import javax.swing.*;
import java.awt.*;

/**
 * Class for creating button with image instead of text
 */
public class CustomButton extends NoFocusableButton {
    private final Color PRESSED_COLOR = new Color(0, 0, 0, 50);/*Color of pressed button background*/
    private final Color RELEASED_COLOR = new Color(0, 0, 0, 0);/*Color of released button background*/
    private final int width;/*Image width*/
    private final int height;/*Image height*/

    public CustomButton(ImageIcon imageIcon, int width, int height) {
        setContentAreaFilled(false);
        setBorderPainted(false);
        this.width = width;
        this.height = height;
        setImage(imageIcon);
        setPreferredSize(new Dimension(width + 5, height + 5));
    }

    /**
     * Sets image to button
     *
     * @param imageIcon - image of button
     */
    public void setImage(ImageIcon imageIcon) {
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        setIcon(new ImageIcon(image));
    }

    /**
     * Repaints button without border
     */
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
