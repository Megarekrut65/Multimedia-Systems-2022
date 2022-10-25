package ua.boa;

import ua.boa.listeners.MediaPlayerListener;
import ua.boa.savers.DataSaver;
import uk.co.caprica.vlcj.media.InfoApi;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Media component for controls media
 */
public class CustomMediaComponent extends EmbeddedMediaPlayerComponent {
    private final DataSaver dataSaver;/*Class for saving app data to file*/
    private final MediaPlayerListener mediaPlayerListener;/*Listener of media events*/
    private final HidingThread hidingThread;/*Class for hiding panels*/
    private final KeyBoardController keyBoardController;/*Controller for adding actions to key(keyboard key)*/

    public CustomMediaComponent(DataSaver dataSaver, HidingThread hidingThread, MediaPlayerListener mediaPlayerListener) {
        this.dataSaver = dataSaver;
        this.hidingThread = hidingThread;
        this.mediaPlayerListener = mediaPlayerListener;
        keyBoardController = new KeyBoardController(hidingThread);
        mediaPlayer().events().addMediaPlayerEventListener(mediaPlayerListener);
        if (dataSaver.getConfiguration().pinned) hidingThread.pin();
        mediaPlayer().audio().setVolume(dataSaver.getConfiguration().volume);
        setKeys();
        videoSurfaceComponent().addMouseListener(createListener());
    }

    /**
     * Creates listener for mouse click and mouse double click
     */
    private MouseListener createListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1) {
                    mediaPlayer().fullScreen().set(!mediaPlayer().fullScreen().isFullScreen());
                    mediaPlayer().controls().pause();
                }
                if (event.getClickCount() == 1 && event.getButton() == MouseEvent.BUTTON1) {
                    mediaPlayer().controls().pause();
                }
            }
        };
    }

    /**
     * Added action to key pressed event. When action for this key is already existed then creates complex action.
     *
     * @param key    - keyboard key code
     * @param action - action to call it when key is pressed
     */
    public void addEventToKeyListener(int key, Action action) {
        keyBoardController.addEventToKeyListener(key, action);
    }

    /**
     * Adds to key map default keys and actions
     */
    private void setKeys() {
        addEventToKeyListener(32/*SPACE*/, () -> mediaPlayer().controls().pause());
        addEventToKeyListener(37/*LEFT*/, this::rewindButton);
        addEventToKeyListener(39/*RIGHT*/, this::forwardButton);
        addEventToKeyListener(27/*ESC*/, () -> mediaPlayer().fullScreen().set(false));
        addEventToKeyListener(83/*S*/, this::stopButton);
    }

    /**
     * Unpins panels and save this state to file
     */
    public void unpinPanels() {
        hidingThread.unpin();
        dataSaver.getConfiguration().pinned = false;
        dataSaver.save();
    }

    /**
     * Pins panels and save this state to file
     */
    public void pinPanels() {
        hidingThread.pin();
        dataSaver.getConfiguration().pinned = true;
        dataSaver.save();
    }

    /**
     * Plays media
     */
    public void playButton() {
        mediaPlayer().controls().play();
    }

    /**
     * Pauses media
     */
    public void pauseButton() {
        mediaPlayer().controls().setPause(true);
    }

    /**
     * Stops media
     */
    public void stopButton() {
        mediaPlayer().controls().stop();
        InfoApi info = mediaPlayer().media().info();
        if (info != null) mediaPlayer().media()
                .startPaused(info.mrl());
    }

    /**
     * Rewinds media position for 5%
     */
    public void rewindButton() {
        hidingThread.moving();
        mediaPlayer().controls().skipPosition(-0.05f);
        mediaPlayerListener.positionChanged(mediaPlayer(), mediaPlayer().status().position());
    }

    /**
     * Forwards media position for 5%
     */
    public void forwardButton() {
        hidingThread.moving();
        mediaPlayer().controls().skipPosition(0.05f);
        mediaPlayerListener.positionChanged(mediaPlayer(), mediaPlayer().status().position());
    }

    /**
     * Changes media position
     *
     * @param position - new media position
     */
    public void changePosition(float position) {
        hidingThread.moving();
        InfoApi info = mediaPlayer().media().info();
        if (info != null) mediaPlayer().controls()
                .setTime((long) (info.duration() * position));
    }

    /**
     * Changes volume value and save it to file
     *
     * @param value - new volume value
     */
    public void changeVolume(int value) {
        hidingThread.moving();
        mediaPlayer().audio().setVolume(value);
        dataSaver.getConfiguration().volume = value;
        dataSaver.save();
    }

    /**
     * @return value of volume
     */
    public int getVolume() {
        return dataSaver.getConfiguration().volume;
    }
}
