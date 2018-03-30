package seedu.address.logic.commands;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Category;
import seedu.address.model.person.Client;
import seedu.address.model.person.MatchContainsKeywordsPredicate;
import seedu.address.model.person.MatchContainsPersonsPredicate;


/**
 * Finds and lists all persons in address book that contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class MatchCommand extends Command {

    public static final String COMMAND_WORD = "match";
    public static final String COMMAND_ALIAS = "m";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all persons that match all the fields listed by the person entered.\n"
            + "Example: " + COMMAND_WORD + " alice";

    private final Index targetIndex;
    private final Category category;

    private Client clientToMatch;

    public MatchCommand(Index index, Category category) {
        this.targetIndex = index;
        this. category = category;
    }

    @Override
    public CommandResult execute() throws CommandException {
        List<Client> lastShownList;

        if (category.isStudent()) {
            lastShownList = model.getFilteredStudentList();
        } else {
            lastShownList = model.getFilteredTutorList();
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        clientToMatch = lastShownList.get(targetIndex.getZeroBased());

        MatchContainsKeywordsPredicate predicate = new MatchContainsKeywordsPredicate(clientToMatch);

        model.updateFilteredStudentList(predicate);
        model.updateFilteredTutorList(predicate);
        return new CommandResult(getMessageForClientListShownSummary(
                model.getFilteredStudentList().size(), model.getFilteredTutorList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatchCommand // instanceof handles nulls
                && this.targetIndex.equals(((MatchCommand) other).targetIndex))
                && this.category.equals(((MatchCommand) other).category); // state check
    }
}
