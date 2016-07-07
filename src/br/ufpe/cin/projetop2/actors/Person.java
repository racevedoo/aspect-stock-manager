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

  public Person setName(String name) {
    return new Person(name, cpf);
  }

  public String getCpf() {
    return cpf;
  }
  
  public Person setCpf(String cpf) throws Exception {
    return new Person(name, cpf);
  }

}
