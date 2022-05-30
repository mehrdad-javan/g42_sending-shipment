package se.lexicon.db;

import se.lexicon.exception.DBConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

  private static final String JDBC_URL = "jdbc:mysql://localhost:3306/shipmentdb";
  private static final String JDBC_USERNAME = "root";
  private static final String JDBC_PASSWORD = "root";

  public static Connection getMySqlConnection() throws DBConnectionException {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
      System.out.println("DONE");
    } catch (SQLException e) {
      throw new DBConnectionException(e.getMessage(), JDBC_URL);
    }

    return connection;
  }

}
