package ua.boa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class HidingThread extends Thread{
    private final AtomicInteger time;
    private final int timeToHide;
    private final List<Action> hide;
    private final List<Action> show;
    private final AtomicBoolean pinned;

    public HidingThread(int timeToHide) {
        hide = new ArrayList<>();
        show = new ArrayList<>();
        pinned = new AtomicBoolean(false);
        this.time = new AtomicInteger(timeToHide);
        this.timeToHide = timeToHide;
    }
    public void addHideAction(Action hide){
        this.hide.add(hide);
    }
    public void addShowAction(Action show){
        this.show.add(show);
    }
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
                if (time.get() > 0) time.decrementAndGet();
                if (time.get() == 0) {
                    if(!pinned.get()) hide.forEach(Action::doAction);
                    synchronized (time){
                        time.wait();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void moving(){
        synchronized (time){
            if(time.get() <= 0) show.forEach(Action::doAction);
            time.set(timeToHide);
            time.notify();
        }
    }
    public void pin(){
        pinned.set(true);
    }
    public void unpin(){
        pinned.set(false);
        moving();
    }
}
