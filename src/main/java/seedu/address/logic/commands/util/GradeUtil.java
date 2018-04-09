package seedu.address.logic.commands.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.person.Grade.getGradeIndex;
import static seedu.address.model.person.Grade.isValidGrade;

/**
 * Helper function for handling different format of grade
 */
public class GradeUtil {

    /**
     * Returns true if the {@code valueWeightage} matches the {@code word} given that word is a valid grade
     *   <br>examples:<pre>
     *       A p3 grade should match primary3.
     *       </pre>
     * @param valueWeightage cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsGradeIgnoreCase(int valueWeightage, String word) {
        requireNonNull(valueWeightage);
        requireNonNull(word);

        if(!isValidGrade(word)) {
            return false;
        }

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        int preppedWordValueWeightage = getGradeIndex(preppedWord);

        if(valueWeightage == preppedWordValueWeightage) {
            return true;
        }
        return false;
    }
}
