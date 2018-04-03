package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.SortByGradeCommand;
import seedu.address.logic.commands.SortByLocationCommand;
import seedu.address.logic.commands.SortByNameCommand;
import seedu.address.logic.commands.SortBySubjectCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Category;

import java.util.stream.Stream;

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
     * @return either SortByGradeCommand, SortByNameCommand, SortByGradeCommand, SortBySubjectCommand
     * object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY);

        if (!arePrefixesPresent(argMultimap, PREFIX_CATEGORY)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        Category category;
        String sortType;

        try {
            sortType = ParserUtil.parseSortType(argMultimap.getPreamble());
            category = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY)).get();
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        switch (sortType) {
            case SortCommand.COMMAND_WORD_NAME:
                return new SortByNameCommand(category);

            case SortCommand.COMMAND_WORD_SUBJECT:
                return new SortBySubjectCommand(category);

            case SortCommand.COMMAND_WORD_LOCATION:
                return new SortByLocationCommand(category);

            case SortCommand.COMMAND_WORD_GRADE:
                return new SortByGradeCommand(category);

            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
