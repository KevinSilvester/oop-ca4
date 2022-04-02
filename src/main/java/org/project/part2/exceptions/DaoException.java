package org.project.part2.exceptions;

import java.sql.SQLException;

public class DaoException extends SQLException {
   public DaoException(String message) {
      super(message);
   }
}
