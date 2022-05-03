package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.maze.SimpleMazeRunner;

public class MazeRunCommand extends Command {
    
    public MazeRunCommand(String argument) {
        super("mazerun", argument);
    }

    @Override
    public boolean execute(Robot robot) {
        robot.setStatus("Starting maze run..");
        SimpleMazeRunner mazeRunner = new SimpleMazeRunner();
        mazeRunner.mazeRun(robot, mapDirection(this.getArgument()));
        return true;
    }

    private IWorld.Direction mapDirection(String side) {
        switch (side) {
            case "top":
                return IWorld.Direction.UP;
            case "bottom":
                return IWorld.Direction.DOWN;
            case "left":
                return IWorld.Direction.LEFT;
            case "right":
                return IWorld.Direction.RIGHT;
        }
        return IWorld.Direction.UP;
    }
}
