package br.ufpe.cin.projetop2.actors;

import br.ufpe.cin.projetop2.exceptions.InvalidStateException;

public class Employee extends Person {
  private String login;
  private String password;
  public Employee(String name, String cpf, String login, String password) {
    super(name, cpf);
    this.login = login;
    this.password = password;
  }

  @Override
  public Employee setName(String name) throws InvalidStateException {
    return new Employee(name, super.getCpf(), login, password);
  }

  @Override
  public Employee setCpf(String cpf) throws InvalidStateException {
    return new Employee(super.getName(), cpf, login, password);
  }

  public String getLogin() {
    return login;
  }

  public Employee setLogin(String login) throws InvalidStateException {
    return new Employee(super.getName(), super.getCpf(), login, password);
  }

  public String getPassword() {
    return password;
  }

  public Employee setPassword(String password) throws InvalidStateException {
    return new Employee(super.getName(), super.getCpf(), login, password);
  }

}
