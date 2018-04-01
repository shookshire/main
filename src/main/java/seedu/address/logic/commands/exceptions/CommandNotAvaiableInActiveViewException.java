package seedu.address.logic.commands.exceptions;

public class CommandNotAvaiableInActiveViewException extends CommandException {
    public CommandNotAvaiableInActiveViewException() {
        super("Operation is not available in active list view." +
                " Please switch back to closed list view with the command word: switch\n");
    }
}
