package za.co.wethinkcode.toyrobot;

public class ReplayCommand extends Command {
    public ReplayCommand() {
        super("replay");
    }

    public ReplayCommand(String argument) {
        super("replay", argument);
    }

    @Override
    public boolean execute(Robot target) {
        return true;
    }
}
