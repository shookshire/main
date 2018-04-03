package seedu.address.logic.commands;

import seedu.address.model.person.Category;

/**
 *Sort the selected list according to their level in ascending order
 */
public class SortByGradeCommand extends SortCommand {

    public static final String MESSAGE_SORT_DESC = " their level in ascending order.";

    private Category category;
    private final String TUTOR_COMMAND_WORD = "t";
    private final String STUDENT_COMMAND_WORD = "s";

    public SortByGradeCommand(Category category) {
        this.category = category;
    }

    @Override
    public CommandResult execute() {
        switch (category.toString()) {

        case TUTOR_COMMAND_WORD:
            model.sortByGradeFilteredClientTutorList();
            return new CommandResult(MESSAGE_SUCCESS_TUTOR + MESSAGE_SORT_DESC);

        case STUDENT_COMMAND_WORD:
            model.sortByGradeFilteredClientStudentList();
            return new CommandResult(MESSAGE_SUCCESS_STUDENT + MESSAGE_SORT_DESC);

        default:
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
