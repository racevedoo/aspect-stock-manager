package br.ufpe.cin.projetop2.application;

import br.ufpe.cin.projetop2.actors.CustomerController;
import br.ufpe.cin.projetop2.actors.EmployeeController;
import br.ufpe.cin.projetop2.annotations.RequireFullPermission;
import br.ufpe.cin.projetop2.annotations.RequireLogin;
import br.ufpe.cin.projetop2.data.products.Product;
import br.ufpe.cin.projetop2.data.products.ProductController;
import br.ufpe.cin.projetop2.exceptions.PermissionDeniedException;
import br.ufpe.cin.projetop2.exceptions.UserNotLoggedInException;

public final class ApplicationController {

  private CustomerController customerController;
  private EmployeeController employeeController;
  private ProductController productController;

  public ApplicationController() {
    this.customerController = CustomerController.getInstance();
    this.employeeController = EmployeeController.getInstance();
    this.productController = ProductController.getInstance();
  }

  public void registerCustomer(String name, String cpf) {
    customerController.registerNewCustomer(name, cpf);
  }

  public void registerEmployee(String name, String cpf, String username, String password) {
    employeeController.registerNewEmployee(name, cpf, username, password);
  }

  public void registerProduct(String name) {
    productController.registerNewProduct(name);
  }

  public void sell(String productName, int amount) {
    productController.sell(productName, amount);
  }

  public void sell(String productName, int amount, String customerCpf) {
    productController.sell(productName, amount);
    Product product = productController.queryProduct(productName);
    customerController.addPurchase(customerCpf, product);
  }

  public void supply(String productName, int amount) {
    productController.sell(productName, amount);
  }

  @RequireLogin
  public String queryProduct(String productName) throws UserNotLoggedInException {
    Product product = productController.queryProduct(productName);
    if (product == null) {
      return "Product not found";
    }
    return product.toString();
  }

  // TODO: Remove this once @RequireFullPermission is being correctly used in the controller
  @RequireFullPermission
  public static void testPermission() throws PermissionDeniedException {
    System.out.println("Hello World");
  }
}
