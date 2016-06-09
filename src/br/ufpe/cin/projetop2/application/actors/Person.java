package br.ufpe.cin.projetop2.application.actors;

import java.util.Scanner;

public class Person {
  private String name;
  private String cpf;
  private String login;
  private String password;  

  public Person(String name, String cpf, String login, String password) {
    this.name = name;
    this.cpf = cpf;
    this.login = login;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean resetName(String password, String name) {
    if (!this.password.equals(password)) return false;

    this.name = name;
    return true;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public boolean resetCpf(String password, String cpf) {
    if (!this.password.equals(password)) return false;

    this.cpf = cpf;
    return true;

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

  public boolean resetPassword(String password, String newPassword) {
    if (!this.password.equals(password)) return false;

    this.password = newPassword;
    return true;
  }

}
