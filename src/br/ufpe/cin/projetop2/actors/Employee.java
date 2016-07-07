package br.ufpe.cin.projetop2.actors;

import br.ufpe.cin.projetop2.annotations.CheckValid;
import br.ufpe.cin.projetop2.exceptions.InvalidStateException;

public class Employee extends Person {
  private String login;
  private String password;
  public Employee(String name, String cpf, String login, String password) {
    super(name, cpf);
    this.login = login;
    this.password = password;
  }

  public Employee setName(String name) {
    return new Employee(name, super.getCpf(), login, password);
  }
  
  @CheckValid
  public Employee setCpf(String cpf) throws InvalidStateException {
    return new Employee(super.getName(), cpf, login, password);
  }
  
  public String getLogin() {
    return login;
  }

  public Employee setLogin(String login) {
    return new Employee(super.getName(), super.getCpf(), login, password);
  }
  
  public String getPassword() {
    return password;
  }

  public Employee setPassword(String password) {
    return new Employee(super.getName(), super.getCpf(), login, password);
  }
  
}
