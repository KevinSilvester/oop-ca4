package org.project.part3.exceptions;

import java.sql.SQLException;

public class DaoException extends SQLException {
   public DaoException(String message) {
      super(message);
   }
}
