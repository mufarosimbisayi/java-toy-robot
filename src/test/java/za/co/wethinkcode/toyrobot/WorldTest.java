package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    @Test
    public void checkCentre() {
        TextWorld world = new TextWorld();
        assertEquals(world.CENTRE, new Position(0,0));
    }
}
