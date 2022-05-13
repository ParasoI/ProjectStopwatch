package engine.main;

public class SecondsUtils {

    public static int toDays(long secondsDB){
        int days = 0;

        days = (int)(secondsDB / 60 / 60 / 24);
        return days;
    }

    public static int toHours(long secondsDB){
        int hours = 0;

        hours = (int)(secondsDB / 60 / 60 % 24);
        return hours;
    }

    public static int toMinutes(long secondsDB){
        int minutes = 0;

        minutes = (int)(secondsDB / 60 % 60);
        return minutes;
    }

    public static int toSeconds(long secondsDB){
        int seconds = 0;

        seconds = (int)(secondsDB % 60);
        return seconds;
    }

    public static String toLabel(long secondsDB){
        String label = " " + toDays(secondsDB) + "d - " + toHours(secondsDB) + "hr - " + toMinutes(secondsDB) + "min - " + toSeconds(secondsDB) + "sec";
        return label;
    }

}
