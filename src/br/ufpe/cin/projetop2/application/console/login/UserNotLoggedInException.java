package br.ufpe.cin.projetop2.application.console.login;

public class UserNotLoggedInException extends Exception {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public UserNotLoggedInException(String message) {
    super(message);
  }
}
