package br.ufpe.cin.projetop2.aspects;

import br.ufpe.cin.projetop2.application.console.ConsoleHandler;

public aspect ConsoleAspect {
  Object around(ConsoleHandler instance) : execution(* ConsoleHandler.getConsole*(..)) && this(instance){
    instance.open();
    Object ret = proceed(instance);
<<<<<<< HEAD
=======
    instance.close();
>>>>>>> 9ef4ad4873afee73c4d3cb4bf1e45d587702ed19
    return ret;
  }
}
