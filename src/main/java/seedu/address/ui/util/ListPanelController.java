package seedu.address.ui.util;

/**
 * Stores the type of list being displayed
 */
public class ListPanelController {
    private static ListPanelController instance = null;

    /**
     * An enum to store which the type of list displayed
     */
    public enum displayType {
        closedList, activeList
    }

    /**
     * Ensure that the active client list is always shown first
     */
    private static displayType currentlyDisplayed = displayType.activeList;

    public displayType getCurrentListDisplayed() {
        return currentlyDisplayed;
    }

    /**
     * Switch the current display
     */
    public void switchDisplay() {
        switch (currentlyDisplayed) {
            case activeList:
                currentlyDisplayed = displayType.closedList;
                break;

            case closedList:
                currentlyDisplayed = displayType.activeList;
                break;

            default:
                throw new AssertionError("This should not be possible.");
        }
    }

    public static boolean isCurrentDisplayActiveList() {
        if(currentlyDisplayed == displayType.activeList) {
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
