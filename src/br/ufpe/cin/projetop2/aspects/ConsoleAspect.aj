package br.ufpe.cin.projetop2.aspects;

import br.ufpe.cin.projetop2.application.console.ConsoleHandler;

public aspect ConsoleAspect {
  Object around(ConsoleHandler instance) : execution(* ConsoleHandler.getConsole*(..)) && this(instance){
    instance.open();
    Object ret = proceed(instance);
    return ret;
  }
}
