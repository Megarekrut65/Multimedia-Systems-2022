package ua.boa.listeners;

import ua.boa.TimeConvector;
import ua.boa.ToastMessage;
import ua.boa.panels.FileNamePanel;
import ua.boa.savers.DataSaver;
import uk.co.caprica.vlcj.media.MediaRef;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

import javax.swing.*;

public class MediaPlayerListener extends MediaPlayerEventAdapter {
    private JProgressBar currentSlider;/*Slider for displaying current position of media*/
    private JLabel timeLabel;/*Label for displaying duration and current time of media*/
    private final DataSaver dataSaver;/*Saves app configuration and other data to file*/
    private final FileNamePanel fileNamePanel;/*Panel for displaying name and path of current file*/

    public MediaPlayerListener(DataSaver dataSaver, FileNamePanel fileNamePanel) {
        this.dataSaver = dataSaver;
        this.fileNamePanel = fileNamePanel;
    }

    /**
     * @param slider - slider for displaying current position of media
     */
    public void setSlider(JProgressBar slider) {
        currentSlider = slider;
    }

    /**
     * @param label - label for displaying duration and current time of media
     */
    public void setTimeLabel(JLabel label) {
        timeLabel = label;
    }

    /**
     * When media is changed this method sets position of slider to 0 and changes media duration and current time.
     * Also displaying of file name will be changed.
     */
    @Override
    public void mediaChanged(MediaPlayer mediaPlayer, MediaRef media) {
        SwingUtilities.invokeLater(() -> {
            if (currentSlider != null) currentSlider.setValue(0);
            String last = dataSaver.getConfiguration().lastPath;
            if (last != null && !last.equals("")) fileNamePanel.setText(last);
            if (timeLabel == null) return;
            timeLabel.setText(TimeConvector.convert(0) + "/" +
                    TimeConvector.convert(mediaPlayer.status().length()));
        });
    }

    /**
     * When media is finished this method sets position of slider to 0.
     */
    @Override
    public void finished(MediaPlayer mediaPlayer) {
        SwingUtilities.invokeLater(() -> {
            if (currentSlider != null) currentSlider.setValue(0);
        });
    }

    /**
     * Every tick changed position of slider and current time of media
     */
    @Override
    public void positionChanged(MediaPlayer mediaPlayer, float newPosition) {
        SwingUtilities.invokeLater(() -> {
            if (currentSlider != null) currentSlider.setValue((int) (100000 * newPosition));
            if (timeLabel == null) return;
            timeLabel.setText(TimeConvector.convert(mediaPlayer.status().time())
                    + "/" + TimeConvector.convert(mediaPlayer.status().length()));
        });
    }

    /**
     * Displays error message when media can't be loaded
     */
    @Override
    public void error(MediaPlayer mediaPlayer) {
        SwingUtilities.invokeLater(() -> {
            ToastMessage toastMessage = new ToastMessage(
                    new ImageIcon(), "Failed to load Media", 4000,
                    currentSlider.getParent().getParent().getParent());//main panel
            toastMessage.display();
        });
    }
}
