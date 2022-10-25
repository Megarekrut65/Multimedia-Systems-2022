package ua.boa;

public class TimeConvector {
    public static String convert(long time) {
        long seconds = (time / 1000) % 60,
                minutes = (time / 60000) % 60,
                hours = time / 3600000;
        return makeString(hours) + ":" + makeString(minutes) + ":" + makeString(seconds);
    }

    private static String makeString(long value) {
        if (value < 0) value = 0;
        String res = String.valueOf(value);
        if (res.length() < 2) res = "0" + res;
        return res;
    }
}
