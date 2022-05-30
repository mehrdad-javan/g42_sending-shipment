package se.lexicon.model;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Box {

  public static final double COUNTRY_FEE = 200;
  public static final double COUNTRY_FEE_SE = 0;

  private int id;
  private BoxType type;
  private String country;
  private DeliveryOption deliveryOption;
  private Receiver receiver;
  private double price;
  private boolean status;
  private final LocalDate date;

  public Box() {
    this.date = LocalDate.now();
  }

  public Box(BoxType type, String country, DeliveryOption deliveryOption, Receiver receiver) {
    this();
    setType(type);
    setCountry(country);
    setDeliveryOption(deliveryOption);
    setReceiver(receiver);
    calcPrice();
  }

  public void calcPrice(){
    double total;
    if (country.equalsIgnoreCase("SWEDEN")){
      total = COUNTRY_FEE_SE + type.getPrice();
    } else {
      total = COUNTRY_FEE + type.getPrice();
    }
    this.price = total;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public BoxType getType() {
    return type;
  }

  public void setType(BoxType type) {
    if (type == null) this.type = BoxType.S_BOX_250G;
    else this.type = type;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    if (country == null || country.trim().length() < 2) throw new IllegalArgumentException("country is not valid");
    this.country = country;
  }

  public DeliveryOption getDeliveryOption() {
    return deliveryOption;
  }

  public void setDeliveryOption(DeliveryOption deliveryOption) {
    if (deliveryOption == null) this.deliveryOption = DeliveryOption.MAIL_BOX;
    else this.deliveryOption = deliveryOption;
  }

  public Receiver getReceiver() {
    return receiver;
  }

  public void setReceiver(Receiver receiver) {
    if (receiver == null) throw new IllegalArgumentException("receiver data is not valid");
    this.receiver = receiver;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public LocalDate getDate() {
    return date;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Box box = (Box) o;
    return id == box.id && Double.compare(box.price, price) == 0 && status == box.status && type == box.type && Objects.equals(country, box.country) && deliveryOption == box.deliveryOption && Objects.equals(receiver, box.receiver) && Objects.equals(date, box.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, country, deliveryOption, receiver, price, status, date);
  }

  @Override
  public String toString() {
    return "Box{" +
            "id='" + id + '\'' +
            ", type=" + type +
            ", country='" + country + '\'' +
            ", deliveryOption=" + deliveryOption +
            ", receiver=" + receiver +
            ", price=" + price +
            ", status=" + status +
            ", date=" + date +
            '}';
  }
}
