package za.co.wethinkcode.toyrobot.maze;

import java.util.*;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

public class DesignedMaze extends AbstractMaze {
    
    public DesignedMaze() {
        mazeObstacles = new ArrayList<Obstacle>();
        createWalls();
    }

    private void createWalls() {
        for (int i = 0; i <= 150; i++) {
            mazeObstacles.add(new SquareObstacle(-120,50 - i));
            mazeObstacles.add(new SquareObstacle(60,50 - i));
            mazeObstacles.add(new SquareObstacle(i - 50, -120));
            mazeObstacles.add(new SquareObstacle(i - 50, 60));
        }
    }
}
