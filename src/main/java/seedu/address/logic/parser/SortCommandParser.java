package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SortByGradeCommand;
import seedu.address.logic.commands.SortByLocationCommand;
import seedu.address.logic.commands.SortByNameCommand;
import seedu.address.logic.commands.SortBySubjectCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new subclass object of SortCommand
 */
public class SortCommandParser implements Parser<SortCommand> {

    private final int listIndex = 0;
    private final int sortTypeIndex = 1;
    private final int tutorIndex = 0;
    private final int studentIndex = 1;

    /**
     * Parse the given {@code String} of arguments in the context of SortCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String[] commandArgs = trimmedArgs.split("");

        switch (commandArgs[listIndex]) {
        case SortCommand.COMMAND_WORD_TUTOR:
            return parseSortType(commandArgs[sortTypeIndex], tutorIndex);

        case SortCommand.COMMAND_WORD_STUDENT:
            return parseSortType(commandArgs[sortTypeIndex], studentIndex);

        default:
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

    }

    /**
     * Parse the given {@code String} of arguments further in the context of SortCommand
     * @param args type of sort
     * @param listIndex indicating whether to sort tutor's or student's list
     * @return either SortByGradeCommand, SortByNameCommand, SortByGradeCommand, SortBySubjectCommand
     * object for execution.
     * @throws ParseException
     */
    private SortCommand parseSortType(String args, int listIndex) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        switch (args) {
        case SortCommand.COMMAND_WORD_NAME:
            return new SortByNameCommand(listIndex);

        case SortCommand.COMMAND_WORD_SUBJECT:
            return new SortBySubjectCommand(listIndex);

        case SortCommand.COMMAND_WORD_LOCATION:
            return new SortByLocationCommand(listIndex);

        case SortCommand.COMMAND_WORD_GRADE:
            return new SortByGradeCommand(listIndex);

        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }
}
