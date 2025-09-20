package BehavioralDP.CommandPattern;

public class Mom {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        this.command.doIt();
    }
}
