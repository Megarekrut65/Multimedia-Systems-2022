package ua.boa.panels;

import ua.boa.CustomButton;
import ua.boa.CustomMediaComponent;
import ua.boa.IconsLoader;
import ua.boa.NoFocusableButton;
import ua.boa.savers.DataSaver;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class HeaderPanel extends JPanel {
    private final JFileChooser jFileChooser;
    private final CustomMediaComponent mediaPlayerComponent;
    private final JFrame parent;
    private final DataSaver dataSaver;
    private final IconsLoader iconsLoader;
    public HeaderPanel(CustomMediaComponent mediaPlayerComponent, JFrame parent,
                       DataSaver dataSaver, IconsLoader icons){
        super(new BorderLayout());
        this.mediaPlayerComponent = mediaPlayerComponent;
        this.parent = parent;
        this.dataSaver = dataSaver;
        this.iconsLoader = icons;
        jFileChooser = new JFileChooser();
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
        buttons.add(createOpenFileButton());
        buttons.add(createEnterUrlButton());
        buttons.add(createHistoryButton());
        add(buttons, BorderLayout.WEST);
        add(createPinButton(), BorderLayout.EAST);
    }
    private JButton createPinButton(){
        CustomButton pin = new CustomButton(dataSaver.getConfiguration().pinned
                ?iconsLoader.UNPIN_ICON
                :iconsLoader.PIN_ICON, 20,20);
        AtomicBoolean pinned = new AtomicBoolean(dataSaver.getConfiguration().pinned);
        pin.addActionListener(l->{
            pinned.set(!pinned.get());
            if(pinned.get()){
                mediaPlayerComponent.pinPanels();
                pin.setImage(iconsLoader.UNPIN_ICON);
                return;
            }
            mediaPlayerComponent.unpinPanels();
            pin.setImage(iconsLoader.PIN_ICON);
        });
        return pin;
    }
    private JButton createHistoryButton(){
        JButton history = new NoFocusableButton("History");
        history.addActionListener(l->{
            String path = (String) JOptionPane.showInputDialog(
                    parent,
                    "Select file:\n",
                    parent.getTitle(),
                    JOptionPane.PLAIN_MESSAGE,
                    null, dataSaver.getConfiguration().history.toArray(), 0);
            if (path != null && path.length() > 0){
                mediaPlayerComponent.mediaPlayer().media().prepare(path);
                mediaPlayerComponent.mediaPlayer().media().startPaused(path);
                mediaPlayerComponent.mediaPlayer().controls().start();
                dataSaver.getConfiguration().lastPath = path;
                dataSaver.addHistory(path);
                dataSaver.save();
            }
        });
        return history;
    }
    private JButton createOpenFileButton(){
        JButton openFile = new NoFocusableButton("Open file");
        openFile.addActionListener(l->{
            int returnVal = jFileChooser.showOpenDialog(parent);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                if(file.exists()) {
                    dataSaver.getConfiguration().lastPath = file.getPath();
                    dataSaver.addHistory(file.getPath());
                    dataSaver.save();
                    mediaPlayerComponent.mediaPlayer().media().startPaused(file.getPath());
                }
            }
        });
        return openFile;
    }
    private JButton createEnterUrlButton(){
        JButton enterURL = new NoFocusableButton("Stream from url");
        enterURL.addActionListener(l->{
            String url = (String) JOptionPane.showInputDialog(
                    parent,
                    "Enter m3u url:\n",
                    parent.getTitle(),
                    JOptionPane.PLAIN_MESSAGE,
                    null, null, dataSaver.getConfiguration().lastUrl);
            if (url != null && url.length() > 0){
                mediaPlayerComponent.mediaPlayer().media().prepare(url);
                mediaPlayerComponent.playButton();
                dataSaver.getConfiguration().lastPath = url;
                dataSaver.getConfiguration().lastUrl = url;
                dataSaver.addHistory(url);
                dataSaver.save();
            }
        });
        return enterURL;
    }
}
