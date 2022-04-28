package za.co.wethinkcode.toyrobot.world;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.AbstractMaze;

public class TextWorld extends AbstractWorld {
    
    private int numberOfObstacles = 0;
    List<Obstacle> randomObstacles = new ArrayList<Obstacle>();

    public TextWorld(AbstractMaze newMaze) {
        this.maze = newMaze;
    }

    @Override
    public void showObstacles() {
        if (this.maze.getObstacles().size() > 0) {
            System.out.println( "There are some obstacles:");
        }
        for (Obstacle obstacle: this.maze.getObstacles()) {
            System.out.println(String.format("- At position %d, %d (to %d,%d)",obstacle.getBottomLeftX(),obstacle.getBottomLeftY(),obstacle.getBottomLeftX() + obstacle.getSize(),obstacle.getBottomLeftY() + obstacle.getSize()));
        }
    }

    @Override
    public List<Obstacle> getObstacles() {
        return this.maze.getObstacles();
    }
}

