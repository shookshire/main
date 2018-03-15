package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's related Level in the TuitionCor.
 * Guarantees: immutable; is valid as declared in {@link #isValidLevel(String)}
 */
public class Level {

    public static final String MESSAGE_SUBJECT_CONSTRAINTS =
            "Level can be any specified level but should not be blank";

    /*
     * The first character of the Subject must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String ADDRESS_VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Level}.
     *
     * @param level A valid level.
     */
    public Level(String level) {
        requireNonNull(level);
        checkArgument(isValidLevel(level), MESSAGE_SUBJECT_CONSTRAINTS);
        this.value = level;
    }

    /**
     * Returns true if a given string is a valid person Level.
     */
    public static boolean isValidLevel(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Subject // instanceof handles nulls
                && this.value.equals(((Subject) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
