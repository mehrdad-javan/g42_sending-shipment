package se.lexicon.dao.impl;

import se.lexicon.dao.BoxDAO;
import se.lexicon.exception.ObjectNotFoundException;
import se.lexicon.model.Box;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoxDAOImpl implements BoxDAO {

  private List<Box> storage;

  public BoxDAOImpl() {
    storage = new ArrayList<>();
  }

  @Override
  public Box create(Box box) {
    if (box == null) throw new IllegalArgumentException("box data is null");
    storage.add(box);
    return box;
  }

  @Override
  public Optional<Box> findById(String id) {
    if (id == null) throw new IllegalArgumentException("Id is null");
    return storage.stream().filter(box -> box.getId().equalsIgnoreCase(id)).findFirst();
  }

  @Override
  public List<Box> findAll() {
    return new ArrayList<>(storage);
  }

  @Override
  public void remove(String id) throws ObjectNotFoundException {
    Optional<Box> optionalBox = findById(id);
    if (optionalBox.isPresent()) {
      storage.remove(optionalBox.get());
    } else {
      throw new ObjectNotFoundException("Box id (" + id + ") does not exist");
    }
  }

}
