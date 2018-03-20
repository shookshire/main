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
    public static final String COMMAND_WORD_TUTOR = "t";
    public static final String COMMAND_WORD_STUDENT = "s";

    public static final String MESSAGE_SUCCESS_TUTOR = "Sorted tutor's list according to";
    public static final String MESSAGE_FAILURE = "Unable to sort the list";


    private static final String USAGE_MESSAGE_LIST = " "
            + COMMAND_WORD  + " " + COMMAND_WORD_TUTOR + COMMAND_WORD_NAME + ", "
            + COMMAND_WORD  + " " + COMMAND_WORD_TUTOR + COMMAND_WORD_LOCATION + ", "
            + COMMAND_WORD  + " " + COMMAND_WORD_TUTOR + COMMAND_WORD_SUBJECT + ", "
            + COMMAND_WORD  + " " + COMMAND_WORD_TUTOR + COMMAND_WORD_LEVEL + ", "
            + "to sort Tutor's list base on name, location, subject and level respectively.\n"
            + COMMAND_WORD  + " " + COMMAND_WORD_STUDENT + COMMAND_WORD_NAME + ", "
            + COMMAND_WORD  + " " + COMMAND_WORD_STUDENT + COMMAND_WORD_LOCATION + ", "
            + COMMAND_WORD  + " " + COMMAND_WORD_STUDENT + COMMAND_WORD_SUBJECT + ", "
            + COMMAND_WORD  + " " + COMMAND_WORD_STUDENT + COMMAND_WORD_LEVEL + ", "
            + "to sort Student's list base on name, location, subject and level respectively.\n"
            + "Example: "
            + COMMAND_WORD + COMMAND_WORD_TUTOR + COMMAND_WORD_LOCATION + "\n";


    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort selected list according to user choice.\n"
            + "Parameters:" + USAGE_MESSAGE_LIST;




    @Override
    public abstract CommandResult execute();
}
