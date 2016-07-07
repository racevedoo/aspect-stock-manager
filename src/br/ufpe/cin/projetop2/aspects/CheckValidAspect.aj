package br.ufpe.cin.projetop2.aspects;

import br.ufpe.cin.projetop2.annotations.CheckValid;

import org.aspectj.lang.ProceedingJoinPoint;

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

    if (cpf.length() < 14) return false;

    int left = 10;
    int right = 1;
    for (int digit = 11; digit < 13; ++digit) {
      int j = 0;
      int accumulated = 0;
      for (int i = left; i > right; --i, ++j) {
        if (cpf.charAt(j) == '.') continue;
        if (cpf.charAt(j) == '-') continue;
        accumulated += i * Integer.parseInt(cpf.substring(j, j));
      }
      int check = (accumulated % 11) % 10;
      if (check != Integer.parseInt(cpf.substring(right, right))) return false;
      ++left;
      --right;
    }

    return true;
  }

  declare parents : Person implements ValidObject;
  
  public void Person.checkValid() throws InvalidStateException {
    if (!isValidCpf(this.getCpf())) {
      throw new InvalidStateException("CPF number is not valid.");
    }
  }  

  declare parents : Product implements ValidObject;
  
  public void Product.checkValid() throws InvalidStateException {
    if (this.getQuantity() < 0) {
      throw new InvalidStateException("Product quantity is below zero.");
    }
  }

  pointcut validObjectMethod(ValidObject o) : execution(@CheckValid * *.*(..)) && this(o);
    
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
}
