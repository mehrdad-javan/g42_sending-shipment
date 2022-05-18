package se.lexicon.model;

import java.util.Objects;
import java.util.UUID;

public class Receiver extends Person {
  private final String id;
  private String customerType;
  private String mobileNumber;

  public Receiver() {
    this.id = UUID.randomUUID().toString();
  }

  public Receiver(String firstName, String lastName, String address, String postalCode, String city, String email, String customerType, String mobileNumber) {
    super(firstName, lastName, address, postalCode, city, email);
    this.id = UUID.randomUUID().toString();
    setCustomerType(customerType);
    setMobileNumber(mobileNumber);
  }

  public String getId() {
    return id;
  }

  public String getCustomerType() {
    return customerType;
  }

  public void setCustomerType(String customerType) {
    if (customerType == null) throw new IllegalArgumentException("CustomerType is not valid");
    this.customerType = customerType;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    if (mobileNumber == null) throw new IllegalArgumentException("MobileNumber is not valid");
    this.mobileNumber = mobileNumber;
  }

  @Override
  public String information() {
    // todo: implement it later
    return "Name: " + getFirstName() + " " + getLastName() + " Email: " + getEmail() + " PostalCode: " + getPostalCode() + " CustomerType: " + customerType +" MobileNumber: " + mobileNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Receiver receiver = (Receiver) o;
    return Objects.equals(id, receiver.id) && Objects.equals(customerType, receiver.customerType) && Objects.equals(mobileNumber, receiver.mobileNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, customerType, mobileNumber);
  }

  @Override
  public String toString() {
    return "Receiver{" +
            "id='" + id + '\'' +
            ", customerType='" + customerType + '\'' +
            ", mobileNumber='" + mobileNumber + '\'' +
            '}';
  }
}
