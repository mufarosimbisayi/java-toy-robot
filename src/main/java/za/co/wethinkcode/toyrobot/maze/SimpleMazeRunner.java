package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.Direction;
import za.co.wethinkcode.toyrobot.Command;
import za.co.wethinkcode.toyrobot.world.IWorld;

public class SimpleMazeRunner implements MazeRunner {

    public int getMazeRunCost() {
        return 0;
    }

    public boolean mazeRun(Robot robot, IWorld.Direction edgeDirection) {
        Command moveForward = Command.create("forward 5");
        Command turnRight = Command.create("right");
        Command turnLeft = Command.create("left");
        int balance = 0;

        setEdge(robot, edgeDirection);
        while (true) {
            if (robot.handleCommand(moveForward)) {
                if (balance > 0) {
                    robot.handleCommand(turnLeft);
                    balance--;
                }
            } else {
                robot.handleCommand(turnRight);
                balance++;
            }

            System.out.println(robot);
            if (arrivedAtEdge(robot, edgeDirection)) {
                break;
            }
        }
        return true;
    }

    private void setEdge(Robot robot, IWorld.Direction edgeDirection) {
        if (edgeDirection.equals(IWorld.Direction.UP)) {
            robot.setDirection(Direction.NORTH);
        }
        else if (edgeDirection.equals(IWorld.Direction.DOWN)) {
            robot.setDirection(Direction.SOUTH);
        }
        else if (edgeDirection.equals(IWorld.Direction.LEFT)) {
            robot.setDirection(Direction.EAST);
        }
        else if (edgeDirection.equals(IWorld.Direction.RIGHT)) {
            robot.setDirection(Direction.WEST);
        }
    }

    private boolean arrivedAtEdge(Robot target, IWorld.Direction edgeDirection) {
        int X = target.getPosition().getX();
        int Y = target.getPosition().getY();

        if (X == 100 && edgeDirection.equals(IWorld.Direction.RIGHT)) {
            return true;
        }
        else if (X == -200 && edgeDirection.equals(IWorld.Direction.LEFT)) {
            return true;
        }
        else if (Y == 100 && edgeDirection.equals(IWorld.Direction.UP)) {
            return true;
        }
        else if (Y == -200 && edgeDirection.equals(IWorld.Direction.DOWN)) {
            return true;
        }

        return false;
    }
}
