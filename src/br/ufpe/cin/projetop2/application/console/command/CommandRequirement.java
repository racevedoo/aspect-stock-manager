package br.ufpe.cin.projetop2.application.console.command;

public class CommandRequirement {
  private String requirementDescription;
  private String type;
  
  public CommandRequirement(String requirementDescription, String type) {
    this.requirementDescription = requirementDescription;
    this.type = type;
  }

  public String getRequirementDescription() {
    return requirementDescription;
  }

  public String getType() {
    return type;
  }
}
