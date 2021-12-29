package za.co.wethinkcode.toyrobot;

import java.util.*;

public class ReplayCommand extends Command {
    public ReplayCommand() {
        super("replay");
    }

    public ReplayCommand(String argument) {
        super("replay", argument);
    }

    @Override
    public boolean execute(Robot target) {
        if (getArgument() == "all") {
            replayAll(target);
        }
        else if (getArgument() == "reversed") {
            replayReversed(target);
        }
        else {
            String[] args = getArgument().trim().split("-");
            if (args.length == 1) {
                replayN(target);
            }
            else if (args.length == 2) {
                replayNM(target);
            }
        }
        return true;
    }

    private void replayAll(Robot target) {
        String cache = "";
        int commands = 0;
        for (Command command: target.getCommandHistory()) {
            if (movementCommand(command)) {
                command.execute(target);
                cache += target.toString() + "\n";
                commands++;
            }
        }
        target.setCache(cache.substring(0, cache.length() - 1));
        target.setStatus("Replayed " + commands + " commands.");
    }

    private void replayReversed(Robot target) {
        String cache = "";
        int commands = 0;
        List<Command> commandHistory = target.getCommandHistory();
        Collections.reverse(commandHistory);
        for (Command command: commandHistory) {
            if (movementCommand(command)) {
                command.execute(target);
                cache += target.toString() + "\n";
                commands++;
            }
        }
        target.setCache(cache.substring(0, cache.length() - 1));
        target.setStatus("Replayed " + commands + " commands reversed.");
    }

    private void replayN(Robot target) {
        String cache = "";
        int commands = 0;
        int limit = Integer.parseInt(getArgument());
        List<Command> commandHistory = target.getCommandHistory();
        for (Command command: commandHistory) {
            if (movementCommand(command)) {
                if (commands >= limit) {
                    break;
                }
                command.execute(target);
                cache += target.toString() + "\n";
                commands++;
            }
        }
        target.setCache(cache.substring(0, cache.length() - 1));
        target.setStatus("Replayed " + commands + " commands.");
    }

    private void replayNM(Robot target) {
        String cache = "";
        int commands = 0;
        String args[] = getArgument().trim().split("-");
        int upperLimit = Integer.parseInt(args[0]);
        int lowerLimit = Integer.parseInt(args[1]);
        List<Command> commandHistory = target.getCommandHistory();
        while (upperLimit > lowerLimit) {
            Command command = commandHistory.get(upperLimit - 1);
            if (movementCommand(command)) {
                command.execute(target);
                cache += target.toString() + "\n";
                commands++;
            }
            upperLimit--;
        }
        target.setCache(cache.substring(0, cache.length() - 1));
        target.setStatus("Replayed " + commands + " commands.");
    }

    private boolean movementCommand(Command command) {
        if (command.getName().equals("forward")) {
            return true;
        }
        else if (command.getName().equals("back")) {
            return true;
        }
        else if (command.getName().equals("left")) {
            return true;
        }
        else if (command.getName().equals("right")) {
            return true;
        }
        else if (command.getName().equals("sprint")) {
            return true;
        } else {
            return false;
        }
    }
}
