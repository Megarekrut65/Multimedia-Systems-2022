package ua.boa;

import ua.boa.listeners.CustomMouseListener;
import ua.boa.listeners.MediaPlayerListener;
import ua.boa.panels.ContainerPanel;
import ua.boa.panels.ControlsPanel;
import ua.boa.panels.FileNamePanel;
import ua.boa.panels.HeaderPanel;
import ua.boa.savers.PathSaver;
import ua.boa.savers.VolumeSaver;

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
    private final PathSaver pathSaver;
    private final FileNamePanel fileNamePanel;
    private final MediaPlayerListener mediaPlayerListener;

    public MediaPlayer(String title, int width, int height){
        pathSaver = new PathSaver("src/main/resources/data/last.txt");
        fileNamePanel = new FileNamePanel();
        mediaPlayerListener = new MediaPlayerListener(pathSaver, fileNamePanel);
        VolumeSaver volumeSaver = new VolumeSaver("src/main/resources/data/volume.txt");
        mediaPlayerComponent = new CustomMediaComponent(volumeSaver);
        mediaComponentSettings();
        size = new Dimension(width, height);
        mainPanel = new JPanel();
        jFrame = new JFrame(title);
        panelSettings();
        frameSettings();
    }
    private void mediaComponentSettings(){
        mediaPlayerComponent.mediaPlayer().controls().setRepeat(true);
        mediaPlayerComponent.mediaPlayer().events().addMediaPlayerEventListener(mediaPlayerListener);
        mediaPlayerComponent.add(fileNamePanel, BorderLayout.PAGE_END);
    }
    private void panelSettings(){
        mainPanel.setLayout(new BorderLayout());
        JPanel header = new HeaderPanel(mediaPlayerComponent, jFrame, pathSaver);
        mainPanel.add(new ContainerPanel(header), BorderLayout.NORTH);
        header.setSize(new Dimension(size.width, header.getPreferredSize().height));
        mainPanel.add(mediaPlayerComponent, BorderLayout.CENTER);
        JPanel controls = new ControlsPanel(mediaPlayerComponent, mediaPlayerListener, ICONS);
        mainPanel.add(new ContainerPanel(controls), BorderLayout.PAGE_END);
        CustomMouseListener mouseListener = new CustomMouseListener(()->{
            header.setVisible(false);
            controls.setVisible(false);
            fileNamePanel.setText("");
        }, ()->{
            header.setVisible(true);
            controls.setVisible(true);
            fileNamePanel.setText(pathSaver.getLastPath());
        },8);
        mainPanel.addMouseMotionListener(mouseListener);
        mainPanel.addMouseListener(mouseListener);
    }
    private void frameSettings(){
        jFrame.setResizable(true);
        jFrame.setMinimumSize(MIN_SIZE);
        jFrame.setSize(size);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(mainPanel);
        jFrame.setIconImage(ICONS.ICON.getImage());
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                jFrame.setVisible(false);
                mediaPlayerComponent.stopButton();
                mediaPlayerComponent.release();
                System.exit(0);
            }
        });
    }
    public void open(){
        jFrame.setVisible(true);
        String last = pathSaver.getLastPath();
        if(last != null && !last.equals("")){
            fileNamePanel.setText(last);
            mediaPlayerComponent.mediaPlayer().media().prepare(last);
            mediaPlayerComponent.mediaPlayer().media().startPaused(last);
        }
    }
}
