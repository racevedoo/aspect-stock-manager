package br.ufpe.cin.projetop2.application.actors;

import java.util.Scanner;

public class Person {
  private String name;
  private String cpf; 

  public Person(String name, String cpf) {
    this.name = name;
    this.cpf = cpf;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

}
