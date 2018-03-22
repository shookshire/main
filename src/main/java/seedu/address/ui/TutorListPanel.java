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
import seedu.address.commons.events.ui.ClientPanelSelectionChangedEvent;
import seedu.address.model.person.Client;

/**
 * Panel containing the list of tutors.
 */
public class TutorListPanel extends UiPart<Region> {
    private static final String FXML = "TutorListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TutorListPanel.class);

    @FXML
    private ListView<ClientCard> tutorListView;

    public TutorListPanel(ObservableList<Client> tutorList) {
        super(FXML);
        setConnections(tutorList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Client> tutorList) {
        ObservableList<ClientCard> mappedList = EasyBind.map(
                tutorList, (client) -> new ClientCard(client, tutorList.indexOf(client) + 1));
        tutorListView.setItems(mappedList);
        tutorListView.setCellFactory(listView -> new StudentListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void setEventHandlerForSelectionChangeEvent() {
        tutorListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.fine("Selection in person list panel changed to : '" + newValue + "'");
                        raise(new ClientPanelSelectionChangedEvent(newValue));
                    }
                });
    }

    /**
     * Scrolls to the {@code ClientCard} at the {@code index} and selects it.
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
     * Custom {@code ListCell} that displays the graphics of a {@code ClientCard}.
     */
    class StudentListViewCell extends ListCell<ClientCard> {

        @Override
        protected void updateItem(ClientCard person, boolean empty) {
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
