package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.AddressBookChangedEvent;
import seedu.address.model.person.Category;
import seedu.address.model.person.Client;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Represents the in-memory model of the address book data.
 * All changes to any model should be synchronized.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final FilteredList<Client> filteredStudents;
    private final FilteredList<Client> filteredTutors;
    private final FilteredList<Client> filteredClosedStudents;
    private final FilteredList<Client> filteredClosedTutors;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, UserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        filteredStudents = new FilteredList<>(this.addressBook.getStudentList());
        filteredTutors = new FilteredList<>(this.addressBook.getTutorList());
        filteredClosedStudents = new FilteredList<>(this.addressBook.getClosedStudentList());
        filteredClosedTutors = new FilteredList<>(this.addressBook.getClosedTutorList());

    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlyAddressBook newData) {
        addressBook.resetData(newData);
        indicateAddressBookChanged();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateAddressBookChanged() {
        raise(new AddressBookChangedEvent(addressBook));
    }

    @Override
    public synchronized void deleteClient(Client target, Category category) throws PersonNotFoundException {
        addressBook.removeClient(target, category);
        indicateAddressBookChanged();
    }

    @Override
    public void updateClient(Client target, Client editedPerson, Category category)
            throws DuplicatePersonException, PersonNotFoundException {
        requireAllNonNull(target, editedPerson, category);

        addressBook.updatePerson(target, editedPerson, category);
        indicateAddressBookChanged();
    }

    @Override
    public synchronized void addTutor(Client tutor) throws DuplicatePersonException {
        addressBook.addTutor(tutor);
        updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);
        indicateAddressBookChanged();
    }

    @Override
    public synchronized void addStudent(Client student) throws DuplicatePersonException {
        addressBook.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        indicateAddressBookChanged();
    }

    @Override
    public synchronized void addClosedTutor(Client closedTutor) throws DuplicatePersonException {
        addressBook.addClosedTutor(closedTutor);
        updateFilteredClosedTutorList(PREDICATE_SHOW_ALL_CLOSED_TUTORS);
        indicateAddressBookChanged();
    }

    @Override
    public synchronized void addClosedStudent(Client closedStudent) throws DuplicatePersonException {
        addressBook.addClosedStudent(closedStudent);
        updateFilteredClosedStudentList(PREDICATE_SHOW_ALL_CLOSED_STUDENTS);
        indicateAddressBookChanged();
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Client} backed by the internal list of
     * {@code addressBook}
     */
    @Override
    public ObservableList<Client> getFilteredStudentList() {
        return FXCollections.unmodifiableObservableList(filteredStudents);
    }

    @Override
    public void updateFilteredStudentList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Client} backed by the internal list of
     * {@code addressBook}
     */
    @Override
    public ObservableList<Client> getFilteredTutorList() {
        return FXCollections.unmodifiableObservableList(filteredTutors);
    }

    @Override
    public void updateFilteredTutorList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredTutors.setPredicate(predicate);
    }

    @Override
    public ObservableList<Client> getFilteredClosedTutorList() {
        return FXCollections.unmodifiableObservableList(filteredClosedTutors);
    }

    @Override
    public void updateFilteredClosedTutorList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClosedTutors.setPredicate(predicate);
    }

    @Override
    public ObservableList<Client> getFilteredClosedStudentList() {
        return FXCollections.unmodifiableObservableList(filteredClosedStudents);
    }

    @Override
    public void updateFilteredClosedStudentList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClosedStudents.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && filteredStudents.equals(other.filteredStudents)
                && filteredTutors.equals(other.filteredTutors)
                && filteredClosedStudents.equals(other.filteredClosedStudents)
                && filteredClosedTutors.equals(other.filteredClosedTutors);
    }

}
