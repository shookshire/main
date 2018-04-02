package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.CommandNotAvailableInClosedViewException;
import seedu.address.ui.util.ListPanelController;

//@@author olimhc
/**
 *Sort the selected list according to their location in alphabetical order
 */
public class SortByLocationCommand extends SortCommand {

    private static final String MESSAGE_SORT_DESC = " their location in alphabetical order.";

    private int index;
    private final int tutorIndex = 0;
    private final int studentIndex = 1;

    public SortByLocationCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() throws CommandException {
        if (!ListPanelController.isCurrentDisplayActiveList()) {
            throw new CommandNotAvailableInClosedViewException();
        }

        switch (index) {

        case tutorIndex:
            model.sortByLocationFilteredClientTutorList();
            return new CommandResult(MESSAGE_SUCCESS_TUTOR + MESSAGE_SORT_DESC);

        case studentIndex:
            model.sortByLocationFilteredClientStudentList();
            return new CommandResult(MESSAGE_SUCCESS_STUDENT + MESSAGE_SORT_DESC);

        default:
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
