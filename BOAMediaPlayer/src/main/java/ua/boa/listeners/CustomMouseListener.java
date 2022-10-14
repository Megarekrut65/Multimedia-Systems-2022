package ua.boa.listeners;

import ua.boa.Action;
import ua.boa.HidingRunnable;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomMouseListener extends MouseAdapter implements MouseMotionListener{
    private final HidingRunnable hidingRunnable;
    public CustomMouseListener(ua.boa.Action hide, Action show, int timeToHide){
       hidingRunnable = new HidingRunnable(hide, show, timeToHide);
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        hidingRunnable.moving();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        hidingRunnable.moving();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        hidingRunnable.moving();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        hidingRunnable.moving();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        hidingRunnable.moving();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        hidingRunnable.moving();
    }
}
