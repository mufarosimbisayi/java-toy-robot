package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObstacleTest {

    @Test
    public void shouldKnowXandY() {
        SquareObstacle obstacle = new SquareObstacle(3,4);
        assertEquals(3, obstacle.getX());
        assertEquals(4, obstacle.getY());
    }

    @Test
    public void blocksPosition() {
        SquareObstacle obstacle = new SquareObstacle(0,0);
        assertTrue(obstacle.blocksPosition(new Position(1,1)));
        assertFalse(obstacle.blocksPosition(new Position(-1,-1)));
    }
}
