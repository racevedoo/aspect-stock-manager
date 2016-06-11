package br.ufpe.cin.projetop2.application.console.command;

public class Command {
  
  private ControllerMethod method;
  
  private CommandRequirement[] requirements;
  
  public Command(ControllerMethod method, CommandRequirement[] requirements) {
    this.method = method;
    this.requirements = requirements;
  }

  public ControllerMethod getMethod() {
    return method;
  }

  public CommandRequirement[] getRequirements() {
    return requirements;
  }
  
  
}
