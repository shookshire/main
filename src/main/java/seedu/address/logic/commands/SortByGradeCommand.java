package seedu.address.logic.commands;

import seedu.address.model.person.Category;

//@@author olimhc
/**
 *Sort the selected list according to their level in ascending order
 */
public class SortByGradeCommand extends SortCommand {

    public static final String MESSAGE_SORT_DESC = " their level in ascending order.";

    private Category category;

    public SortByGradeCommand(Category category) {
        this.category = category;
    }

    @Override
    public CommandResult execute() {
        switch (category.toString()) {
        case COMMAND_WORD_TUTOR:
            model.sortByGradeFilteredClientTutorList();
            return new CommandResult(MESSAGE_SUCCESS_TUTOR + MESSAGE_SORT_DESC);

        case COMMAND_WORD_STUDENT:
            model.sortByGradeFilteredClientStudentList();
            return new CommandResult(MESSAGE_SUCCESS_STUDENT + MESSAGE_SORT_DESC);

        default:
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
