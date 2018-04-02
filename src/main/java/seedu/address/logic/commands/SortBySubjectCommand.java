package seedu.address.logic.commands;

/**
 *Sort the selected list according to their subject in alphabetical order
 */
public class SortBySubjectCommand extends SortCommand {

    public static final String MESSAGE_SORT_DESC = " their subject in alphabetical order.";

    private int index;
    private final int tutorIndex = 0;
    private final int studentIndex = 1;

    public SortBySubjectCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        switch (index) {

        case tutorIndex:
            model.sortBySubjectFilteredClientTutorList();
            return new CommandResult(MESSAGE_SUCCESS_TUTOR + MESSAGE_SORT_DESC);

        case studentIndex:
            model.sortBySubjectFilteredClientStudentList();
            return new CommandResult(MESSAGE_SUCCESS_STUDENT + MESSAGE_SORT_DESC);

        default:
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
