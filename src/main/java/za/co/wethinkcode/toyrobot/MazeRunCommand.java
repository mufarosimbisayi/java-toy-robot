package za.co.wethinkcode.toyrobot;

public class MazeRunCommand extends Command {
    
    public MazeRunCommand(String argument) {
        super("mazerun", argument);
    }

    @Override
    public boolean execute(Robot target) {
        target.setStatus("Starting maze run..");
        return true;
    }

    private void setEdge(Robot target) {
        switch (this.getArgument()) {
            case "top":
                target.setDirection(Direction.NORTH);
            case "bottom":
                target.setDirection(Direction.SOUTH);
            case "left":
                target.setDirection(Direction.EAST);
            default:
                target.setDirection(Direction.WEST);
        }
    }

    private boolean isAtEdge(Robot target) {
        int X = target.getPosition().getX();
        int Y = target.getPosition().getY();

        if (X == 100 || X == -200) {
            return true;
        }
        else if (Y == 100 || Y == -200) {
            return true;
        }

        return false;
    }
}
