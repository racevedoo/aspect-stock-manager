package br.ufpe.cin.projetop2.aspects;

import br.ufpe.cin.projetop2.actors.Person;
import br.ufpe.cin.projetop2.data.products.Product;
import br.ufpe.cin.projetop2.exceptions.InvalidStateException;
import br.ufpe.cin.projetop2.application.ApplicationController;

public aspect CheckValidAspect {
  public interface ValidObject {
    public void checkValid() throws InvalidStateException;
  }

  private static boolean isValidCpf(String cpf) {
    if (cpf == null) return false;

    if (cpf.length() != 11) return false;

    int maxWeight = 10;
    int minWeight = 2;

    for (int digit = 9; digit < 11; ++digit) {
      int pos = 0;
      int accumulated = 0;
      for (int i = maxWeight; i >= minWeight; --i, ++pos) {
        int d = Integer.parseInt(cpf.substring(pos, pos + 1));
        accumulated += i * d;
      }

      int remainder = (accumulated % 11);
      int check = 11 - remainder;

      if (check > 9) {
        check = 0;
      }

      if (check != Integer.parseInt(cpf.substring(digit, digit + 1))) {
        return false;
      }

      ++maxWeight;
    }

    return true;
  }

  declare parents : Person implements ValidObject;
  
  public void Person.checkValid() throws InvalidStateException {
    if (!isValidCpf(this.getCpf())) {
      throw new InvalidStateException("CPF number is not valid. Rolling back");
    }
  }  

  declare parents : Product implements ValidObject;
  
  public void Product.checkValid() throws InvalidStateException {
    if (this.getQuantity() < 0) {
      throw new InvalidStateException("Product quantity below zero. Rolling back");
    }
  }

  pointcut validObjectMethod(ValidObject o) : execution(ValidObject+ ValidObject+.*(..)) && this(o);
    
  declare soft: InvalidStateException: call(* ApplicationController.* (..));
  
  Object around(): call(* ApplicationController.* (..)) {
    try {
      return proceed();
    } catch(InvalidStateException e) {
      System.err.println(e.getMessage());
      return null;
    }
  }
  
  before(ValidObject o) throws InvalidStateException : validObjectMethod(o) { 
    o.checkValid();
  }
  
  after(ValidObject o) returning(ValidObject r) throws InvalidStateException : validObjectMethod(o) {
    r.checkValid();
  }  

  after(ValidObject o) throws InvalidStateException : execution(ValidObject+.new(..)) && this(o) {
    o.checkValid();
  }
}
