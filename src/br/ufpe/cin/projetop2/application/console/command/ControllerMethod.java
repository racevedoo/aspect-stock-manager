package br.ufpe.cin.projetop2.application.console.command;

import java.util.ArrayList;
import java.util.List;

public class ControllerMethod {
  private String methodName;
  private List<Class> paramTypes;
  
  public ControllerMethod(String methodName, String... paramTypes) {
    this.methodName = methodName;
    this.paramTypes = new ArrayList<Class>();
    for (String type : paramTypes) {
      if (type.equalsIgnoreCase("string")) {
        this.paramTypes.add(String.class);
      } else if (type.equalsIgnoreCase("int")) {
        this.paramTypes.add(Integer.TYPE);
      } else {
        //TODO throw error
      }
    }
  }
  
  public String getMethodName() {
    return this.methodName;
  }
  
  public Class[] getParamTypes() {
    return (Class[]) this.paramTypes.toArray();
  }
}
