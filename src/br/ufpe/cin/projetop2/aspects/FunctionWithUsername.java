package br.ufpe.cin.projetop2.aspects;

public interface FunctionWithUsername <T> {
  T run(String username) throws Exception;
}

