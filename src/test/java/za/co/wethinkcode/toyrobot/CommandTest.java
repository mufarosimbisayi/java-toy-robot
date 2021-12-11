package za.co.wethinkcode.toyrobot;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void getShutdownName() {
        Command test = new ShutdownCommand();
        assertEquals("off", test.getName());
    }

    @Test
    void executeShutdown() {
        Robot robot = new Robot("CrashTestDummy");
        Command shutdown = Command.create("shutdown");
        assertFalse(shutdown.execute(robot));
        assertEquals("Shutting down...", robot.getStatus());
    }

    @Test
    void getForwardName() {
        ForwardCommand test = new ForwardCommand("100");
        assertEquals("forward", test.getName());
        assertEquals("100", test.getArgument());
    }

    @Test
    void executeForward() {
        Robot robot = new Robot("CrashTestDummy");
        Command forward100 = Command.create("forward 10");
        assertTrue(forward100.execute(robot));
        Position expectedPosition = new Position(Robot.CENTRE.getX(), Robot.CENTRE.getY() + 10);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals("Moved forward by 10 steps.", robot.getStatus());
    }


    @Test
    void getBackName() {
        BackCommand test = new BackCommand("100");
        assertEquals("back", test.getName());
        assertEquals("100", test.getArgument());
    }


    @Test
    void executeBack() {
        Robot robot = new Robot("test");
        Command backCommand = Command.create("back 10");
        backCommand.execute(robot);
        assertEquals(new Position(0, -10), robot.getPosition());
        assertEquals("Moved back by 10 steps.", robot.getStatus());
    }

    @Test
    void getRightName() {
        RightCommand rightCommand = new RightCommand();
        assertEquals("right", rightCommand.getName());
    }

    @Test
    void executeRightCommand() {
        RightCommand rightCommand = new RightCommand();
        Robot robot = new Robot("HAL");
        rightCommand.execute(robot);
        assertEquals(new Position(0, 0), robot.getPosition());
        assertEquals(Direction.WEST, robot.getDirection());
    }

    @Test
    void getLeftName() {
        LeftCommand leftCommand = new LeftCommand();
        Robot robot = new Robot("HAL");
        leftCommand.execute(robot);
        assertEquals(new Position(0,0), robot.getPosition());
        assertEquals(Direction.EAST, robot.getDirection());
        assertEquals("Turned left.", robot.getStatus());
    }

    @Test
    void getHelpName() {
        Command test = new HelpCommand();                                                               //<1>
        assertEquals("help", test.getName());
    }

    @Test
    void executeHelp() {
        Robot robot = new Robot("CrashTestDummy");
        Command help = Command.create("help");
        assertTrue(help.execute(robot));
        assertEquals("I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'\n" +
                "BACK - move back by specified number of steps, e.g. 'BACK 10'", robot.getStatus());
    }

    @Test
    void createCommand() {
        Command forward = Command.create("forward 10");                                                 //<1>
        assertEquals("forward", forward.getName());
        assertEquals("10", forward.getArgument());

        Command shutdown = Command.create("shutdown");                                                  //<2>
        assertEquals("off", shutdown.getName());

        Command help = Command.create("help");                                                          //<3>
        assertEquals("help", help.getName());
                        //<4>
        Command back = Command.create("back 10");
        assertEquals("back", back.getName());
        assertEquals("10", back.getArgument());
                        //<4>
        Command right = Command.create("right");
        assertEquals("right", right.getName());
                        //<5>
        Command left = Command.create("left");
        assertEquals("left", left.getName());
    }

    @Test
    void createInvalidCommand() {
        try {
            Command forward = Command.create("say hello");                                              //<4>
            fail("Should have thrown an exception");                                                    //<5>
        } catch (IllegalArgumentException e) {
            assertEquals("Unsupported command: say hello", e.getMessage());                             //<6>
        }
    }
}
