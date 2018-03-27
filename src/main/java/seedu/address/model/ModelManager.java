package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.AddressBookChangedEvent;
import seedu.address.model.person.Client;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Represents the in-memory model of the address book data.
 * All changes to any model should be synchronized.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Client> filteredStudents;
    private final FilteredList<Client> filteredTutors;

    private SortedList<Client> sortedFilteredTutors;
    private SortedList<Client> sortedFilteredStudents;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, UserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredStudents = new FilteredList<>(this.addressBook.getStudentList());
        filteredTutors = new FilteredList<>(this.addressBook.getTutorList());
        sortedFilteredTutors = new SortedList<>(filteredTutors);
        sortedFilteredStudents = new SortedList<>(filteredStudents);
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
    public synchronized void deletePerson(Person target) throws PersonNotFoundException {
        addressBook.removePerson(target);
        indicateAddressBookChanged();
    }

    @Override
    public synchronized void addPerson(Person person) throws DuplicatePersonException {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        indicateAddressBookChanged();
    }

    @Override
    public void updatePerson(Person target, Person editedPerson)
            throws DuplicatePersonException, PersonNotFoundException {
        requireAllNonNull(target, editedPerson);

        addressBook.updatePerson(target, editedPerson);
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

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code addressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return FXCollections.unmodifiableObservableList(filteredPersons);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Tutor} backed by the internal list of
     * {@code addressBook}
     */

    @Override
    public void sortByNameFilteredClientTutorList() {
        Comparator<Client> sortByName = (tutor1, tutor2)-> (tutor1.getName().fullName)
                .compareToIgnoreCase(tutor2.getName().fullName);
        sortedFilteredTutors.setComparator(sortByName);
        indicateAddressBookChanged();
    }

    @Override
    public void sortByNameFilteredClientStudentList() {
        Comparator<Client> sortByName = (student1, student2)-> (student1.getName().fullName)
                .compareToIgnoreCase(student2.getName().fullName);
        sortedFilteredStudents.setComparator(sortByName);
        indicateAddressBookChanged();
    }

    @Override
    public void sortByLocationFilteredClientTutorList() {
        Comparator<Client> sortByLocation = (tutor1, tutor2)-> (tutor1.getLocation().value)
                .compareToIgnoreCase(tutor2.getLocation().value);
        sortedFilteredTutors.setComparator(sortByLocation);
        indicateAddressBookChanged();
    }

    @Override
    public void sortByLocationFilteredClientStudentList() {
        Comparator<Client> sortByLocation = (student1, student2)-> (student1.getLocation().value)
                .compareToIgnoreCase(student2.getLocation().value);
        sortedFilteredStudents.setComparator(sortByLocation);
        indicateAddressBookChanged();
    }


    @Override
    public void sortByGradeFilteredClientTutorList() {
        Comparator<Client> sortByGrade = (tutor1, tutor2)-> (tutor1.getGrade().value)
                .compareToIgnoreCase(tutor2.getGrade().value);
        sortedFilteredTutors.setComparator(sortByGrade);
        indicateAddressBookChanged();
    }

    @Override
    public void sortByGradeFilteredClientStudentList() {
        Comparator<Client> sortByGrade = (student1, student2)-> (student1.getGrade().value)
                .compareToIgnoreCase(student2.getGrade().value);
        sortedFilteredStudents.setComparator(sortByGrade);
        indicateAddressBookChanged();
    }

    @Override
    public void sortBySubjectFilteredClientTutorList() {
        Comparator<Client> sortBySubject = (tutor1, tutor2)-> (tutor1.getSubject().value)
                .compareToIgnoreCase(tutor2.getSubject().value);
        sortedFilteredTutors.setComparator(sortBySubject);
        indicateAddressBookChanged();
    }

    @Override
    public void sortBySubjectFilteredClientStudentList() {
        Comparator<Client> sortBySubject = (student1, student2)-> (student1.getSubject().value)
                .compareToIgnoreCase(student2.getSubject().value);
        sortedFilteredStudents.setComparator(sortBySubject);
        indicateAddressBookChanged();
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Client} backed by the internal list of
     * {@code addressBook}
     */
    @Override
    public ObservableList<Client> getFilteredStudentList() {
        return FXCollections.unmodifiableObservableList(sortedFilteredStudents);
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
        return FXCollections.unmodifiableObservableList(sortedFilteredTutors);
    }

    @Override
    public void updateFilteredTutorList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredTutors.setPredicate(predicate);
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
                && filteredTutors.equals(other.filteredTutors);
    }

}
