package br.ufpe.cin.projetop2.exceptions;

public class UserNotLoggedInException extends Exception {
  private static final long serialVersionUID = 1L;

  public UserNotLoggedInException(String message) {
    super(message);
  }
}
