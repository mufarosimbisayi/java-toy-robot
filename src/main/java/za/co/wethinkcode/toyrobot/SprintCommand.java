package za.co.wethinkcode.toyrobot;

public class SprintCommand extends Command {
    public SprintCommand(String argument) {
        super("sprint", argument);
    }

    @Override
    public boolean execute(Robot target) {
        String cache = "";
        String status = "";
        int nrSteps = factorial(Integer.parseInt(getArgument()));
        if (target.canUpdate(nrSteps)) {
            for (int i = Integer.parseInt(getArgument()); i > 0; i--) {
                target.handleCommand(Command.create("forward " + i));
                cache += target.toString() + "\n";
                status += target.getStatus() + "\n";
            }
            target.setCache(cache.substring(0, cache.length() - 1));
            target.setStatus(status.substring(0, status.length() - 1));
        } else {
            target.setStatus("Sorry, I cannot go outside my safe zone.");
        }

        return true;
    }

    private int factorial(int inputNumber) {
        int factorial = 1;
        for(int i = 1; i <= inputNumber; i++) {
            factorial = factorial + i;
        }
        return factorial;
    }
}
