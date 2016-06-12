package br.ufpe.cin.projetop2.actors;

import br.ufpe.cin.projetop2.annotations.CheckValid;

public class Person {
  private String name;
  private String cpf; 
  
  @CheckValid
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
  
  @CheckValid
  public void setCpf(String cpf) throws Exception {
    this.cpf = cpf;
  }

}
