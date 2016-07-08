package br.ufpe.cin.projetop2.aspects;

import java.util.Arrays;
import java.util.List;

import br.ufpe.cin.projetop2.actors.Employee;
import br.ufpe.cin.projetop2.actors.EmployeeController;
import br.ufpe.cin.projetop2.annotations.RequireFullPermission;
import br.ufpe.cin.projetop2.annotations.WrapLogin;
import br.ufpe.cin.projetop2.application.ApplicationController;
import br.ufpe.cin.projetop2.application.console.login.LoginHandler;
import br.ufpe.cin.projetop2.exceptions.InvalidStateException;
import br.ufpe.cin.projetop2.exceptions.PermissionDeniedException;

privileged public aspect AccessControlAspect {
  
  enum AccessLevel {
    READ_ONLY,
    FULL
  }
  
  private AccessLevel Employee.accessLevel = AccessLevel.READ_ONLY;
  
  public AccessLevel Employee.getAccessLevel() {
    return this.accessLevel;
  }
  
  public void Employee.setAccessLevel(AccessLevel level) {
    this.accessLevel = level;
  }
  
  public void EmployeeController.updatePermission(String username, AccessLevel level) {
    Employee employee = this.entries.getData(username);
    if (employee == null) {
      return;
    }
    employee.setAccessLevel(level);
    this.entries.saveData(username, employee);
  }
  
  declare soft: PermissionDeniedException: call(* ApplicationController.*(..));
  
  Object around(): call(* ApplicationController.*(..)) {
    try {
      return proceed();
    } catch (PermissionDeniedException e) {
      System.err.println(e.getMessage());
      return null;
    }
  }
  
  private static EmployeeController employeeController = EmployeeController.getInstance();
  
  before() throws PermissionDeniedException: call(@RequireFullPermission * *(..)) {
    checkFullPermission();
  }
  
  @WrapLogin
  FunctionWithUsername<Void> checkFullPermission() throws PermissionDeniedException {
    return new FunctionWithUsername<Void>() {
      @Override
      public Void run(String username) throws PermissionDeniedException {
        Employee employee = employeeController.queryEmployee(username);
        if (employee == null || employee.getAccessLevel() != AccessLevel.FULL) {
          throw new PermissionDeniedException("User does not have required permission");
        }
        return null;
      }
    };
  }
  
  static {
    try {
      employeeController.registerNewEmployee("Admin", "11144477735", "admin", "admin");
    } catch (InvalidStateException e) {
      System.err.println("Register employee creation in AccessControlAspect failed");
    }
  }
  
  private List<String> fullAccessList = Arrays.asList("admin", "lapl");

  after(String username) returning(boolean loginStatus)
      : execution(boolean LoginHandler.login(String, ..)) && args(username, ..) {
    
    if (loginStatus) {
      if (fullAccessList.contains(username)) {
        employeeController.updatePermission(username, AccessLevel.FULL);
      }
    }
  }
}
