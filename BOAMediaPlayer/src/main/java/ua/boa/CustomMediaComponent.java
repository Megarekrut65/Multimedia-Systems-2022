package ua.boa;

import ua.boa.savers.DataSaver;
import uk.co.caprica.vlcj.media.InfoApi;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class CustomMediaComponent extends EmbeddedMediaPlayerComponent {
    private final DataSaver dataSaver;
    private final HidingThread hidingThread;
    public CustomMediaComponent(DataSaver dataSaver, HidingThread hidingThread) {
        this.dataSaver = dataSaver;
        this.hidingThread = hidingThread;
        if (dataSaver.getConfiguration().pinned) hidingThread.pin();
        mediaPlayer().audio().setVolume(dataSaver.getConfiguration().volume);
    }
    public void unpinPanels() {
        hidingThread.unpin();
        dataSaver.getConfiguration().pinned = false;
        dataSaver.save();
    }
    public void pinPanels() {
        hidingThread.pin();
        dataSaver.getConfiguration().pinned = true;
        dataSaver.save();
    }

    public void playButton(){
        mediaPlayer().controls().play();
    }
    public void pauseButton(){
        mediaPlayer().controls().setPause(true);
    }
    public void stopButton(){
        hidingThread.moving();
        mediaPlayer().controls().stop();
        InfoApi info = mediaPlayer().media().info();
        if(info != null) mediaPlayer().media()
                .startPaused(info.mrl());
    }
    public void rewindButton(){
        hidingThread.moving();
        mediaPlayer().controls().skipTime(-1000);
    }
    public void forwardButton(){
        hidingThread.moving();
        mediaPlayer().controls().skipTime(1000);
    }
    public void changePosition(float position){
        hidingThread.moving();
        InfoApi info = mediaPlayer().media().info();
        if(info != null) mediaPlayer().controls()
                .setTime((long) (info.duration() * position));
    }
    public void changeVolume(int value){
        hidingThread.moving();
        mediaPlayer().audio().setVolume(value);
        dataSaver.getConfiguration().volume = value;
        dataSaver.save();
    }
    public int getVolume(){
        return dataSaver.getConfiguration().volume;
    }
}
