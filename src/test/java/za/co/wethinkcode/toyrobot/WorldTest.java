package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import za.co.wethinkcode.toyrobot.maze.*;

class WorldTest {

    @Test
    public void checkTextWorldCentre() {
        TextWorld world = new TextWorld(new EmptyMaze());
        assertEquals(world.CENTRE, new Position(0,0));
    }

    @Test
    public void checkTurtleWorldCentre() {
        TurtleWorld world = new TurtleWorld();
        assertEquals(world.CENTRE, new Position(0,0));
    }

    @Test
    public void checkAllowedPosition() {
        TextWorld world = new TextWorld(new EmptyMaze());
        assertTrue(world.isNewPositionAllowed(new Position(10,10)));
        assertFalse(world.isNewPositionAllowed(new Position(-2000,10)));
    }

    @Test
    public void checkUpdatePosition() {
        TextWorld world = new TextWorld(new EmptyMaze());
        world.updatePosition(250);
        assertEquals(world.robotPosition, new Position(0,0));
        world.updateDirection(false);
        world.updatePosition(150);
        assertEquals(world.robotPosition, new Position(-150,0));
    }
}
