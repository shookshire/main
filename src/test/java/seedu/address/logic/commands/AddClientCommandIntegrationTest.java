package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Client;
import seedu.address.testutil.ClientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddClientCommandIntegrationTest {


    private Model model;

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() throws Exception {
        Client validClient = new ClientBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addStudent(validClient);

        assertCommandSuccess(prepareCommand(validClient, model), model,
                String.format(AddClientCommand.MESSAGE_SUCCESS_STUDENT, validClient), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Client personInList = model.getAddressBook().getStudentList().get(0);
        assertCommandFailure(prepareCommand(personInList, model), model, AddClientCommand.MESSAGE_DUPLICATE_PERSON);
    }

    /**
     * Generates a new {@code AddCommand} which upon execution, adds {@code person} into the {@code model}.
     */
    private AddClientCommand prepareCommand(Client client, Model model) {
        AddClientCommand command = new AddClientCommand(client);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }
}
