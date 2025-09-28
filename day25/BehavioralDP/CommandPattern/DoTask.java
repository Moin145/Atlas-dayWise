package BehavioralDP.CommandPattern;

public class DoTask implements Command {
    private Task task;

    public DoTask(Task task) { // ✅ Proper constructor
        this.task = task;
    }

    @Override
    public void doIt() {
        this.task.doTask();
    }
}
