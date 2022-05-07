package za.co.wethinkcode.lms.test;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;

import static org.junit.jupiter.api.Assertions.*;

public class WorldTest {

    @Test
    void updatePosition() {
        IWorld world = new TextWorld(new EmptyMaze());
        assertEquals(IWorld.CENTRE, world.getPosition());
        world.updatePosition(100);
        Position newPosition = new Position(IWorld.CENTRE.getX(), IWorld.CENTRE.getY() + 100);
        assertEquals(newPosition, world.getPosition());
    }

    @Test
    void updateDirectionRight() {
        IWorld world = new TextWorld(new EmptyMaze());
        world.updateDirection(true);
        assertEquals(IWorld.Direction.RIGHT, world.getCurrentDirection());
        world.updatePosition(100);
        Position newPosition = new Position(IWorld.CENTRE.getX() + 100, IWorld.CENTRE.getY());
        assertEquals(newPosition, world.getPosition());
    }

    @Test
    void updateDirectionLeft() {
        IWorld world = new TextWorld(new EmptyMaze());
        world.updateDirection(false);
        assertEquals(IWorld.Direction.LEFT, world.getCurrentDirection());
        world.updatePosition(100);
        Position newPosition = new Position(IWorld.CENTRE.getX() - 100, IWorld.CENTRE.getY());
        assertEquals(newPosition, world.getPosition());
    }

    @Test
    void isNewPositionAllowed() {
        IWorld world = new TextWorld(new EmptyMaze());
        assertTrue(world.isNewPositionAllowed(new Position(100,0)));
        assertTrue(world.isNewPositionAllowed(new Position(100,100)));
        assertFalse(world.isNewPositionAllowed(new Position(201,0)));
        assertFalse(world.isNewPositionAllowed(new Position(-201,0)));
    }

    @Test
    void isAtEdge() {
        IWorld world = new TextWorld(new EmptyMaze());
        assertFalse(world.isAtEdge());
        assertEquals(IWorld.UpdateResponse.SUCCESS,world.updatePosition(200));
        assertTrue(world.isAtEdge());
    }

    @Test
    void reset() {
        IWorld world = new TextWorld(new EmptyMaze());
        world.updatePosition(100);
        world.updateDirection(true);
        assertEquals(IWorld.Direction.RIGHT, world.getCurrentDirection());
        world.reset();
        assertEquals(IWorld.Direction.UP, world.getCurrentDirection());
        assertEquals(IWorld.CENTRE, world.getPosition());
    }
}