package ua.boa.panels;

import ua.boa.CustomMediaComponent;
import ua.boa.DataSaver;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class HeaderPanel extends JPanel {
    private final JFileChooser jFileChooser;
    private final CustomMediaComponent mediaPlayerComponent;
    private final JFrame parent;
    private final DataSaver dataSaver;
    public HeaderPanel(CustomMediaComponent mediaPlayerComponent, JFrame parent, DataSaver dataSaver){
        super(new BorderLayout());
        this.mediaPlayerComponent = mediaPlayerComponent;
        this.parent = parent;
        this.dataSaver = dataSaver;
        jFileChooser = new JFileChooser();
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
        buttons.add(createOpenFileButton());
        buttons.add(createEnterUrlButton());
        add(buttons, BorderLayout.WEST);
    }
    private JButton createOpenFileButton(){
        JButton openFile = new JButton("Open file");
        openFile.addActionListener(l->{
            int returnVal = jFileChooser.showOpenDialog(parent);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                if(file.exists()) {
                    dataSaver.save(file.getPath(), "file");
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
                    null, null, dataSaver.getLastUrl());
            if (url != null && url.length() > 0){
                mediaPlayerComponent.mediaPlayer().media().prepare(url);
                mediaPlayerComponent.playButton();
                dataSaver.save(url, "url");
            }
        });
        return enterURL;
    }
}
