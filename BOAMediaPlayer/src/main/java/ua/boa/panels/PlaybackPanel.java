package ua.boa.panels;

import ua.boa.CustomMediaComponent;
import ua.boa.listeners.MediaPlayerListener;
import uk.co.caprica.vlcj.media.InfoApi;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;

public class PlaybackPanel extends JPanel {
    private final CustomMediaComponent mediaPlayerComponent;

    public PlaybackPanel(CustomMediaComponent mediaPlayerComponent, MediaPlayerListener mediaPlayerListener) {
        super(new BorderLayout());
        this.mediaPlayerComponent = mediaPlayerComponent;
        JSlider jSlider = createSlider();
        mediaPlayerListener.setSlider(jSlider);
        add(jSlider, BorderLayout.CENTER);
    }
    private JSlider createSlider(){
        JSlider jSlider = new JSlider(0, 100, 0);
        jSlider.addChangeListener(l->mediaPlayerComponent.changePosition(jSlider.getValue()));
        return jSlider;
    }
}
