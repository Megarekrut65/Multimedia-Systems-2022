package ua.boa;

import uk.co.caprica.vlcj.media.InfoApi;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class CustomMediaComponent extends EmbeddedMediaPlayerComponent {
    public void playButton(){
        mediaPlayer().controls().play();
    }
    public void pauseButton(){
        mediaPlayer().controls().setPause(true);
    }
    public void stopButton(){
        mediaPlayer().controls().stop();
        InfoApi info = mediaPlayer().media().info();
        if(info != null) mediaPlayer().media()
                .startPaused(info.mrl());
    }
    public void rewindButton(){
        mediaPlayer().controls().skipTime(-1000);
    }
    public void forwardButton(){
        mediaPlayer().controls().skipTime(1000);
    }
    public void changePosition(float position){
        InfoApi info = mediaPlayer().media().info();
        if(info != null) mediaPlayer().controls()
                .setTime((long) (info.duration() * position));
    }
    public void changeVolume(int value){
        mediaPlayer().audio().setVolume(value);
    }
    public void mouseMove(){

    }
}
