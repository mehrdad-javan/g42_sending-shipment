package se.lexicon.dao;

import se.lexicon.exception.ObjectNotFoundException;
import se.lexicon.model.Sender;

import java.util.List;
import java.util.Optional;

public interface SenderDAO {

  Sender create(Sender sender);

  Optional<Sender> findById(String id);

  List<Sender> findByFirstName(String firstName);

  List<Sender> findAll();

  void remove(String id) throws ObjectNotFoundException;
}
