package BehavioralDP.CommandPattern;

public class DontDoTask implements Command {
    private Task task;

    public DontDoTask(Task task) { // ✅ Proper constructor
        this.task = task;
    }

    @Override
    public void doIt() {
        this.task.dontTask();
    }
}
