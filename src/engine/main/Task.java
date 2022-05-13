package engine.main;

public class Task {

    private int taskId;
    private String taskName;
    private long taskDuration;
    private long runningDuration;

    public Task(int taskId, String taskName, long taskDuration) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDuration = taskDuration;
        runningDuration = 0;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public long getTaskDuration() {
        return taskDuration;
    }

    public void setTaskDuration(long taskDuration) {
        this.taskDuration = taskDuration;
    }

    public long getRunningDuration() {
        return runningDuration;
    }

    public void setRunningDuration(long runningDuration) {
        this.runningDuration = runningDuration;
    }
}
