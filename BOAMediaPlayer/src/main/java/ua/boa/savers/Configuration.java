package ua.boa.savers;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Data class for saving it to file
 */
public class Configuration implements Serializable {
    public String lastPath = "";
    public String lastUrl = "";
    public int volume = 50;
    public boolean pinned = false;
    public ArrayList<String> history = new ArrayList<>();

    public Configuration() {
    }
}
