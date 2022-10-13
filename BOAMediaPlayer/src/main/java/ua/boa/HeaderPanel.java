package ua.boa;

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class HeaderPanel extends JPanel {
    private final JFileChooser jFileChooser;
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private final JFrame parent;
    public HeaderPanel(EmbeddedMediaPlayerComponent mediaPlayerComponent, JFrame parent){
        this.mediaPlayerComponent = mediaPlayerComponent;
        this.parent = parent;
        jFileChooser = new JFileChooser();
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        add(createOpenFileButton());
        add(createEnterUrlButton());
    }
    private JButton createOpenFileButton(){
        JButton openFile = new JButton("Open file");
        openFile.addActionListener(l->{
            int returnVal = jFileChooser.showOpenDialog(parent);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                if(file.exists()) {
                    mediaPlayerComponent.mediaPlayer().controls().stop();
                    mediaPlayerComponent.mediaPlayer().media().prepare(file.getPath());
                    mediaPlayerComponent.mediaPlayer().media().startPaused(file.getPath());
                }
            }
        });
        return openFile;
    }
    private JButton createEnterUrlButton(){
        JButton enterURL = new JButton("Stream from url");
        enterURL.addActionListener(l->{
            String url = (String) JOptionPane.showInputDialog(
                    parent,
                    "Enter m3u url:\n",
                    parent.getTitle(),
                    JOptionPane.PLAIN_MESSAGE,
                    null, null, "");
            if (url != null && url.length() > 0){
                mediaPlayerComponent.mediaPlayer().controls().stop();
                mediaPlayerComponent.mediaPlayer().media().prepare(url);
                mediaPlayerComponent.mediaPlayer().controls().play();
            }
        });
        return enterURL;
    }
}
