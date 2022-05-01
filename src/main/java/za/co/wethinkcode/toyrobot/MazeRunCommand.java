package za.co.wethinkcode.toyrobot;

public class MazeRunCommand extends Command {
    
    public MazeRunCommand(String argument) {
        super("mazerun", argument);
    }

    @Override
    public boolean execute(Robot target) {
        target.setStatus("Test mazerunner through status");
        return true;
    }
}
