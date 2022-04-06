package org.project.part3.comparators;

import org.project.part3.enumerators.SortType;

import java.util.Comparator;

public class MapKeyComparator implements Comparator<String> {
    private final SortType sortType;

    public MapKeyComparator(SortType sortType) {
        this.sortType = sortType;
    }

    @Override
    public int compare(String s1, String s2) {
        if (s1 == null) {
            return -1 * sortType.getValue();
        }
        if (s2 == null) {
            return sortType.getValue();
        }
        if (s1.equalsIgnoreCase(s2)) {
            return 0;
        }
        return sortType.getValue() *  s1.compareTo(s2);
    }
}
