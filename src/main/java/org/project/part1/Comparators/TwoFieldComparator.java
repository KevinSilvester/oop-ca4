package org.project.part1.Comparators;

import org.project.part1.Movie;
import org.project.part1.Enumerators.SortType;

import java.util.Comparator;

public class TwoFieldComparator implements Comparator<Movie> {
    private final SortType sortType;

    public TwoFieldComparator(SortType sortType) {
        this.sortType = sortType;
    }

    @Override
    public int compare(Movie m1, Movie m2) {
        String title1 = m1.getTitle(),
               title2 = m2.getTitle();
        int    year1  = m1.getYear(),
               year2  = m2.getYear();

        if (title1 == null) {
            return -1 * sortType.getValue();
        }
        else if (title2 == null) {
            return sortType.getValue();
        }
        else if (title1.equalsIgnoreCase(title2)) {
            if (year1 > year2) {
                return sortType.getValue();
            }
            if (year1 < year2) {
                return -1 * sortType.getValue();
            }
            return 0;
        }
        return sortType.getValue() * title1.compareToIgnoreCase(title2);
    }
}
