package ua.boa.listeners;

import ua.boa.HidingThread;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;

public class CustomMouseListener extends MouseAdapter implements MouseMotionListener{
    private final HidingThread hidingThread;
    public CustomMouseListener(HidingThread hidingThread){
       this.hidingThread = hidingThread;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        hidingThread.moving();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        hidingThread.moving();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        hidingThread.moving();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        hidingThread.moving();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        hidingThread.moving();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        hidingThread.moving();
    }
}
