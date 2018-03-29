package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class MatchContainsPersonsPredicate implements Predicate<Client> {
    private final Client client;

    public MatchContainsPersonsPredicate(Client client) {
        this.client = client;
    }

    @Override
    public boolean test(Client other) {
        return other.toString().equals(client.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatchContainsPersonsPredicate // instanceof handles nulls
                && this.client.equals(((MatchContainsPersonsPredicate) other).client)); // state check
    }

}
