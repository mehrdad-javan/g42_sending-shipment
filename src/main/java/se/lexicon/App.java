package se.lexicon;


import se.lexicon.dao.BoxDAO;
import se.lexicon.dao.ReceiverDAO;
import se.lexicon.dao.SenderDAO;
import se.lexicon.dao.impl.BoxDAOImpl;
import se.lexicon.dao.impl.ReceiverDAOImpl;
import se.lexicon.dao.impl.SenderDAOImpl;
import se.lexicon.exception.ObjectNotFoundException;
import se.lexicon.io.JsonManager;
import se.lexicon.model.*;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {

  static Scanner scanner = new Scanner(System.in);
  static ReceiverDAO receiverDAO = new ReceiverDAOImpl();
  static BoxDAO boxDAO = new BoxDAOImpl();
  static SenderDAO senderDAO = new SenderDAOImpl();

  public static void main(String[] args) {
    while (true) {
      System.out.println("#################");
      System.out.println("1. Create #######");
      System.out.println("2. Find All #####");
      System.out.println("3. Find By Id ###");
      System.out.println("4. Delete By Id #");
      System.out.println("5. Exit #########");
      System.out.println("#################");

      System.out.println("Enter a valid operation:");
      String operationType = scanner.next();

      switch (operationType) {
        case "1":
          // business logic for creating a shipment
          create();
          break;
        case "2":
          findAll();
          break;
        case "3":
          find();
          break;
        case "4":
          delete();
          break;
        case "5":
          JsonManager jsonManager = new JsonManager();
          jsonManager.serializeToJson(senderDAO.findAll(), new File("src/main/resources/json/sender.json"));
          jsonManager.serializeToJson(receiverDAO.findAll(), new File("src/main/resources/json/receiver.json"));
          jsonManager.serializeToJson(boxDAO.findAll(), new File("src/main/resources/json/box.json"));
          System.exit(0);
          break;
        default:
          System.out.println("Operation is not valid");
      }


    }


  }

  public static void findAll() {
    List<Box> boxList = boxDAO.findAll();
    System.out.println("------------");
    System.out.println(boxList);
    System.out.println("------------");
  }

  public static void create() {
    Box consoleDataForBox = getBoxData();
    Sender consoleDataForSender = getSenderData();
    Receiver consoleDataForReceiver = getReceiverData();


    receiverDAO.create(consoleDataForReceiver);

    consoleDataForBox.setReceiver(consoleDataForReceiver);
    boxDAO.create(consoleDataForBox);

    consoleDataForSender.setBox(consoleDataForBox);
    senderDAO.create(consoleDataForSender);

    System.out.println("Operation is Done!");
    System.out.println("Sender Information: " + consoleDataForSender.information());
    System.out.println("Receiver Information: " + consoleDataForReceiver.information());
    System.out.println("Box DATA: " + consoleDataForBox);
  }

  public static void find() {
    System.out.println("Enter box id:");
    String boxId = scanner.next();
    Optional<Box> optionalBox = boxDAO.findById(boxId);
    if (optionalBox.isPresent()) {
      System.out.println(optionalBox.get());
    } else {
      System.out.println("id is not valid");
    }
  }

  public static void delete() {
    System.out.println("Enter box id:");
    try {
      boxDAO.remove(scanner.next());
    } catch (ObjectNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }


  public static Box getBoxData() {
    Box box = new Box();

    System.out.println("Box Information:");
    while (true) {
      System.out.println("Where do you want to send?");
      String country = scanner.next();
      try {
        box.setCountry(country);
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }


    while (true) {
      System.out.println("What do you want to send (1. S_BOX_250G, 2. S_BOX_500G, 3. S_BOX_1K, 4. M_BOX_2K, 5. L_BOX_3K)?");
      String boxType = scanner.next();
      BoxType bt = null;
      switch (boxType) {
        case "1":
          bt = BoxType.S_BOX_250G;
          break;
        case "2":
          bt = BoxType.S_BOX_500G;
          break;
        case "3":
          bt = BoxType.S_BOX_1K;
          break;
        case "4":
          bt = BoxType.M_BOX_2K;
          break;
        case "5":
          bt = BoxType.L_BOX_3K;
          break;
        default:
          System.out.println("Operation is not valid");
      }
      if (bt != null) {
        box.setType(bt);
        break;
      }
    }


    while (true) {
      System.out.println("Choose delivery option (1. MAIL_BOX, 2. SERVICE_POINT):");
      String deliveryOption = scanner.next();
      DeliveryOption dOpt = null;
      switch (deliveryOption) {
        case "1":
          dOpt = DeliveryOption.MAIL_BOX;
          break;
        case "2":
          dOpt = DeliveryOption.SERVICE_POINT;
          break;
        default:
          System.out.println("Operation is not valid");
      }
      if (dOpt != null) {
        box.setDeliveryOption(dOpt);
        break;
      }
    }

    box.calcPrice();

    return box;
  }

  public static Sender getSenderData() {
    Sender sender = new Sender();
    System.out.println("Sender Information:");

    while (true) {
      System.out.println("FirstName: ");
      try {
        sender.setFirstName(scanner.next());
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }


    while (true) {
      System.out.println("LastName: ");
      try {
        sender.setLastName(scanner.next());
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }


    while (true) {
      System.out.println("Address: ");
      try {
        sender.setAddress(scanner.next());
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }


    while (true) {
      System.out.println("PostalCode: ");
      try {
        sender.setPostalCode(scanner.next());
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }


    while (true) {
      System.out.println("Area: ");
      try {
        sender.setCity(scanner.next());
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }


    while (true) {
      System.out.println("Email: ");
      try {
        sender.setEmail(scanner.next());
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
    return sender;

  }

  public static Receiver getReceiverData() {
    Receiver receiver = new Receiver();
    System.out.println("Receiver Information:");

    while (true) {
      System.out.println("FirstName: ");
      try {
        receiver.setFirstName(scanner.next());
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }


    while (true) {
      System.out.println("LastName: ");
      try {
        receiver.setLastName(scanner.next());
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }


    while (true) {
      System.out.println("Address: ");
      try {
        receiver.setAddress(scanner.next());
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }


    while (true) {
      System.out.println("PostalCode: ");
      try {
        receiver.setPostalCode(scanner.next());
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }


    while (true) {
      System.out.println("Area: ");
      try {
        receiver.setCity(scanner.next());
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }


    while (true) {
      System.out.println("Email: ");
      try {
        receiver.setEmail(scanner.next());
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }


    while (true) {
      System.out.println("Customer Type: ");
      try {
        receiver.setCustomerType(scanner.next());
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }


    while (true) {
      System.out.println("Mobile Number: ");
      try {
        receiver.setMobileNumber(scanner.next());
        break;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }


    return receiver;

  }

}
