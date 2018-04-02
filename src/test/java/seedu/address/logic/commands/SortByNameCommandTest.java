package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.SortedClients.getSortedByNameAddressBook;
import static seedu.address.testutil.UnsortedClients.getUnsortedAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

//@@author olimhc
/**
 * Contains integration tests (interaction with the Model) for {@code SortByNameCommand}.
 */
public class SortByNameCommandTest {
    private Model model;
    private Model expectedModel;
    private SortByNameCommand sortByNameCommandForStudentList;
    private SortByNameCommand sortByNameCommandForTutorList;

    private final int tutorIndex = 0;
    private final int studentIndex = 1;

    @Before
    public void setup() {
        model = new ModelManager(getUnsortedAddressBook(), new UserPrefs());
        expectedModel =  new ModelManager(getSortedByNameAddressBook(), new UserPrefs());

        sortByNameCommandForStudentList = new SortByNameCommand(studentIndex);
        sortByNameCommandForStudentList.setData(model, new CommandHistory(), new UndoRedoStack());
        sortByNameCommandForTutorList = new SortByNameCommand(tutorIndex);
        sortByNameCommandForTutorList.setData(model, new CommandHistory(), new UndoRedoStack());
    }

    @Test
    public void executeSortUnsortedToSortedByName() {
        assertCommandSuccessForStudentList(sortByNameCommandForStudentList, (SortByNameCommand.MESSAGE_SUCCESS_STUDENT
                        + SortByNameCommand.MESSAGE_SORT_DESC));
        assertCommandSuccessForTutorList(sortByNameCommandForTutorList, (SortByNameCommand.MESSAGE_SUCCESS_TUTOR
                        + SortByNameCommand.MESSAGE_SORT_DESC));
    }

    /**
     * Asserts that the code is successfully executed.
     * Asserts the command feedback is equal to expectedMessage.
     * Asserts actual FilteredList is equal to the expected FilteredList
     * Asserts the {@code AddressBook} in model remains the same after executing the {@code command}
     */
    private void assertCommandSuccessForStudentList(SortByNameCommand command, String expectedMessage) {
        AddressBook expectedAddressBook = new AddressBook(model.getAddressBook());
        CommandResult commandResult = command.execute();

        assertEquals(expectedMessage, commandResult.feedbackToUser);
        assertEquals(expectedModel.getFilteredStudentList(), model.getFilteredStudentList());
        assertEquals(expectedAddressBook, model.getAddressBook());
    }

    /**
     * Asserts that the code is successfully executed.
     * Asserts the command feedback is equal to expectedMessage.
     * Asserts actual FilteredList is equal to the expected FilteredList
     * Asserts the {@code AddressBook} in model remains the same after executing the {@code command}
     */
    private void assertCommandSuccessForTutorList(SortByNameCommand command, String expectedMessage) {
        AddressBook expectedAddressBook = new AddressBook(model.getAddressBook());
        CommandResult commandResult = command.execute();

        assertEquals(expectedMessage, commandResult.feedbackToUser);
        assertEquals(expectedModel.getFilteredTutorList(), model.getFilteredTutorList());
        assertEquals(expectedAddressBook, model.getAddressBook());
    }
}
