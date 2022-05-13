package engine.main;


import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch {

    private long elapsed;
    private long currentStart;
    private long currentEnd;
    private JLabel label;
    private boolean isPlaying;
    private Task task;

    public Stopwatch() {
        elapsed = 0;
        currentStart = 0;
        currentEnd = 0;
        isPlaying = false;
    }

    public void pauseTime(){

        isPlaying = false;

        currentEnd = System.currentTimeMillis();
        elapsed += (currentEnd - currentStart) / 1000;
    };

    public void playTime() throws InterruptedException {

        isPlaying = true;

        currentStart = System.currentTimeMillis();

        SwingWorker swingWorker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {

                while(isPlaying){
                    Long elapsedSeconds = task.getTaskDuration() + elapsed + ((System.currentTimeMillis() - currentStart ) / 1000);
                    label.setText(SecondsUtils.toLabel(elapsedSeconds));
                    Thread.sleep(1000);
                }

                return null;
            }
        };

        swingWorker.execute();

    }

    public void saveTime(){

        task.setTaskDuration(task.getTaskDuration() + elapsed);
        resetTime();
    }

    public void resetTime(){

        elapsed = 0;
        currentStart = 0;
        currentEnd = 0;
        label.setText(SecondsUtils.toLabel(task.getTaskDuration()));
        isPlaying = false;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
