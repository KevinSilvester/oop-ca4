package org.project.part2.DAOs;

import org.javatuples.Pair;
import org.project.part2.Exceptions.DaoException;
import org.project.part2.DTOs.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlMovieDao extends MySqlDao implements MovieDaoInterface {

   @Override
   public List<Pair<Integer, Movie>> findAllMovies() throws DaoException {
      PreparedStatement ps = null;
      ResultSet resultSet = null;
      List<Pair<Integer, Movie>> movieList = new ArrayList<>();

      try (Connection connection = this.getConnection()) {
         String query = "SELECT * FROM movies";
         ps = connection.prepareStatement(query);
         resultSet = ps.executeQuery();

         while (resultSet.next()) {
            String title = resultSet.getString("title");
            int year = resultSet.getInt("year");
            double boxOffice = resultSet.getDouble("boxOffice");
            String director = resultSet.getString("director");
            String leadActor = resultSet.getString("leadActor");
            Movie m = new Movie(title, year, boxOffice, director, leadActor);
            movieList.add(new Pair<>(resultSet.getInt("id"), m));
         }
      } catch (SQLException e) {
         throw new DaoException("findAllMovies() " + e.getMessage());
      }
      return movieList;
   }

   @Override
   public Movie findMovieById(int id) throws DaoException {
      PreparedStatement ps = null;
      ResultSet resultSet = null;
      Movie movie = null;

      try (Connection connection = this.getConnection()) {
         String query = "SELECT * FROM movies WHERE id = ?";
         ps = connection.prepareStatement(query);
         ps.setInt(1, id);

         resultSet = ps.executeQuery();
         if (resultSet.next()) {
            String title = resultSet.getString("title");
            int year = resultSet.getInt("year");
            double boxOffice = resultSet.getDouble("boxOffice");
            String director = resultSet.getString("director");
            String leadActor = resultSet.getString("leadActor");

            movie = new Movie(title, year, boxOffice, director, leadActor);
         }
      } catch (SQLException e) {
         throw new DaoException("findMovieById() " + e.getMessage());
      }
      return movie;
   }

   @Override
   public void deleteMovieById(int id) throws DaoException {
      PreparedStatement ps = null;

      try (Connection connection = this.getConnection()) {
         String query = "DELETE FROM movies WHERE id = ?";
         ps = connection.prepareStatement(query);
         ps.setInt(1, id);
         ps.executeUpdate();
      } catch (SQLException e) {
         throw new DaoException("deleteMovieById() " + e.getMessage());
      }
   }

   @Override
   public void addMovie(Movie m) throws DaoException {
      PreparedStatement ps = null;

      try (Connection connection = this.getConnection()) {
         String query = "INSERT INTO movies (title, year, boxOffice, director, leadActor) VALUE (?, ?, ?, ?, ?)";
         ps = connection.prepareStatement(query);
         ps.setString(1, m.getTitle());
         ps.setInt(2, m.getYear());
         ps.setDouble(3, m.getBoxOffice());
         ps.setString(4, m.getDirector());
         ps.setString(5, m.getLeadActor());
         ps.executeUpdate();
      } catch (SQLException e) {
         throw new DaoException("deleteMovieById() " + e.getMessage());
      }
   }
}
