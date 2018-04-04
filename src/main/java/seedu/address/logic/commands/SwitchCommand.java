package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ClientListSwitchEvent;
import seedu.address.ui.util.ListPanelController;

//@@author olimhc
/**
 * Represents a switch command to enable user to switch between closed and active client list
 */
public class SwitchCommand extends Command {

    public static final String COMMAND_WORD = "switch";
    public static final String COMMAND_ALIAS = "sw";

    public static final String MESSAGE_SUCCESS = "Switched to ";
    public static final String MESSAGE_CLOSED_DISPLAY_LIST = "closed list of student's and tutors.\n";
    public static final String MESSAGE_ACTIVE_DISPLAY_LIST = "active list of student's and tutors.\n";


    @Override
    public CommandResult execute() {
        EventsCenter.getInstance().post(new ClientListSwitchEvent());
        listPanelController.switchDisplay();
        if (listPanelController.getCurrentListDisplayed() == ListPanelController.DisplayType.closedList) {
            return new CommandResult(MESSAGE_SUCCESS + MESSAGE_CLOSED_DISPLAY_LIST);
        } else {
            return new CommandResult(MESSAGE_SUCCESS + MESSAGE_ACTIVE_DISPLAY_LIST);
        }
    }
}
