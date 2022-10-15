package ua.boa.listeners;

import ua.boa.DataSaver;
import ua.boa.ToastMessage;
import uk.co.caprica.vlcj.media.InfoApi;
import uk.co.caprica.vlcj.media.MediaRef;
import uk.co.caprica.vlcj.media.TrackType;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventListener;

import javax.swing.*;

public class MediaPlayerListener implements MediaPlayerEventListener {
    private JSlider currentSlider;
    private final DataSaver dataSaver;
    public MediaPlayerListener(DataSaver dataSaver) {
        this.dataSaver = dataSaver;
    }


    public void setSlider(JSlider slider){
        currentSlider = slider;
    }
    @Override
    public void mediaChanged(MediaPlayer mediaPlayer, MediaRef media) {
        SwingUtilities.invokeLater(()->{
            if(currentSlider != null) currentSlider.setValue(0);
            String last = dataSaver.getLastPath();
            if(last != null && !last.equals("")){
                mediaPlayer.marquee().setText(last);
            }
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
    }

    @Override
    public void timeChanged(MediaPlayer mediaPlayer, long newTime) {

    }

    @Override
    public void positionChanged(MediaPlayer mediaPlayer, float newPosition) {
        SwingUtilities.invokeLater(()->currentSlider.setValue((int) (100*newPosition)));
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
                    new ImageIcon(), "Failed to load Media", 4000, currentSlider);
            toastMessage.display();
        });
    }

    @Override
    public void mediaPlayerReady(MediaPlayer mediaPlayer) {
    }
}
