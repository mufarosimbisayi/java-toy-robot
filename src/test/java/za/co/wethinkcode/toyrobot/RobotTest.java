package za.co.wethinkcode.toyrobot;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    @Test
    void initialPosition() {
        Robot robot = new Robot("CrashTestDummy");
        assertEquals(Robot.CENTRE, robot.getPosition());
        assertEquals(Direction.NORTH, robot.getCurrentDirection());
    }

    @Test
    void dump() {
        Robot robot = new Robot("CrashTestDummy");
        assertEquals("[0,0] CrashTestDummy> Ready", robot.toString());
    }

    @Test
    void shutdown() {
        Robot robot = new Robot("CrashTestDummy");
        ShutdownCommand command = new ShutdownCommand();
        assertFalse(robot.handleCommand(command));
    }

    @Test
    void forward() {
        Robot robot = new Robot("CrashTestDummy");
        ForwardCommand command = new ForwardCommand("10");
        assertTrue(robot.handleCommand(command));
        Position expectedPosition = new Position(Robot.CENTRE.getX(), Robot.CENTRE.getY() + 10);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals("Moved forward by 10 steps.", robot.getStatus());
    }

    
    @Test
    void back() {
        Robot robot = new Robot("CrashTestDummy");
        BackCommand command = new BackCommand("10");
        assertTrue(robot.handleCommand(command));
        assertEquals(new Position(0, -10), robot.getPosition());
        assertEquals("Moved back by 10 steps.", robot.getStatus());
    }


    @Test
    void forwardforward() {
        Robot robot = new Robot("CrashTestDummy");
        assertTrue(robot.handleCommand(new ForwardCommand("10")));
        assertTrue(robot.handleCommand(new ForwardCommand("5")));
        assertEquals("Moved forward by 5 steps.", robot.getStatus());
    }

    @Test
    void tooFarForward() {
        Robot robot = new Robot("CrashTestDummy");
        assertTrue(robot.handleCommand(new ForwardCommand("1000")));
        assertEquals(Robot.CENTRE, robot.getPosition());
        assertEquals("Sorry, I cannot go outside my safe zone.", robot.getStatus());
    }

    @Test
    void rightForward() {
        Robot robot = new Robot("CrashTestDummy");
        assertTrue(robot.handleCommand(new RightCommand()));
        assertEquals("Turned right.", robot.getStatus());
        assertTrue(robot.handleCommand(new ForwardCommand("10")));
        assertEquals("Moved forward by 10 steps.", robot.getStatus());
        assertEquals(new Position(10,0), robot.getPosition());
    }

    @Test
    void leftForward() {
        Robot robot = new Robot("CrashTestDummy");
        assertTrue(robot.handleCommand(new LeftCommand()));
        assertEquals("Turned left.", robot.getStatus());
        assertTrue(robot.handleCommand(new ForwardCommand("10")));
        assertEquals("Moved forward by 10 steps.", robot.getStatus());
        assertEquals(new Position(-10,0), robot.getPosition());
    }

    @Test
    void sprint() {
        Robot robot = new Robot("test");
        assertTrue(robot.handleCommand(new SprintCommand("5")));
        assertEquals(new Position(0,15), robot.getPosition());
        assertEquals("Moved forward by 5 steps.\n" +
                    "Moved forward by 4 steps.\n" +
                    "Moved forward by 3 steps.\n" +
                    "Moved forward by 2 steps.\n" +
                    "Moved forward by 1 steps.)\n",
                    robot.getStatus());
    }

    @Test
    void help() {
        Robot robot = new Robot("CrashTestDummy");
        Command command = new HelpCommand();
        assertTrue(robot.handleCommand(command));
        assertEquals("I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'\n" +
                "BACK - move back by specified number of steps, e.g. 'BACK 10'", robot.getStatus());
    }

    @Test
    void setAndGetDirection() {
        Robot robot = new Robot("test");
        robot.setDirection(Direction.SOUTH);
        assertEquals(Direction.SOUTH, robot.getDirection());
    }
}
