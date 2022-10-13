package ua.boa;

import uk.co.caprica.vlcj.media.Media;
import uk.co.caprica.vlcj.player.base.MediaApi;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MediaPlayer{
    private final Dimension MIN_SIZE = new Dimension(500, 400);
    private final IconsLoader ICONS = new IconsLoader();
    private final Dimension size;
    private final JFrame jFrame;
    private final JPanel mainPanel;
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

    public MediaPlayer(String title, int width, int height){
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        size = new Dimension(width, height);
        mainPanel = new JPanel();
        jFrame = new JFrame(title);
        panelSettings();
        frameSettings();
    }
    private void panelSettings(){
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(new HeaderPanel(mediaPlayerComponent, jFrame), BorderLayout.NORTH);
        mainPanel.add(mediaPlayerComponent, BorderLayout.CENTER);
        mainPanel.add(new ControlsPanel(mediaPlayerComponent, ICONS), BorderLayout.PAGE_END);
    }
    private void frameSettings(){
        jFrame.setResizable(true);
        jFrame.setMinimumSize(MIN_SIZE);
        jFrame.setSize(size);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(mainPanel);
        jFrame.setIconImage(ICONS.ICON.getImage());
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
                System.exit(0);
            }
        });
    }
    public void open(){
        jFrame.setVisible(true);
    }
}
