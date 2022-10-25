package ua.boa;

import javax.swing.*;

public class NoFocusableButton extends JButton {
    public NoFocusableButton() {
        setFocusable(false);
    }

    public NoFocusableButton(String text) {
        super(text);
        setFocusable(false);
    }
}
