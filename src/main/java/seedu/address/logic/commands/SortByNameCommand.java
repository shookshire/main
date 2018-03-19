package seedu.address.logic.commands;

/**
 *Sort the selected list according to their name in alphabetical order
 */
public class SortByNameCommand extends SortCommand {

    private int index;
    private final int tutorIndex = 0;
    //private final int studentIndex = 1;

    public SortByNameCommand(int index) {
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
            model.sortByNameFilteredPersonList();
            return new CommandResult(MESSAGE_SUCCESS);

        default:
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
