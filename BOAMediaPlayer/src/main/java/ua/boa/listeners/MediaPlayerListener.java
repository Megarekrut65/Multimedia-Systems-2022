package ua.boa.listeners;

import ua.boa.TimeConvector;
import ua.boa.panels.FileNamePanel;
import ua.boa.ToastMessage;
import ua.boa.savers.DataSaver;
import uk.co.caprica.vlcj.media.InfoApi;
import uk.co.caprica.vlcj.media.MediaRef;
import uk.co.caprica.vlcj.media.TrackType;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventListener;

import javax.swing.*;

public class MediaPlayerListener implements MediaPlayerEventListener {
    private JProgressBar currentSlider;
    private JLabel timeLabel;
    private final DataSaver dataSaver;
    private final FileNamePanel fileNamePanel;
    public MediaPlayerListener(DataSaver dataSaver, FileNamePanel fileNamePanel) {
        this.dataSaver = dataSaver;
        this.fileNamePanel = fileNamePanel;
    }


    public void setSlider(JProgressBar slider){
        currentSlider = slider;
    }
    public void setTimeLabel(JLabel label) {
        timeLabel = label;
    }
    @Override
    public void mediaChanged(MediaPlayer mediaPlayer, MediaRef media) {
        SwingUtilities.invokeLater(()->{
            if(currentSlider != null) currentSlider.setValue(0);
            String last = dataSaver.getConfiguration().lastPath;
            if(last != null && !last.equals("")) fileNamePanel.setText(last);
            if(timeLabel == null) return;
            timeLabel.setText(TimeConvector.convert(0) + "/" +
                    TimeConvector.convert(mediaPlayer.status().length()));
        });
    }

    @Override
    public void opening(MediaPlayer mediaPlayer) {
    }

    @Override
    public void buffering(MediaPlayer mediaPlayer, float newCache) {

    }

    @Override
    public void playing(MediaPlayer mediaPlayer) {
    }

    @Override
    public void paused(MediaPlayer mediaPlayer) {

    }

    @Override
    public void stopped(MediaPlayer mediaPlayer) {

    }

    @Override
    public void forward(MediaPlayer mediaPlayer) {

    }

    @Override
    public void backward(MediaPlayer mediaPlayer) {

    }

    @Override
    public void finished(MediaPlayer mediaPlayer) {
        SwingUtilities.invokeLater(()->{
            if(currentSlider != null) currentSlider.setValue(0);});
    }

    @Override
    public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
    }

    @Override
    public void positionChanged(MediaPlayer mediaPlayer, float newPosition) {
        SwingUtilities.invokeLater(()->{
            if(currentSlider != null) currentSlider.setValue((int) (100000*newPosition));
            if(timeLabel == null) return;
            timeLabel.setText(TimeConvector.convert(mediaPlayer.status().time())
                    + "/" + TimeConvector.convert(mediaPlayer.status().length()));
        });
    }

    @Override
    public void seekableChanged(MediaPlayer mediaPlayer, int newSeekable) {

    }

    @Override
    public void pausableChanged(MediaPlayer mediaPlayer, int newPausable) {

    }

    @Override
    public void titleChanged(MediaPlayer mediaPlayer, int newTitle) {

    }

    @Override
    public void snapshotTaken(MediaPlayer mediaPlayer, String filename) {

    }

    @Override
    public void lengthChanged(MediaPlayer mediaPlayer, long newLength) {

    }

    @Override
    public void videoOutput(MediaPlayer mediaPlayer, int newCount) {

    }

    @Override
    public void scrambledChanged(MediaPlayer mediaPlayer, int newScrambled) {

    }

    @Override
    public void elementaryStreamAdded(MediaPlayer mediaPlayer, TrackType type, int id) {

    }

    @Override
    public void elementaryStreamDeleted(MediaPlayer mediaPlayer, TrackType type, int id) {

    }

    @Override
    public void elementaryStreamSelected(MediaPlayer mediaPlayer, TrackType type, int id) {

    }

    @Override
    public void corked(MediaPlayer mediaPlayer, boolean corked) {

    }

    @Override
    public void muted(MediaPlayer mediaPlayer, boolean muted) {

    }

    @Override
    public void volumeChanged(MediaPlayer mediaPlayer, float volume) {

    }

    @Override
    public void audioDeviceChanged(MediaPlayer mediaPlayer, String audioDevice) {

    }

    @Override
    public void chapterChanged(MediaPlayer mediaPlayer, int newChapter) {

    }

    @Override
    public void error(MediaPlayer mediaPlayer) {
        SwingUtilities.invokeLater(() -> {
            ToastMessage toastMessage = new ToastMessage(
                    new ImageIcon(), "Failed to load Media", 4000,
                    currentSlider.getParent().getParent().getParent());//main panel
            toastMessage.display();
        });
    }

    @Override
    public void mediaPlayerReady(MediaPlayer mediaPlayer) {
    }

}
