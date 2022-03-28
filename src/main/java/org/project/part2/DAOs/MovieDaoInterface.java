package org.project.part2.DAOs;

import org.project.part2.Exceptions.DaoException;
import org.project.part2.DTOs.Movie;

import java.util.List;

public interface MovieDaoInterface {
   public List<Movie> findAllMovies() throws DaoException;

   public Movie findMovieById(int id) throws DaoException;

//   public Movie deleteMovieById(int id) throws DaoException;
//
//   public void addNewMovie(Movie movie);
//
//   public List<Movie> findMovieUsingFilter();
}
