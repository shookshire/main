package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Client in tuitionCor.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client extends Person {

    private final ArrayList<Location> location;
    private final ArrayList<Level> level;
    private final ArrayList<Subject> subject;

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, Phone phone, Email email, Address address, Set<Tag> tags, ArrayList<Location> location, ArrayList<Level> level, ArrayList<Subject> subject) {
        super(name, phone, email, address, tags);
        requireAllNonNull(location, level, subject);
        this.location = location;
        this.level = level;
        this.subject = subject;
    }

    public boolean hasLocation(Location toCheck) {
        return location.contains(toCheck);
    }

    public boolean hasLevel(Level toCheck) {
        return level.contains(toCheck);
    }

    public boolean hasSubject(Subject toCheck) {
        return subject.contains(toCheck);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(), this.getTags(), location, level, subject);
    }

}
