package se.lexicon.dao.impl;

import se.lexicon.dao.ReceiverDAO;
import se.lexicon.db.MySQLConnection;
import se.lexicon.exception.DBConnectionException;
import se.lexicon.exception.ObjectNotFoundException;
import se.lexicon.model.Receiver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReceiverDAOImpl implements ReceiverDAO {


  @Override
  public Receiver create(Receiver receiver) {
    String insertQuery = "insert into receiver (first_name, last_name, address, postal_code, city, email, customer_type, mobile_number) values (?,?,?,?,?,?,?,?)";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet keySet = null;
    try {
      connection = MySQLConnection.getMySqlConnection();
      preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

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
      keySet = preparedStatement.getGeneratedKeys();
      System.out.println("insert operation is done successfully");
      if (keySet.next()) {
        int generatedId = keySet.getInt(1);
        receiver.setId(generatedId);
      }
    } catch (SQLException e) {
      System.out.println("SQL Exception : " + e.getMessage());
    } catch (DBConnectionException e) {
      System.out.println("Database URL " + e.getJdbcURL() + " is not available.");
      System.out.println("Message: " + e.getMessage());
    } finally {
      /*try {
        System.out.println("Close connections and statements");
        if (keySet != null) keySet.close();
        if (preparedStatement != null) preparedStatement.close();
        if (connection != null) connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }*/
      closeAll(keySet, preparedStatement, connection);
    }
    return receiver;
  }

  @Override
  public Optional<Receiver> findById(int id) {
    Receiver result = null;
    String selectQuery = "select * from receiver where id = ?";
    ResultSet resultSet = null;
    try (
            Connection connection = MySQLConnection.getMySqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
    ) {
      preparedStatement.setInt(1, id);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        result = new Receiver(
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("address"),
                resultSet.getString("postal_code"),
                resultSet.getString("city"),
                resultSet.getString("email"),
                resultSet.getInt("id"),
                resultSet.getString("customer_type"),
                resultSet.getString("mobile_number")
        );
        //..
      }


    } catch (SQLException e) {
      System.out.println("SQL Exception : " + e.getMessage());
    } catch (DBConnectionException e) {
      System.out.println("Database URL " + e.getJdbcURL() + " is not available.");
      System.out.println("Message: " + e.getMessage());
    } finally {
      closeAll(resultSet);
    }
    return Optional.ofNullable(result);
  }

  @Override
  public List<Receiver> findByFirstName(String firstName) {
    List<Receiver> receiverList = new ArrayList<>();
    String selectQuery = "select * from receiver where first_name = ?";
    ResultSet resultSet = null;
    try (
            Connection connection = MySQLConnection.getMySqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
    ) {
      preparedStatement.setString(1, firstName);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        Receiver result = new Receiver(
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("address"),
                resultSet.getString("postal_code"),
                resultSet.getString("city"),
                resultSet.getString("email"),
                resultSet.getInt("id"),
                resultSet.getString("customer_type"),
                resultSet.getString("mobile_number")
        );
        receiverList.add(result);

      }


    } catch (SQLException e) {
      System.out.println("SQL Exception : " + e.getMessage());
    } catch (DBConnectionException e) {
      System.out.println("Database URL " + e.getJdbcURL() + " is not available.");
      System.out.println("Message: " + e.getMessage());
    } finally {
      closeAll(resultSet);
    }
    return receiverList;
  }

  @Override
  public List<Receiver> findAll() {
    List<Receiver> receiverList = new ArrayList<>();
    String selectQuery = "select * from receiver";
    ResultSet resultSet = null;
    try (
            Connection connection = MySQLConnection.getMySqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
    ) {
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        Receiver result = new Receiver(
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("address"),
                resultSet.getString("postal_code"),
                resultSet.getString("city"),
                resultSet.getString("email"),
                resultSet.getInt("id"),
                resultSet.getString("customer_type"),
                resultSet.getString("mobile_number")
        );
        receiverList.add(result);
      }

    } catch (SQLException e) {
      System.out.println("SQL Exception : " + e.getMessage());
    } catch (DBConnectionException e) {
      System.out.println("Database URL " + e.getJdbcURL() + " is not available.");
      System.out.println("Message: " + e.getMessage());
    } finally {
      closeAll(resultSet);
    }
    return receiverList;
  }

  @Override
  public int remove(int id) throws ObjectNotFoundException {
    String deleteQuery = "delete from receiver where id = ?";
    int result = 0;
    try (
            Connection connection = MySQLConnection.getMySqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
    ) {

      preparedStatement.setInt(1, id);
      result = preparedStatement.executeUpdate();
      if (result == 0) throw new ObjectNotFoundException("row for receiver by id " + id + " dose not exist");
    } catch (SQLException e) {
      System.out.println("SQL Exception : " + e.getMessage());
    } catch (DBConnectionException e) {
      System.out.println("Database URL " + e.getJdbcURL() + " is not available.");
      System.out.println("Message: " + e.getMessage());
    }
    return result;
  }

  @Override
  public int update(Receiver receiver) throws ObjectNotFoundException {
    String updateQuery = "update receiver set first_name = ?, last_name = ?, address = ?, postal_code = ?, city = ?, email = ?, customer_type = ?, mobile_number = ? where id = ?";
    int result = 0;
    try(
      Connection connection = MySQLConnection.getMySqlConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)
            ){
      preparedStatement.setString(1, receiver.getFirstName());
      preparedStatement.setString(2, receiver.getLastName());
      preparedStatement.setString(3, receiver.getAddress());
      preparedStatement.setString(4, receiver.getPostalCode());
      preparedStatement.setString(5, receiver.getCity());
      preparedStatement.setString(6, receiver.getEmail());
      preparedStatement.setString(7, receiver.getCustomerType());
      preparedStatement.setString(8, receiver.getMobileNumber());

      preparedStatement.setInt(9, receiver.getId());

      result = preparedStatement.executeUpdate();
      if (result == 0) throw new ObjectNotFoundException("row for receiver by id " + receiver.getId() + " dose not exist");
    } catch (SQLException e) {
      System.out.println("SQL Exception : " + e.getMessage());
    } catch (DBConnectionException e) {
      System.out.println("Database URL " + e.getJdbcURL() + " is not available.");
      System.out.println("Message: " + e.getMessage());
    }

    return result;
  }
}
