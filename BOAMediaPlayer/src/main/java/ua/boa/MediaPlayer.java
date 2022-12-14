package ua.boa;

import ua.boa.listeners.CustomMouseListener;
import ua.boa.listeners.MediaPlayerListener;
import ua.boa.panels.ContainerPanel;
import ua.boa.panels.ControlsPanel;
import ua.boa.panels.FileNamePanel;
import ua.boa.panels.HeaderPanel;
import ua.boa.savers.DataSaver;
import uk.co.caprica.vlcj.player.embedded.fullscreen.adaptive.AdaptiveFullScreenStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Media player window with buttons, events and media playing
 */
public class MediaPlayer {
    private final Dimension MIN_SIZE = new Dimension(500, 400);
    private final IconsLoader ICONS;
    private final Dimension size;
    private final JFrame jFrame;
    private final JPanel mainPanel;
    private final CustomMediaComponent mediaPlayerComponent;
    private final DataSaver dataSaver;
    private final FileNamePanel fileNamePanel;
    private final MediaPlayerListener mediaPlayerListener;
    private final HidingThread hidingThread;

    public MediaPlayer(String title, int width, int height) {
        hidingThread = new HidingThread(8);
        dataSaver = new DataSaver("src/main/resources/data/data.conf");
        ICONS = new IconsLoader();
        fileNamePanel = new FileNamePanel();
        mediaPlayerListener = new MediaPlayerListener(dataSaver, fileNamePanel);
        mediaPlayerComponent = new CustomMediaComponent(dataSaver, hidingThread, mediaPlayerListener);
        jFrame = new JFrame(title);
        mediaComponentSettings();
        size = new Dimension(width, height);
        mainPanel = new JPanel();
        panelSettings();
        frameSettings();
    }

    private void mediaComponentSettings() {
        mediaPlayerComponent.mediaPlayer().controls().setRepeat(true);
        mediaPlayerComponent.add(fileNamePanel, BorderLayout.PAGE_END);
        mediaPlayerComponent.mediaPlayer().fullScreen().strategy(
                new AdaptiveFullScreenStrategy(jFrame));
    }

    private void panelSettings() {
        mainPanel.setLayout(new BorderLayout());
        HeaderPanel header = new HeaderPanel(mediaPlayerComponent, jFrame, dataSaver, ICONS);
        mainPanel.add(new ContainerPanel(header), BorderLayout.NORTH);
        header.setSize(new Dimension(size.width, header.getPreferredSize().height));
        mainPanel.add(mediaPlayerComponent, BorderLayout.CENTER);
        JPanel controls = new ControlsPanel(mediaPlayerComponent, mediaPlayerListener, ICONS);
        mainPanel.add(new ContainerPanel(controls), BorderLayout.PAGE_END);
        hidingThread.addShowAction(() -> {
            header.setVisible(true);
            controls.setVisible(true);
            fileNamePanel.setText(dataSaver.getConfiguration().lastPath);
        });
        hidingThread.addHideAction(() -> {
            header.setVisible(false);
            controls.setVisible(false);
            fileNamePanel.setText("");
        });
        CustomMouseListener mouseListener = new CustomMouseListener(hidingThread);
        mainPanel.addMouseMotionListener(mouseListener);
        mainPanel.addMouseListener(mouseListener);
    }

    private void frameSettings() {
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

    public void open() {
        jFrame.setVisible(true);
        hidingThread.start();
        String last = dataSaver.getConfiguration().lastPath;
        if (last != null && !last.equals("")) {
            fileNamePanel.setText(last);
            mediaPlayerComponent.mediaPlayer().media().prepare(last);
            mediaPlayerComponent.mediaPlayer().media().startPaused(last);
        }
    }
}
