package se.lexicon.dao.impl;

import se.lexicon.dao.BoxDAO;
import se.lexicon.exception.ObjectNotFoundException;
import se.lexicon.model.Box;

import java.util.List;
import java.util.Optional;

public class BoxDAOImpl implements BoxDAO {

  // todo: implement CRUD operations for Box table
  @Override
  public Box create(Box box) {
    return null;
  }

  @Override
  public Optional<Box> findById(int id) {
    return Optional.empty();
  }

  @Override
  public List<Box> findAll() {
    return null;
  }

  @Override
  public int remove(int id) throws ObjectNotFoundException {
    return 0;
  }

  @Override
  public int update(Box box) throws ObjectNotFoundException {
    return 0;
  }

}
