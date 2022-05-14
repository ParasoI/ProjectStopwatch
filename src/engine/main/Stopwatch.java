package engine.main;


import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch {

    private long elapsed;
    private long currentStart;
    private long currentEnd;
    private boolean isPlaying;

    public Stopwatch() {
        elapsed = 0;
        currentStart = 0;
        currentEnd = 0;
        isPlaying = false;
    }


    public long getElapsed() {
        return elapsed;
    }

    public void setElapsed(long elapsed) {
        this.elapsed = elapsed;
    }

    public long getCurrentStart() {
        return currentStart;
    }

    public void setCurrentStart(long currentStart) {
        this.currentStart = currentStart;
    }

    public long getCurrentEnd() {
        return currentEnd;
    }

    public void setCurrentEnd(long currentEnd) {
        this.currentEnd = currentEnd;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
