package ua.boa.listeners;

import ua.boa.CustomMediaComponent;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlaybackMouseListener extends MouseAdapter {
    private final CustomMediaComponent mediaComponent;

    public PlaybackMouseListener(CustomMediaComponent mediaComponent) {
        this.mediaComponent = mediaComponent;
    }

    private void mouse(MouseEvent e) {
        JProgressBar jProgressBar = (JProgressBar) e.getSource();
        float position = (float) e.getX() / jProgressBar.getWidth();
        mediaComponent.changePosition(position);
        jProgressBar.setValue((int) (100000 * position));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouse(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouse(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouse(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouse(e);
    }

}
