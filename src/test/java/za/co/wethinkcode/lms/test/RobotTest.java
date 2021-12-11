package za.co.wethinkcode.lms.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.toyrobot.Play;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RobotTest {
    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }

    private void verifyOutput(String[] actualOutput, String[] expectedOutput) {
        for (int i = actualOutput.length - 1, j = expectedOutput.length - 1; j > 0; i--, j--) {
            assertEquals(expectedOutput[j], actualOutput[i]);
        }
    }

    @Test
    void testOff() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\noff\n".getBytes());
        System.setIn(mockedInput);
        Play.main(new String[]{"text", "EmptyMaze"});
        String[] actualOutput = outputStreamCaptor.toString().trim().split("\n");
        String[] expectedOutput = {"HAL> What must I do next?",
                "[0,0] HAL> Shutting down..."};
        verifyOutput(actualOutput, expectedOutput);
    }


    @Test
    void testInvalidCommand() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\ninvalid\noff\n".getBytes());
        System.setIn(mockedInput);
        Play.main(new String[]{"text", "EmptyMaze"});
        String[] actualOutput = outputStreamCaptor.toString().trim().split("\n");
        String[] expectedOutput = {"HAL> What must I do next?",
                "[0,0] HAL> Sorry, I did not understand 'invalid'.",
                "HAL> What must I do next?",
                "[0,0] HAL> Shutting down..."};
        verifyOutput(actualOutput, expectedOutput);
    }

    @Test
    void testForwardCorrect() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nforward 10\noff\n".getBytes());
        System.setIn(mockedInput);
        Play.main(new String[]{"text", "EmptyMaze"});
        String[] actualOutput = outputStreamCaptor.toString().trim().split("\n");
        String[] expectedOutput = {"HAL> What must I do next?",
                "[0,10] HAL> Moved forward by 10 steps.",
                "HAL> What must I do next?",
                "[0,10] HAL> Shutting down..."};
        verifyOutput(actualOutput, expectedOutput);
    }

    @Test
    void testForwardPastLimit() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nforward 210\noff\n".getBytes());
        System.setIn(mockedInput);
        Play.main(new String[]{"text", "EmptyMaze"});
        String[] actualOutput = outputStreamCaptor.toString().trim().split("\n");
        String[] expectedOutput = {"HAL> What must I do next?",
                "[0,0] HAL> Sorry, I cannot go outside my safe zone.",
                "HAL> What must I do next?",
                "[0,0] HAL> Shutting down..."};
        verifyOutput(actualOutput, expectedOutput);
    }

    @Test
    void testBackCorrect() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nback 50\noff\n".getBytes());
        System.setIn(mockedInput);
        Play.main(new String[]{"text", "EmptyMaze"});
        String[] actualOutput = outputStreamCaptor.toString().trim().split("\n");
        String[] expectedOutput = {"HAL> What must I do next?",
                "[0,-50] HAL> Moved back by 50 steps.",
                "HAL> What must I do next?",
                "[0,-50] HAL> Shutting down..."};
        verifyOutput(actualOutput, expectedOutput);
    }

    @Test
    void testRight() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nright\nforward 10\noff\n".getBytes());
        System.setIn(mockedInput);
        Play.main(new String[]{"text", "EmptyMaze"});
        String[] actualOutput = outputStreamCaptor.toString().trim().split("\n");
        String[] expectedOutput = {"HAL> What must I do next?",
                "[0,0] HAL> Turned right.",
                "HAL> What must I do next?",
                "[10,0] HAL> Moved forward by 10 steps.",
                "HAL> What must I do next?",
                "[10,0] HAL> Shutting down..."};
        verifyOutput(actualOutput, expectedOutput);
    }

    @Test
    void testLeft() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nleft\nforward 10\noff\n".getBytes());
        System.setIn(mockedInput);
        Play.main(new String[]{"text", "EmptyMaze"});
        String[] actualOutput = outputStreamCaptor.toString().trim().split("\n");
        String[] expectedOutput = {"HAL> What must I do next?",
                "[0,0] HAL> Turned left.",
                "HAL> What must I do next?",
                "[-10,0] HAL> Moved forward by 10 steps.",
                "HAL> What must I do next?",
                "[-10,0] HAL> Shutting down..."};
        verifyOutput(actualOutput, expectedOutput);
    }

    @Test
    void testSprint() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nsprint 5\noff\n".getBytes());
        System.setIn(mockedInput);
        Play.main(new String[]{"text", "EmptyMaze"});
        String[] actualOutput = outputStreamCaptor.toString().trim().split("\n");
        String[] expectedOutput = {"HAL> What must I do next?",
                "[0,5] HAL> Moved forward by 5 steps.",
                "[0,9] HAL> Moved forward by 4 steps.",
                "[0,12] HAL> Moved forward by 3 steps.",
                "[0,14] HAL> Moved forward by 2 steps.",
                "[0,15] HAL> Moved forward by 1 steps.",
                "HAL> What must I do next?",
                "[0,15] HAL> Shutting down..."};
        verifyOutput(actualOutput, expectedOutput);
    }

    @Test
    void testReplayNoArg() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nforward 10\nforward 20\nreplay\noff\n".getBytes());
        System.setIn(mockedInput);
        Play.main(new String[]{"text", "EmptyMaze"});
        String[] actualOutput = outputStreamCaptor.toString().trim().split("\n");
        String[] expectedOutput = {"HAL> What must I do next?",
                "[0,10] HAL> Moved forward by 10 steps.",
                "HAL> What must I do next?",
                "[0,30] HAL> Moved forward by 20 steps.",
                "HAL> What must I do next?",
                "[0,40] HAL> Moved forward by 10 steps.",
                "[0,60] HAL> Moved forward by 20 steps.",
                "[0,60] HAL> replayed 2 commands.",
                "HAL> What must I do next?",
                "[0,60] HAL> Shutting down..."};

        verifyOutput(actualOutput, expectedOutput);
    }

    @Test
    void testReplayNoArgReversed() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nforward 10\nforward 20\nreplay reversed\noff\n".getBytes());
        System.setIn(mockedInput);
        Play.main(new String[]{"text", "EmptyMaze"});
        String[] actualOutput = outputStreamCaptor.toString().trim().split("\n");
        String[] expectedOutput = {"HAL> What must I do next?",
                "[0,10] HAL> Moved forward by 10 steps.",
                "HAL> What must I do next?",
                "[0,30] HAL> Moved forward by 20 steps.",
                "HAL> What must I do next?",
                "[0,50] HAL> Moved forward by 20 steps.",
                "[0,60] HAL> Moved forward by 10 steps.",
                "[0,60] HAL> replayed 2 commands.",
                "HAL> What must I do next?",
                "[0,60] HAL> Shutting down..."};
        verifyOutput(actualOutput, expectedOutput);
    }

    @Test
    void testReplayLast2() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nforward 10\nforward 20\nback 30\nreplay 2\noff\n".getBytes());
        System.setIn(mockedInput);
        Play.main(new String[]{"text", "EmptyMaze"});
        String[] actualOutput = outputStreamCaptor.toString().trim().split("\n");
        String[] expectedOutput = {"HAL> What must I do next?",
                "[0,10] HAL> Moved forward by 10 steps.",
                "HAL> What must I do next?",
                "[0,30] HAL> Moved forward by 20 steps.",
                "HAL> What must I do next?",
                "[0,0] HAL> Moved back by 30 steps.",
                "HAL> What must I do next?",
                "[0,20] HAL> Moved forward by 20 steps.",
                "[0,-10] HAL> Moved back by 30 steps.",
                "[0,-10] HAL> replayed 2 commands.",
                "HAL> What must I do next?",
                "[0,-10] HAL> Shutting down..."};
        verifyOutput(actualOutput, expectedOutput);
    }

    @Test
    void testReplayLast2Reversed() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nforward 10\nforward 20\nback 30\nreplay reversed 2\noff\n".getBytes());
        System.setIn(mockedInput);
        Play.main(new String[]{"text", "EmptyMaze"});
        String[] actualOutput = outputStreamCaptor.toString().trim().split("\n");
        String[] expectedOutput = {"HAL> What must I do next?",
                "[0,10] HAL> Moved forward by 10 steps.",
                "HAL> What must I do next?",
                "[0,30] HAL> Moved forward by 20 steps.",
                "HAL> What must I do next?",
                "[0,0] HAL> Moved back by 30 steps.",
                "HAL> What must I do next?",
                "[0,-30] HAL> Moved back by 30 steps.",
                "[0,-10] HAL> Moved forward by 20 steps.",
                "[0,-10] HAL> replayed 2 commands.",
                "HAL> What must I do next?",
                "[0,-10] HAL> Shutting down..."};
        verifyOutput(actualOutput, expectedOutput);
    }

    @Test
    void testReplayRange() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nforward 10\nforward 20\nback 30\nback 40\nreplay 4-2\noff\n".getBytes());
        System.setIn(mockedInput);
        Play.main(new String[]{"text", "EmptyMaze"});
        String[] actualOutput = outputStreamCaptor.toString().trim().split("\n");
        String[] expectedOutput = {"HAL> What must I do next?",
                "[0,10] HAL> Moved forward by 10 steps.",
                "HAL> What must I do next?",
                "[0,30] HAL> Moved forward by 20 steps.",
                "HAL> What must I do next?",
                "[0,0] HAL> Moved back by 30 steps.",
                "HAL> What must I do next?",
                "[0,-40] HAL> Moved back by 40 steps.",
                "HAL> What must I do next?",
                "[0,-30] HAL> Moved forward by 10 steps.",
                "[0,-10] HAL> Moved forward by 20 steps.",
                "[0,-10] HAL> replayed 2 commands.",
                "HAL> What must I do next?",
                "[0,-10] HAL> Shutting down..."};
        verifyOutput(actualOutput, expectedOutput);
    }

    @Test
    void testReplayRangeReversed() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nforward 10\nforward 20\nback 30\nback 40\nreplay reversed 4-2\noff\n".getBytes());
        System.setIn(mockedInput);
        Play.main(new String[]{"text", "EmptyMaze"});
        String[] actualOutput = outputStreamCaptor.toString().trim().split("\n");
        String[] expectedOutput = {"HAL> What must I do next?",
                "[0,10] HAL> Moved forward by 10 steps.",
                "HAL> What must I do next?",
                "[0,30] HAL> Moved forward by 20 steps.",
                "HAL> What must I do next?",
                "[0,0] HAL> Moved back by 30 steps.",
                "HAL> What must I do next?",
                "[0,-40] HAL> Moved back by 40 steps.",
                "HAL> What must I do next?",
                "[0,-20] HAL> Moved forward by 20 steps.",
                "[0,-10] HAL> Moved forward by 10 steps.",
                "[0,-10] HAL> replayed 2 commands.",
                "HAL> What must I do next?",
                "[0,-10] HAL> Shutting down..."};
        verifyOutput(actualOutput, expectedOutput);
    }

    @Test
    void testMazerunCommandEmptyMaze() {
        InputStream mockedInput = new ByteArrayInputStream("HAL\nmazerun top\noff\n".getBytes());
        System.setIn(mockedInput);
        Play.main(new String[]{"text", "EmptyMaze"});
        String[] actualOutput = outputStreamCaptor.toString().trim().split("\n");
        String[] expectedOutput = {"[0,200] HAL> I am at the top edge. (Cost: 2 steps)",
                "HAL> What must I do next?",
                "[0,200] HAL> Shutting down..."};

        verifyOutput(actualOutput, expectedOutput);
    }
}
