package seedu.address.logic.commands;

public class SwitchCommand extends Command {

    public static final String COMMAND_WORD = "switch";

    public static final String MESSAGE_SUCCESS = "Switch to Main/Closed List";

    @Override
    public CommandResult execute() {

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
