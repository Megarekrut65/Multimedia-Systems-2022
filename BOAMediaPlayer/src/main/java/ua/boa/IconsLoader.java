package ua.boa;

import javax.swing.*;
import java.util.Objects;

public class IconsLoader {
    public final ImageIcon ICON;
    public final ImageIcon PLAY_ICON;
    public final ImageIcon PAUSE_ICON;
    public final ImageIcon STOP_ICON;
    public final ImageIcon FORWARD_ICON;
    public final ImageIcon REWIND_ICON;
    public final ImageIcon VOLUME_ON_ICON;
    public final ImageIcon VOLUME_OFF_ICON;
    public final ImageIcon PIN_ICON;
    public final ImageIcon UNPIN_ICON;
    public IconsLoader(){
        ICON = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/icon.png")));
        PLAY_ICON = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/play.png")));
        PAUSE_ICON = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/pause.png")));
        STOP_ICON = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/stop.png")));
        FORWARD_ICON = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/forward.png")));
        REWIND_ICON = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/rewind.png")));
        VOLUME_ON_ICON = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/audio.png")));
        VOLUME_OFF_ICON = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/silence.png")));
        PIN_ICON = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/pin.png")));
        UNPIN_ICON = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/unpin.png")));
    }
}
