package org.project.part2.DAOs;

import org.javatuples.Pair;
import org.project.part2.Exceptions.DaoException;
import org.project.part2.DTOs.Movie;

import java.util.List;

public interface MovieDaoInterface {
   public List<Pair<Integer, Movie>> findAllMovies() throws DaoException;

   public Movie findMovieById(int id) throws DaoException;

   public void deleteMovieById(int id) throws DaoException;

   public void addMovie(Movie m) throws DaoException;
//
//   public void addNewMovie(Movie movie);
//
//   public List<Movie> findMovieUsingFilter();
}
