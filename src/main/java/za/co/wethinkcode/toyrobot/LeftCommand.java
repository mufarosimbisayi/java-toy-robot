package za.co.wethinkcode.toyrobot;

public class LeftCommand extends Command {

    public LeftCommand() {
        super("left");
    }


    @Override
    public boolean execute(Robot target) {
        if (target.getDirection().equals(Direction.NORTH)) {
            target.setDirection(Direction.EAST);
        } 
        else if (target.getDirection().equals(Direction.EAST)) {
            target.setDirection(Direction.SOUTH);
        }
        else if (target.getDirection().equals(Direction.SOUTH)) {
            target.setDirection(Direction.WEST);
        } else {
            target.setDirection(Direction.NORTH);
        }
        target.setStatus("Turned left.");
        return true;
    }
}
