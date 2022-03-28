package org.project.part2.DAOs;

import org.project.part2.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDao {
   public Connection getConnection() throws DaoException {
      String driver = "com.mysql.cj.jdbc.Driver";
      String url = "jdbc:mysql://127.0.0.1:3306/oop_movie_db";
      String username = "wsl1";
      String password = "admin";
      Connection connection = null;

      try {
         Class.forName(driver);
         connection = DriverManager.getConnection(url, username, password);
      } catch (ClassNotFoundException e) {
         System.out.println("Failed to find driver class " + e.getMessage());
         System.exit(1);
      } catch (SQLException e) {
         System.out.println("Connection failed " + e.getMessage());
         System.exit(2);
      }
      return connection;
   }

   public void freeConnection(Connection connection) throws DaoException {
      try {
         if (connection != null) {
            connection.close();
            connection = null;
         }
      } catch (SQLException e) {
         System.out.println("Failed to free connection: " + e.getMessage());
         System.exit(1);
      }
   }
}