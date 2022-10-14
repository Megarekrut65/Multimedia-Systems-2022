package ua.boa;

import uk.co.caprica.vlcj.media.InfoApi;
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
        add(createPauseButton());
        add(createStopButton());
    }
    private JButton createPlayButton(){
        CustomButton play = new CustomButton(ICONS.PLAY_ICON, 25, 25);
        play.addActionListener(l-> mediaPlayerComponent.mediaPlayer().controls().play());
        return play;
    }
    private JButton createPauseButton(){
        CustomButton pause = new CustomButton(ICONS.PAUSE_ICON, 25, 25);
        pause.addActionListener(l-> mediaPlayerComponent.mediaPlayer().controls().setPause(true));
        return pause;
    }
    private JButton createStopButton(){
        CustomButton stop = new CustomButton(ICONS.STOP_ICON, 25, 25);
        stop.addActionListener(l-> {
            mediaPlayerComponent.mediaPlayer().controls().stop();
            InfoApi info = mediaPlayerComponent.mediaPlayer().media().info();
            if(info != null) mediaPlayerComponent.mediaPlayer().media()
                    .startPaused(info.mrl());
        });
        return stop;
    }
}
