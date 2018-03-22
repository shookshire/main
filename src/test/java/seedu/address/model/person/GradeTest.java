package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class GradeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Grade(null));
    }

    @Test
    public void constructor_invalidSubject_throwsIllegalArgumentException() {
        String invalidSubject = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Grade(invalidSubject));
    }

    @Test
    public void isValidGrade() {
        // null subject
        Assert.assertThrows(NullPointerException.class, () -> Grade.isValidGrade(null));

        // invalid subject
        assertFalse(Grade.isValidGrade("")); // empty string
        assertFalse(Grade.isValidGrade(" ")); // spaces only

        // valid subject
        assertTrue(Grade.isValidGrade("pri3"));
        assertTrue(Grade.isValidGrade("-")); // one character
        assertTrue(Grade.isValidGrade("pri3 pri5 pri6 sec3")); // long subject
    }
}
