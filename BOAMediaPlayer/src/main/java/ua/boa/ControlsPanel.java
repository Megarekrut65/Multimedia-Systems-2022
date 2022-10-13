package ua.boa;

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ControlsPanel extends JPanel {
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private final IconsLoader ICONS;

    public ControlsPanel(EmbeddedMediaPlayerComponent mediaPlayerComponent, IconsLoader icons) {
        this.mediaPlayerComponent = mediaPlayerComponent;
        ICONS = icons;
        setLayout(new FlowLayout());
        add(createPlayButton());
    }
    private JButton createPlayButton(){
        CustomButton play = new CustomButton(ICONS.PLAY_ICON, 50, 50);
        AtomicBoolean isPlayed = new AtomicBoolean(false);
        play.addActionListener(l->{
            if(isPlayed.get()){
                mediaPlayerComponent.mediaPlayer().controls().pause();
                play.setImage(ICONS.PLAY_ICON);
                isPlayed.set(false);
                return;
            }
            mediaPlayerComponent.mediaPlayer().controls().play();
            play.setImage(ICONS.PAUSE_ICON);
            isPlayed.set(true);
        });
        return play;
    }
}
