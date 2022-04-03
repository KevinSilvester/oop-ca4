package org.project.part2.DAOs;

import org.javatuples.Pair;
import org.project.part2.exceptions.DaoException;
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
      List<Pair<Integer, Movie>> movieList = new ArrayList<>();
      String query = "SELECT * FROM movies";

      try (
         Connection connection = this.getConnection();
         PreparedStatement ps = connection.prepareStatement(query);
         ResultSet resultSet = ps.executeQuery();
      ) {
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
   public Pair<Integer, Movie> findMovieById(int id) throws DaoException {
      Movie movie = null;
      String query = "SELECT * FROM movies WHERE id = ?";

      try (
         Connection connection = this.getConnection();
         PreparedStatement ps = connection.prepareStatement(query);
         ResultSet resultSet = ps.executeQuery();
      ) {
         ps.setInt(1, id);

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
      return new Pair<>(id, movie);
   }

   @Override
   public void deleteMovieById(int id) throws DaoException {
      String query = "DELETE FROM movies WHERE id = ?";

      try (
         Connection connection = this.getConnection();
         PreparedStatement ps = connection.prepareStatement(query);
      ) {
         ps.setInt(1, id);
         ps.executeUpdate();
      } catch (SQLException e) {
         throw new DaoException("deleteMovieById() " + e.getMessage());
      }
   }


   @Override
   public void addMovie(Movie m) throws DaoException {
      String query = "INSERT INTO movies (title, year, boxOffice, director, leadActor) VALUE (?, ?, ?, ?, ?)";

      try (
         Connection connection = this.getConnection();
         PreparedStatement ps = connection.prepareStatement(query);
      ) {
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
