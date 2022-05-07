package za.co.wethinkcode.toyrobot;

public abstract class Command {
    private final String name;
    private String argument;

    public abstract boolean execute(Robot target);

    public Command(String name){
        this.name = name.trim().toLowerCase();
        this.argument = "";
    }

    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }

    public String getName() {                                                                           //<2>
        return name;
    }

    public String getArgument() {
        return this.argument;
    }

    public void setArgument(String newArgument) {
        this.argument = newArgument;
    }

    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]){
            case "shutdown":
            case "off":
                return new ShutdownCommand();
            case "help":
                return new HelpCommand();
            case "forward":
                return new ForwardCommand(args[1]);
            case "back":
                return new BackCommand(args[1]);
            case "right":
                return new RightCommand();
            case "left":
                return new LeftCommand();
            case "sprint":
                return new SprintCommand(args[1]);
            case "replay":
                if (args.length == 1) {
                    return new ReplayCommand("all");
                }
                else if(args.length == 2) {
                    return new ReplayCommand(args[1]);
                }
                else if(args.length == 3) {
                    return new ReplayCommand(args[1] + " " + args[2]);
                }
            case "mazerun":
                return new MazeRunCommand(args[1]);
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }
}

