package ua.boa;

import ua.boa.listeners.MediaPlayerListener;
import ua.boa.savers.DataSaver;
import uk.co.caprica.vlcj.media.*;
import uk.co.caprica.vlcj.player.base.State;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import java.awt.*;
import java.awt.event.*;
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
        setKeys();
        videoSurfaceComponent().addMouseListener(createListener());
    }
    private MouseListener createListener(){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1) {
                    mediaPlayer().fullScreen().set(!mediaPlayer().fullScreen().isFullScreen());
                    mediaPlayer().controls().pause();
                }
                if(event.getClickCount() == 1&& event.getButton() == MouseEvent.BUTTON1){
                    mediaPlayer().controls().pause();
                }
            }
        };
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
    private void setKeys(){
        keyMap.put(32/*SPACE*/, ()->mediaPlayer().controls().pause());
        keyMap.put(37/*LEFT*/, this::rewindButton);
        keyMap.put(39/*RIGHT*/, this::forwardButton);
        keyMap.put(27/*RIGHT*/, ()->mediaPlayer().fullScreen().set(false));
        keyMap.put(83/*S*/, ()->mediaPlayer().controls().stop());
    }
    private void setKeyListener(){
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            hidingThread.moving();
            if(e.getID() != KeyEvent.KEY_PRESSED) return false;
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
