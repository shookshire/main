package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents if a Client is a student or tutor in TuitionCor.
 * Guarantees: immutable; is valid as declared in {@link #isValidType(String)}
 */
public class Type {

    public static final String MESSAGE_TYPE_CONSTRAINTS =
            "Client Type can only be s or t, representing student or tutor respectively";

    /*
     * Must be either s or t
     */
    public static final String ADDRESS_VALIDATION_REGEX = "[st]";

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param type A valid address.
     */
    public Type(String type) {
        requireNonNull(type);
        checkArgument(isValidType(type), MESSAGE_TYPE_CONSTRAINTS);
        this.value = type;
    }

    /**
     * Returns true if a given string is a valid client type.
     */
    public static boolean isValidType(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    public boolean isStudent() {
        return value.equals("s");
    }

    public boolean isTutor() {
        return value.equals("t");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Type // instanceof handles nulls
                && this.value.equals(((Type) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
