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
        ICON = new ImageIcon("src/main/resources/images/icon.png");
        PLAY_ICON = new ImageIcon("src/main/resources/images/play.png");
        PAUSE_ICON = new ImageIcon("src/main/resources/images/pause.png");
        STOP_ICON = new ImageIcon("src/main/resources/images/stop.png");
        FORWARD_ICON = new ImageIcon("src/main/resources/images/forward.png");
        REWIND_ICON = new ImageIcon("src/main/resources/images/rewind.png");
        VOLUME_ON_ICON = new ImageIcon("src/main/resources/images/audio.png");
        VOLUME_OFF_ICON = new ImageIcon("src/main/resources/images/silence.png");
        PIN_ICON = new ImageIcon("src/main/resources/images/pin.png");
        UNPIN_ICON = new ImageIcon("src/main/resources/images/unpin.png");
    }
}
