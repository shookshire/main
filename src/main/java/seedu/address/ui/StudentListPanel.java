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
import seedu.address.commons.events.ui.ClientPanelSelectionChangedEvent;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.model.person.Client;

/**
 * Panel containing the list of students.
 */
public class StudentListPanel extends UiPart<Region> {
    private static final String FXML = "StudentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentListPanel.class);

    @FXML
    private ListView<ClientCard> studentListView;

    public StudentListPanel(ObservableList<Client> studentList) {
        super(FXML);
        setConnections(studentList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Client> studentList) {
        ObservableList<ClientCard> mappedList = EasyBind.map(
                studentList, (client) -> new ClientCard(client, studentList.indexOf(client) + 1));
        studentListView.setItems(mappedList);
        studentListView.setCellFactory(listView -> new StudentListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void setEventHandlerForSelectionChangeEvent() {
        studentListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.fine("Selection in student list panel changed to : '" + newValue + "'");
                        raise(new ClientPanelSelectionChangedEvent(newValue));
                    }
                });
    }

    /**
     * Scrolls to the {@code ClientCard} at the {@code index} and selects it.
     */
    private void scrollTo(int index) {
        Platform.runLater(() -> {
            studentListView.scrollTo(index);
            studentListView.getSelectionModel().clearAndSelect(index);
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
        protected void updateItem(ClientCard client, boolean empty) {
            super.updateItem(client, empty);

            if (empty || client == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(client.getRoot());
            }
        }
    }

}
