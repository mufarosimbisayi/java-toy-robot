package za.co.wethinkcode.lms.test;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

import static org.junit.jupiter.api.Assertions.*;

public class ObstacleTest {
    @Test
    void testObstacleDimensions() {
        Obstacle obstacle = new SquareObstacle(1,1);
        assertEquals(1, obstacle.getBottomLeftX());
        assertEquals(1, obstacle.getBottomLeftY());
        assertEquals(5, obstacle.getSize());
    }

    @Test
    void testBlockPosition(){
        Obstacle obstacle = new SquareObstacle(1,1);
        assertTrue(obstacle.blocksPosition(new Position(1,1)));
        assertTrue(obstacle.blocksPosition(new Position(5,1)));
        assertTrue(obstacle.blocksPosition(new Position(1,5)));
        assertFalse(obstacle.blocksPosition(new Position(0,5)));
        assertFalse(obstacle.blocksPosition(new Position(6,5)));
    }

    @Test
    void testBlockPath(){
        Obstacle obstacle = new SquareObstacle(1,1);
        assertTrue(obstacle.blocksPath(new Position(1,0), new Position(1,50)));
        assertTrue(obstacle.blocksPath(new Position(2,-10), new Position(2, 100)));
        assertTrue(obstacle.blocksPath(new Position(-10,5), new Position(20, 5)));
        assertFalse(obstacle.blocksPath(new Position(0,1), new Position(0, 100)));
        assertFalse(obstacle.blocksPath(new Position(1,6), new Position(1, 100)));

    }

}
