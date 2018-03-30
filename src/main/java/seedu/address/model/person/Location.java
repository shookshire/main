package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's available Location in the TuitionCor.
 * Guarantees: immutable; is valid as declared in {@link #isValidLocation(String)}
 */
public class Location {

    public static final String MESSAGE_LOCATION_CONSTRAINTS =
            "Location should only be north, south, east, west, central and only one location should be entered";

    private static final String LOCATION_VALIDATION_REGEX_NORTH = "(?i)North";
    private static final String LOCATION_VALIDATION_REGEX_SOUTH = "(?i)South";
    private static final String LOCATION_VALIDATION_REGEX_EAST = "(?i)East";
    private static final String LOCATION_VALIDATION_REGEX_WEST = "(?i)West";
    private static final String LOCATION_VALIDATION_REGEX_CENTRAL = "(?i)Central";

    public final String value;

    /**
     * Constructs a {@code Location}.
     *
     * @param location A valid location.
     */
    public Location(String location) {
        requireNonNull(location);
        checkArgument(isValidLocation(location), MESSAGE_LOCATION_CONSTRAINTS);
        this.value = location;
    }

    /**
     * Returns true if a given string is a valid person Location.
     */
    public static boolean isValidLocation(String test) {
        return test.matches(LOCATION_VALIDATION_REGEX_NORTH) || test.matches(LOCATION_VALIDATION_REGEX_EAST)
                || test.matches(LOCATION_VALIDATION_REGEX_SOUTH)
                || test.matches(LOCATION_VALIDATION_REGEX_WEST)
                || test.matches(LOCATION_VALIDATION_REGEX_CENTRAL);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Location // instanceof handles nulls
                && this.value.equals(((Location) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
