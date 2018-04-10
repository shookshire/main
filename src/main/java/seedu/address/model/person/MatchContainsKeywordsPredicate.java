package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;

import java.util.function.Predicate;

//@@author Zhu-Jiahui
/**
 * Tests that a {@code Client}'s {@code Location, Grade and Subject} matches the entered {@code Client}'s
 * {@code Location, Grade and Subject}.
 */
public class MatchContainsKeywordsPredicate implements Predicate<Client> {
    private final Client client;

    public MatchContainsKeywordsPredicate(Client client) {
        this.client = client;
    }

    @Override
    public boolean test(Client other) {
        boolean isMatch = false;
        int rank = 0;

        if (StringUtil.containsWordIgnoreCase(other.getLocation().toString(), client.getLocation().toString())) {
            isMatch = true;
            other.setMatchedLocation(isMatch);
            rank++;
        }
        if (StringUtil.containsWordIgnoreCase(other.getGrade().toString(), client.getGrade().toString())) {
            isMatch = true;
            other.setMatchedGrade(isMatch);
            rank++;
        }
        if (StringUtil.containsWordIgnoreCase(other.getSubject().toString(), client.getSubject().toString())) {
            isMatch = true;
            other.setMatchedSubject(isMatch);
            rank++;
        }
        other.setRank(rank);
        return isMatch;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatchContainsKeywordsPredicate // instanceof handles nulls
                && this.client.equals(((MatchContainsKeywordsPredicate) other).client)); // state check
    }

}
//@@author
