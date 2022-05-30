package se.lexicon.dao;

import se.lexicon.exception.ObjectNotFoundException;
import se.lexicon.model.Receiver;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ReceiverDAO {

  Receiver create(Receiver receiver);

  Optional<Receiver> findById(int id);

  List<Receiver> findByFirstName(String firstName);

  List<Receiver> findAll();

  int remove(int id) throws ObjectNotFoundException;

  int update(Receiver receiver) throws ObjectNotFoundException;


  default void closeAll(AutoCloseable... closeables) {
    if (closeables != null) {
      System.out.println("Close connections and statements");
      for (AutoCloseable autoCloseable : closeables) {
        try {
          autoCloseable.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }


}
