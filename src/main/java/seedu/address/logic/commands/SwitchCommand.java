package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ClientListSwitchEvent;

public class SwitchCommand extends Command {

    public static final String COMMAND_WORD = "switch";

    public static final String MESSAGE_SUCCESS = "Switch to Main/Closed List";

    @Override
    public CommandResult execute() {
        EventsCenter.getInstance().post(new ClientListSwitchEvent());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
