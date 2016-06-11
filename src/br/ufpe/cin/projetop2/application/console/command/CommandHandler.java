package br.ufpe.cin.projetop2.application.console.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufpe.cin.projetop2.application.console.ConsoleHandler;

public class CommandHandler extends ConsoleHandler{
  private Map<String, Command> registeredCommands;

  public CommandHandler() {
    super();
    registeredCommands = new HashMap<>();
    this.registerCommands();
  }

  private void registerCommands() {
    this.registeredCommands.put("query",
        new Command(
            new ControllerMethod("queryProducts", "string"),
            new CommandRequirement[]{
                new CommandRequirement("product name", "string")
            }
        ));
  }

  public String begin() {
    CommandRequirement[] requirements = null;
    String command = "";
    do {
      command = super.getConsoleString("Enter your command:");
      requirements = this.registeredCommands.get(command);
      if (requirements == null) {
        System.out.println("Command not supported.");
      } 
    } while(requirements == null);


  }


}
