package br.ufpe.cin.projetop2.application.console.command;

import java.util.HashMap;
import java.util.Map;

import br.ufpe.cin.projetop2.application.ApplicationController;
import br.ufpe.cin.projetop2.application.console.ConsoleHandler;

public class CommandHandler extends ConsoleHandler{
  private Map<String, Boolean> registeredCommands;
  private ApplicationController applicationController;

  public CommandHandler() {
    super();
    this.registeredCommands = new HashMap<String, Boolean>();
    this.registerCommands();
    this.applicationController = ApplicationController.getInstance();
  }

  private void registerCommands() {
    this.registeredCommands.put("register customer", true);
    this.registeredCommands.put("register employee", true);
    this.registeredCommands.put("register product", true);
    this.registeredCommands.put("sell", true);
    this.registeredCommands.put("supply", true);
    this.registeredCommands.put("query product", true);
    this.registeredCommands.put("login", true);
    this.registeredCommands.put("logout", true);
    this.registeredCommands.put("exit", true);
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

    if (command.equals("query product")) {
      String ret = applicationController.queryProduct(this.getProductName());
      System.out.println(ret);
    } else if (command.equals("register customer")) {
      applicationController.registerCustomer(this.getName(), this.getCpf());
    } else if (command.equals("register employee")) {
      applicationController.registerEmployee(this.getName(), this.getCpf(), this.getUsername(), this.getPassword());
    } else if (command.equals("register product")) {
      applicationController.registerProduct(this.getName());
    } else if (command.equals("sell")) {
      String hasCustomerCpf = super.getConsoleString("Customer cpf? (y/n)");
      if (hasCustomerCpf.equals("y")) {
        applicationController.sell(this.getProductName(), this.getAmount(), this.getCpf());
      } else {
        applicationController.sell(this.getProductName(), this.getAmount());
      }
    } else if(command.equals("supply")) {
      applicationController.supply(this.getProductName(), this.getAmount());
    } else if (command.equals("login")) {
      applicationController.login();
    } else if (command.equals("logout")) {
      applicationController.logout();
    } else if (command.equals("exit")) {
      super.close();
      System.exit(0);
    }
  }

  private int getAmount() {
    return super.getConsoleInt("Enter amount:");
  }

  private String getProductName() {
    return super.getConsoleString("Enter the product name:");
  }

  private String getName() {
    return super.getConsoleString("Enter name:");
  }

  private String getCpf() {
    return super.getConsoleString("Enter cpf:");
  }

  private String getUsername() {
    return super.getConsoleString("Enter employee username:");
  }

  private String getPassword() {
    return super.getConsoleString("Enter employee password:");
  }

}
