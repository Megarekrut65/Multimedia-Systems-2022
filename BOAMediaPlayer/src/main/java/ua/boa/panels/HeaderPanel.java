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

/**
 * Panel with header buttons.
 */
public class HeaderPanel extends JPanel {
    private final JFileChooser jFileChooser;/*Class for choosing local file*/
    private final CustomMediaComponent mediaPlayerComponent;/*Component to control media*/
    private final JFrame parent;/*Main frame*/
    private final DataSaver dataSaver;/*Class to saving app configuration and other data*/
    private final IconsLoader iconsLoader;/*Icons for buttons*/

    public HeaderPanel(CustomMediaComponent mediaPlayerComponent, JFrame parent,
                       DataSaver dataSaver, IconsLoader icons) {
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

    /**
     * Creates button for pin and unpin panels
     */
    private JButton createPinButton() {
        CustomButton pin = new CustomButton(dataSaver.getConfiguration().pinned
                ? iconsLoader.UNPIN_ICON
                : iconsLoader.PIN_ICON, 20, 20);
        pin.setToolTipText("Pin/unpin: P");
        AtomicBoolean pinned = new AtomicBoolean(dataSaver.getConfiguration().pinned);
        pin.addActionListener(l -> {
            pinned.set(!pinned.get());
            if (pinned.get()) {
                mediaPlayerComponent.pinPanels();
                pin.setImage(iconsLoader.UNPIN_ICON);
                return;
            }
            mediaPlayerComponent.unpinPanels();
            pin.setImage(iconsLoader.PIN_ICON);
        });
        mediaPlayerComponent.addEventToKeyListener(80/*P*/, pin::doClick);
        return pin;
    }

    /**
     * Creates button that open history of opened files and urls
     */
    private JButton createHistoryButton() {
        JButton history = new NoFocusableButton("History");
        history.setToolTipText("Open history: H");
        history.addActionListener(l -> {
            String path = (String) JOptionPane.showInputDialog(
                    parent,
                    "Select file:\n",
                    parent.getTitle(),
                    JOptionPane.PLAIN_MESSAGE,
                    null, dataSaver.getConfiguration().history.toArray(), 0);
            if (path != null && path.length() > 0) {
                mediaPlayerComponent.mediaPlayer().media().prepare(path);
                mediaPlayerComponent.mediaPlayer().media().startPaused(path);
                mediaPlayerComponent.mediaPlayer().controls().start();
                dataSaver.getConfiguration().lastPath = path;
                dataSaver.addHistory(path);
                dataSaver.save();
            }
        });
        mediaPlayerComponent.addEventToKeyListener(72/*H*/, history::doClick);
        return history;
    }

    /**
     * Creates button that open local file
     */
    private JButton createOpenFileButton() {
        JButton openFile = new NoFocusableButton("Open file");
        openFile.setToolTipText("Open file: F");
        openFile.addActionListener(l -> {
            int returnVal = jFileChooser.showOpenDialog(parent);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                if (file.exists()) {
                    dataSaver.getConfiguration().lastPath = file.getPath();
                    dataSaver.addHistory(file.getPath());
                    dataSaver.save();
                    mediaPlayerComponent.mediaPlayer().media().startPaused(file.getPath());
                }
            }
        });
        mediaPlayerComponent.addEventToKeyListener(70/*F*/, openFile::doClick);
        return openFile;
    }

    /**
     * Creates button that open url with streaming video or audio
     */
    private JButton createEnterUrlButton() {
        JButton enterURL = new NoFocusableButton("Stream from url");
        enterURL.setToolTipText("Open url: U");
        enterURL.addActionListener(l -> {
            String url = (String) JOptionPane.showInputDialog(
                    parent,
                    "Enter media url:\n",
                    parent.getTitle(),
                    JOptionPane.PLAIN_MESSAGE,
                    null, null, dataSaver.getConfiguration().lastUrl);
            if (url != null && url.length() > 0) {
                mediaPlayerComponent.mediaPlayer().media().prepare(url);
                mediaPlayerComponent.playButton();
                dataSaver.getConfiguration().lastPath = url;
                dataSaver.getConfiguration().lastUrl = url;
                dataSaver.addHistory(url);
                dataSaver.save();
            }
        });
        mediaPlayerComponent.addEventToKeyListener(85/*U*/, enterURL::doClick);
        return enterURL;
    }
}
