package seedu.address.ui.util;

//@@author olimhc
/**
 * Stores the type of list being displayed
 */
public class ListPanelController {
    private static ListPanelController instance = null;

    /**
     * An enum to store which the type of list displayed
     */
    public enum DisplayType {
        closedList, activeList
    }

    /**
     * Ensure that the active client list is always shown first
     */
    private static DisplayType currentlyDisplayed = DisplayType.activeList;

    public DisplayType getCurrentListDisplayed() {
        return currentlyDisplayed;
    }

    /**
     * Switch the current display
     */
    public void switchDisplay() {
        switch (currentlyDisplayed) {
        case activeList:
            currentlyDisplayed = DisplayType.closedList;
            break;

        case closedList:
            currentlyDisplayed = DisplayType.activeList;
            break;

        default:
            throw new AssertionError("This should not be possible.");
        }
    }

    /**
     * @return true if displayed list is active list
     */
    public static boolean isCurrentDisplayActiveList() {
        if (currentlyDisplayed == DisplayType.activeList) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Ensure that only one instance of ListPanelController is created
     * @return instance
     */
    public static ListPanelController getInstance() {
        if (instance == null) {
            instance = new ListPanelController();
        }

        return instance;
    }
}
