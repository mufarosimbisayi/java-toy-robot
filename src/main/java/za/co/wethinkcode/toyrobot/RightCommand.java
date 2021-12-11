package za.co.wethinkcode.toyrobot;

public class RightCommand extends Command {

    public RightCommand() {
        super("right");
    }


    @Override
    public boolean execute(Robot target) {
        if (target.getDirection().equals(Direction.NORTH)) {
            target.setDirection(Direction.WEST);
        } 
        else if (target.getDirection().equals(Direction.WEST)) {
            target.setDirection(Direction.SOUTH);
        }
        else if (target.getDirection().equals(Direction.SOUTH)) {
            target.setDirection(Direction.EAST);
        } else {
            target.setDirection(Direction.NORTH);
        }
        target.setStatus("Turned right.");
        return true;
    }
}
