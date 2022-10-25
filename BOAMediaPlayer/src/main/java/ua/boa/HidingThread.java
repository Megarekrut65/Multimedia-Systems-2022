package ua.boa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class for hiding panels after fixed time ran out
 */
public class HidingThread extends Thread {
    private final AtomicInteger time;/*Current time that is running out*/
    private final int timeToHide;/*Fixed time after it ran out panels will be hidden*/
    private final List<Action> hide;/*List of action when time ran out*/
    private final List<Action> show;/*List of action when time is start to run out*/
    private final AtomicBoolean pinned;/*Time doesn't run out when it is true*/

    public HidingThread(int timeToHide) {
        hide = new ArrayList<>();
        show = new ArrayList<>();
        pinned = new AtomicBoolean(false);
        this.time = new AtomicInteger(timeToHide);
        this.timeToHide = timeToHide;
    }

    /**
     * @param hide - action to call when time ran out
     */
    public void addHideAction(Action hide) {
        this.hide.add(hide);
    }

    /**
     * @param show - action to call when time starts to ran out
     */
    public void addShowAction(Action show) {
        this.show.add(show);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
                if (time.get() > 0) time.decrementAndGet();
                if (time.get() == 0) {
                    if (!pinned.get()) hide.forEach(Action::doAction);
                    synchronized (time) {
                        time.wait();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * When mouse moved time is reassigned to max time
     */
    public void moving() {
        synchronized (time) {
            if (time.get() <= 0) show.forEach(Action::doAction);
            time.set(timeToHide);
            time.notify();
        }
    }

    public void pin() {
        pinned.set(true);
    }

    public void unpin() {
        pinned.set(false);
        moving();
    }
}
