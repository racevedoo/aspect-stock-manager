package br.ufpe.cin.projetop2.data.products;

import br.ufpe.cin.projetop2.annotations.CheckValid;
import br.ufpe.cin.projetop2.exceptions.InvalidStateException;

public final class Product {
  String name;
  int quantity;

  public Product(String name) {
    this.name = name;
    this.quantity = 0;
  }
  
  public Product(String name, int quantity) {
    this.name = name;
    this.quantity = quantity;
  }

  public String getName() {
    return this.name;
  }

  public int getQuantity() {
    return this.quantity;
  }

  @CheckValid
  public Product increaseQuantity(int amount) throws InvalidStateException {
    return new Product(name, this.quantity + amount);
  }

  @CheckValid
  public Product decreaseQuantity(int amount) throws InvalidStateException {
    return new Product(name, this.quantity - amount);
  }

  @Override
  public String toString() {
    return name + ": " + quantity;
  }
}
