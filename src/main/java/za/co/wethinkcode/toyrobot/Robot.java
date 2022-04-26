package za.co.wethinkcode.toyrobot;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import za.co.wethinkcode.toyrobot.world.*;

public class Robot {

    private AbstractWorld world;

    private List<Command> commandHistory; 
    private Position position;
    private Direction currentDirection;
    private String status;
    private String name;
    private String cache;

    public Robot(String name) {
        this.name = name;
        this.status = "Ready";
        this.position = world.CENTRE;
        this.currentDirection = Direction.NORTH;
        this.cache = "";
        this.commandHistory = new ArrayList<>();
        this.world = new TextWorld();
    }

    public void setWorld(String worldName) {
        if (worldName == "turtle") {
            this.world = new TextWorld();
        }
        this.world.showObstacles();
    }

    public AbstractWorld getWorld() {
        return this.world;
    }

    public String getStatus() {
        return this.status;
    }

    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    public boolean handleCommand(Command command) {
        boolean state = command.execute(this);
        addCommand(command);
        return state;
    }

    public boolean updatePosition(int nrSteps){
        int newX = this.position.getX();
        int newY = this.position.getY();
        boolean update = true;

        if (Direction.NORTH.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        }
        else if (Direction.EAST.equals(this.currentDirection)) {
            newX = newX - nrSteps;
        }
        else if (Direction.SOUTH.equals(this.currentDirection)) {
            newY = newY - nrSteps;
        }
        else if (Direction.WEST.equals(this.currentDirection)) {
            newX = newX + nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        if (!world.isNewPositionAllowed(newPosition)){
            setStatus("Sorry, I cannot go outside my safe zone.");
            update = false;
        }
        for (Obstacle obstacle: world.getObstacles()) {
            if (obstacle.blocksPath(this.position, newPosition)) {
                setStatus("Sorry, there is an obstacle in the way.");
                update = false;
            }
        }
        if (update) {
            this.position = newPosition;
        }
        return update;
    }

    public boolean canUpdate(int nrSteps){
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (Direction.NORTH.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        }
        else if (Direction.EAST.equals(this.currentDirection)) {
            newX = newX - nrSteps;
        }
        else if (Direction.SOUTH.equals(this.currentDirection)) {
            newY = newY - nrSteps;
        }
        else if (Direction.WEST.equals(this.currentDirection)) {
            newX = newX + nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        if (world.isNewPositionAllowed(newPosition)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (getCache().isEmpty()) {
            return "[" + this.position.getX() + "," + this.position.getY() + "] "
               + this.name + "> " + this.status;
        } else {
            String localStatus = getCache();
            clearCache();
            return localStatus;
        }
    }

    public Position getPosition() {
        return this.position;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setDirection(Direction direction) {
        this.currentDirection = direction;
    }

    public Direction getDirection() {
        return this.currentDirection;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getCache() {
        return this.cache;
    }

    public void clearCache() {
        this.cache = "";
    }

    public void addCommand(Command command) {
        this.commandHistory.add(command);
    }

    public List<Command> getCommandHistory() {
        return this.commandHistory;
    }
}
