package seedu.address.logic.commands.exceptions;

//@@author olimhc
/**
 * Signals that the command is not available in active list view.
 */
public class CommandNotAvaiableInActiveViewException extends CommandException {
    public CommandNotAvaiableInActiveViewException() {
        super("Operation is not available in active list view."
                + " Please switch back to closed list view with the command word: switch\n");
    }
}
