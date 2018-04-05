package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Client in tuitionCor.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client extends Person {

    private final Location location;
    private final Grade grade;
    private final Subject subject;
    private final Category category;
    private int rank = 0;
    private boolean matchedGrade = false;
    private boolean matchedSubject = false;
    private boolean matchedLocation = false;

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Location location,
                  Grade grade, Subject subject, Category category) {
        super(name, phone, email, address, tags);
        requireAllNonNull(location, grade, subject);
        this.location = location;
        this.grade = grade;
        this.subject = subject;
        this.category = category;
    }

    public Location getLocation() {
        return location;
    }

    public Grade getGrade() {
        return grade;
    }

    public Subject getSubject() {
        return subject;
    }

    public Category getCategory() {
        return category;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int value) {
        this.rank = value;
    }

    public boolean getMatchedGrade() {
        return matchedGrade;
    }

    public void setMatchedGrade(boolean isMatch) {
        this.matchedGrade = isMatch;
    }

    public boolean getMatchedSubject() {
        return matchedSubject;
    }

    public void setMatchedSubject(boolean isMatch) {
        this.matchedSubject = isMatch;
    }

    public boolean getMatchedLocation() {
        return matchedLocation;
    }

    public void setMatchedLocation(boolean isMatch) {
        this.matchedLocation = isMatch;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(),
                this.getTags(), location, grade, subject, category);
    }

}
