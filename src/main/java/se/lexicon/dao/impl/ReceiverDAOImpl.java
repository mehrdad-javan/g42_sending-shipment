package se.lexicon.dao.impl;

import se.lexicon.dao.ReceiverDAO;
import se.lexicon.db.MySQLConnection;
import se.lexicon.exception.DBConnectionException;
import se.lexicon.exception.ObjectNotFoundException;
import se.lexicon.model.Receiver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReceiverDAOImpl implements ReceiverDAO {


  @Override
  public Receiver create(Receiver receiver) {
    String insertQuery = "insert into receiver (first_name, last_name, address, postal_code, city, email, customer_type, mobile_number) values (?,?,?,?,?,?,?,?)";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = MySQLConnection.getMySqlConnection();
      preparedStatement = connection.prepareStatement(insertQuery);

      preparedStatement.setString(1, receiver.getFirstName());
      preparedStatement.setString(2, receiver.getLastName());
      preparedStatement.setString(3, receiver.getAddress());
      preparedStatement.setString(4, receiver.getPostalCode());
      preparedStatement.setString(5, receiver.getCity());
      preparedStatement.setString(6, receiver.getEmail());
      preparedStatement.setString(7, receiver.getCustomerType());
      preparedStatement.setString(8, receiver.getMobileNumber());
      // execute query
      preparedStatement.executeUpdate();
      System.out.println("insert operation is done successfully");

    } catch (SQLException e) {
      System.out.println("SQL Exception : " + e.getMessage());
    } catch (DBConnectionException e) {
      System.out.println("Database URL " + e.getJdbcURL() + " is not available.");
      System.out.println("Message: " + e.getMessage());
    }
    return receiver;
  }

  @Override
  public Optional<Receiver> findById(int id) {
    return Optional.empty();
  }

  @Override
  public List<Receiver> findByFirstName(String firstName) {
    return null;
  }

  @Override
  public List<Receiver> findAll() {
    return null;
  }

  @Override
  public int remove(int id) throws ObjectNotFoundException {
    return 0;
  }

  @Override
  public int update(Receiver receiver) throws ObjectNotFoundException {
    return 0;
  }
}
