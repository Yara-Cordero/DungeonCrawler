package Zork;

public class UnknownCommand implements Command {

    private String bogusCommand;

    public UnknownCommand(String bogusCommand) {
        this.bogusCommand = bogusCommand;
    }

    public String execute() {
        if (bogusCommand.isEmpty()) {
            return "I beg your pardon?";
        } else if (bogusCommand.toLowerCase().contains("fuck you")) {
            return "Fuck you too.";
        } else if (bogusCommand.toLowerCase().contains("help")){
            return "help yourself";
        }
        return "uh...\nI don't understand.";
    }
}
