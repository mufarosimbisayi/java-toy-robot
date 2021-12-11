package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.world.IWorld;

/**
 * Interface to represent a maze. A World will be loaded with a Maze, and will delegate the work to check if a path is blocked by certain obstacles etc to this maze instance.
 */
public interface MazeRunner {
    /**
     * Asks Mazerunner to start its mazerun.
     * @param target the instance of Robot to use to run the maze
     * @param edgeDirection the edge to try and reach, one of Direction.UP, RIGHT, DOWN, or LEFT
     * @return true if it was successful
     */
    boolean mazeRun(Robot target, IWorld.Direction edgeDirection);

    /**
     * Returns the cost for the previous mazerun attempt:
     *
     * - Each command that involves moving 1 or more steps must count the number of steps taken in that command towards the total steps.
     * - Each time your robot turns, it also counts as 1 step.
     * - Commands that fails because it is blocked by an obstacle or an edge must also count the steps involved in the command towards the total number of steps.
     * @return the total cost in steps of most recent mazerun
     */
    int getMazeRunCost();
}
