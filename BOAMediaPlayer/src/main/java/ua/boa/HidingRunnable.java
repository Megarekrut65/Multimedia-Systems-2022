package ua.boa;

import java.util.concurrent.atomic.AtomicInteger;

public class HidingRunnable implements Runnable{
    private final AtomicInteger time;
    private final int timeToHide;
    private final Action hide;
    private final Action show;

    public HidingRunnable(Action hide,Action show, int timeToHide) {
        this.hide = hide;
        this.show = show;
        this.time = new AtomicInteger(timeToHide);
        this.timeToHide = timeToHide;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
                if (time.get() > 0) time.decrementAndGet();
                if (time.get() == 0) {
                    hide.doAction();
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
            if(time.get() <= 0) show.doAction();
            time.set(timeToHide);
            time.notify();
        }
    }
    public void pressed(){
        synchronized (time){
            if(time.get() <= 0) show.doAction();
            time.set(timeToHide);
            time.notify();
        }
    }
}
