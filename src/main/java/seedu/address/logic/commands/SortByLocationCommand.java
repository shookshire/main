package seedu.address.logic.commands;

/**
 *Sort the selected list according to their location in alphabetical order
 */
public class SortByLocationCommand extends SortCommand {

    private int index;
    private final int tutorIndex = 0;
    //private final int studentIndex = 1;

    public SortByLocationCommand(int index) {
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
            model.sortByLocationFilteredPersonList();
            return new CommandResult(MESSAGE_SUCCESS);

        default:
            return new CommandResult(MESSAGE_FAILURE);
        }
    }
}
