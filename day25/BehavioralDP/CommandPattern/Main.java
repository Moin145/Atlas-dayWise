package BehavioralDP.CommandPattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("Command Pattern - Behavioural DP");

        Task task = new Task();
        Mom remote = new Mom();

        Command onCommand = new DoTask(task);
        Command offCommand = new DontDoTask(task);

        remote.setCommand(onCommand);
        remote.executeCommand();   // "do your home tasks"

        remote.setCommand(offCommand);
        remote.executeCommand();   // "don't do your home tasks"
    }
}
