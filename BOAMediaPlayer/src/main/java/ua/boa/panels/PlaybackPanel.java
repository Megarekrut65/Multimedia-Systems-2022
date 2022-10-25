package ua.boa.panels;

import ua.boa.CustomMediaComponent;
import ua.boa.listeners.MediaPlayerListener;
import ua.boa.listeners.PlaybackMouseListener;

import javax.swing.*;
import java.awt.*;

/**
 * Class for displaying position of media and changing it
 */
public class PlaybackPanel extends JPanel {
    private final CustomMediaComponent mediaPlayerComponent;

    public PlaybackPanel(CustomMediaComponent mediaPlayerComponent, MediaPlayerListener mediaPlayerListener) {
        super(new BorderLayout());
        this.mediaPlayerComponent = mediaPlayerComponent;
        JProgressBar jProgressBar = createSlider();
        mediaPlayerListener.setSlider(jProgressBar);
        add(jProgressBar, BorderLayout.CENTER);
    }

    /**
     * Creates slider for displaying media position and changing it
     */
    private JProgressBar createSlider() {
        JProgressBar jProgressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 99999);
        PlaybackMouseListener playbackMouseListener = new PlaybackMouseListener(mediaPlayerComponent);
        jProgressBar.addMouseListener(playbackMouseListener);
        jProgressBar.addMouseMotionListener(playbackMouseListener);
        return jProgressBar;
    }
}
