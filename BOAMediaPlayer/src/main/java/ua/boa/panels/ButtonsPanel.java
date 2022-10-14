package ua.boa.panels;

import ua.boa.CustomButton;
import ua.boa.CustomMediaComponent;
import ua.boa.IconsLoader;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {
    private final CustomMediaComponent mediaPlayerComponent;
    private final IconsLoader ICONS;

    public ButtonsPanel(CustomMediaComponent mediaPlayerComponent, IconsLoader icons) {
        this.mediaPlayerComponent = mediaPlayerComponent;
        ICONS = icons;
        setLayout(new FlowLayout());
        add(createPlayButton());
        add(createPauseButton());
        add(createStopButton());
    }
    private JButton createPlayButton(){
        CustomButton play = new CustomButton(ICONS.PLAY_ICON, 25, 25);
        play.addActionListener(l-> mediaPlayerComponent.playButton());
        return play;
    }
    private JButton createPauseButton(){
        CustomButton pause = new CustomButton(ICONS.PAUSE_ICON, 25, 25);
        pause.addActionListener(l-> mediaPlayerComponent.pauseButton());
        return pause;
    }
    private JButton createStopButton(){
        CustomButton stop = new CustomButton(ICONS.STOP_ICON, 25, 25);
        stop.addActionListener(l-> mediaPlayerComponent.stopButton());
        return stop;
    }
}
