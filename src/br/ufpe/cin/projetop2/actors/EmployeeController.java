package br.ufpe.cin.projetop2.actors;

import br.ufpe.cin.projetop2.annotations.Singleton;
import br.ufpe.cin.projetop2.data.DataModel;
import br.ufpe.cin.projetop2.data.HashMapDataModel;

@Singleton
public final class EmployeeController {
  DataModel<Employee> entries;

  private EmployeeController() {
    this.entries = new HashMapDataModel<Employee>();
  }

  public static EmployeeController getInstance() {
    return new EmployeeController();
  }

  public void registerNewEmployee(String name, String cpf, String username, String password) {
    Employee employee = new Employee(name, cpf, username, password);
    entries.saveData(cpf, employee);
  }

}
