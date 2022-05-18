package se.lexicon.model;

public abstract class Person {

  private String firstName;
  private String lastName;
  private String address;
  private String postalCode;
  private String city;
  private String email;

  public Person() {
  }

  public Person(String firstName, String lastName, String address, String postalCode, String city, String email) {
    setFirstName(firstName);
    setLastName(lastName);
    setAddress(address);
    setPostalCode(postalCode);
    setCity(city);
    setEmail(email);
  }

  public abstract String information();

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    if (firstName == null) throw new IllegalArgumentException("FirstName is not valid");
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    if (lastName == null) throw new IllegalArgumentException("LastName is not valid");
    this.lastName = lastName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    if (address == null) throw new IllegalArgumentException("Address is not valid");
    this.address = address;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    if (postalCode == null) throw new IllegalArgumentException("PostalCode is not valid");
    this.postalCode = postalCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    if (city == null) throw new IllegalArgumentException("City is not valid");
    this.city = city;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    if (email == null) throw new IllegalArgumentException("Email is not valid");
    this.email = email;
  }

  @Override
  public String toString() {
    return "Person{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address='" + address + '\'' +
            ", postalCode='" + postalCode + '\'' +
            ", city='" + city + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
