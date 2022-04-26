package za.co.wethinkcode.toyrobot.maze;

import java.util.*;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

public class SimpleMaze extends AbstractMaze {

        public SimpleMaze() {
            mazeObstacles = new ArrayList<Obstacle>();
            createCenter();
        }

        private void createCenter() {
            mazeObstacles.add(new SquareObstacle(0,0));
        }
}
