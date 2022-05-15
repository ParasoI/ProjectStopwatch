package engine.main;

public class SecondsUtils {

    public static int MINUTES = 60;
    public static int HOURS = 60;
    public static int DAY = 24;

    public static int toDays(long secondsDB){
        int days = 0;

        days = (int)(secondsDB / MINUTES / HOURS / DAY);
        return days;
    }

    public static int toHours(long secondsDB){
        int hours = 0;

        hours = (int)(secondsDB / MINUTES / HOURS % DAY);
        return hours;
    }

    public static int toMinutes(long secondsDB){
        int minutes = 0;

        minutes = (int)(secondsDB / MINUTES % HOURS);
        return minutes;
    }

    public static int toSeconds(long secondsDB){
        int seconds = 0;

        seconds = (int)(secondsDB % MINUTES);
        return seconds;
    }

    public static String toLabel(long secondsDB){
        String label = " " + toDays(secondsDB) + "d - " + toHours(secondsDB) + "hr - " + toMinutes(secondsDB) + "min - " + toSeconds(secondsDB) + "sec";
        return label;
    }
}
