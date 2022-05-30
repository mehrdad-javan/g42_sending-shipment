package se.lexicon;


import se.lexicon.dao.DBManager;
import se.lexicon.dao.ReceiverDAO;
import se.lexicon.dao.impl.ReceiverDAOImpl;
import se.lexicon.exception.ObjectNotFoundException;
import se.lexicon.model.Receiver;

import java.util.List;
import java.util.Optional;

public class App {

  static ReceiverDAO receiverDAO = new ReceiverDAOImpl();


  public static void main(String[] args) {
    //findReceiverById();
    //findReceiverByFirstName("test");
    //findAllReceivers();
    // deleteReceiverById();
    //updateReceiverById();
    createReceiverTable();
  }

  public static void testInsertReceiver() {
    Receiver testDataForReceiver = new Receiver(
            "test4",
            "test4",
            "test address",
            "12345",
            "Växjö",
            "test.test4@test.se",
            "private",
            "1234567890"
    );
    Receiver createdTestReceiver = receiverDAO.create(testDataForReceiver);
    System.out.println("ID: " + createdTestReceiver.getId());
    System.out.println("FN: " + createdTestReceiver.getFirstName());
    System.out.println("LN: " + createdTestReceiver.getLastName());
  }

  public static void findReceiverById() {
    Optional<Receiver> result = receiverDAO.findById(5);
    if (result.isPresent()) {
      System.out.println(result.get().information());
    } else {
      System.out.println(">>>> Data not found!");
    }
  }

  public static void findReceiverByFirstName(String firstName) {
    List<Receiver> list = receiverDAO.findByFirstName(firstName);
    list.forEach(System.out::println);
  }

  public static void findAllReceivers() {
    List<Receiver> list = receiverDAO.findAll();
    list.forEach(System.out::println);
  }

  public static void deleteReceiverById() {
    try {
      System.out.println(receiverDAO.remove(4));
    } catch (ObjectNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void updateReceiverById() {
    Receiver receiver = new Receiver("testA", "TestA", "test address", "12345", "Växjö", "test.test@test.se", 1, "private", "1234567890");
    try {
      receiverDAO.update(receiver);
    } catch (ObjectNotFoundException e) {
      e.printStackTrace();
    }

  }

  public static void createReceiverTable() {
    DBManager.createDropReceiverTable();
  }

}
