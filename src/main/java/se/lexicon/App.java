package se.lexicon;


import se.lexicon.dao.ReceiverDAO;
import se.lexicon.dao.impl.ReceiverDAOImpl;
import se.lexicon.model.Receiver;

public class App {


  public static void main(String[] args) {
    ReceiverDAO receiverDAO = new ReceiverDAOImpl();
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


}
