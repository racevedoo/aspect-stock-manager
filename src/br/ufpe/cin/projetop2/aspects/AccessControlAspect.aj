package br.ufpe.cin.projetop2.aspects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.ufpe.cin.projetop2.actors.Employee;
import br.ufpe.cin.projetop2.actors.EmployeeController;
import br.ufpe.cin.projetop2.annotations.RequireFullPermission;
import br.ufpe.cin.projetop2.annotations.WrapLogin;
import br.ufpe.cin.projetop2.data.DataModel;
import br.ufpe.cin.projetop2.data.HashMapDataModel;

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
  
  public void EmployeeController.updatePermission(String cpf, AccessLevel level) {
    Employee employee = this.entries.getData(cpf);
    if (employee == null) return;
    employee.setAccessLevel(level);
    this.entries.saveData(cpf, employee);
  }
  
  private static EmployeeController employeeController = EmployeeController.getInstance();
  
  before() throws Exception: call(@RequireFullPermission * *(..)) {
    checkFullPermission();
  }
  
  @WrapLogin
  FunctionWithUsername<Void> checkFullPermission() throws Exception {
    return new FunctionWithUsername<Void>() {
      @Override
      public Void run(String username) throws Exception {
        Employee employee = employeeController.queryEmployee(username);
        if (employee == null || employee.getAccessLevel() != AccessLevel.FULL) {
          throw new Exception("User does not have required permission");
        }
        System.out.println("Permission ok");
        return null;
      }
    };
  }
  
  static {
    updateFullPermissions();
  }
  
  private static void updateFullPermissions() {
    // TODO: Remove this register
    employeeController.registerNewEmployee("Admin", "CPF", "admin", "admin");

    List<String> fullAccessList = Arrays.asList("admin");
    
    for (String employeeCpf : fullAccessList) {
      employeeController.updatePermission(employeeCpf, AccessLevel.FULL);
    }
  }
}
