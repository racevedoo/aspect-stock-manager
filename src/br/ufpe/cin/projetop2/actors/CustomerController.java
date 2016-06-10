package br.ufpe.cin.projetop2.actors;

import java.util.ArrayList;
import java.util.List;

import br.ufpe.cin.projetop2.annotations.Singleton;
import br.ufpe.cin.projetop2.data.DataModel;
import br.ufpe.cin.projetop2.data.HashMapDataModel;
import br.ufpe.cin.projetop2.data.products.Product;

@Singleton
public final class CustomerController {
  DataModel<Customer> entries;
  DataModel<List<Product>> purchases;

  private CustomerController() {
    this.entries = new HashMapDataModel<Customer>();
    this.purchases = new HashMapDataModel<List<Product>>();
  }

  public static CustomerController getInstance() {
    return new CustomerController();
  }

  public void registerNewCustomer(String name, String cpf) {
    Customer customer = new Customer(name, cpf);
    entries.saveData(cpf, customer);
  }

  public void addPurchase(String cpf, Product product) {
    List<Product> customerPurchases = purchases.getData(cpf);
    if (customerPurchases == null) {
      customerPurchases = new ArrayList<Product>();
    }

    customerPurchases.add(product);
    purchases.saveData(cpf, customerPurchases);
  }
}
