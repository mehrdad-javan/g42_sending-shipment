package se.lexicon.dao;

import se.lexicon.exception.ObjectNotFoundException;
import se.lexicon.model.Box;

import java.util.List;
import java.util.Optional;

public interface BoxDAO {

  Box create(Box box);

  Optional<Box> findById(String id);

  List<Box> findAll();

  void remove(String id) throws ObjectNotFoundException;

  //...
  //List<Box> findBoxByReceiverId(String receiverId);
  //List<Box> findBoxByReceiverFirstName(String firstName);
  //List<Box> findBoxByDateBetween(LocalDate start, LocalDate end);

}
