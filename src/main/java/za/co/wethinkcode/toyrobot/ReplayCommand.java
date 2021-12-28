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
