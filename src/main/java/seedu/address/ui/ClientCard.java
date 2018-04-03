package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Client;

/**
 * An UI component that displays information of a {@code Client}.
 */
public class ClientCard extends UiPart<Region> {

    private static final String FXML = "ClientListCard.fxml";

    private static final String[] TAGS_COLOUR_STYLES = {"red" , "blue" , "green" , "yellow"};

    private static final String MATCH_COLOUR_STYLE = "orange";
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Client client;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane places;
    @FXML
    private FlowPane grades;
    @FXML
    private FlowPane subjects;
    @FXML
    private FlowPane tags;


    public ClientCard(Client client, int displayedIndex) {
        super(FXML);
        this.client = client;
        id.setText(displayedIndex + ". ");
        name.setText(client.getName().fullName);
        phone.setText(client.getPhone().value);
        address.setText(client.getAddress().value);
        email.setText(client.getEmail().value);
        intPlaces(client);
        intGrades(client);
        intSubjects(client);
        intTags(client);
    }

    /**
     *@author olimhc-reused
     *Reused from https://github.com/se-edu/addressbook-level4/pull/798/commits with minor modification
     * Initialises tags
     * @param client
     */
    private void intTags(Client client) {
        client.getTags().forEach(tag -> {
            Label newLabel = new Label(tag.tagName);
            newLabel.getStyleClass().add(getColour(tag.tagName));
            tags.getChildren().add(newLabel);
        });
    }

    /**
     *@author olimhc-reused
     *Reused from https://github.com/se-edu/addressbook-level4/pull/798/commits with minor modification
     * Initialises tags
     * @param client
     */
    private void intPlaces(Client client) {
        Label newLabel = new Label(client.getLocation().value);
        newLabel.getStyleClass().add(MATCH_COLOUR_STYLE);
        places.getChildren().add(newLabel);
    }

    /**
     *@author olimhc-reused
     *Reused from https://github.com/se-edu/addressbook-level4/pull/798/commits with minor modification
     * Initialises tags
     * @param client
     */
    private void intGrades(Client client) {
        Label newLabel = new Label(client.getGrade().value);
        newLabel.getStyleClass().add(MATCH_COLOUR_STYLE);
        grades.getChildren().add(newLabel);
    }

    /**
     *@author olimhc-reused
     *Reused from https://github.com/se-edu/addressbook-level4/pull/798/commits with minor modification
     * Initialises tags
     * @param client
     */
    private void intSubjects(Client client) {
        Label newLabel = new Label(client.getSubject().value);
        newLabel.getStyleClass().add(MATCH_COLOUR_STYLE);
        subjects.getChildren().add(newLabel);
    }

    /**
     * @param name
     * @return String colour
     */
    private String getColour(String name) {
        return TAGS_COLOUR_STYLES[(Math.abs(name.hashCode() % TAGS_COLOUR_STYLES.length))];
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClientCard)) {
            return false;
        }

        // state check
        ClientCard card = (ClientCard) other;
        return id.getText().equals(card.id.getText())
                && client.equals(card.client);
    }
}
