package seedu.address.testutil;

import seedu.address.logic.commands.AddClientCommand;
import seedu.address.model.person.Client;

import static seedu.address.logic.parser.CliSyntax.*;

/**
 * A utility class for Client.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Client person) {
        return AddClientCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Client person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        sb.append(PREFIX_LOCATION + person.getLocation().value + " ");
        sb.append(PREFIX_GRADE + person.getGrade().value + " ");
        sb.append(PREFIX_SUBJECT + person.getSubject().value + " ");
        sb.append(PREFIX_CATEGORY + person.getCategory().value);
        return sb.toString();
    }
}
