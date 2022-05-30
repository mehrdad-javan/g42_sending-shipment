package se.lexicon.dao;

import se.lexicon.exception.ObjectNotFoundException;
import se.lexicon.model.Box;

import java.util.List;
import java.util.Optional;

public interface BoxDAO {

  Box create(Box box);

  Optional<Box> findById(int id);

  List<Box> findAll();

  int remove(int id) throws ObjectNotFoundException;

  int update(Box box) throws ObjectNotFoundException;


}
