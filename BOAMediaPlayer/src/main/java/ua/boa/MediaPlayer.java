package ua.boa;

import ua.boa.listeners.CustomMouseListener;
import ua.boa.panels.ContainerPanel;
import ua.boa.panels.ControlsPanel;
import ua.boa.panels.HeaderPanel;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MediaPlayer{
    private final Dimension MIN_SIZE = new Dimension(500, 400);
    private final IconsLoader ICONS = new IconsLoader();
    private final Dimension size;
    private final JFrame jFrame;
    private final JPanel mainPanel;
    private final CustomMediaComponent mediaPlayerComponent;

    public MediaPlayer(String title, int width, int height){
        mediaPlayerComponent = new CustomMediaComponent();
        mediaPlayerComponent.mediaPlayer().controls().setRepeat(true);
        size = new Dimension(width, height);
        mainPanel = new JPanel();
        jFrame = new JFrame(title);
        panelSettings();
        frameSettings();
    }
    private void panelSettings(){
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setLayout(new BorderLayout());
        JPanel header = new HeaderPanel(mediaPlayerComponent, jFrame);
        mainPanel.add(new ContainerPanel(header), BorderLayout.NORTH);
        header.setSize(new Dimension(size.width, header.getPreferredSize().height));
        mainPanel.add(mediaPlayerComponent, BorderLayout.CENTER);
        JPanel controls = new ControlsPanel(mediaPlayerComponent, ICONS);
        mainPanel.add(new ContainerPanel(controls), BorderLayout.PAGE_END);
        mainPanel.addMouseMotionListener(new CustomMouseListener(()->{
            header.setVisible(false);
            controls.setVisible(false);
        }, ()->{
            header.setVisible(true);
            controls.setVisible(true);
        },5));
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
