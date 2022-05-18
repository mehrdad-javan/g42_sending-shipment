package se.lexicon.model;

import java.util.Objects;
import java.util.UUID;

public class Sender extends Person {

  private final String id;
  private Box box;

  public Sender() {
    this.id = UUID.randomUUID().toString();
  }

  public Sender(String firstName, String lastName, String address, String postalCode, String city, String email) {
    super(firstName, lastName, address, postalCode, city, email);
    this.id = UUID.randomUUID().toString();
  }

  public String getId() {
    return id;
  }

  public Box getBox() {
    return box;
  }

  public void setBox(Box box) {
    this.box = box;
  }

  @Override
  public String information() {
    return "Name: " + getFirstName() + " " + getLastName() + " Email: " + getEmail() + " PostalCode: " + getPostalCode() + " Box Id: " + box.getId();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Sender sender = (Sender) o;
    return Objects.equals(id, sender.id) && Objects.equals(box, sender.box);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, box);
  }

  @Override
  public String toString() {
    return "Sender{" +
            "id='" + id + '\'' +
            ", box=" + box +
            '}';
  }
}
