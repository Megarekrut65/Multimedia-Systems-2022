package ua.boa;

import ua.boa.listeners.MediaPlayerListener;
import ua.boa.savers.DataSaver;
import uk.co.caprica.vlcj.media.*;
import uk.co.caprica.vlcj.player.base.State;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class CustomMediaComponent extends EmbeddedMediaPlayerComponent {
    private final DataSaver dataSaver;
    private final MediaPlayerListener mediaPlayerListener;
    private final HidingThread hidingThread;
    private final Map<Integer, Action> keyMap;
    public CustomMediaComponent(DataSaver dataSaver, HidingThread hidingThread, MediaPlayerListener mediaPlayerListener) {
        this.dataSaver = dataSaver;
        this.hidingThread = hidingThread;
        this.mediaPlayerListener = mediaPlayerListener;
        keyMap = new HashMap<>();
        setKeyListener();
        mediaPlayer().events().addMediaPlayerEventListener(mediaPlayerListener);
        if (dataSaver.getConfiguration().pinned) hidingThread.pin();
        mediaPlayer().audio().setVolume(dataSaver.getConfiguration().volume);
    }
    public void addEventToKeyListener(int key, Action action){
        Action act = keyMap.get(key);
        if(act != null){
            keyMap.put(key, ()->{
                act.doAction();
                action.doAction();
            });
            return;
        }
        keyMap.put(key, action);
    }
    private void setKeyListener(){
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if(e.getID() != KeyEvent.KEY_PRESSED) return false;
            System.out.println("Manager: " + e.getKeyCode());
            Action action = keyMap.get(e.getKeyCode());
            if(action != null) action.doAction();
            return false;
        });
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
        mediaPlayer().controls().skipPosition(-0.05f);
        mediaPlayerListener.positionChanged(mediaPlayer(), mediaPlayer().status().position());
    }
    public void forwardButton(){
        hidingThread.moving();
        mediaPlayer().controls().skipPosition(0.05f);
        mediaPlayerListener.positionChanged(mediaPlayer(), mediaPlayer().status().position());
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
