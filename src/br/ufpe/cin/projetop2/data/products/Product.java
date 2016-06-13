package br.ufpe.cin.projetop2.data.products;

import br.ufpe.cin.projetop2.annotations.CheckValid;
import br.ufpe.cin.projetop2.exceptions.InvalidStateException;

public final class Product {
  String name;
  int quantity;

  Product(String name) {
    this.name = name;
    this.quantity = 0;
  }

  public String getName() {
    return this.name;
  }

  public int getQuantity() {
    return this.quantity;
  }

  @CheckValid
  public void increaseQuantity(int amount) throws InvalidStateException {
    this.quantity += amount;
  }

  @CheckValid
  public void decreaseQuantity(int amount) throws InvalidStateException {
    this.quantity -= amount;
  }

  @Override
  public String toString() {
    return name + ": " + quantity;
  }
}
