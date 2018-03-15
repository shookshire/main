package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Location {

    public static final String MESSAGE_LOCATION_CONSTRAINTS =
            "Location should only be north, south, east, west, central";
    public static final String LOCATION_NORTH = "north";
    public static final String LOCATION_SOUTH = "south";
    public static final String LOCATION_EAST = "east";
    public static final String LOCATION_WEST = "west";
    public static final String LOCATION_CENTRAL = "central";
    public final String value;

    /**
     * Constructs a {@code Location}.
     *
     * @param location A valid location.
     */
    public Location(String location) {
        requireNonNull(location);
        checkArgument(isValidPhone(location), MESSAGE_LOCATION_CONSTRAINTS);
        this.value = location;
    }

    /**
     * Returns true if a given string is a valid person phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.equals(LOCATION_CENTRAL) || test.equals(LOCATION_EAST) || test.equals(LOCATION_NORTH) || test.equals(LOCATION_SOUTH) || test.equals(LOCATION_WEST);
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
