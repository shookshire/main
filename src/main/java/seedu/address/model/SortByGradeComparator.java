package seedu.address.model;

import seedu.address.model.person.Client;

import java.util.Comparator;

/**
 * Comparator to sort by int base on valueWeight
 */
public class SortByGradeComparator
        implements Comparator<Client> {

    @Override
    public int compare(Client o1, Client o2) {
        return o1.getGrade().valueWeightage - o2.getGrade().valueWeightage;
    }
}
