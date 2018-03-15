package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class LevelTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Level(null));
    }

    @Test
    public void constructor_invalidLevel_throwsIllegalArgumentException() {
        String invalidLevel = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Level(invalidLevel));
    }

    @Test
    public void isValidLevel() {
        // null level
        Assert.assertThrows(NullPointerException.class, () -> Level.isValidLevel(null));

        // invalid level
        assertFalse(Level.isValidLevel("")); // empty string
        assertFalse(Level.isValidLevel(" ")); // spaces only

        // valid level
        assertTrue(Level.isValidLevel("pri2"));
        assertTrue(Level.isValidLevel("-")); // one character
        assertTrue(Level.isValidLevel("pri1, pri2, pri3, sec4, sec5, jc1, jc2")); // long level
    }
}
