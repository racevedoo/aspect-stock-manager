package br.ufpe.cin.projetop2.data.products;

public final class Product {
  String name;
  int quantity;

  Product(String name) {
    this.name = name;
    this.quantity = 0;
  }

  public void increaseQuantity(int amount) {
    this.quantity += amount;
  }

  public void decreaseQuantity(int amount) {
    this.quantity -= amount;
  }

  @Override
  public String toString() {
    return name + ": " + quantity;
  }
}
