package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.ObservableList;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Category;
import seedu.address.model.person.Client;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.testutil.ClientBuilder;

public class AddClientCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructor_nullClient_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddClientCommand(null);
    }

    @Test
    public void execute_clientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingClientAdded modelStub = new ModelStubAcceptingClientAdded();
        Client validClient = new ClientBuilder().build();

        CommandResult commandResult = getAddClientCommandForPerson(validClient, modelStub).execute();

        assertEquals(String.format(AddClientCommand.MESSAGE_SUCCESS_STUDENT, validClient),
                commandResult.feedbackToUser);
        assertEquals(Arrays.asList(validClient), modelStub.studentsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() throws Exception {
        ModelStub modelStub = new ModelStubThrowingDuplicateClientException();
        Client validClient = new ClientBuilder().build();

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddClientCommand.MESSAGE_DUPLICATE_PERSON);

        getAddClientCommandForPerson(validClient, modelStub).execute();
    }

    @Test
    public void equals() {
        Client alice = new ClientBuilder().withName("Alice").build();
        Client bob = new ClientBuilder().withName("Bob").build();
        AddClientCommand addAliceCommand = new AddClientCommand(alice);
        AddClientCommand addBobCommand = new AddClientCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddClientCommand addAliceCommandCopy = new AddClientCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * Generates a new AddClientCommand with the details of the given person.
     */
    private AddClientCommand getAddClientCommandForPerson(Client client, Model model) {
        AddClientCommand command = new AddClientCommand(client);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void resetData(ReadOnlyAddressBook newData) {
            fail("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void deleteClient(Client target, Category category) throws PersonNotFoundException {
            fail("This method should not be called.");
        }

        @Override
        public void updateClient(Client target, Client editedPerson, Category category)
                throws DuplicatePersonException, PersonNotFoundException {
            fail("This method should not be called.");
        }

        @Override
        public void addTutor(Client tutor) throws DuplicatePersonException {
            fail("This method should not be called.");
        }

        @Override
        public void addStudent(Client student) throws DuplicatePersonException {
            fail("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getFilteredStudentList() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void updateFilteredStudentList(Predicate<Client> predicate) {
            fail("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getFilteredTutorList() {
            fail("This method should not be called.");
            return null;
        }

        @Override
        public void updateFilteredTutorList(Predicate<Client> predicate) {
            fail("This method should not be called.");
        }

        @Override
        public void updateRankedStudentList() {
            fail("This method should not be called.");
        }

        @Override
        public void updateRankedTutorList() {
            fail("This method should not be called.");
        }

        @Override
        public void sortByNameFilteredClientTutorList() {
            fail("This method should not be called.");
        }

        @Override
        public void sortByLocationFilteredClientTutorList() {
            fail("This method should not be called.");
        }

        @Override
        public void sortByGradeFilteredClientTutorList() {
            fail("This method should not be called.");
        }

        @Override
        public void sortBySubjectFilteredClientTutorList() {
            fail("This method should not be called.");
        }

        public void sortByNameFilteredClientStudentList() {
            fail("This method should not be called.");
        }

        public void sortByLocationFilteredClientStudentList() {
            fail("This method should not be called.");
        }

        public void sortByGradeFilteredClientStudentList() {
            fail("This method should not be called.");
        }

        public void sortBySubjectFilteredClientStudentList() {
            fail("This method should not be called.");
        }
    }

    /**
     * A Model stub that always throw a DuplicatePersonException when trying to add a person.
     */
    private class ModelStubThrowingDuplicateClientException extends AddClientCommandTest.ModelStub {
        @Override
        public void addTutor(Client tutor) throws DuplicatePersonException {
            throw new DuplicatePersonException();
        }

        @Override
        public void addStudent(Client student) throws DuplicatePersonException {
            throw new DuplicatePersonException();
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

    /**
     * A Model stub that always accept the client being added.
     */
    private class ModelStubAcceptingClientAdded extends AddClientCommandTest.ModelStub {
        final ArrayList<Client> tutorsAdded = new ArrayList<>();
        final ArrayList<Client> studentsAdded = new ArrayList<>();

        @Override
        public void addTutor(Client tutor) throws DuplicatePersonException {
            requireNonNull(tutor);
            tutorsAdded.add(tutor);
        }

        @Override
        public void addStudent(Client student) throws DuplicatePersonException {
            requireNonNull(student);
            studentsAdded.add(student);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
