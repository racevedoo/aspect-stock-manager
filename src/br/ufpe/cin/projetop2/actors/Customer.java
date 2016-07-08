package br.ufpe.cin.projetop2.actors;

import br.ufpe.cin.projetop2.exceptions.InvalidStateException;

public class Customer extends Person {
  public Customer(String name, String cpf) throws InvalidStateException {
    super(name, cpf);
  }
}
