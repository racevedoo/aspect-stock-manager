package br.ufpe.cin.projetop2.exceptions;

public class PermissionDeniedException extends Exception {
  private static final long serialVersionUID = 1L;

  public PermissionDeniedException(String msg) {
    super(msg);
  }
}
