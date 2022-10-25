package ua.boa.panels;

import ua.boa.CustomButton;
import ua.boa.CustomMediaComponent;
import ua.boa.IconsLoader;

import javax.swing.*;
import java.awt.*;

/**
 * Panel with buttons that change media state. Play, pause, stop, forward, backward buttons.
 */
public class ButtonsPanel extends JPanel {
    private final Dimension SIZE = new Dimension(25, 25);
    private final CustomMediaComponent mediaPlayerComponent;
    private final IconsLoader ICONS;

    /**
     * @param mediaPlayerComponent - component to change media states
     * @param icons                - class with icons for buttons
     */
    public ButtonsPanel(CustomMediaComponent mediaPlayerComponent, IconsLoader icons) {
        this.mediaPlayerComponent = mediaPlayerComponent;
        ICONS = icons;
        setLayout(new FlowLayout());
        add(createPlayButton());
        add(createPauseButton());
        add(createStopButton());
        add(createRewindButton());
        add(createForwardButton());
    }

    private JButton createPlayButton() {
        CustomButton play = new CustomButton(ICONS.PLAY_ICON, SIZE.width, SIZE.height);
        play.setToolTipText("Play: SPACE");
        play.addActionListener(l -> mediaPlayerComponent.playButton());
        return play;
    }

    private JButton createPauseButton() {
        CustomButton pause = new CustomButton(ICONS.PAUSE_ICON, SIZE.width, SIZE.height);
        pause.setToolTipText("Pause: SPACE");
        pause.addActionListener(l -> mediaPlayerComponent.pauseButton());
        return pause;
    }

    private JButton createStopButton() {
        CustomButton stop = new CustomButton(ICONS.STOP_ICON, SIZE.width, SIZE.height);
        stop.setToolTipText("Stop: S");
        stop.addActionListener(l -> mediaPlayerComponent.stopButton());
        return stop;
    }

    private JButton createRewindButton() {
        CustomButton rewind = new CustomButton(ICONS.REWIND_ICON, SIZE.width, SIZE.height);
        rewind.setToolTipText("Rewind: Left");
        rewind.addActionListener(l -> mediaPlayerComponent.rewindButton());
        return rewind;
    }

    private JButton createForwardButton() {
        CustomButton forward = new CustomButton(ICONS.FORWARD_ICON, SIZE.width, SIZE.height);
        forward.setToolTipText("Forward: Right");
        forward.addActionListener(l -> mediaPlayerComponent.forwardButton());
        return forward;
    }
}
