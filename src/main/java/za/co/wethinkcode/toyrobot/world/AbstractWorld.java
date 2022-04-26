package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorld implements IWorld {

    public Position robotPosition = CENTRE;
    private final Position TOP_LEFT = new Position(-200,100);
    private final Position BOTTOM_RIGHT = new Position(100, -200);

    private Direction currentDirection = Direction.UP;

    public boolean isNewPositionAllowed(Position position) {
        return position.isIn(TOP_LEFT,BOTTOM_RIGHT);
    }

    public UpdateResponse updatePosition(int nrSteps) {
        int newX = this.robotPosition.getX();
        int newY = this.robotPosition.getY();

        if (Direction.UP.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        }
        else if (Direction.LEFT.equals(this.currentDirection)) {
            newX = newX - nrSteps;
        }
        else if (Direction.DOWN.equals(this.currentDirection)) {
            newY = newY - nrSteps;
        }
        else if (Direction.RIGHT.equals(this.currentDirection)) {
            newX = newX + nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        if (isNewPositionAllowed(newPosition)) {
            this.robotPosition = newPosition;
            return UpdateResponse.SUCCESS;
        }
        return UpdateResponse.FAILED_OUTSIDE_WORLD;
    } 

    public void updateDirection(boolean turnRight) {
        if (turnRight) {
            if (Direction.UP.equals(this.currentDirection)) {
                this.currentDirection = Direction.RIGHT;
            }
            else if (Direction.LEFT.equals(this.currentDirection)) {
                this.currentDirection = Direction.UP;
            }
            else if (Direction.DOWN.equals(this.currentDirection)) {
                this.currentDirection = Direction.LEFT;
            }
            else if (Direction.RIGHT.equals(this.currentDirection)) {
                this.currentDirection = Direction.DOWN;
            }
        }
        else {
            if (Direction.UP.equals(this.currentDirection)) {
                this.currentDirection = Direction.LEFT;
            }
            else if (Direction.LEFT.equals(this.currentDirection)) {
                this.currentDirection = Direction.DOWN;
            }
            else if (Direction.DOWN.equals(this.currentDirection)) {
                this.currentDirection = Direction.RIGHT;
            }
            else if (Direction.RIGHT.equals(this.currentDirection)) {
                this.currentDirection = Direction.UP;
            }
        }
    }

    public Position getPosition() {
        return this.robotPosition;
    }

    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    public boolean isAtEdge() {
        return true;
    }

    public void reset() {
    
    }

    public void showObstacles() {
    
    }

    public List<Obstacle> getObstacles() {
        return new ArrayList<Obstacle>();
    }
}
