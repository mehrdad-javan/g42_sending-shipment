package se.lexicon.dao;

import se.lexicon.db.MySQLConnection;
import se.lexicon.exception.DBConnectionException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

public interface DBManager {

  static void createDropReceiverTable(){
    try (
            Connection connection= MySQLConnection.getMySqlConnection();
            Statement statement = connection.createStatement()
            ) {
      Stream<String> stream = Files.lines(Paths.get("src/main/resources/scripts/receiver.sql"));
      String createReceiverTableQuery = stream.reduce(String::concat).orElse(null);
      statement.addBatch("drop table if exists receiver");
      statement.addBatch(createReceiverTableQuery);
      statement.executeBatch();
    } catch (SQLException | DBConnectionException | IOException e) {
      e.printStackTrace();
    }
  }
}
