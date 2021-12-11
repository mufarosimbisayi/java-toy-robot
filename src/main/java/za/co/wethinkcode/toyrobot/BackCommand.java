package za.co.wethinkcode.toyrobot;

public class BackCommand extends Command{

    public BackCommand() {
        super("back");
    }

    public BackCommand(String argument) {
        super("back", argument);
    }

    @Override
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());
        if (target.updatePosition(-nrSteps)) {
            target.setStatus("Moved back by " + nrSteps + " steps.");
        } else {
            target.setStatus("Sorry, I cannot fo outside my safe zone.");
        }
        return true;
    }
}
