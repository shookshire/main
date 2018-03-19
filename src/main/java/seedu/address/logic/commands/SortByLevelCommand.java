package seedu.address.logic.commands;

/**
 *Sort the selected list according to their level in ascending order
 */
public class SortByLevelCommand extends SortCommand {

    private static final String MESSAGE_SORT_DESC = " their level in ascending order.";

    private int index;
    private final int tutorIndex = 0;
    //private final int studentIndex = 1;

    public SortByLevelCommand(int index) {
        this.index = index;
    }

    /**
     * @// TODO: 19/3/2018 Update to sort by different list
     * @return
     */
    @Override
    public CommandResult execute() {
        switch (index) {

        case tutorIndex:
            model.sortByLevelFilteredPersonList();
            return new CommandResult(MESSAGE_SUCCESS + MESSAGE_SORT_DESC);

        default:
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
