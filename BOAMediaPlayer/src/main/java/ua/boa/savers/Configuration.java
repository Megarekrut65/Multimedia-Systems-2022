package ua.boa.savers;

import java.io.Serializable;

public class Configuration implements Serializable {
    public String lastPath = "";
    public String lastUrl = "";
    public int volume = 50;
    public boolean pinned = false;

    public Configuration(String lastPath, String lastUrl, int volume, boolean pinned) {
        this.lastPath = lastPath;
        this.lastUrl = lastUrl;
        this.volume = volume;
        this.pinned = pinned;
    }

    public Configuration() {
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "lastPath='" + lastPath + '\'' +
                ", lastUrl='" + lastUrl + '\'' +
                ", volume=" + volume +
                ", pinned=" + pinned +
                '}';
    }
}
