package se.lexicon.dao.impl;

import se.lexicon.dao.SenderDAO;
import se.lexicon.exception.ObjectNotFoundException;
import se.lexicon.model.Sender;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SenderDAOImpl implements SenderDAO {


  private List<Sender> storage;

  public SenderDAOImpl() {
    storage = new ArrayList<>();
  }

  @Override
  public Sender create(Sender sender) {
    if (sender == null) throw new IllegalArgumentException("Sender data is null");
    storage.add(sender);
    return sender;
  }

  @Override
  public Optional<Sender> findById(String id) {
    if (id == null) throw new IllegalArgumentException("Id is null");
    return storage.stream().filter(sender -> sender.getId().equalsIgnoreCase(id)).findFirst();
  }

  @Override
  public List<Sender> findByFirstName(String firstName) {
    if (firstName == null) throw new IllegalArgumentException("FirstName is null");
    return storage.stream()
            .filter(sender -> sender.getFirstName().equalsIgnoreCase(firstName))
            .collect(Collectors.toList());
  }

  @Override
  public List<Sender> findAll() {
    return new ArrayList<>(storage);
  }

  @Override
  public void remove(String id) throws ObjectNotFoundException {
    Optional<Sender> optionalSender = findById(id);
    if (optionalSender.isPresent()){
      storage.remove(optionalSender.get());
    } else {
      throw new ObjectNotFoundException("Sender id (" + id + ") does not exist");
    }
  }


}
