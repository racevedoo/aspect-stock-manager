package br.ufpe.cin.projetop2.actors;

import br.ufpe.cin.projetop2.annotations.Singleton;
import br.ufpe.cin.projetop2.data.DataModel;
import br.ufpe.cin.projetop2.data.HashMapDataModel;
import br.ufpe.cin.projetop2.exceptions.InvalidStateException;

@Singleton
public final class EmployeeController {
  DataModel<Employee> entries;

  private EmployeeController() {
    this.entries = new HashMapDataModel<Employee>();
  }

  public static EmployeeController getInstance() {
    return new EmployeeController();
  }

  public void registerNewEmployee(String name, String cpf, String username, String password)
      throws InvalidStateException {
    Employee employee = new Employee(name, cpf, username, password);
    entries.saveData(username, employee);
  }

  public Employee queryEmployee(String username) {
    return entries.getData(username);
  }
}
