package za.co.wethinkcode.toyrobot;

import java.util.Scanner;

public class Play {
    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        Robot robot;

        String world = getInput("Would you like to use the text or turtle world?");
        String name = getInput("What do you want to name your robot?");
        robot = new Robot(name);
        System.out.println("Hello Kiddo!");
        robot.setWorld(world);


        Command command;
        boolean shouldContinue = true;
        do {
            String instruction = getInput(robot.getName() + "> What must I do next?").strip().toLowerCase();
            try {
                command = Command.create(instruction);
                shouldContinue = robot.handleCommand(command);
            } catch (IllegalArgumentException e) {
                robot.setStatus("Sorry, I did not understand '" + instruction + "'.");
            }
            System.out.println(robot);
        } while (shouldContinue);

    }

    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }
}
