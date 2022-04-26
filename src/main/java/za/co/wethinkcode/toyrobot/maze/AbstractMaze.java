package za.co.wethinkcode.toyrobot.maze;

import java.util.*;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;
import za.co.wethinkcode.toyrobot.Position;

public abstract class AbstractMaze implements Maze {
    
    List<Obstacle> mazeObstacles;

    public List<Obstacle> getObstacles() {
        return this.mazeObstacles;
    }

    public boolean blocksPosition(Position position) {
        for (Obstacle obstacle: mazeObstacles) {
            if (obstacle.blocksPosition(position)) {
                return true;
            }
        }
        return false;
    }

    public boolean blocksPath(Position a, Position b) {
        for (Obstacle obstacle: mazeObstacles) {
            if (obstacle.blocksPath(a,b)) {
                return true;
            }
        }

        return false;
    }

}
