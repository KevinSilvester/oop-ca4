package org.project;

import java.util.Comparator;

public class YearComparator implements Comparator<Movie> {
    private final SortType sortType;

    public YearComparator(SortType sortType) {
        this.sortType = sortType;
    }

    @Override
    public int compare(Movie m1, Movie m2) {
        int year1 = m1.getYear(), year2 = m2.getYear();

        if (year1 > year2)
            return sortType.getValue();
        if (year1 < year2)
            return -1 * sortType.getValue();
        return 0;
    }
}
