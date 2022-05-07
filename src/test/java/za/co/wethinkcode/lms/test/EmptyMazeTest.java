package za.co.wethinkcode.lms.test;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.maze.Maze;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmptyMazeTest {

    @Test
    void testEmptyMazeIsEmpty() {
        Maze maze = new EmptyMaze();
        assertEquals(0, maze.getObstacles().size());
    }

}