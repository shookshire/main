package seedu.address.logic.commands;

/**
 * Represents a sort command
 */
public abstract class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String COMMAND_ALIAS = "so";

    public static final String COMMAND_WORD_NAME = "n";
    public static final String COMMAND_WORD_LOCATION = "o";
    public static final String COMMAND_WORD_SUBJECT = "s";
    public static final String COMMAND_WORD_LEVEL = "l";
    public static final String COMMAND_WORD_TUTORS = "t";
    public static final String COMMAND_WORD_STUDENTS = "s";

    public static final String MESSAGE_SUCCESS = "Sorted all persons according to";
    public static final String MESSAGE_FAILURE = "Unable to sort the list";


    private static final String USAGE_MESSAGE_LIST = "Enter"
            + COMMAND_WORD  + " " + COMMAND_WORD_TUTORS + " to sort Tutor's list.\n"
            + COMMAND_WORD + " " + COMMAND_WORD_STUDENTS + " to sort Student's list.\n"
            + "Followed by:"
            + COMMAND_WORD_NAME + " to sort by name.\n"
            + COMMAND_WORD_LOCATION + " to sort by location.\n"
            + COMMAND_WORD_SUBJECT + " to sort by subjects.\n"
            + COMMAND_WORD_LEVEL + " to sort by level.\n"
            + "Example: "
            + COMMAND_WORD + COMMAND_WORD_TUTORS + COMMAND_WORD_LOCATION
            + "to sort Tutor's list based on location\n";


    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort selected list according to user choice.\n"
            + "Parameters: " + USAGE_MESSAGE_LIST;




    @Override
    public abstract CommandResult execute();
}
