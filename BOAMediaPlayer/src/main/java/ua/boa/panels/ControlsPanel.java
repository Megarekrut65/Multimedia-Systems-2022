package ua.boa.panels;

import ua.boa.CustomMediaComponent;
import ua.boa.IconsLoader;
import ua.boa.listeners.MediaPlayerListener;

import javax.swing.*;
import java.awt.*;

/**
 * Panel with control parts like buttons, sliders and other
 */
public class ControlsPanel extends JPanel {

    public ControlsPanel(CustomMediaComponent mediaPlayerComponent,
                         MediaPlayerListener mediaPlayerListener,
                         IconsLoader icons) {

        setLayout(new BorderLayout());
        add(new PlaybackPanel(mediaPlayerComponent, mediaPlayerListener), BorderLayout.NORTH);
        add(new ButtonsPanel(mediaPlayerComponent, icons), BorderLayout.WEST);
        add(new TimePanel(mediaPlayerListener), BorderLayout.CENTER);
        add(new VolumePanel(mediaPlayerComponent, icons), BorderLayout.EAST);
    }

}
