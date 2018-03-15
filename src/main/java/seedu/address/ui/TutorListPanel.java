package seedu.address.ui;

import java.util.logging.Logger;

import org.fxmisc.easybind.EasyBind;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of tutors.
 */
public class TutorListPanel extends UiPart<Region> {
    private static final String FXML = "TutorListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TutorListPanel.class);

    @FXML
    private ListView<PersonCard> tutorListView;

    public TutorListPanel(ObservableList<Person> tutorList) {
        super(FXML);
        setConnections(tutorList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Person> tutorList) {
        ObservableList<PersonCard> mappedList = EasyBind.map(
                tutorList, (person) -> new PersonCard(person, tutorList.indexOf(person) + 1));
        tutorListView.setItems(mappedList);
        tutorListView.setCellFactory(listView -> new StudentListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void setEventHandlerForSelectionChangeEvent() {
        tutorListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.fine("Selection in person list panel changed to : '" + newValue + "'");
                        raise(new PersonPanelSelectionChangedEvent(newValue));
                    }
                });
    }

    /**
     * Scrolls to the {@code PersonCard} at the {@code index} and selects it.
     */
    private void scrollTo(int index) {
        Platform.runLater(() -> {
            tutorListView.scrollTo(index);
            tutorListView.getSelectionModel().clearAndSelect(index);
        });
    }

    @Subscribe
    private void handleJumpToListRequestEvent(JumpToListRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        scrollTo(event.targetIndex);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code PersonCard}.
     */
    class StudentListViewCell extends ListCell<PersonCard> {

        @Override
        protected void updateItem(PersonCard person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(person.getRoot());
            }
        }
    }

}
