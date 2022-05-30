package se.lexicon.dao;

import se.lexicon.exception.ObjectNotFoundException;
import se.lexicon.model.Sender;

import java.util.List;
import java.util.Optional;

public interface SenderDAO {

  Sender create(Sender sender);

  Optional<Sender> findById(int id);

  List<Sender> findByFirstName(String firstName);

  List<Sender> findAll();

  int remove(int id) throws ObjectNotFoundException;

  int update(Sender sender) throws ObjectNotFoundException;
}
