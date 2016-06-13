package br.ufpe.cin.projetop2.aspects;

import br.ufpe.cin.projetop2.exceptions.PermissionDeniedException;

public interface FunctionWithUsername <T> {
  T run(String username) throws PermissionDeniedException;
}

