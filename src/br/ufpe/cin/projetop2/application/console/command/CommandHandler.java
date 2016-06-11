package br.ufpe.cin.projetop2.application.console.command;

import java.util.Map;

import br.ufpe.cin.projetop2.application.ApplicationController;
import br.ufpe.cin.projetop2.application.console.ConsoleHandler;
import br.ufpe.cin.projetop2.application.console.login.UserNotLoggedInException;

public class CommandHandler extends ConsoleHandler{
  private Map<String, Boolean> registeredCommands;
  private ApplicationController applicationController;

  public CommandHandler() {
    super();
    this.registerCommands();
    this.applicationController = ApplicationController.getInstance();
  }

  private void registerCommands() {
    this.registeredCommands.put("query", true);
  }

  public void processCommand() {
    Boolean exists = false;
    String command = "";
    do {
      command = super.getConsoleString("Enter your command:");
      exists = this.registeredCommands.get(command);
      if (exists == null || !exists) {
        System.out.println("Command not supported.");
      } 
    } while(exists == null);
    
    if (command.equals("query")) {
      try {
        applicationController.queryProduct(this.getProductName());
      } catch (UserNotLoggedInException e) {
        // TODO temporary catch
      }
    } else if (command.equals("exit")) {
      System.exit(0);
    }


  }
  
  private String getProductName() {
    return super.getConsoleString("Enter the product name:");
  }


}
