package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.CommandNotAvailableInClosedViewException;
import seedu.address.model.person.Category;
import seedu.address.ui.util.ListPanelController;

//@@author olimhc
/**
 *Sort the selected list according to their name in alphabetical order
 */
public class SortByNameCommand extends SortCommand {

    public static final String MESSAGE_SORT_DESC = " their name in alphabetical order.";

    private Category category;

    public SortByNameCommand(Category category) {
        this.category = category;
    }

    @Override
    public CommandResult execute() throws CommandException {
        if (!ListPanelController.isCurrentDisplayActiveList()) {
            throw new CommandNotAvailableInClosedViewException();
        }

        switch (category.toString()) {
        case COMMAND_WORD_TUTOR:
            model.sortByNameFilteredClientTutorList();
            return new CommandResult(MESSAGE_SUCCESS_TUTOR + MESSAGE_SORT_DESC);

        case COMMAND_WORD_STUDENT:
            model.sortByNameFilteredClientStudentList();
            return new CommandResult(MESSAGE_SUCCESS_STUDENT + MESSAGE_SORT_DESC);

        default:
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
