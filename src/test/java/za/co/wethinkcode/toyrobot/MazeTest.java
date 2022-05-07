package za.co.wethinkcode.toyrobot.maze;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

public class MazeTest {
    
    @Test
    public void checkBlocksPath() {
        Obstacle obstacle = new SquareObstacle(0,0);
        assertTrue(obstacle.blocksPath(new Position(-10,0), new Position(10,0)));
        assertFalse(obstacle.blocksPath(new Position(-10,6), new Position(10,6)));
    }

    @Test
    public void testEmptyMaze() {
        AbstractMaze emptyMaze = new EmptyMaze();
        assertTrue(emptyMaze.getObstacles() != null && emptyMaze.getObstacles().isEmpty());
    }

    @Test
    public void testSimpleMaze() {
        AbstractMaze simpleMaze = new SimpleMaze();
        assertTrue(simpleMaze.getObstacles().size() == 1);
        assertTrue(simpleMaze.blocksPath(new Position(1,0), new Position(1,7)));
    }

    @Test
    public void testRandomMaze() {
        AbstractMaze randomMaze = new RandomMaze();
        assertTrue(randomMaze.getObstacles() != null);
    }

    @Test
    public void testDesignedMaze() {
        AbstractMaze designedMaze = new DesignedMaze();
        assertTrue(designedMaze.getObstacles() != null);
        assertTrue(designedMaze.blocksPath(new Position(-130,0), new Position(-110,0)));
        assertTrue(designedMaze.blocksPath(new Position(70,0), new Position(50,0)));
        assertTrue(designedMaze.blocksPath(new Position(0,-130), new Position(0,-110)));
        assertTrue(designedMaze.blocksPath(new Position(0,50), new Position(0,70)));
    }
}
