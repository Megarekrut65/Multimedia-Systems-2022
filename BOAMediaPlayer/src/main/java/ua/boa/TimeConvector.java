package ua.boa;

/**
 * Class that convert milliseconds time to `hh:mm:ss` time
 */
public class TimeConvector {
    /**
     * @param time - time in milliseconds
     * @return `hh:mm:ss` time string
     */
    public static String convert(long time) {
        long seconds = (time / 1000) % 60,
                minutes = (time / 60000) % 60,
                hours = time / 3600000;
        return makeString(hours) + ":" + makeString(minutes) + ":" + makeString(seconds);
    }

    /**
     * @param value - value of hours, minutes or seconds
     * @return string with length 2
     */
    private static String makeString(long value) {
        if (value < 0) value = 0;
        String res = String.valueOf(value);
        if (res.length() < 2) res = "0" + res;
        return res;
    }
}
