package org.project.part2.comparators;

import org.javatuples.Pair;
import org.project.part2.DTOs.Movie;
import org.project.part2.enumerators.SortType;

import java.util.Comparator;

public class PairTwoFieldComparator implements Comparator<Pair<Integer, Movie>> {
   private final SortType sortType;

   public PairTwoFieldComparator(SortType sortType) {
      this.sortType = sortType;
   }

   @Override
   public int compare(Pair<Integer, Movie> o1, Pair<Integer, Movie> o2) {
      return (new TwoFieldComparator(sortType).compare(o1.getValue1(), o2.getValue1()));
   }
}
