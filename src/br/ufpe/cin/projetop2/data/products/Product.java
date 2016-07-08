package br.ufpe.cin.projetop2.data.products;

import br.ufpe.cin.projetop2.exceptions.InvalidStateException;

public final class Product {
  String name;
  int quantity;

  public Product(String name) throws InvalidStateException {
    this.name = name;
    this.quantity = 0;
  }

  private Product(String name, int quantity) throws InvalidStateException {
    this.name = name;
    this.quantity = quantity;
  }

  public String getName() {
    return this.name;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public Product increaseQuantity(int amount) throws InvalidStateException {
    return new Product(name, this.quantity + amount);
  }

  public Product decreaseQuantity(int amount) throws InvalidStateException {
    return new Product(name, this.quantity - amount);
  }

  @Override
  public String toString() {
    return name + ": " + quantity;
  }
}
