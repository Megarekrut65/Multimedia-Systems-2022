package ua.boa.panels;

import ua.boa.CustomMediaComponent;
import ua.boa.listeners.MediaPlayerListener;
import uk.co.caprica.vlcj.media.InfoApi;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlaybackPanel extends JPanel {
    private final CustomMediaComponent mediaPlayerComponent;

    public PlaybackPanel(CustomMediaComponent mediaPlayerComponent, MediaPlayerListener mediaPlayerListener) {
        super(new BorderLayout());
        this.mediaPlayerComponent = mediaPlayerComponent;
        JProgressBar jProgressBar = createSlider();
        mediaPlayerListener.setSlider(jProgressBar);
        add(jProgressBar, BorderLayout.CENTER);
    }
    private JProgressBar createSlider(){
        JProgressBar jProgressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 99999);
        jProgressBar.setStringPainted(true);
        jProgressBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                float position = (float)e.getX()/jProgressBar.getWidth();
                mediaPlayerComponent.changePosition(position);
                jProgressBar.setValue((int) (100000*position));
            }
        });
        return jProgressBar;
    }
}
