package za.co.wethinkcode.toyrobot.maze;

import java.util.*;
import za.co.wethinkcode.toyrobot.world.Obstacle;

public class EmptyMaze extends AbstractMaze {
    
    public EmptyMaze() {
        mazeObstacles = new ArrayList<Obstacle>();
    }
}
