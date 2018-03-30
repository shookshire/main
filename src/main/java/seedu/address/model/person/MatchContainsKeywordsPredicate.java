package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Client}'s {@code Location, Grade and Subject} matches the entered {@code Client}'s {@code Location, Grade and Subject}.
 */
public class MatchContainsKeywordsPredicate implements Predicate<Client> {
    private final Client client;

    public MatchContainsKeywordsPredicate(Client client) {
        this.client = client;
    }

    @Override
    public boolean test(Client other) {
        boolean isMatch = false;
        int count = 0;

        System.out.println("0th: " + count);
        if(other.getLocation().equals(client.getLocation())) {
            count++;
            isMatch = true;
            System.out.println("1st: " + count);
        }
        if(other.getGrade().equals(client.getGrade())) {
            count++;
            isMatch = true;
            System.out.println("2nd: " + count);
        }
        if(other.getSubject().equals(client.getSubject())) {
            count++;
            isMatch = true;
            System.out.println("3rd: " + count);
        }
        client.setRank(count);
        System.out.println(client.getName().fullName + client.getRank() );
        return isMatch;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatchContainsKeywordsPredicate // instanceof handles nulls
                && this.client.equals(((MatchContainsKeywordsPredicate) other).client)); // state check
    }

}
