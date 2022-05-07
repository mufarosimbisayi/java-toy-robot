package za.co.wethinkcode.toyrobot;

import java.util.Scanner;

public class Play {
    static Scanner scanner;

    public static void main(String[] args) {
        
        String worldType;
        String mazeType;

        //Set the world type
        try {
            worldType = args[0].strip();
        }
        catch (Exception e) {
            worldType = "text";
        }

        //Set maze Type
        try {
            mazeType = args[1].strip();
        }
        catch (Exception e) {
            mazeType = "random";
        }

        scanner = new Scanner(System.in);
        Robot robot;

        String name = getInput("What do you want to name your robot?");
        robot = new Robot(name);
        System.out.println("Hello Kiddo!");
        robot.setWorld(worldType.toLowerCase(), mazeType.toLowerCase());


        Command command = Command.create("help");
        boolean shouldContinue = true;
        do {
            shouldContinue = true;
            String instruction = getInput(robot.getName() + "> What must I do next?").strip().toLowerCase();
            try {
                command = Command.create(instruction);
                robot.handleCommand(command);
                if(command.getName().equals("off") || command.getName().equals("shutdown")) {
                    shouldContinue = false;
                }
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
