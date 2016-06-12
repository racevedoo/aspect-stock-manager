package br.ufpe.cin.projetop2.application;

import br.ufpe.cin.projetop2.application.console.command.CommandHandler;

public class Application {

	public static void main(String[] args) {
	  CommandHandler handler = new CommandHandler();
	  while(true) {
	    handler.processCommand();
	  }
	}



}
