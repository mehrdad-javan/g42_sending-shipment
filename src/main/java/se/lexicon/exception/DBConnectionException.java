package se.lexicon.exception;

public class DBConnectionException extends Exception {

  private String jdbcURL;

  public DBConnectionException(String message, String jdbcURL) {
    super(message);
    this.jdbcURL = jdbcURL;
  }

  public String getJdbcURL() {
    return jdbcURL;
  }

}
