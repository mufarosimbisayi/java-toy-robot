package za.co.wethinkcode.toyrobot;

import java.util.*;

public class ReplayCommand extends Command {

    private boolean reversed = false;

    public ReplayCommand() {
        super("replay");
    }

    public ReplayCommand(String argument) {
        super("replay", argument);
    }

    @Override
    public boolean execute(Robot target) {

        if(getArgument().contains("reversed")) {
            reversed = true;
            String newArgument = getArgument().replace("reversed", "");
            if(!newArgument.equals("")) {
                newArgument = newArgument.trim();
            }
            setArgument(newArgument);
        }

        if (getArgument().equals("all") || getArgument().equals("")) {
            replay(target);
        }
        else {
            String[] args = getArgument().trim().split("-");
            if (args.length == 1) {
                replayLimit(target);
            }
            else if (args.length == 2) {
                replayRange(target);
            }
        }
        return true;
    }

    private void replay(Robot target) {
        List<Command> commandHistory = target.getCommandHistory();
        if(reversed) {
            Collections.reverse(commandHistory);
        }
        runCommands(commandHistory, target);
    }

    private void replayLimit(Robot target) {
        int commands = 0;
        int limit = Integer.parseInt(getArgument());
        List<Command> newCommandHistory = new ArrayList<Command>();
        List<Command> commandHistory = target.getCommandHistory();
        Collections.reverse(commandHistory);
        while(commands < limit) {
            Command command = commandHistory.get(commands);
            newCommandHistory.add(command);
            commands++;
        }
        commandHistory = newCommandHistory;
        Collections.reverse(commandHistory);
        if(reversed) {
            Collections.reverse(commandHistory);
        }
        runCommands(commandHistory, target);
    }

    private void replayRange(Robot target) {
        String args[] = getArgument().trim().split("-");
        int upperLimit = Integer.parseInt(args[0]);
        int lowerLimit = Integer.parseInt(args[1]);
        List<Command> commandHistory = target.getCommandHistory();
        List<Command> newCommandHistory = new ArrayList<Command>();
        Collections.reverse(commandHistory);
        while(upperLimit > lowerLimit) {
            Command command = commandHistory.get(upperLimit - 1);
            newCommandHistory.add(command);
            upperLimit--;
        }
        if(reversed) {
            Collections.reverse(newCommandHistory);
        }
        commandHistory = newCommandHistory;
        runCommands(commandHistory, target);
    }

    private void runCommands(List<Command> targetCommands, Robot target) {
        String cache = "";
        int commands = 0;
        for(Command command: targetCommands) {
            if (movementCommand(command)) {
                command.execute(target);
                cache += target.toString() + "\n";
                commands++;
            }
        }
        target.setCache(cache.substring(0, cache.length() - 1));
        target.setStatus("replayed " + commands + " commands.");
        System.out.println(target);
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
