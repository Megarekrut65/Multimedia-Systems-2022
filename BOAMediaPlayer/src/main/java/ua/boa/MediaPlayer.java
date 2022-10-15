package ua.boa;

import ua.boa.listeners.CustomMouseListener;
import ua.boa.listeners.MediaPlayerListener;
import ua.boa.panels.ContainerPanel;
import ua.boa.panels.ControlsPanel;
import ua.boa.panels.HeaderPanel;
import uk.co.caprica.vlcj.media.MediaEventListener;
import uk.co.caprica.vlcj.player.base.Marquee;
import uk.co.caprica.vlcj.player.base.MarqueePosition;
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
    private final DataSaver dataSaver;
    private final Marquee marquee;
    private final MediaPlayerListener mediaPlayerListener;

    public MediaPlayer(String title, int width, int height){
        dataSaver = new DataSaver("src/main/resources/data/last.txt");
        mediaPlayerComponent = new CustomMediaComponent();
        mediaPlayerComponent.mediaPlayer().controls().setRepeat(true);
        mediaPlayerListener = new MediaPlayerListener(dataSaver);
        mediaPlayerComponent.mediaPlayer().events().addMediaPlayerEventListener(mediaPlayerListener);
        marquee = Marquee.marquee();
        size = new Dimension(width, height);
        mainPanel = new JPanel();
        jFrame = new JFrame(title);
        panelSettings();
        frameSettings();
        marqueeSettings();
    }
    private void marqueeSettings(){
        marquee.size(40)
                .colour(Color.WHITE)
                .position(MarqueePosition.BOTTOM_RIGHT)
                .opacity(0.5f)
                .enable();
    }
    private void panelSettings(){
        mainPanel.setLayout(new BorderLayout());
        JPanel header = new HeaderPanel(mediaPlayerComponent, jFrame, dataSaver);
        mainPanel.add(new ContainerPanel(header), BorderLayout.NORTH);
        header.setSize(new Dimension(size.width, header.getPreferredSize().height));
        mainPanel.add(mediaPlayerComponent, BorderLayout.CENTER);
        JPanel controls = new ControlsPanel(mediaPlayerComponent, mediaPlayerListener, ICONS);
        mainPanel.add(new ContainerPanel(controls), BorderLayout.PAGE_END);
        CustomMouseListener mouseListener = new CustomMouseListener(()->{
            header.setVisible(false);
            controls.setVisible(false);
            mediaPlayerComponent.mediaPlayer().marquee().set(marquee.text(""));
        }, ()->{
            header.setVisible(true);
            controls.setVisible(true);
            mediaPlayerComponent.mediaPlayer().marquee().set(marquee.text(dataSaver.getLastPath()));
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
        String last = dataSaver.getLastPath();
        if(last != null && !last.equals("")){
            ToastMessage toastMessage = new ToastMessage(ICONS.ICON, last, 3000, jFrame);
            toastMessage.display();
            mediaPlayerComponent.mediaPlayer().marquee().set(marquee.text(last));
            mediaPlayerComponent.mediaPlayer().media().prepare(last);
            mediaPlayerComponent.mediaPlayer().media().startPaused(last);
        }
    }
}
