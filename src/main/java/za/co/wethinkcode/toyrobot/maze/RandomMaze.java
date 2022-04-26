package za.co.wethinkcode.toyrobot.maze;

import java.util.*;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

public class RandomMaze extends AbstractMaze {

    private int numberOfObstacles = 0;
    Random rand = new Random();

    public RandomMaze() {
        mazeObstacles = new ArrayList<Obstacle>();
        generateObstacles();
    }

    private Obstacle generateRandomObstacle() {
        int randomX = rand.nextInt(301) - 200;
        int randomY = rand.nextInt(301) - 200;
        return new SquareObstacle(randomX,randomY);
    }

    private void generateObstacles() {
        int numberOfObstacles = rand.nextInt(10);
        for (int i = 0; i < numberOfObstacles; i++) {
            this.mazeObstacles.add(generateRandomObstacle());
        }
    }

}
