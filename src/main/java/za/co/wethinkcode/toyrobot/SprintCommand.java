package za.co.wethinkcode.toyrobot;

public class SprintCommand extends Command {
    public SprintCommand(String argument) {
        super("sprint", argument);
    }

    @Override
    public boolean execute(Robot target) {
        int nrSteps = factorial(Integer.parseInt(getArgument()));
        if (target.canUpdate(nrSteps)) {
            for (int i = Integer.parseInt(getArgument()); i > 0; i--) {
                target.handleCommand(Command.create("forward " + i));
            }
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
