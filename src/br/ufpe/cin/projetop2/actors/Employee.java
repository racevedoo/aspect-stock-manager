package br.ufpe.cin.projetop2.actors;

public class Employee extends Person {
  private String login;
  private String password;
  public Employee(String name, String cpf, String login, String password) {
    super(name, cpf);
    this.login = login;
    this.password = password;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }
  
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
}
