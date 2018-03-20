package seedu.address.logic.commands;

import seedu.address.model.person.MatchContainsKeywordsPredicate;
import seedu.address.model.person.MatchContainsPersonsPredicate;
import seedu.address.model.person.Person;

import java.util.Arrays;
/**
 * Finds and lists all persons in address book that contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class MatchCommand extends Command {

    public static final String COMMAND_WORD = "match";
    public static final String COMMAND_ALIAS = "m";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons that match all the fields listed by the person entered.\n"
            + "Example: " + COMMAND_WORD + " alice";

    private final MatchContainsKeywordsPredicate predicate;

    public MatchCommand(MatchContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute() {
        model.updateFilteredPersonList(predicate);
        Person A = model.getFilteredPersonList().get(0);
        String B = A.toString();
        String[] C = B.split("\\s+");
        model.updateFilteredPersonList(new MatchContainsPersonsPredicate(Arrays.asList(C)));
        return new CommandResult(getMessageForPersonListShownSummary(model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatchCommand // instanceof handles nulls
                && this.predicate.equals(((MatchCommand) other).predicate)); // state check
    }
}
