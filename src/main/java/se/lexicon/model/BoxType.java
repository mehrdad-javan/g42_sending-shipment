package se.lexicon.model;

public enum BoxType {

  S_BOX_250G(51),
  S_BOX_500G(57),
  S_BOX_1K(66),
  M_BOX_2K(99),
  L_BOX_3K(122);

  private final double price;

  BoxType(double price){
    this.price= price;
  }

  public double getPrice() {
    return price;
  }
}
