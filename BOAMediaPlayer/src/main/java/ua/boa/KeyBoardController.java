package ua.boa;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for adding actions to key events
 */
public class KeyBoardController {
    private final Map<Integer, Action> keyMap;/*Map with keys and actions for keys(keyboard keys)*/
    private final HidingThread hidingThread;/*Class for hiding panels*/

    public KeyBoardController(HidingThread hidingThread) {
        this.hidingThread = hidingThread;
        keyMap = new HashMap<>();
        setKeyListener();
    }

    /**
     * Added action to key pressed event. When action for this key is already existed then creates complex action.
     *
     * @param key    - keyboard key code
     * @param action - action to call it when key is pressed
     */
    public void addEventToKeyListener(int key, Action action) {
        Action act = keyMap.get(key);
        if (act != null) {
            keyMap.put(key, () -> {
                act.doAction();
                action.doAction();
            });
            return;
        }
        keyMap.put(key, action);
    }

    /**
     * Adds this controller as key event listener to KeyboardFocusManager.
     * When key is pressed calls action from map for this key. If there isn't action than do nothing
     */
    private void setKeyListener() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            hidingThread.moving();
            if (e.getID() != KeyEvent.KEY_PRESSED) return false;
            Action action = keyMap.get(e.getKeyCode());
            if (action != null) action.doAction();
            return false;
        });
    }
}
