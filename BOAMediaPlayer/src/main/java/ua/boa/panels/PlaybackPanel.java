package ua.boa.panels;

import ua.boa.CustomMediaComponent;
import ua.boa.listeners.MediaPlayerListener;
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
        add(jSlider, BorderLayout.EAST);
    }
    private JSlider createSlider(){
        JSlider jSlider = new JSlider(0, 101, 0);
        jSlider.addChangeListener(l->{
            //System.out.println(jSlider.getValue());
            //mediaPlayerComponent.mediaPlayer().controls().setPosition(jSlider.getValue()/100f);
        });
        return jSlider;
    }
}
