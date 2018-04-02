package seedu.address.logic.commands;

/**
 *Sort the selected list according to their level in ascending order
 */
public class SortByGradeCommand extends SortCommand {

    public static final String MESSAGE_SORT_DESC = " their level in ascending order.";

    private int index;
    private final int tutorIndex = 0;
    private final int studentIndex = 1;

    public SortByGradeCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        switch (index) {

        case tutorIndex:
            model.sortByGradeFilteredClientTutorList();
            return new CommandResult(MESSAGE_SUCCESS_TUTOR + MESSAGE_SORT_DESC);

        case studentIndex:
            model.sortByGradeFilteredClientStudentList();
            return new CommandResult(MESSAGE_SUCCESS_STUDENT + MESSAGE_SORT_DESC);

        default:
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
