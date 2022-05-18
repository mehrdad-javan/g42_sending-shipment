package se.lexicon.dao.impl;

import se.lexicon.dao.ReceiverDAO;
import se.lexicon.exception.ObjectNotFoundException;
import se.lexicon.model.Receiver;
import se.lexicon.model.Sender;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReceiverDAOImpl implements ReceiverDAO {

  private List<Receiver> storage;

  public ReceiverDAOImpl() {
    storage = new ArrayList<>();
  }

  @Override
  public Receiver create(Receiver receiver) {
    if (receiver == null) throw new IllegalArgumentException("receiver data is null");
    storage.add(receiver);
    return receiver;  }

  @Override
  public Optional<Receiver> findById(String id) {
    if (id == null) throw new IllegalArgumentException("Id is null");
    return storage.stream().filter(receiver -> receiver.getId().equalsIgnoreCase(id)).findFirst();
  }

  @Override
  public List<Receiver> findByFirstName(String firstName) {
    if (firstName == null) throw new IllegalArgumentException("FirstName is null");
    return storage.stream()
            .filter(receiver -> receiver.getFirstName().equalsIgnoreCase(firstName))
            .collect(Collectors.toList());
  }

  @Override
  public List<Receiver> findAll() {
    return new ArrayList<>(storage);
  }

  @Override
  public void remove(String id) throws ObjectNotFoundException {
    Optional<Receiver> optionalReceiver = findById(id);
    if (optionalReceiver.isPresent()){
      storage.remove(optionalReceiver.get());
    } else {
      throw new ObjectNotFoundException("Receiver id (" + id + ") does not exist");
    }
  }

}
