package se.lexicon.dao;

import se.lexicon.exception.ObjectNotFoundException;
import se.lexicon.model.Receiver;

import java.util.List;
import java.util.Optional;

public interface ReceiverDAO {

  Receiver create(Receiver receiver);

  Optional<Receiver> findById(int id);

  List<Receiver> findByFirstName(String firstName);

  List<Receiver> findAll();

  int remove(int id) throws ObjectNotFoundException;

  int update(Receiver receiver) throws ObjectNotFoundException;
}
