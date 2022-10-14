package ua.boa;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomMouseListener implements MouseMotionListener {
    private final HidingRunnable hidingRunnable;
    public CustomMouseListener(Action hide,Action show,  int timeToHide){
       hidingRunnable = new HidingRunnable(hide, show, timeToHide);
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        hidingRunnable.moving();
    }
}
