package br.ufpe.cin.projetop2.aspects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.ufpe.cin.projetop2.actors.Employee;
import br.ufpe.cin.projetop2.data.DataModel;
import br.ufpe.cin.projetop2.data.HashMapDataModel;

public aspect AccessControlAspect {
  
  enum AccessLevel {
    READ_ONLY,
    FULL
  }
  
  // default access level
  private AccessLevel Employee.access_level = AccessLevel.READ_ONLY;
  
  private static Set<String> fullAccessEmployees;
  static {
    fullAccessEmployees = new HashSet<>();
    List<String> fullAccessList = Arrays.asList("cpf1", "cpf2");
    
    for (String employeeCpf : fullAccessList) {
      fullAccessEmployees.add(employeeCpf);
    }
  }
  
  before(String cpf): execution(@RequireFullPermission * *.*(String, ..)) && args(cpf, ..) {
  }
  
}
